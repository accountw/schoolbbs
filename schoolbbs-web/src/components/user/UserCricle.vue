<template>
  <div class="UserCricle">
    <el-button
      type="primary"
      icon="el-icon-edit"
      circle
      @click="dialogFormVisible = true"
    ></el-button>
    <el-dialog title="圈子发表" :visible.sync="dialogFormVisible">
      <el-upload
        class="upload-demo"
        ref="upload"
        action="http://localhost:8081/api/reply/savePicture"
        :headers="header"
        :on-success="upload"
        :limit="9"
        :on-exceed="handleExceed"
        :show-file-list="true"
        :multiple="false"
        list-type="picture-card"
        :on-preview="handlePictureCardPreview"
        :on-remove="handleRemove"
        :before-upload="beforeAvatarUpload"
        style="width: 480px"
      >
        <i class="el-icon-plus"></i>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="" />
      </el-dialog>
      <el-input
        v-model="context"
        :rows="3"
        type="textarea"
        placeholder="请输入内容"
      ></el-input>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addcricle">发 表</el-button>
      </div>
    </el-dialog>
    <div v-for="cricle in cricles" :key="cricle.id">
      <Cricle v-bind:cricle="cricle" @delete="update"></Cricle>
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
  </div>
</template>

<script>
import { add, getCountbyUserid, getcricleByuserId } from "../../network/cricle";
import Cricle from "../cricle/Cricle";
export default {
  name: "UserCricle",
  components: {
    Cricle
  },

  data() {
    return {
      cricles: [],
      index: 1,
      count: 0,
      dialogFormVisible: false,
      dialogImageUrl: "",
      dialogVisible: false,
      context: "",
      one: "",
      two: "",
      three: "",
      four: "",
      five: "",
      six: "",
      seven: "",
      eight: "",
      nine: ""
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
      if (this.seven) {
        if (l == 0) {
          p = this.seven;
          l = 1;
        } else {
          p = p + "," + this.seven;
        }
      }
      if (this.eight) {
        if (l == 0) {
          p = this.eight;
          l = 1;
        } else {
          p = p + "," + this.eight;
        }
      }
      if (this.nine) {
        if (l == 0) {
          p = this.nine;
          l = 1;
        } else {
          p = p + "," + this.nine;
        }
      }
      return p;
    }
  },
  created() {
    this.getcount();
    this.getcricle();
  },
  methods: {
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 9 个文件，本次选择了 ${
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
      } else if (this.seven == "") {
        this.seven = response.data;
      } else if (this.eight == "") {
        this.eight = response.data;
      } else if (this.nine == "") {
        this.nine = response.data;
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
      } else if (this.seven == file.name) {
        this.seven = "";
      } else if (this.eight == file.name) {
        this.eight = "";
      } else if (this.nine == file.name) {
        this.nine = "";
      }
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 10;

      if (!isJPG) {
        this.$message.error("上传图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传图片大小不能超过 10MB!");
      }
      return isJPG && isLt2M;
    },
    update() {
      this.getcricle();
      this.getcount();
    },
    getcricle() {
      getcricleByuserId(this.$store.state.id, this.index)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.cricles = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    nextclick() {
      this.index = this.index + 1;
      this.getcricle();
    },
    prevclick() {
      this.index = this.index - 1;
      this.getcricle();
    },
    handleCurrentChange(val) {
      this.index = val;
      this.getcricle();
    },
    getcount() {
      getCountbyUserid(this.$store.state.id).then(res => {
        if (res.data.code === "SUCCESS") {
          this.count = res.data.data;
        }
      });
    },
    addcricle() {
      const cricleDto = {
        context: this.context,
        picture: this.picture,
        userId: this.$store.state.id
      };
      if (this.picture) {
        add(cricleDto).then(res => {
          if (res.data.code === "SUCCESS") {
            this.$message.success("发表成功");
            this.context = "";
            this.one = "";
            this.two = "";
            this.three = "";
            this.four = "";
            this.five = "";
            this.six = "";
            this.seven = "";
            this.eight = "";
            this.nine = "";
            this.$refs.upload.clearFiles();
            this.getcricle();
            this.getcount();
            this.dialogFormVisible = false;
          }
        });
      } else {
        this.$message.error("必须添加一张以上的图片才能发表圈子");
      }
    }
  }
};
</script>

<style scoped>
.el-divider--horizontal {
  margin: 5px 0 10px 0;
}
</style>
