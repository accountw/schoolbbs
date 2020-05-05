<template>
  <div class="TopicManager">
    <el-container>
      <el-aside width="150px">
        <el-menu
          default-active="/index"
          class="el-menu-demo"
          style="width: 150px"
        >
          <el-menu-item index="/index">帖子管理</el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <el-card>
          <el-input
            v-model="input"
            placeholder="请输入内容"
            style="width: 200px"
          ></el-input>
          <el-button>搜索</el-button>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "TopicManager"
};
</script>

<style scoped></style>
