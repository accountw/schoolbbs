<template>
  <div class="CollectPlate">
    <div
      v-for="plate in plates"
      :key="plate.id"
      style="display: inline-block;width: 150px;  margin-bottom: 5px;"
    >
      <el-button
        type="info"
        plain
        style="width: 120px"
        @click="to(plate.plateId)"
        >{{ plate.platename }}</el-button
      >
    </div>
  </div>
</template>

<script>
import { getCollectPlate } from "../../network/collect";

export default {
  name: "CollectPlate",
  data() {
    return {
      plates: []
    };
  },
  created() {
    this.getplate();
  },
  methods: {
    getplate() {
      getCollectPlate(this.$store.state.id).then(res => {
        if (res.data.code === "SUCCESS") {
          this.plates = res.data.data;
        }
      });
    },
    to(plateid) {
      this.$router.push("/plate/" + plateid + "/1");
    }
  }
};
</script>

<style scoped></style>
