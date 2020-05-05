<template>
  <div class="CricleUser">
    <div>
      <div v-for="cricle in cricles" :key="cricle.id">
        <Cricle v-bind:cricle="cricle" @delete="update"></Cricle>
      </div>
      <el-pagination
        background
        layout="prev, pager, next"
        :total="count"
        :page-size="10"
        :current-page="this.index"
        hide-on-single-page
        @current-change="handleCurrentChange"
        @prev-click="prevclick"
        @next-click="nextclick"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { getCricle, getFocusCricleCount } from "../../network/cricle";
import Cricle from "./Cricle";
export default {
  name: "CricleUser",
  components: {
    Cricle
  },
  data() {
    return {
      cricles: [],
      index: 1,
      count: 0
    };
  },
  created() {
    this.getcricles();
    this.getcount();
  },
  methods: {
    getcricles() {
      getCricle(this.$store.state.id, this.index).then(res => {
        if (res.data.code === "SUCCESS") {
          this.cricles = res.data.data;
        }
      });
    },
    getcount() {
      getFocusCricleCount(this.$store.state.id).then(res => {
        if (res.data.code === "SUCCESS") {
          this.count = res.data.data;
        }
      });
    },
    update() {
      this.getcricles();
      this.getcount();
    },
    nextclick() {
      this.index = this.index + 1;
      this.getcricles();
    },
    prevclick() {
      this.index = this.index - 1;
      this.getcricles();
    },
    handleCurrentChange(val) {
      this.index = val;
      this.getcricles();
    }
  }
};
</script>

<style scoped></style>
