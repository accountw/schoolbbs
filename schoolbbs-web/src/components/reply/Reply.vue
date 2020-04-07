<template>
  <div class="Reply">
    <el-card class="reply" shadow="never" body-style="padding:0px">
      <el-container>
        <el-aside width="150px">
          <div class="head">
            <img :src="reply.head" id="image" />
            <div>{{ reply.username }}</div>
            <div>会员等级:{{ getlevel(reply.exp) }}级</div>
          </div></el-aside
        >
        <el-container>
          <el-header>
            <div class="storey" style="color: lightslategray;font-size: 15px">
              {{ reply.storey }}楼
            </div>
          </el-header>
          <el-main>
            <div>{{ reply.context }}</div>
            <div>
              <Picture
                v-if="reply.picture"
                v-bind:picture="reply.picture"
              ></Picture>
            </div>
            <div class="tail">
              <div class="time" style="color: lightslategray">
                发表于{{ reply.replyTime }}
              </div>
              <div v-if="reply.storey != 1">
                <el-link
                  type="primary"
                  style="float: right"
                  :underline="false"
                  v-if="!put"
                  @click="take"
                  >回复<span v-if="reply.count != 0"
                    >({{ reply.count }})</span
                  ></el-link
                >
                <el-link
                  type="primary"
                  style="float: right"
                  :underline="false"
                  v-if="put"
                  @click="take"
                  >收起回复</el-link
                >
              </div>
            </div>
          </el-main>
          <transition>
            <el-footer v-if="put">
              <Comment v-bind:replyid="reply.id"></Comment>
            </el-footer>
          </transition>
        </el-container>
      </el-container>
    </el-card>
  </div>
</template>

<script>
import Picture from "./Picture";
import Comment from "../comment/Comment";

export default {
  name: "Reply",
  props: ["reply"],
  data() {
    return {
      put: false
    };
  },

  components: {
    Picture,
    Comment
  },
  methods: {
    getlevel(exp) {
      var level = 1;
      if (exp < 2) {
        level = 1;
      } else if (exp < 5) {
        level = 2;
      } else if (exp < 15) {
        level = 3;
      } else if (exp < 30) {
        level = 4;
      } else if (exp < 50) {
        level = 5;
      } else if (exp < 100) {
        level = 6;
      } else if (exp < 200) {
        level = 7;
      } else if (exp < 500) {
        level = 8;
      } else if (exp < 1000) {
        level = 9;
      } else if (exp < 2000) {
        level = 10;
      } else if (exp < 3000) {
        level = 11;
      } else if (exp < 6000) {
        level = 12;
      } else if (exp < 10000) {
        level = 13;
      } else if (exp < 18000) {
        level = 14;
      } else if (exp < 30000) {
        level = 15;
      } else if (exp < 60000) {
        level = 16;
      } else if (exp < 100000) {
        level = 17;
      } else if (exp < 300000) {
        level = 18;
      } else if (exp < 600000) {
        level = 19;
      } else if (exp < 1000000) {
        level = 20;
      }
      return level;
    },
    take() {
      if (this.put == false) {
        this.put = true;
      } else {
        this.put = false;
      }
    }
  }
};
</script>

<style scoped>
.head {
  margin: 0 auto;
  text-align: center;
  margin-top: 5px;
}
#image {
  width: 100px;
  height: 100px;
  border: 1px solid #eeeeee;
  padding: 1px;
}
.el-aside {
  border-right: 1px solid #eeeeee;
  background-color: rgb(248, 248, 248);
  min-height: 200px;
}
.el-main {
  position: relative;
  padding-bottom: 0;
  padding-top: 0;
  min-height: 180px;
}
.tail {
  font-size: 12px;
  position: absolute;
  right: 20px;
  bottom: 0;
}
.storey {
  display: inline;
  float: left;
}
.time {
  float: left;
}

.el-card {
  border: 0;
  border-top: 1px solid #ebeef5;
  border-right: 1px solid #ebeef5;
  border-left: 1px solid #ebeef5;
}
.el-footer {
  background-color: #ebeef5;
  margin-left: 20px;
  padding-left: 20px;
  padding-top: 5px;
  padding-bottom: 5px;
  font-size: 13px;
  height: auto !important;
}
.el-header {
  height: 20px !important;
  margin-top: 5px;
  margin-bottom: 5px;
}
</style>
