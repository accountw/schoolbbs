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
      <el-divider></el-divider>
      <el-form-item label="旧密码" prop="oldpassword">
        <el-input
          v-model="form.oldpassword"
          type="password"
          show-password
          style="width: 200px"
        ></el-input>
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
      <el-form-item label="验证码" prop="code">
        <el-input
          v-model="form.code"
          style="width: 200px"
          placeholder="验证码会发送到注册邮箱"
        ></el-input>
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
import { sendMse } from "../../network/User";
import { validatecode } from "../../network/User";
import { changepd } from "../../network/User";

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
    var validateCode = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入验证码"));
      } else {
        if (value !== "") {
          validatecode(value)
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
    var validateoldpd = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };
    return {
      form: {
        oldpassword: "",
        newpassword: "",
        password: "",
        code: ""
      },
      show: true,
      num: "0",
      rules: {
        newpassword: [{ validator: validatepd, trigger: "blur" }],
        oldpassword: [{ validator: validateoldpd, trigger: "blur" }],
        password: [{ validator: validatePass, trigger: "blur" }],
        code: [{ validator: validateCode, trigger: "blur" }]
      }
    };
  },
  methods: {
    sendcode() {
      sendMse()
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
    },
    submit(form) {
      this.$refs[form].validate(valid => {
        if (valid) {
          const data = {
            newpassword: this.form.newpassword,
            oldpassword: this.form.oldpassword
          };
          changepd(data)
            .then(res => {
              if (res.data.code === "ERROR") {
                this.$message({
                  showClose: true,
                  message: "旧密码错误",
                  type: "error"
                });
              }
              if (res.data.code === "SUCCESS") {
                this.$message({
                  showClose: true,
                  message: "修改成功",
                  type: "success"
                });
                this.form.newpassword = "";
                this.form.oldpassword = "";
                this.form.password = "";
                this.form.code = "";
              }
            })
            .catch(err => {
              console.log(err);
            });
        } else {
          this.$message({
            showClose: true,
            message: "请按步骤完成密码修改",
            type: "error"
          });
        }
      });
    }
  }
};
</script>

<style scoped></style>
