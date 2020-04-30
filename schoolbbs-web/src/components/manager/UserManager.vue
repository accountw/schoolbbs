<template>
  <div class="UserManager">
    <el-tabs :tab-position="tabPosition" >
      <el-tab-pane label="用户封禁"><Userban></Userban></el-tab-pane>
      <el-tab-pane label="注册申请">注册申请</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import Userban from "./Userban";
export default {
  components: {
    Userban
  },
  name: "UserManager",
  data() {
    return {
      tabPosition: "left"
    };
  }
};
</script>

<style scoped></style>
