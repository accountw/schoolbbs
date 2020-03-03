<template>
  <div class="Login">
    <div class="main">
      <div class="head">
        <img src="../../assets/head/head.png" id="image" />
      </div>
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item prop="name">
          <el-input
            v-model="form.name"
            placeholder="用户名/username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="form.password"
            type="password"
            placeholder="密码/password"
          ></el-input>
        </el-form-item>
      </el-form>
      <div id="button">
        <el-button type="primary" @click="login('form')">登录</el-button>
        <el-button type="primary">
          <a id="a" href="/register" target="_blank">注册</a>
        </el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
import qs from "qs";
import { login } from "../../network/login";

export default {
  name: "Login",
  data() {
    return {
      form: {
        name: "",
        password: ""
      },
      rules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 1, max: 8, message: "长度小于8个字符", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "change" },
          { main: 6, max: 16, message: "密码长度6-16", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    ...mapMutations(["Login"]),
    login() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const userDto = qs.stringify({
            username: this.form.name,
            password: this.form.password
          });
          login(userDto)
            .then(response => {
              if (response.data.code === "ERROR") {
                this.$message({
                  showClose: true,
                  message: "密码或用户名错误",
                  type: "error"
                });
              }
              if (response.data.code === "SUCCESS") {
                this.$message({
                  showClose: true,
                  message: "登陆成功",
                  type: "success"
                });
                const data = {
                  Authorization: response.data.message,
                  username: this.form.name
                };
                this.Login(data);
              }
            })
            .catch(function(error) {
              // 请求失败处理
              console.log(error);
            });
        } else {
          return false;
        }
      });
    }
  }
};
</script>

<style scoped>
#image {
  width: 130px;
  height: 130px;
  border-radius: 50%;
  background-color: aliceblue;
}
.Login {
  background-color: white;
  margin-right: 1px;
  padding-top: 10px;
  padding-bottom: 10px;
}
.main {
  width: 85%;
  text-align: center;
  margin: 0 auto;
}
.head {
  height: 140px;
}
#a {
  color: white;
  text-decoration: none;
}
#button {
  text-align: center;
}
</style>
