<template>
  <div class="Criclemessage">
    <el-container>
      <el-aside width="50px"
        ><img :src="criclemessage.head" style="width: 45px;height: 45px"
      /></el-aside>
      <el-main style="padding: 5px">
        <div style="font-size: 11px">
          <el-link style="font-size: 11px" :underline="false">
            {{ criclemessage.username }} </el-link
          ><span style="float: right">{{ criclemessage.createTime }}</span>
        </div>
        <div>{{ criclemessage.context }}</div>
      </el-main>
    </el-container>
    <el-divider></el-divider>
  </div>
</template>

<script>
export default {
  props: ["criclemessage"],
  name: "Criclemessage"
};
</script>

<style scoped>
.el-divider--horizontal {
  margin: 5px 0 5px 0;
}
</style>
