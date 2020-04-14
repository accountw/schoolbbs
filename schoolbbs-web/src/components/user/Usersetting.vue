<template>
  <div class="Usersetting">
    <el-card>
      <template>
        <el-tabs v-model="activeName">
          <el-tab-pane label="信息管理" name="first"
            ><InfoManager></InfoManager
          ></el-tab-pane>
          <el-tab-pane label="密码管理" name="second"
            ><PasswordManager></PasswordManager
          ></el-tab-pane>
        </el-tabs>
      </template>
    </el-card>
  </div>
</template>

<script>
import InfoManager from "./InfoManager";
import PasswordManager from "./PasswordManager";

export default {
  components: {
    InfoManager,
    PasswordManager
  },
  name: "Usersetting",
  data() {
    return {
      userid: this.$route.params.userid,
      activeName: "first"
    };
  },
  created() {},
  methods: {}
};
</script>

<style scoped></style>
