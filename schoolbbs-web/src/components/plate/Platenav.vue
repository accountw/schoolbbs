<template>
  <div class="Platenav" style="border-bottom: 1px solid #eeeeee">
    <div style="font-size: 28px">{{ this.plate.name }}</div>
    <div>
      <span style="color: gray">帖子:</span>
      <span style="color: crimson">{{ count }}&nbsp;&nbsp;</span>
      <span style="color: gray">收藏:</span>
      <span style="color: crimson">{{this.plate.tcount}}</span>
    </div>
    <div>
      <span style="color: gray"> 版主:</span
      ><span v-for="admin in admins" :key="admin.id">
        <el-link
          style="margin-left: 10px"
          :href="/user/ + admin.userId"
          :underline="false"
          type="danger"
          >{{ admin.username }}</el-link
        ></span
      >
    </div>
    <div style="color: gray">{{ this.plate.depict }}</div>
  </div>
</template>

<script>
import { getPlateByid } from "../../network/plate";
import { getTopicCount } from "../../network/topic";
import { getPlateAdmin } from "../../network/plateadmin";

export default {
  props: ["plateid"],
  name: "Platenav",
  data() {
    return {
      plate: [],
      count: 0,
      admins: []
    };
  },
  created() {
    this.getplate();
    this.getcount();
    this.getadmin();
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
    },
    getcount() {
      getTopicCount(this.plateid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.count = res.data.data;
        }
      });
    },
    getadmin() {
      getPlateAdmin(this.plateid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.admins = res.data.data;
        }
      });
    }
  }
};
</script>

<style scoped></style>
