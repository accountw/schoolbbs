<template>
  <div class="Registernavbar">
    <div style="text-align: center">
      <h1>校园论坛</h1>
    </div>
    <el-tabs v-model="activeName" stretch>
      <el-tab-pane label="注册" name="first"><Userregister /></el-tab-pane>
      <el-tab-pane label="在校学生注册" name="second">
        <StudentRegister></StudentRegister
      ></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import Userregister from "../register/Userregister";
import StudentRegister from "../register/StudentRegister";
export default {
  name: "Registernavbar",
  components: {
    Userregister,
    StudentRegister
  },
  data() {
    return {
      activeName: "first"
    };
  },
  methods: {}
};
</script>

<style scoped>
.Registernavbar {
  width: 60%;
  margin: 0 auto;
}
</style>
