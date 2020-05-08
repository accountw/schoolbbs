<template>
  <div class="message">
    <el-card>
      <div v-for="message in messages" :key="message.id">
        <el-card shadow="never">
          <div>
            <el-container>
              <el-aside width="44px"
                ><img :src="message.head" style="width: 40px;height: 40px"
              /></el-aside>
              <el-main style="padding: 1px">
                <div>
                  <el-link
                    type="primary"
                    :underline="false"
                    :href="/user/ + message.userId"
                    >{{ message.username }}</el-link
                  >
                </div>
                <div style="font-size: 11px">
                  {{ message.createTime }}
                </div></el-main
              >
            </el-container>
            <el-divider></el-divider>
            <div v-if="message.event == 0">
              {{ message.context }}
              <div>
                <el-card shadow="never" body-style="padding:15px"
                  ><div>
                    <el-link
                      :href="'/topic/' + message.topicId + '/1'"
                      target="_blank"
                      style="font-size: 14px"
                      :underline="false"
                      >{{ message.tTitle }}</el-link
                    >
                  </div></el-card
                >
              </div>
            </div>
            <div v-if="message.event == 1">
              <span v-if="message.uname">回复 {{ message.uname }}：</span>
              {{ message.context }}
              <div>
                <el-card shadow="never" body-style="padding:15px"
                  ><div>
                    <el-link
                      :href="'/topic/' + message.rTopicId + '/1'"
                      target="_blank"
                      style="font-size: 14px"
                      :underline="false"
                      >{{ message.rContext }}</el-link
                    >
                  </div></el-card
                >
              </div>
            </div>
            <div v-if="message.event == 2">
              点赞了你的
              <span v-if="!message.replyId">
                帖子：
              </span>
              <span v-if="message.replyId">
                回复：
              </span>
              <div v-if="message.topicId">
                <el-card shadow="never" body-style="padding:15px"
                  ><div>
                    <el-link
                      :href="'/topic/' + message.topicId + '/1'"
                      target="_blank"
                      style="font-size: 14px"
                      :underline="false"
                      >{{ message.tTitle }}</el-link
                    >
                  </div></el-card
                >
              </div>
              <div v-if="message.replyId">
                <el-card shadow="never" body-style="padding:15px"
                  ><div>
                    <el-link
                      :href="'/topic/' + message.rTopicId + '/1'"
                      target="_blank"
                      style="font-size: 14px"
                      :underline="false"
                    >
                      {{ message.rContext }}</el-link
                    >
                  </div></el-card
                >
              </div>
            </div>
          </div>
        </el-card>
      </div>
    </el-card>
    <el-pagination
      small
      layout="prev, pager, next"
      hide-on-single-page
      :total="count"
      :page-size="15"
      @current-change="handleCurrentChange"
      @prev-click="prevclick"
      @next-click="nextclick"
    >
    </el-pagination>
  </div>
</template>

<script>
import { getmessage } from "../../network/message";
import { getcount } from "../../network/message";

export default {
  name: "Message",
  data() {
    return {
      messages: "",
      index: 1,
      count: 0
    };
  },
  created() {
    this.getMessage();
    this.getnum();
  },
  methods: {
    getnum() {
      getcount(this.$store.state.id).then(res => {
        if (res.data.code === "SUCCESS") {
          this.count = res.data.data;
        }
      });
    },
    getMessage() {
      getmessage(this.$store.state.id, this.index)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.messages = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    nextclick() {
      this.index = this.index + 1;
      this.getMessage();
      this.getnum();
    },
    prevclick() {
      this.index = this.index - 1;
      this.getMessage();
      this.getnum();
    },
    handleCurrentChange(val) {
      this.index = val;
      this.getMessage();
      this.getnum();
    }
  }
};
</script>

<style scoped>
.el-divider--horizontal {
  margin: 5px 0 10px 0;
}
</style>
