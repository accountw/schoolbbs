<template>
  <div class="Platenav" style="border-bottom: 1px solid #eeeeee">
    <div style="font-size: 28px">{{ this.plate.name }}</div>
    <div>
      <span style="color: gray">帖子:</span>
      <span style="color: crimson">555&nbsp;&nbsp;</span>
      <span style="color: gray">收藏:</span>
      <span style="color: crimson">666</span>
    </div>
    <div style="color: gray">{{ this.plate.depict }}</div>
  </div>
</template>

<script>
import { getPlateByid } from "../../network/plate";

export default {
  props: ["plateid"],
  name: "Platenav",
  data() {
    return {
      plate: []
    };
  },
  created() {
    this.getplate();
  },
  methods: {
    getplate() {
      getPlateByid(this.plateid)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.plate = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style scoped></style>
