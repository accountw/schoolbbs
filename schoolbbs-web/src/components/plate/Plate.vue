<template>
  <div class="Plate">
    <ul id="ul">
      <li v-for="plate in plates" :key="plate.id">
        <el-link id="a" :href="'/plate/' + plate.id" target="_blank">
          {{ plate.name }}</el-link
        >
        <span></span>
      </li>
    </ul>
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
</style>
