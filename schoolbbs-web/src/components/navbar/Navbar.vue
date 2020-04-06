<template>
  <div class="navbar">
    <el-menu
      router
      default-active="/home"
      class="el-menu-vertical-demo"
      text-color="#303133"
    >
      <el-menu-item index="/home"
        ><i class="el-icon-s-home"></i>首页</el-menu-item
      >
      <el-menu-item index="/block"
        ><i class="el-icon-guide"></i>板块</el-menu-item
      >
      <el-menu-item index="/index"
        ><i class="el-icon-folder-opened"></i>我的收藏</el-menu-item
      >
      <el-menu-item index="/index"
        ><i class="el-icon-user"></i>关注/粉丝</el-menu-item
      >
      <el-menu-item index="/index"
        ><i class="el-icon-chat-dot-round"></i>消息</el-menu-item
      >
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "navbar",
  components: {}
};
</script>

<style>
.navbar {
  width: 100%;
}
.el-menu {
  border: 0 !important;
}
</style>
