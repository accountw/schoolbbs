<template>
  <div class="Cplate">
    <el-card class="box-card">
      <Platenav v-bind:plateid="this.plateid"></Platenav>
      <div>
        <ul
          style=" list-style: none;
  padding-inline-start: 0;"
        >
          <li v-for="topic in toptopices" :key="topic.id">
            <el-card class="box-card" shadow="never" body-style="padding: 5px">
              <div class="text item">
                <div>
                  <el-link
                    :href="'/topic/' + topic.id + '/1'"
                    target="_blank"
                    style="font-size: 15px"
                    :underline="false"
                    >{{ titlelimit(topic.title) }}</el-link
                  >
                  <i class="el-icon-chat-round" style="float: right">{{
                    topic.count
                  }}</i>
                  <el-link
                    style="float: right;font-size: 11px"
                    :underline="false"
                    @click="removetop(topic.id)"
                    v-if="isadmin"
                  >
                    取消置顶
                  </el-link>
                  <span
                    style="float: right;color: crimson"
                    v-if="topic.fine == 1"
                    >精</span
                  >
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
                  <el-link
                    style="font-size: 11px "
                    :underline="false"
                    @click="setfine(topic.id)"
                    v-if="topic.fine == 0 && isadmin"
                  >
                    加精
                  </el-link>
                  <el-link
                    style="font-size: 11px "
                    :underline="false"
                    @click="removefine(topic.id)"
                    v-if="topic.fine == 1 && isadmin"
                  >
                    取消加精
                  </el-link>
                  <el-link
                    style="float: right;font-size: 11px"
                    :underline="false"
                    v-if="isadmin"
                    @click="deleteTopic(topic.id)"
                    >删除</el-link
                  >
                </div>
              </div>
            </el-card>
          </li>
        </ul>
        <el-tabs value="first">
          <el-tab-pane label="看帖" name="first"
            ><ul id="ul">
              <li v-for="topic in topices" :key="topic.id">
                <el-card class="box-card" shadow="never">
                  <div class="text item">
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
                      <el-link
                        style="float: right;font-size: 11px"
                        :underline="false"
                        @click="settop(topic.id)"
                        v-if="isadmin"
                      >
                        置顶
                      </el-link>
                      <span
                        style="float: right;color: crimson"
                        v-if="topic.fine == 1"
                        >精</span
                      >
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
                      <el-link
                        style="font-size: 11px "
                        :underline="false"
                        @click="setfine(topic.id)"
                        v-if="topic.fine == 0 && isadmin"
                      >
                        加精
                      </el-link>
                      <el-link
                        style="font-size: 11px "
                        :underline="false"
                        @click="removefine(topic.id)"
                        v-if="topic.fine == 1 && isadmin"
                      >
                        取消加精
                      </el-link>
                      <el-link
                        style="float: right;font-size: 11px"
                        :underline="false"
                        v-if="isadmin"
                        @click="deleteTopic(topic.id)"
                        >删除</el-link
                      >
                    </div>
                  </div>
                </el-card>
              </li>
            </ul>
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
            <el-card shadow="never">
              <el-input
                type="textarea"
                :rows="1"
                maxlength="50"
                placeholder="请填写标题"
                v-model="title"
              >
              </el-input>
              <el-upload
                class="upload-demo"
                ref="upload"
                action="http://localhost:8081/api/reply/savePicture"
                :headers="header"
                :on-success="upload"
                :limit="3"
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
                maxlength="250"
                show-word-limit
                :rows="3"
              >
              </el-input>
              <el-button type="primary" @click="submit">发表</el-button>
            </el-card></el-tab-pane
          >
          <el-tab-pane label="精品贴" name="second">
            <ul
              style=" list-style: none;
  padding-inline-start: 0;"
            >
              <li v-for="topic in finetopices" :key="topic.id">
                <el-card class="box-card" shadow="never">
                  <div class="text item">
                    <div>
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
                      <el-link
                        style="float: right;font-size: 11px"
                        :underline="false"
                        @click="settop(topic.id)"
                        v-if="isadmin"
                      >
                        置顶
                      </el-link>
                      <span
                        style="float: right;color: crimson"
                        v-if="topic.fine == 1"
                        >精</span
                      >
                    </div>
                    <div
                      style="white-space: pre-wrap; word-break:break-all;overflow:hidden;  font-size: 16px;"
                    >
                      {{ contextlimit(topic.context) }}
                    </div>
                    <div>
                      <Picture
                        v-if="topic.picture"
                        v-bind:picture="topic.picture"
                      ></Picture>
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
                      <el-link
                        style="font-size: 11px "
                        :underline="false"
                        @click="setfine(topic.id)"
                        v-if="topic.fine == 0 && isadmin"
                      >
                        加精
                      </el-link>
                      <el-link
                        style="font-size: 11px "
                        :underline="false"
                        @click="removefine(topic.id)"
                        v-if="topic.fine == 1 && isadmin"
                      >
                        取消加精
                      </el-link>
                      <el-link
                        style="float: right;font-size: 11px"
                        :underline="false"
                        v-if="isadmin"
                        @click="deleteTopic(topic.id)"
                        >删除</el-link
                      >
                    </div>
                  </div>
                </el-card>
              </li>
            </ul>
            <div class="block">
              <el-pagination
                background
                layout="prev, pager, next"
                :total="finecount"
                :page-size="15"
                :current-page="this.fineindex"
                @current-change="CurrentChange"
                @prev-click="prev"
                @next-click="next"
                hide-on-single-page
              >
              </el-pagination></div
          ></el-tab-pane>
        </el-tabs>
      </div>
    </el-card>
  </div>
</template>

<script>
import {
  deleteTopicbyadmin,
  getFinecount,
  getFineList,
  getTopicByPlateid,
  getTopList,
  removeFine,
  removeTop,
  setFine,
  setTop
} from "../../network/topic";
import { saveTopic } from "../../network/topic";
import { getTopicCount } from "../../network/topic";
import Picture from "./Picture";
import Platenav from "./Platenav";
import { isAdmin } from "../../network/plateadmin";

export default {
  components: {
    Picture,
    Platenav
  },
  name: "cplate",
  data() {
    return {
      activeName: "first",
      plateid: this.$route.params.plateid,
      topices: [],
      toptopices: [],
      finetopices: [],
      index: parseInt(this.$route.params.index),
      fineindex: 1,
      textarea: "",
      dialogImageUrl: "",
      dialogVisible: false,
      one: "",
      two: "",
      three: "",
      title: "",
      count: null,
      finecount: null,
      isadmin: false
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
      return p;
    }
  },
  created() {
    this.gettopic();
    this.getcount();
    this.getFineCount();
    this.getfineTopice();
    this.gettoptopic();
    if (
      this.$store.state.role == "ROLE_MANAGER" ||
      this.$store.state.role == "ROLE_ADMIN"
    ) {
      isAdmin(this.plateid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.isadmin = true;
        }
      });
    }
  },
  methods: {
    gettopic() {
      getTopicByPlateid(this.plateid, this.index)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.topices = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    gettoptopic() {
      getTopList(this.plateid)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.toptopices = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    getfineTopice() {
      getFineList(this.plateid, this.fineindex)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.finetopices = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${
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
      }
    },
    handleRemove(file) {
      if (this.one == file.name) {
        this.one = "";
      } else if (this.two == file.name) {
        this.two = "";
      } else if (this.three == file.name) {
        this.three = "";
      }
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    submit() {
      if (!this.textarea || !this.title) {
        this.$message.warning("发送内容不能为空");
      } else {
        const topicDto = {
          userId: this.$store.state.id,
          plateId: this.plateid,
          title: this.title,
          context: this.textarea,
          picture: this.picture
        };
        saveTopic(topicDto)
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
    nextclick() {
      this.$router.push({
        path: "/plate/" + this.plateid + "/" + (this.index + 1)
      });
    },
    prevclick() {
      this.$router.push({
        path: "/plate/" + this.plateid + "/" + (this.index - 1)
      });
    },
    handleCurrentChange(val) {
      this.$router.push({ path: "/plate/" + this.plateid + "/" + val });
    },
    next() {
      this.fineindex = this.fineindex + 1;
      this.getfineTopice();
    },
    prev() {
      this.fineindex = this.fineindex - 1;
      this.getfineTopice();
    },
    CurrentChange(val) {
      this.fineindex = val;
      this.getfineTopice();
    },
    getcount() {
      getTopicCount(this.plateid)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.count = parseInt(res.data.data);
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    getFineCount() {
      getFinecount(this.plateid)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.finecount = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    deleteTopic(topicid) {
      this.$confirm("确认删除该贴?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteTopicbyadmin(topicid)
            .then(res => {
              if (res.data.code === "SUCCESS") {
                this.$message({
                  type: "success",
                  message: "删除成功!"
                });
                this.gettopic();
                this.getcount();
                this.getFineCount();
                this.getfineTopice();
                this.gettoptopic();
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
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isPNG = file.type === "image/png";
      const isLt2M = file.size / 1024 / 1024 < 5;

      if (!isJPG && !isPNG) {
        this.$message.error("上传头像图片只能是 JPG 或者 PNG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传图片大小不能超过 5MB!");
      }
      return (isJPG || isPNG) && isLt2M;
    },
    setfine(topicid) {
      this.$confirm("确定加精?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          setFine(topicid)
            .then(res => {
              if (res.data.code === "SUCCESS") {
                this.$message({
                  type: "success",
                  message: "加精成功!"
                });
                this.gettopic();
                this.getcount();
                this.getFineCount();
                this.getfineTopice();
                this.gettoptopic();
              }
              if (res.data.code === "ERROR") {
                this.$message({
                  type: "error",
                  message: "设置失败!"
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
    settop(topicid) {
      this.$confirm("确定置顶?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          setTop(topicid, this.plateid)
            .then(res => {
              if (res.data.code === "SUCCESS") {
                this.$message({
                  type: "success",
                  message: "置顶成功!"
                });
                this.gettopic();
                this.getcount();
                this.getFineCount();
                this.getfineTopice();
                this.gettoptopic();
              }
              if (res.data.code === "ERROR") {
                this.$message({
                  type: "error",
                  message: "置顶已达上现!"
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
    removetop(topicid) {
      this.$confirm("取消置顶?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          removeTop(topicid)
            .then(res => {
              if (res.data.code === "SUCCESS") {
                this.$message({
                  type: "success",
                  message: "设置成功!"
                });
                this.gettopic();
                this.getcount();
                this.getFineCount();
                this.getfineTopice();
                this.gettoptopic();
              }
              if (res.data.code === "ERROR") {
                this.$message({
                  type: "error",
                  message: "设置失败!"
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
    removefine(topicid) {
      this.$confirm("取消加精?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          removeFine(topicid)
            .then(res => {
              if (res.data.code === "SUCCESS") {
                this.$message({
                  type: "success",
                  message: "设置成功!"
                });
                this.gettopic();
                this.getcount();
                this.getFineCount();
                this.getfineTopice();
                this.gettoptopic();
              }
              if (res.data.code === "ERROR") {
                this.$message({
                  type: "error",
                  message: "设置失败!"
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
.el-link--inner {
  font-size: 20px !important;
}
#context {
  font-size: 16px;
}
#ul {
  list-style: none;
  padding-inline-start: 0;
}
</style>
