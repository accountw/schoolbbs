<template>
  <div class="Topic">
    <el-card class="box-card">
      <div>{{ topic.title }}</div>
      <div>{{ topic.context }}</div>
      <ul id="ul">
        <li v-for="reply in replies" :key="reply.id">
          <el-card class="box-card" shadow="never">
            <div>{{ reply.context }}</div>
          </el-card>
        </li>
      </ul>
      <el-card shadow="never">
        <el-upload
          class="upload-demo"
          ref="upload"
          action="https://jsonplaceholder.typicode.com/posts/"
          :auto-upload="false"
        >
          <el-button
            slot="trigger"
            size="small"
            icon="el-icon-picture-outline"
            circle
          ></el-button>
        </el-upload>
        <el-input
          type="textarea"
          placeholder="请输入内容"
          v-model="textarea"
          maxlength="200"
          show-word-limit
          :rows="6"
        >
        </el-input>

        <el-button type="primary" @click="submit">发表</el-button>
      </el-card>
    </el-card>
  </div>
</template>

<script>
import { getReplybyTopicid } from "../../network/reply";
import { getTopicbyid } from "../../network/topic";
import { saveReply } from "../../network/reply";

export default {
  name: "Topic",
  data() {
    return {
      replies: [],
      topicid: this.$route.params.topicid,
      topic: [],
      textarea: "",
      fileList: [],
      replyDto: null
    };
  },
  created() {
    this.getReply();
    this.gettopic();
  },
  methods: {
    getReply() {
      getReplybyTopicid(this.topicid)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.replies = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    gettopic() {
      getTopicbyid(this.topicid)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.topic = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    submit() {
      this.replyDto = {
        topicId: this.topicid,
        userId: this.$store.state.id,
        context: this.textarea
      };
      saveReply(this.replyDto)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.getReply();
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
