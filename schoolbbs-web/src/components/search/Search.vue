<template>
  <div class="Search">

    <div id="input" >
      <el-input
        placeholder="请输入内容"
        prefix-icon="el-icon-search"
        v-model="input"
        size="lager"
      >
      </el-input>
    </div>
    <div id="button">
      <el-button type="primary" icon="el-icon-search"  size="lager"  >搜索</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Search",
  data() {
    return {
      input: ""
    };
  }
};
</script>

<style scoped>

#input {
  width: 300px;
  float: left;
}
  #button{
    float: left;
  }
</style>
