<template>
  <div class="Userregister">
    <span
      >此注册方法适用于有校园邮箱的同学,完整填写表单完成邮箱验证后即可注册成为会员</span
    >
    <el-divider></el-divider>
    <el-form
      ref="form"
      :model="form"
      label-width="100px"
      label-position="left"
      :rules="rules"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="form.password"
          type="password"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmpd">
        <el-input
          type="password"
          v-model="form.confirmpd"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.gender">
          <el-radio label="0">男</el-radio>
          <el-radio label="1">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="邮箱" prop="mail">
        <el-input v-model="form.mail"></el-input>
      </el-form-item>
      <el-form-item label="验证码" v-if="form.mail" prop="code">
        <el-input v-model="form.code" id="code"></el-input>
        <el-button type="primary" @click="sendcode" v-if="show"
          >发送验证码</el-button
        >
        <el-button type="primary" disabled v-if="!show" style="width: 80px"
          >{{ num }}s</el-button
        >
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="sumbit('form')">立即创建</el-button>
        <el-button @click="rset('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { register } from "../../network/register";
import { sendMessage } from "../../network/register";
import { selectMail } from "../../network/register";
import { selectName } from "../../network/register";
import { selectCode } from "../../network/register";

export default {
  name: "Register",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.form.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
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
          } else {
            selectMail(value)
              .then(response => {
                if (response.data.code === "ERROR") {
                  callback(new Error("该邮箱已被注册"));
                  this.confirm = false;
                } else {
                  this.confirm = true;
                }
              })
              .catch(function(error) {
                // 请求失败处理
                console.log(error);
              });
          }
        }
      }
    };
    var validateUsername = (rule, value, callback) => {
      selectName(value)
        .then(response => {
          if (response.data.code === "ERROR") {
            callback(new Error("该用户名已被注册"));
          }
        })
        .catch(function(error) {
          // 请求失败处理
          console.log(error);
        });
    };

    return {
      confirm: false,
      show: true,
      num: "0",
      timer: null,
      form: {
        code: "",
        confirmpd: "",
        username: "",
        password: "",
        gender: "0",
        mail: ""
      },
      rules: {
        code: [{ validator: validateCode, trigger: "blur" }],
        confirmpd: [{ validator: validatePass, trigger: "change" }],
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 1, max: 8, message: "长度小于8个字符", trigger: "blur" },
          {
            validator: validateUsername,
            trigger: "change"
          }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "change" },
          { min: 6, max: 16, message: "密码长度6-16", trigger: "blur" }
        ],
        mail: [{ validator: validateMail, trigger: "change" }]
      }
    };
  },
  methods: {
    sumbit() {
      //提交表单内容
      this.$refs["form"].validate(valid => {
        if (valid) {
          const userdto = {
            username: this.form.username,
            password: this.form.password,
            gender: this.form.gender,
            mail: this.form.mail
          };
          register(userdto)
            .then(response => console.log(response))
            .catch(function(error) {
              // 请求失败处理
              console.log(error);
            });
        } else {
          return false;
        }
      });
    },
    rset() {
      //重置表单
      this.$refs["form"].resetFields();
      this.form.confirmpd = "";
      this.form.code = "";
    },
    sendcode() {
      //发送验证码
      if (this.confirm === true) {
        sendMessage(this.form.mail)
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
      }
    }
  }
};
</script>

<style scoped>
.Userregister {
  width: 60%;
  margin: 0 auto;
}

.el-input {
  width: 300px !important;
}
</style>
