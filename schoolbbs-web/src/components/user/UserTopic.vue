<template>
  <div class="UserTopic">
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
                  >{{ topic.title }}</el-link
                >
                <i class="el-icon-chat-round" style="float: right">{{
                  topic.count
                }}</i>
              </div>
              <div id="context">{{ topic.context }}</div>
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
              <div style="font-size: 12px;">
                <i class="el-icon-position"></i>{{ topic.lastTime }}
                <div style="font-size: 14px;float: right">
                  <img
                    src="../../assets/点赞-空.png"
                    style="width:12px;height: 12px"
                  />
                  {{ topic.likenum }}
                </div>
                <el-link
                  type="primary"
                  @click="open(topic.id)"
                  :underline="false"
                  style="float: right;font-size: 12px"
                  >删除</el-link
                >
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
import { deleteTopic, getTopicByUserid } from "../../network/topic";
import Picture from "../plate/Picture";

export default {
  components: {
    Picture
  },
  props: ["userid", "count"],
  name: "UserTopic",
  data() {
    return {
      topices: [],
      index: 1
    };
  },
  created() {
    this.getTopic();
  },
  methods: {
    getTopic() {
      getTopicByUserid(this.index, this.userid)
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
    open(topicid) {
      this.$confirm("确认删除该贴?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteTopic(topicid)
            .then(res => {
              if (res.data.code === "SUCCESS") {
                this.$message({
                  type: "success",
                  message: "删除成功!"
                });
                this.$emit("update");
                this.getTopic();
              }
              if (res.data.code === "ERROR") {
                this.$message({
                  type: "error",
                  message: "删除失败!"
                });
              }
            })
            .catch(err => {
              console.log(err);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    }
  }
};
</script>

<style scoped>
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
