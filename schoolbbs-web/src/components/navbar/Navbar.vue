<template>
  <div class="navbar">
    <el-menu class="el-menu-demo" mode="horizontal" router>
      <el-menu-item index="/index">首页</el-menu-item>
      <el-menu-item index="/topic">*****</el-menu-item>
      <el-menu-item>****</el-menu-item>
      <el-menu-item>****</el-menu-item>
      <el-menu-item id="nav2">
        <el-button type="text" @click="dialogFormVisible = true"
          >登录</el-button
        >
        <el-dialog
          title="登录"
          :visible.sync="dialogFormVisible"
          center
          width="30%"
          @closed="closeDialog"
        >
          <el-form :model="form" :rules="rules" ref="form">
            <el-form-item label="用户名:" label-width="80px" prop="name">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="密码:" label-width="80px" prop="password">
              <el-input v-model="form.password" type="password"></el-input>
            </el-form-item>
            <div id="link">
              <el-link
                href="/register"
                target="_blank"
                type="primary"
                :underline="false"
                >还没有账号，马上注册</el-link
              >
            </div>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="login('form')">确 定</el-button>
            <el-button @click="dialogFormVisible = false">取 消</el-button>
          </div>
        </el-dialog>
      </el-menu-item>
      <el-menu-item id="nav1">
        <el-input placeholder="请输入内容" prefix-icon="el-icon-search" />
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import { login } from "../../network/login.js";

export default {
  name: "navbar",
  components: {},
  data() {
    return {
      dialogFormVisible: false,
      form: {
        name: "",
        password: ""
      },
      rules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { max: 20, message: "长度大于20个字符", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "change" },
          { max: 16, message: "密码长度大于20个字符", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    login() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const userDto = {
            username: this.form.name,
            password: this.form.password
          };
          login(userDto)
            .then(response => alert(response))
            .catch(function(error) {
              // 请求失败处理
              console.log(error);
            });
        } else {
          return false;
        }
      });
    },
    closeDialog() {
      this.$refs["form"].resetFields();
    }
  }
};
</script>

<style>
.navbar {
  width: 80%;
  margin: 0 auto;
}

#nav1,
#nav2 {
  float: right;
}
#register {
  text-align: right;
}
div.el-dialog__footer {
  padding: 10px 20px 10px;
}
div.el-dialog__body {
  padding: 0px 25px !important;
}
#link {
  text-align: right;
}
.el-link.el-link--primary:hover {
  color: #66b1ff !important;
}
div.el-dialog__header {
  padding: 10px 20px 10px;
}
</style>
