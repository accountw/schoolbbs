<template>
  <div class="PasswordManager" style="margin-left: 40px;margin-right: 40px">
    <h2>密码修改</h2>
    <el-form
      ref="form"
      :model="form"
      label-width="100px"
      label-position="left"
      :rules="rules"
      style="margin-left: 30px;margin-right: 30px"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" style="width: 200px"></el-input>
      </el-form-item>
      <el-divider></el-divider>
      <el-form-item label="新密码" prop="newpassword">
        <el-input
          v-model="form.newpassword"
          type="password"
          style="width: 200px"
        ></el-input>
      </el-form-item>
      <el-divider></el-divider>
      <el-form-item label="确认密码" prop="password">
        <el-input
          v-model="form.password"
          type="password"
          style="width: 200px"
        ></el-input>
      </el-form-item>
      <el-divider></el-divider>
      <el-form-item label="邮箱" prop="mail">
        <el-input v-model="form.mail" style="width: 200px"></el-input>
      </el-form-item>
      <el-divider></el-divider>
      <el-form-item label="验证码" v-if="form.mail" prop="code">
        <el-input v-model="form.code" id="code" style="width: 200px"></el-input>
        <el-button type="primary" @click="sendcode" v-if="show"
          >发送验证码</el-button
        >
        <el-button type="primary" disabled v-if="!show" style="width: 80px"
          >{{ num }}s</el-button
        >
      </el-form-item>

      <el-divider></el-divider>
      <el-button type="primary" @click="submit('form')">确认</el-button>
    </el-form>
  </div>
</template>

<script>
import { changepassword} from "../../network/User";
import {selectCode, sendMs} from "../../network/register";

export default {
  name: "PasswordManager",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请确认输入密码"));
      } else if (value !== this.form.newpassword) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    var validatepd = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        const reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
        if (!reg.test(value)) {
          callback(new Error("密码必须包括字母和数字，长度为6-16"));
        } else {
          callback();
        }
      }
    };
    var validateUsername = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入用户名"));
      } else {
        const reg = /^.{1,8}$/;
        if (!reg.test(value)) {
          callback(new Error("用户名长度小于8"));
        }
      }
      callback();
    };
    var validateCode = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入验证码"));
      } else {
        if (value !== "") {
          const data = {
            userMail: this.form.mail,
            code: this.form.code
          };
          selectCode(data)
                  .then(response => {
                    if (response.data.code === "ERROR") {
                      callback(new Error("验证码错误"));
                    } else {
                      callback();
                    }
                  })
                  .catch(function(error) {
                    // 请求失败处理
                    console.log(error);
                  });
        }
      }
    };
    var validateMail = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请填写邮箱"));
      } else {
        if (value !== "") {
          const reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
          if (!reg.test(value)) {
            callback(new Error("请输入有效的邮箱"));
            this.confirm = false;
          }
        }
      }
      this.confirm = true;
      callback();
    };
    return {
      confirm: false,
      form: {
        newpassword: "",
        password: "",
        mail: "",
        code: "",
        username: ""
      },
      show: true,
      num: "0",
      rules: {
        newpassword: [{ validator: validatepd, trigger: "blur" }],
        mail: [{ validator: validateMail, trigger: "blur" }],
        password: [{ validator: validatePass, trigger: "blur" }],
        code: [{ validator: validateCode, trigger: "blur" }],
        username: [{ validator: validateUsername, trigger: "blur" }]
      }
    };
  },
  methods: {
    sendcode() {
      //发送验证码
      if (this.confirm === true) {
        sendMs(this.form.mail)
          .then(response => {
            if (response.data.code === "SUCCESS") {
              this.show = false;
              const TIME_COUNT = 60;
              if (!this.timer) {
                this.num = TIME_COUNT;
                this.timer = setInterval(() => {
                  if (this.num > 0 && this.num <= TIME_COUNT) {
                    this.num--;
                  } else {
                    this.show = true;
                    clearInterval(this.timer);
                    this.timer = null;
                  }
                }, 1000);
              }
            }
          })
          .catch(function(error) {
            // 请求失败处理
            console.log(error);
          });
      } else {
        this.$message({
          showClose: true,
          message: "请填写正确的邮箱",
          type: "error"
        });
      }
    },
    submit(form) {
      this.$refs[form].validate(valid => {
        if (valid) {
          changepassword(
            this.form.newpassword,
            this.form.username,
            this.form.mail
          )
            .then(res => {
              if (res.data.code === "ERROR") {
                this.$message({
                  showClose: true,
                  message: "账号信息错误",
                  type: "error"
                });
                this.form.newpassword = "";
                this.form.password = "";
                this.form.username = "";
                this.form.code = "";
                this.form.mail = "";
              }
              if (res.data.code === "SUCCESS") {
                this.$message({
                  showClose: true,
                  message: "重置密码成功",
                  type: "success"
                });
                this.$router.push("/");
              }
            })
            .catch(err => {
              console.log(err);
            });
        } else {
          this.$message({
            showClose: true,
            message: "请按步骤完成密码找回",
            type: "error"
          });
        }
      });
    }
  }
};
</script>

<style scoped></style>
