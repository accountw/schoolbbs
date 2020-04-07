<template>
  <div class="index">
    <el-container>
      <el-aside width="200px">
        <div class="title">
          校园论坛
        </div>
        <Login v-if="username == null"></Login>
        <User v-else></User>
        <navbar></navbar>
      </el-aside>
      <el-container id="container">
        <el-header>
          <Search></Search>
        </el-header>
        <el-main>
          <router-view :key="key"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import navbar from "@/components/navbar/Navbar.vue";
import Search from "../components/search/Search";
import Login from "../components/login/Login";
import User from "../components/login/User";
export default {
  name: "index",
  components: {
    navbar,
    Search,
    Login,
    User
  },

  computed: {
    username() {
      return this.$store.state.username;
    },
    key() {
      return this.$route.path;
    }
  }
};
</script>

<style scoped>
.index {
  width: 80%;
  margin: 0 auto;
}
main.el-main {
  padding-top: 0;
}
.title {
  background-color: white;
  text-align: center;
  font-size: 30px;
  height: 75px;
  line-height: 75px;
}
.el-aside {
  position: fixed;
}
#container {
  margin: 0 0 0 18%;
}
</style>
