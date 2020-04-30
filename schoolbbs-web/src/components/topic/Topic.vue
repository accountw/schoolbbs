<template>
  <div class="Topic">
    <el-card class="box-card">
      <Platenav v-bind:plateid="plateid" v-if="plateid"></Platenav>
      <el-card shadow="never" body-style="padding-bottom:5px">
        <div style="font-size: 20px;">
          {{ topic.title }}
        </div>
        <div style="float: left">
          <el-link
            v-if="!collect"
            :underline="false"
            @click="collectThis"
            style="font-size: 12px"
            >收藏本贴
          </el-link>
        </div>
        <div style="float: left">
          <el-link
            v-if="collect"
            :underline="false"
            @click="removeCollect"
            style="font-size: 12px"
            >已收藏
          </el-link>
        </div>
        <div style="font-size: 14px;float: right" v-if="islike">
          <img
            src="../../assets/点赞-空.png"
            style="width:12px;height: 12px"
            @click="add"
          />
          {{ topic.likenum }}
        </div>
        <div style="font-size: 14px;float: right" v-if="!islike">
          <img
            src="../../assets/点赞.png"
            style="width:12px;height: 12px"
            @click="remove"
          />
          {{ topic.likenum }}
        </div>
      </el-card>
      <Reply v-bind:reply="first" v-if="index == 1"></Reply>
      <ul id="ul" style="margin: 0">
        <li v-for="reply in replies" :key="reply.id">
          <Reply
            v-bind:reply="reply"
            @delete="update"
            v-bind:userid="topic.userId"
          ></Reply>
        </li>
      </ul>
      <el-pagination
        background
        layout="prev, pager, next"
        :total="count"
        :page-size="15"
        :current-page="this.index"
        @current-change="handleCurrentChange"
        @prev-click="prevclick"
        @next-click="nextclick"
        hide-on-single-page
      >
      </el-pagination>
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
          :before-upload="beforeAvatarUpload"
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
import { getcount, getReplybyTopicid } from "../../network/reply";
import { getTopicbyid } from "../../network/topic";
import { saveReply } from "../../network/reply";
import Platenav from "../plate/Platenav";

import Reply from "../reply/Reply";
import {
  collectPlate,
  deleteCollectTopic,
  isCollectTopic
} from "../../network/collect";
import {
  addFavorite,
  deleteTopicFavorite,
  isTopicFavorite
} from "../../network/favorite";
export default {
  name: "Topic",
  components: {
    Reply,
    Platenav
  },
  data() {
    return {
      replies: [],
      topicid: this.$route.params.topicid,
      topic: [],
      plateid: "",
      textarea: "",
      dialogImageUrl: "",
      dialogVisible: false,
      one: "",
      two: "",
      three: "",
      four: "",
      five: "",
      six: "",
      first: [],
      index: parseInt(this.$route.params.index),
      collect: false,
      islike: true,
      count: 0
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
    this.getcollect();
    this.isfavorite();
    this.getreplycount();
  },
  methods: {
    getReply() {
      getReplybyTopicid(this.topicid, this.index)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.replies = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    getreplycount() {
      getcount(this.topicid)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.count = res.data.data;
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
            this.plateid = res.data.data.plateId;
            this.first = {
              head: res.data.data.head,
              username: res.data.data.username,
              exp: res.data.data.exp,
              storey: 1,
              sign: res.data.data.sign,
              context: res.data.data.context,
              picture: res.data.data.picture,
              replyTime: res.data.data.firstTime
            };
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
    },
    nextclick() {
      this.$router.push({
        path: "/topic/" + this.topicid + "/" + (this.index + 1)
      });
    },
    prevclick() {
      this.$router.push({
        path: "/topic/" + this.topicid + "/" + (this.index - 1)
      });
    },
    handleCurrentChange(val) {
      this.$router.push({ path: "/topic/" + this.topicid + "/" + val });
    },
    update() {
      this.getReply();
      this.gettopic();
    },
    getcollect() {
      isCollectTopic(this.$store.state.id, this.topicid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.collect = true;
        }
        if (res.data.code === "ERROR") {
          this.collect = false;
        }
      });
    },
    removeCollect() {
      deleteCollectTopic(this.$store.state.id, this.topicid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.getcollect();
        }
      });
    },
    collectThis() {
      const collectDto = {
        userId: this.$store.state.id,
        topicId: this.topicid
      };
      collectPlate(collectDto).then(res => {
        if (res.data.code === "SUCCESS") {
          this.getcollect();
        }
      });
    },
    add() {
      const favoriteDto = {
        userId: this.$store.state.id,
        topicId: this.topicid
      };
      addFavorite(favoriteDto).then(res => {
        if (res.data.code === "SUCCESS") {
          this.gettopic();
          this.isfavorite();
          this.$message.success("点赞成功");
        }
        if (res.data.code === "ERROR") {
          this.$message.info("已经点赞");
        }
      });
    },
    remove() {
      deleteTopicFavorite(this.$store.state.id, this.topicid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.gettopic();
          this.isfavorite();
          this.$message.success("取消点赞");
        }
      });
    },
    isfavorite() {
      isTopicFavorite(this.$store.state.id, this.topicid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.islike = false;
        }
        if (res.data.code === "ERROR") {
          this.islike = true;
        }
      });
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 5;

      if (!isJPG) {
        this.$message.error("上传图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
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
