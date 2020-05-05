<template>
  <div class="PlateManager">
    <el-container>
      <el-aside width="150px">
        <el-menu
          default-active="activeIndex"
          class="el-menu-demo"
          :router="true"
        >
          <el-menu-item index="/platelist">板块管理</el-menu-item>
          <el-menu-item index="/addplate">添加板块</el-menu-item>
        </el-menu></el-aside
      >
      <el-main> <router-view></router-view></el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "PlateManager"
};
</script>

<style scoped></style>
