<template>
  <div class="SearchResult">
    <el-card>
      <div></div>
      <el-tabs value="first">
        <el-tab-pane label="按主题搜索" name="first">
          <div v-if="!topices">
            无搜索结果
          </div>
          <div v-if="topices">
            <ul
              style=" list-style: none;
  padding-inline-start: 0;"
            >
              <li v-for="topic in topices" :key="topic.id">
                <el-card
                  class="box-card"
                  shadow="never"
                  body-style="padding: 5px"
                >
                  <div class="text item">
                    <div>
                      <el-link
                        :href="'/topic/' + topic.id + '/1'"
                        target="_blank"
                        style="font-size: 15px"
                        :underline="false"
                        >{{ topic.title }}</el-link
                      >
                      <i class="el-icon-chat-round" style="float: right">{{
                        topic.count
                      }}</i>
                    </div>
                    <div
                      id="context"
                      style="white-space: pre-wrap; word-break:break-all;overflow:hidden"
                    >
                      {{ topic.context }}
                    </div>
                    <div>
                      <el-link
                        style="font-size: 12px;float: left;margin-right: 10px"
                        :href="/user/ + topic.userId"
                        :underline="false"
                        ><i class="el-icon-user-solid"></i
                        >{{ topic.username }}</el-link
                      >
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
                    </div>
                  </div>
                </el-card>
              </li>
            </ul>
          </div>
          <div class="block">
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
          </div>
        </el-tab-pane>
        <el-tab-pane label="按内容搜搜" name="second"
          ><div v-if="!replies">
            无搜索结果
          </div>
          <ul
            style=" list-style: none;
  padding-inline-start: 0;"
            v-if="replies">
            <li v-for="reply in replies" :key="reply.id">
              <el-card
                class="box-card"
                shadow="never"
                body-style="padding: 5px"
              >
                <div class="text item">
                  <div>
                    <el-link
                      :href="'/topic/' + reply.topicId + '/1'"
                      target="_blank"
                      style="font-size: 15px"
                      :underline="false"
                      >{{ reply.context }}</el-link
                    >
                  </div>
                  <div>
                    <el-link
                            style="font-size: 12px;float: left;margin-right: 10px"
                            :href="/user/ + reply.userId"
                            :underline="false"
                    ><i class="el-icon-user-solid"></i
                    >{{ reply.username }}</el-link
                    >
                  </div>
                  <div style="font-size: 12px;">
                    <i class="el-icon-position"></i>{{ reply.replyTime }}
                    <div style="font-size: 14px;float: right">
                      <img
                              src="../../assets/点赞-空.png"
                              style="width:12px;height: 12px"
                      />
                      {{ reply.likenum }}
                    </div>
                  </div>
                </div>
              </el-card>
            </li>
          </ul></el-tab-pane
        >
        <div class="block">
          <el-pagination
            background
            layout="prev, pager, next"
            :total="rcount"
            :page-size="15"
            :current-page="this.index"
            @current-change="CurrentChange"
            @prev-click="prev"
            @next-click="next"
            hide-on-single-page
          >
          </el-pagination>
        </div>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
import { getsearch, getsearchCount } from "../../network/topic";
import { getreplysearch, getreplysearchCount } from "../../network/reply";

export default {
  name: "SearchResult",
  data() {
    return {
      context: this.$route.params.context,
      index: 1,
      rindex: 1,
      topices: [],
      replies: [],
      count: 0,
      rcount: 0
    };
  },
  created() {
    this.gettopices();
    this.getreplies();
    this.getrcount();
    this.getcount();
  },
  methods: {
    gettopices() {
      getsearch(this.context, this.index)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.topices = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    getcount() {
      getsearchCount(this.context).then(res => {
        if (res.data.code === "SUCCESS") {
          this.count = res.data.data;
        }
      });
    },
    getrcount() {
      getreplysearchCount(this.context).then(res => {
        if (res.data.code === "SUCCESS") {
          this.rcount = res.data.data;
        }
      });
    },
    getreplies() {
      getreplysearch(this.context, this.index)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.replies = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    nextclick() {
      this.index = this.index + 1;
      this.gettopices();
    },
    prevclick() {
      this.index = this.index - 1;
      this.gettopices();
    },
    handleCurrentChange(val) {
      this.index = val;
      this.gettopices();
    },
    next() {
      this.rindex = this.rindex + 1;
      this.getreplies();
    },
    prev() {
      this.rindex = this.rindex - 1;
      this.getreplies();
    },
    CurrentChange(val) {
      this.rindex = val;
      this.getreplies();
    }
  }
};
</script>

<style scoped></style>
