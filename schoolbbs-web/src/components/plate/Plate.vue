<template>
  <div class="Plate">
    <span
      v-for="plate in plates"
      :key="plate.id"
      style="width: 33.33%;display:inline-block"
    >
      <el-link
        id="a"
        :href="'/plate/' + plate.id + '/1'"
        target="_blank"
        :underline="false"
        style="font-size: 18px;"
      >
        [{{ plate.name }}]</el-link
      >
    </span>
  </div>
</template>

<script>
import { getplate } from "../../network/plate";
export default {
  props: ["block"],
  name: "Plate",
  data() {
    return {
      blockid: this.block,
      plates: null
    };
  },
  created() {
    this.get();
  },
  methods: {
    get() {
      getplate(this.blockid)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.plates = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style scoped>
li {
  display: inline;
  list-style: none;
  padding-right: 5px;
}
#ul {
  list-style: none;
  padding-inline-start: 0;
}
</style>
