<template>
  <div class="topic">

    <div>
      <ul id="ul">
        <li v-for="topic in topices" :key="topic.id">
          <el-card class="box-card" shadow="never">
            <div class="text item">
              <div id="title">{{ topic.title }}</div>
              <div id="context">{{ topic.context }}</div>
              <div id="user">
                <i class="el-icon-user-solid"></i>{{ topic.username }}
              </div>
            </div>
          </el-card>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { getFirstTopic } from "../../network/topic";

export default {
  name: "topic",
  data() {
    return {
      topices: null
    };
  },
  created() {
    this.getTopic();
  },
  methods: {
    getTopic() {
      getFirstTopic()
        .then(response => {
          this.topices = response.data.data;
        })
        .catch(function(error) {
          // 请求失败处理
          console.log(error);
        });
    }
  }
};
</script>

<style>
.topic {
  margin: 0 auto;
  margin-top: 0%;
}
#ul {
  list-style: none;
}
ul#ul {
  padding-inline-start: 0;
}
#title {
  font-family: "Microsoft YaHei";
  font-size: 20px;
  font-weight: bold;
}
#context {
  font-size: 16px;
}
#user {
  font-size: 12px;
}
</style>
