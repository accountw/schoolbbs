<template>
  <div class="Cplate">
    <el-card class="box-card">
      <div>{{ this.plate.name }}</div>
      <div>{{ this.plate.depict }}</div>
      <ul id="ul">
        <li v-for="topic in topices" :key="topic.id">
          <el-card class="box-card" shadow="hover">
            <div class="text item">
              <el-link :href="'/topic/' + topic.id" target="_blank">{{
                topic.title
              }}</el-link>
              <div id="context">{{ topic.context }}</div>
              <div id="user">
                <i class="el-icon-user-solid"></i>{{ topic.username }}
              </div>
            </div>
          </el-card>
        </li>
      </ul>
    </el-card>
  </div>
</template>

<script>
import { getTopicByPlateid } from "../../network/topic";
import { getPlateByid } from "../../network/plate";

export default {
  name: "cplate",
  data() {
    return {
      plateid: this.$route.params.plateid,
      topices: [],
      plate: []
    };
  },
  created() {
    this.gettopic();
    this.getplate();
  },
  methods: {
    gettopic() {
      getTopicByPlateid(this.plateid)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.topices = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
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
