<template>
  <div class="Topic">
    <el-card class="box-card">
      <div>{{ topic.title }}</div>
      <div>{{ topic.context }}</div>
      <ul id="ul">
        <li v-for="reply in replies" :key="reply.id">
          <Reply v-bind:reply="reply"></Reply>
        </li>
      </ul>
      <el-card shadow="never">
        <el-upload
          class="upload-demo"
          ref="upload"
          action="http://localhost:8081/api/reply/savePicture"
          :headers="header"
          :on-success="upload"
          :limit="6"
          :on-exceed="handleExceed"
          :show-file-list="true"
          :multiple="false"
          list-type="picture"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove"
        >
          <i class="el-icon-picture-outline"></i>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="" />
        </el-dialog>
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

import Reply from "../reply/Reply";
export default {
  name: "Topic",
  components: {
    Reply
  },
  data() {
    return {
      replies: [],
      topicid: this.$route.params.topicid,
      topic: [],
      textarea: "",
      dialogImageUrl: "",
      dialogVisible: false,
      one: "",
      two: "",
      three: "",
      four: "",
      five: "",
      six: ""
    };
  },
  computed: {
    header() {
      return {
        Authorization: this.$store.state.Authorization
      };
    },
    picture() {
      var p = "";
      var l = 0;
      if (this.one) {
        p = this.one;
        l = 1;
      }
      if (this.two) {
        if (l == 0) {
          p = this.two;
          l = 1;
        } else {
          p = p + "," + this.two;
        }
      }
      if (this.three) {
        if (l == 0) {
          p = this.three;
          l = 1;
        } else {
          p = p + "," + this.three;
        }
      }
      if (this.four) {
        if (l == 0) {
          p = this.four;
          l = 1;
        } else {
          p = p + "," + this.four;
        }
      }
      if (this.five) {
        if (l == 0) {
          p = this.five;
          l = 1;
        } else {
          p = p + "," + this.five;
        }
      }
      if (this.six) {
        if (l == 0) {
          p = this.six;
          l = 1;
        } else {
          p = p + "," + this.six;
        }
      }
      return p;
    }
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
      if (
        !this.one &&
        !this.two &&
        !this.three &&
        !this.four &&
        !this.five &&
        !this.six &&
        !this.textarea
      ) {
        this.$message.warning("发送内容不能为空");
      } else {
        // 对字符串进行编码
        const replyDto = {
          userId: this.$store.state.id,
          topicId: this.topicid,
          context: this.textarea,
          picture: this.picture
        };
        saveReply(replyDto)
          .then(res => {
            if (res.data.code === "SUCCESS") {
              this.$router.go(0);
            }
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 6 个文件，本次选择了 ${
          files.length
        } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      );
    },
    upload(response, file) {
      file.name = response.data;
      if (this.one == "") {
        this.one = response.data;
      } else if (this.two == "") {
        this.two = response.data;
      } else if (this.three == "") {
        this.three = response.data;
      } else if (this.four == "") {
        this.four = response.data;
      } else if (this.five == "") {
        this.five = response.data;
      } else if (this.six == "") {
        this.six = response.data;
      }
    },
    handleRemove(file) {
      if (this.one == file.name) {
        this.one = "";
      } else if (this.two == file.name) {
        this.two = "";
      } else if (this.three == file.name) {
        this.three = "";
      } else if (this.four == file.name) {
        this.four = "";
      } else if (this.five == file.name) {
        this.five = "";
      } else if (this.six == file.name) {
        this.six = "";
      }
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    }
  }
};
</script>

<style scoped>
#ul {
  list-style: none;
  padding-inline-start: 0;
}
</style>
