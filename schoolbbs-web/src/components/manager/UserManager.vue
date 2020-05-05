<template>
  <div class="UserManager">
    <el-container>
      <el-aside width="150px">
        <el-menu
          default-active="/userban"
          class="el-menu-demo"
          :router="true"
          style="width: 150px"
        >
          <el-menu-item index="/userban">用户封禁</el-menu-item>
          <el-menu-item index="/schoolregistermanager">在校生注册</el-menu-item>
        </el-menu></el-aside
      >
      <el-main> <router-view></router-view></el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "UserManager",
  data() {
    return {
      tabPosition: "left"
    };
  }
};
</script>

<style scoped></style>
