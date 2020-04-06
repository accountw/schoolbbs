<template>
  <div class="Block">
    <ul id="ul">
      <li v-for="block in blocks" :key="block.id">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>{{ block.name }}</span>
          </div>
          <div>
            <plate v-bind:block="block.id"></plate>
          </div>
        </el-card>
      </li>
    </ul>
  </div>
</template>

<script>
import { getblock } from "../../network/block";
import Plate from "../plate/Plate";
export default {
  name: "Block",
  components: {
    Plate
  },
  data() {
    return {
      blocks: null
    };
  },
  created() {
    this.get();
  },
  methods: {
    get() {
      getblock()
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.blocks = res.data.data;
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
.clearfix {
  font-size: 20px;
}
.box-card {
  margin-bottom: 5px;
}
#ul {
  list-style: none;
  padding-inline-start: 0;
}
</style>
