<template>
  <div class="Cricle">
    <el-card body-style="padding-bottom:5px;" shadow="never">
      <el-container>
        <el-aside width="44px"
          ><img :src="cricle.head" style="width: 40px;height: 40px;"
        /></el-aside>
        <el-main style="padding: 1px">
          <div>
            <el-link
              type="primary"
              :underline="false"
              :href="/user/ + cricle.userId"
              >{{ cricle.username }}</el-link
            >
            <el-link
              type="primary"
              :underline="false"
              style="float: right"
              @click="removecricle(cricle.id)"
              v-if="this.$store.state.id == cricle.userId"
              >删除</el-link
            >
          </div>
          <div style="font-size: 11px">
            {{ cricle.createTime }}
          </div></el-main
        >
      </el-container>
      <el-divider></el-divider>
      <div
        style="font-size: 18px;padding-bottom: 5px ;white-space: pre-wrap; word-break:break-all;overflow:hidden"
      >
        {{ cricle.context }}
      </div>
      <Criclepicture v-bind:picture="cricle.picture"></Criclepicture>
      <div>
        <el-row>
          <el-col
            :span="12"
            style="border-right-width: 1px;border-right: darkslategrey"
            ><div style="text-align: center">
              <el-link :underline="false" style="color: darkslategrey">
                <i
                  class="el-icon-alipinglun"
                  style="width: 20px;height: 20px"
                  @click="getmessage"
                ></i>
              </el-link>
              <span style="font-size: large"> {{ cricle.replynum }}</span>
            </div></el-col
          >
          <el-col :span="12"
            ><div style="text-align: center">
              <el-link :underline="false" style="color: darkslategrey">
                <i
                  class="el-icon-alidianzan"
                  style="width: 20px;height: 20px"
                  @click="likecricle(cricle.id)"
                  v-if="!like"
                ></i>
                <i
                  class="el-icon-alidianzan1"
                  style="width: 20px;height: 20px;color: crimson"
                  @click="removelikecricle(cricle.id)"
                  v-if="like"
                ></i>
              </el-link>
              <span style="font-size: large"> {{ cricle.likenum }}</span>
            </div></el-col
          >
        </el-row>
      </div>
      <div v-if="message">
        <el-card shadow="never">
          <div v-for="criclemessage in messages" :key="criclemessage.id">
            <Criclemessage v-bind:criclemessage="criclemessage"></Criclemessage>
          </div>
          <el-pagination
            background
            layout="prev, pager, next"
            :total="count"
            :page-size="10"
            :current-page="this.index"
            hide-on-single-page
            @current-change="handleCurrentChange"
            @prev-click="prevclick"
            @next-click="nextclick"
          >
          </el-pagination>
          <el-input
            type="textarea"
            row="3"
            maxlength="50"
            v-model="context"
          ></el-input>
          <el-button type="primary" size="mini" @click="sumbit">发表</el-button>
        </el-card>
      </div>
    </el-card>
  </div>
</template>

<script>
import {
  likeCricle,
  removeLikeCricle,
  selectLikeCricle
} from "../../network/favorite";
import {
  addMessage,
  deleteCricle,
  getCount,
  getMessage
} from "../../network/cricle";
import Criclepicture from "./Criclepicture";
import Criclemessage from "./Criclemessage";

export default {
  components: {
    Criclepicture,
    Criclemessage
  },
  props: ["cricle"],
  name: "Cricle",
  data() {
    return {
      like: true,
      message: false,
      messages: [],
      index: 1,
      context: "",
      count: 0
    };
  },
  created() {
    this.islike();
    this.getcount();
  },
  methods: {
    getmessages() {
      getMessage(this.cricle.id, this.index).then(res => {
        if (res.data.code === "SUCCESS") {
          this.messages = res.data.data;
        }
      });
    },
    getcount() {
      getCount(this.cricle.id).then(res => {
        if (res.data.code === "SUCCESS") {
          this.count = res.data.data;
        }
      });
    },
    likecricle(id) {
      likeCricle(id).then(res => {
        if (res.data.code === "SUCCESS") {
          this.islike();
          this.$emit("delete");
        }
      });
    },
    sumbit() {
      if (!this.context) {
        this.$message.error("请输入内容");
      } else {
        const cricleMessageDto = {
          context: this.context,
          userId: this.$store.state.id,
          cricleId: this.cricle.id
        };
        addMessage(cricleMessageDto).then(res => {
          if (res.data.code === "SUCCESS") {
            this.$message.success("发表成功");
            this.context = "";
            this.$emit("delete");
            this.getmessages();
            this.getcount();
          }
        });
      }
    },
    removelikecricle(id) {
      removeLikeCricle(id).then(res => {
        if (res.data.code === "SUCCESS") {
          this.islike();
          this.$emit("delete");
        }
      });
    },
    getmessage() {
      if (this.message == true) {
        this.message = false;
      } else {
        this.message = true;
        this.getmessages();
      }
    },
    islike() {
      selectLikeCricle(this.cricle.id).then(res => {
        if (res.data.code === "SUCCESS") {
          this.like = true;
        }
        if (res.data.code === "ERROR") {
          this.like = false;
        }
      });
    },
    removecricle(id) {
      this.$confirm("确认删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteCricle(id)
            .then(res => {
              if (res.data.code === "SUCCESS") {
                this.$message({
                  type: "success",
                  message: "删除成功!"
                });
                this.$emit("delete");
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
    },
    nextclick() {
      this.index = this.index + 1;
      this.getmessages();
    },
    prevclick() {
      this.index = this.index - 1;
      this.getmessages();
    },
    handleCurrentChange(val) {
      this.index = val;
      this.getmessages();
    }
  }
};
</script>

<style scoped>
.el-divider--horizontal {
  margin: 5px 0 10px 0;
}
</style>
