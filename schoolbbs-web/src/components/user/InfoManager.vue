<template>
  <div class="InfoManager" style="margin-left: 40px;margin-right: 40px">
    <h2>个人信息</h2>
    <el-divider></el-divider>
    <el-row :gutter="20">
      <el-col :span="4">
        <span>头像</span>
      </el-col>
      <el-col :span="4">
        <img :src="user.head" style="width: 80px;height: 80px" />
      </el-col>
      <el-col :span="8">
        <div style="font-size: 12px">支持 jpg格式大小 2M 以内的图片</div>

        <el-upload
          class="upload-demo"
          action="http://localhost:8081/api/user/saveHead"
          :headers="header"
          :on-success="upload"
          :before-upload="beforeAvatarUpload"
          :limit="1"
          :multiple="false"
          :show-file-list="false"
        >
          <el-button size="mini" type="primary">点击上传</el-button>
        </el-upload>
      </el-col>
    </el-row>
    <el-divider></el-divider>
    <el-row :gutter="20">
      <el-col :span="4">
        <span>个人介绍</span>
      </el-col>
      <el-col :span="20">
        <el-input v-model="user.sign" maxlength="30"></el-input
      ></el-col>
    </el-row>

    <el-divider></el-divider>
    <el-row :gutter="20">
      <el-col :span="4">
        <span>性别</span>
      </el-col>
      <el-col :span="20">
        <template>
          <el-radio-group v-model="user.gender">
            <el-radio :label="0">男</el-radio>
            <el-radio :label="1">女</el-radio>
          </el-radio-group>
        </template>
      </el-col>
    </el-row>

    <el-divider></el-divider>
    <el-row :gutter="20">
      <el-col :span="4">
        <span>生日</span>
      </el-col>
      <el-col :span="20">
        <el-date-picker v-model="user.birth" type="date" placeholder="选择日期">
        </el-date-picker
      ></el-col>
    </el-row>
    <el-divider></el-divider>
    <el-button type="primary" @click="sumbit">保存</el-button>
  </div>
</template>

<script>
import { getUserByid } from "../../network/User";
import { updateUser } from "../../network/User";

export default {
  name: "Infosetting",
  data() {
    return {
      userid: this.$store.state.id,
      user: []
    };
  },
  computed: {
    header() {
      return {
        Authorization: this.$store.state.Authorization
      };
    }
  },
  created() {
    this.getuser();
  },
  methods: {
    getuser() {
      getUserByid(this.userid)
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.user = res.data.data;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    upload(response) {
      if (response.code === "SUCCESS") {
        this.user.head = "/head/" + response.data;
      }
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    sumbit() {
      updateUser(this.user).then(res => {
        if (res.data.code === "SUCCESS") {
          this.$message.success("保存成功");
        }
      });
    }
  }
};
</script>

<style scoped></style>
