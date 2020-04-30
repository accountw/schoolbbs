<template>
  <div class="topic">
    <div>
      <ul id="ul" style="margin-top: 0;">
        <li v-for="topic in topices" :key="topic.id">
          <el-card class="box-card" shadow="never">
            <div class="text item">
              <div>
                <el-link
                  :href="'/plate/' + topic.plateId + '/1'"
                  :underline="false"
                  >{{ topic.platename }}</el-link
                >
              </div>
              <div id="title">
                <el-link
                  :href="'/topic/' + topic.id + '/1'"
                  target="_blank"
                  style="font-size: 20px"
                  :underline="false"
                  >{{ titlelimit(topic.title) }}</el-link
                >
                <i class="el-icon-chat-round" style="float: right">{{
                  topic.count
                }}</i>
              </div>
              <div
                id="context"
                style="white-space: pre-wrap; word-break:break-all;overflow:hidden"
              >
                {{ contextlimit(topic.context) }}
              </div>
              <div>
                <Picture
                  v-if="topic.picture"
                  v-bind:picture="topic.picture"
                ></Picture>
              </div>
              <div id="user">
                <el-link
                  style="font-size: 12px;float: left;margin-right: 10px"
                  :href="/user/ + topic.userId"
                  :underline="false"
                  ><i class="el-icon-user-solid"></i>{{ topic.username }}
                </el-link>
              </div>
              <div style="font-size: 12px;float: left">
                <i class="el-icon-position"></i>{{ topic.lastTime }}
              </div>
              <div style="font-size: 14px;float: right">
                <img
                  src="../../assets/点赞-空.png"
                  style="width:12px;height: 12px"
                />
                {{ topic.likenum }}
              </div>
            </div>
          </el-card>
        </li>
      </ul>
      <el-pagination
        background
        layout="prev, pager, next"
        :total="count"
        :page-size="15"
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
import { getFirstCount, getTopicFlow } from "../../network/topic";
import Picture from "../plate/Picture";

export default {
  components: {
    Picture
  },
  name: "topic",
  data() {
    return {
      topices: [],
      index: 1,
      count: null
    };
  },
  created() {
    this.getTopic();
    this.getcount();
  },
  methods: {
    getTopic() {
      getTopicFlow(this.index)
        .then(response => {
          this.topices = response.data.data;
        })
        .catch(function(error) {
          // 请求失败处理
          console.log(error);
        });
    },
    nextclick() {
      this.index = this.index + 1;
      this.getTopic();
    },
    prevclick() {
      this.index = this.index - 1;
      this.getTopic();
    },
    handleCurrentChange(val) {
      this.index = val;
      this.getTopic();
    },
    getcount() {
      getFirstCount()
        .then(res => {
          if (parseInt(res.data.data) < 75) {
            this.count = parseInt(res.data.data);
          } else {
            this.count = 75;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    titlelimit(text) {
      if (text.length > 30) {
        var newText = text.substring(0, 27) + "...";
        return newText;
      }
      return text;
    },
    contextlimit(text) {
      if (text.length > 100) {
        var newText = text.substring(0, 97) + "...";
        return newText;
      }
      return text;
    }
  }
};
</script>

<style scoped>
.topic {
  margin: 0 auto;
  margin-top: 0%;
}

ul#ul {
  padding-inline-start: 0;
  list-style: none;
}
#context {
  font-size: 16px;
}
#user {
  font-size: 12px;
}
</style>
