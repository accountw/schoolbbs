<template>
  <div class="Cplate">
    <el-card class="box-card">
      <div>{{ this.plate.name }}</div>
      <div>{{ this.plate.depict }}</div>
      <ul id="ul">
        <li v-for="topic in topices" :key="topic.id">
          <el-card class="box-card" shadow="hover">
            <div class="text item">
              <div id="title">
                <el-link
                  :href="'/topic/' + topic.id"
                  target="_blank"
                  style="font-size: 20px"
                  :underline="false"
                  >{{ topic.title }}</el-link
                >
              </div>
              <div id="context">{{ topic.context }}</div>
              <div>
                <Picture
                  v-if="topic.picture"
                  v-bind:picture="topic.picture"
                ></Picture>
              </div>
              <div id="user">
                <el-link style="font-size: 12px"
                  ><i class="el-icon-user-solid"></i
                  >{{ topic.username }}</el-link
                >
              </div>
              <div style="font-size: 12px">{{topic.lastTime}}</div>
            </div>
          </el-card>
        </li>
      </ul>
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
          :rows="3"
        >
        </el-input>
        <el-button type="primary" @click="submit">发表</el-button>
      </el-card>
    </el-card>
  </div>
</template>

<script>
import { getTopicByPlateid } from "../../network/topic";
import { getPlateByid } from "../../network/plate";
import { saveTopic } from "../../network/topic";
import Picture from "./Picture";

export default {
  components: {
    Picture
  },
  name: "cplate",
  data() {
    return {
      plateid: this.$route.params.plateid,
      topices: [],
      plate: [],
      index: this.$route.params.index,
      textarea: "",
      dialogImageUrl: "",
      dialogVisible: false,
      one: "",
      two: "",
      three: "",
      title: ""
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
    this.getplate();
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
    getplate() {
      getPlateByid(this.plateid)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.plate = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
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
