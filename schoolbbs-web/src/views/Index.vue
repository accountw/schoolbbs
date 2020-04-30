<template>
  <div class="index">
    <el-container>
      <el-aside width="200px" style="position: fixed">
        <div class="title">
          校园论坛
        </div>
        <Login v-if="username == null"></Login>
        <User v-else></User>
        <navbar></navbar>
      </el-aside>
      <el-container id="container">
        <el-header style="padding: 0" height="50px">
          <Search></Search>
        </el-header>
        <el-container>
          <el-main style="padding: 0;margin-right: 21%">
            <router-view :key="key"></router-view>
          </el-main>
          <el-aside style="width: 210px;position: fixed;right: 10%;top:8px"
            ><Top></Top>
          </el-aside>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import navbar from "@/components/navbar/Navbar.vue";
import Search from "../components/search/Search";
import Login from "../components/login/Login";
import User from "../components/login/User";
import Top from "../components/navbar/Top";
export default {
  name: "index",
  components: {
    navbar,
    Search,
    Login,
    User,
    Top
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
#container {
  margin: 0 0 0 17.5%;
}
</style>
