<template>
  <div class="Collect">
    <el-card>
      <el-tabs v-model="activeName">
        <el-tab-pane label="收藏的板块" name="first"
          ><CollectPlate></CollectPlate
        ></el-tab-pane>
        <el-tab-pane label="收藏的帖子" name="second"
          ><CollectTopic></CollectTopic
        ></el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
import CollectPlate from "./CollectPlate";
import CollectTopic from "./CollectTopic";
export default {
  components: {
    CollectTopic,
    CollectPlate
  },
  name: "Collect",
  data() {
    return {
      activeName: "first"
    };
  }
};
</script>

<style scoped></style>
