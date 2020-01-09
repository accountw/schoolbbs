<template>
  <div class="index">
    <navbar></navbar>
    <router-view></router-view>
  </div>
</template>

<script>
import navbar from "@/components/Navbar.vue";
export default {
  name: "index",
  components: {
    navbar
  }
};
</script>

<style></style>
