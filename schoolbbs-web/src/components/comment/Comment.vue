<template>
  <div class="Comment">
    <div v-if="comments">
      <div v-for="comment in comments" :key="comment.id">
        <el-container>
          <el-aside style="width: 35px;">
            <div class="head">
              <img :src="comment.head" id="image" /></div
          ></el-aside>
          <el-main style="padding: 0">
            <div>
              <span
                ><el-link
                  type="primary"
                  :underline="false"
                  :href="/user/ + comment.userId"
                  >{{ comment.username }}</el-link
                > </span
              ><span v-if="comment.replyUsername">
                回复
                <el-link
                  type="primary"
                  :underline="false"
                  :href="/user/ + comment.userId"
                  >{{ comment.replyUsername }}</el-link
                > </span
              >:<span>{{ comment.context }}</span>
            </div>
            <div style="float: right;font-size: 10px">
              {{ comment.replyTime }}
              <el-link
                style="font-size: 10px"
                @click="reply(comment.username, comment.userId)"
                >回复</el-link
              >
            </div>
          </el-main>
        </el-container>
      </div>
      <el-pagination
        small
        layout="prev, pager, next"
        :total="count"
        hide-on-single-page
        @current-change="handleCurrentChange"
        @prev-click="prevclick"
        @next-click="nextclick"
      >
      </el-pagination>
      <div>
        <div>
          回复 <span v-if="replyname">{{ replyname }}</span> :
        </div>
        <div>
          <el-input v-model="textarea" type="textarea"></el-input>
        </div>
        <el-button type="primary" size="mini" @click="sumbit">发表</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { getCommentByreplyid } from "../../network/comment";
import { saveComment } from "../../network/comment";
import { getCommentCount } from "../../network/comment";

export default {
  name: "Comment",
  props: ["replyid"],
  data() {
    return {
      comments: [],
      textarea: "",
      replyname: "",
      replyUserId: "",
      count: null,
      index: 1
    };
  },
  created() {
    this.getcomment();
    this.getcount();
  },
  methods: {
    getcomment() {
      getCommentByreplyid(this.replyid, this.index)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.comments = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    reply(name, id) {
      this.replyname = name;
      this.replyUserId = id;
    },
    sumbit() {
      const commentDto = {
        replyId: this.replyid,
        userId: this.$store.state.id,
        replyUserId: this.replyUserId,
        context: this.textarea
      };
      saveComment(commentDto)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.textarea = "";
            this.replyname = "";
            this.replyUserId = "";
            this.getcomment();
            this.$message({
              showClose: true,
              message: "发表成功",
              type: "success"
            });
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    getcount() {
      getCommentCount(this.replyid)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.count = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    nextclick() {
      this.index = this.index + 1;
      this.getcomment();
    },
    prevclick() {
      this.index = this.index - 1;
      this.getcomment();
    },
    handleCurrentChange(val) {
      this.index = val;
      this.getcomment();
    }
  }
};
</script>

<style scoped>
.head {
  width: 30px;
}
#image {
  width: 28px;
  height: 28px;
  border: 1px solid #eeeeee;
  padding: 1px;
}
</style>
