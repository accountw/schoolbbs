<template>
  <div class="navbar">
    <el-menu class="el-menu-demo" mode="horizontal" router>
      <el-menu-item index="/index">首页</el-menu-item>
      <el-menu-item index="/topic">处理中心</el-menu-item>
      <el-menu-item>消息中心</el-menu-item>
      <el-menu-item>订单管理</el-menu-item>
      <el-menu-item id="nav2">
        <el-button type="text" @click="login">登录</el-button>
        <el-dialog
          title="登录"
          :visible.sync="dialogFormVisible"
          center
          width="30%"
        >
          <el-form :model="form">
            <el-form-item label="用户名:" label-width="80px">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="密码:" label-width="80px">
              <el-input v-model="form.password" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>

          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="dialogFormVisible = false"
              >确 定</el-button
            >
            <el-button @click="dialogFormVisible = false">取 消</el-button>
          </div>
        </el-dialog>
      </el-menu-item>
      <el-menu-item id="nav1">
        <el-input
          placeholder="请输入内容"
          prefix-icon="el-icon-search"
        ></el-input>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import { login } from "../network/login.js";

export default {
  name: "navbar",
  data() {
    return {
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        name: "",
        Password: ""
      }
    };
  },
  methods: {
    login: function() {
      this.dialogFormVisible = true;
      login();
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
</style>
