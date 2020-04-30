<template>
  <div class="Top">
    <el-card>
      <div style="font-size: 18px;color: crimson">近日热点</div>
      <div v-for="(topic, index) in topices" :key="topic.id">
        <el-tooltip
          class="item"
          effect="light"
          :content="topic.title"
          placement="bottom"
          :enterable="false"
        >
          <div style="padding-top: 5px">
            {{ index + 1 }}.<el-link
              :href="'/topic/' + topic.id + '/1'"
              target="_blank"
              style="font-size: 14px"
              :underline="false"
              >{{ textlimit(topic.title) }}
            </el-link>
          </div>
        </el-tooltip>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getTop } from "../../network/topic";

export default {
  name: "Top",
  data() {
    return {
      topices: []
    };
  },
  created() {
    this.get();
  },
  methods: {
    get() {
      getTop().then(res => {
        if (res.data.code === "SUCCESS") {
          this.topices = res.data.data;
          console.log(this.topices);
        }
      });
    },
    textlimit(text) {
      if (text.length > 8) {
        var newText = text.substring(0, 9) + "...";
        return newText;
      }
      return text;
    }
  }
};
</script>

<style scoped></style>
