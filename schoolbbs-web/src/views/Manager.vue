<template>
  <div class="Manager" >
    <div v-if="this.$store.state.role != 'ROLE_ADMIN'">
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item prop="name">
          <el-input
            v-model="form.name"
            placeholder="用户名/username"
            style="width: 200px"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="form.password"
            type="password"
            placeholder="密码/password"
            style="width: 200px"
          ></el-input>
        </el-form-item>
        <el-button type="primary" @click="login('form')">管理员登录</el-button>
      </el-form>
    </div>
    <div v-if="this.$store.state.role == 'ROLE_ADMIN'" >
      <el-tabs style="height: 200px;" type="card">
        <el-tab-pane label="用户管理"><UserManager></UserManager></el-tab-pane>
        <el-tab-pane label="版主管理"
          ><AdminManager></AdminManager
        ></el-tab-pane>
        <el-tab-pane label="板块管理"
          ><PlateManager></PlateManager
        ></el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import UserManager from "../components/manager/UserManager";
import { mapMutations } from "vuex";
import qs from "qs";
import { login } from "../network/login";
import AdminManager from "../components/manager/AdminManager";
import PlateManager from "../components/manager/PlateManager";
export default {
  components: {
    UserManager,
    AdminManager,
    PlateManager
  },
  name: "Manager",
  data() {
    return {
      form: {
        username: "",
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
                  username: this.form.name,
                  role: response.data.role
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

<style scoped></style>
