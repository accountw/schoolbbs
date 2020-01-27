<template>
  <div class="Register">
    <Registernavbar></Registernavbar>
    <router-view></router-view>
  </div>
</template>

<script>
import Registernavbar from "../components/navbar/Registernavbar";
export default {
  name: "Register",
  components: { Registernavbar },
  comments: {
    Registernavbar
  }
};
</script>

<style scoped></style>
