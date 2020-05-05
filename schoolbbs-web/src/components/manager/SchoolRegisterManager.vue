<template>
  <div class="SchoolRegisterManager">
    <el-card shadow="never">
      <el-table :data="register">
        <el-table-column prop="username" label="用户名" width="100px">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="80px">
        </el-table-column>
        <el-table-column prop="mail" label="邮箱" width="180px">
        </el-table-column>
        <el-table-column prop="grade" label="年级" width="80px">
        </el-table-column>
        <el-table-column prop="college" label="学院" width="150px">
        </el-table-column>
        <el-table-column prop="major" label="专业" width="100px">
        </el-table-column>
        <el-table-column prop="studentNo" label="学号" width="150px">
        </el-table-column>
        <el-table-column prop="askTime" label="申请时间"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="deal(scope.row.id, 1)"
              >申请通过</el-button
            >
            <el-button size="mini" @click="deal(scope.row.id, 2)"
              >不通过</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        small
        layout="prev, pager, next"
        :total="count"
        page-size="15"
        hide-on-single-page
        @current-change="handleCurrentChange"
        @prev-click="prevclick"
        @next-click="nextclick"
      >
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
import {
  dealSchoolRegister,
  getSchoolRegister,
  getSchoolRegisterCount
} from "../../network/register";

export default {
  name: "SchoolRegisterManager",
  data() {
    return {
      register: [],
      index: 1,
      count: 0
    };
  },
  created() {
    this.getRegister();
    this.getnum();
  },
  methods: {
    getRegister() {
      getSchoolRegister(this.index).then(res => {
        if (res.data.code === "SUCCESS") {
          this.register = res.data.data;
        }
      });
    },
    getnum() {
      getSchoolRegisterCount().then(res => {
        if (res.data.code === "SUCCESS") {
          this.count = res.data.data;
        }
      });
    },
    nextclick() {
      this.index = this.index + 1;
      this.getRegister();
      this.getnum();
    },
    prevclick() {
      this.index = this.index - 1;
      this.getRegister();
      this.getnum();
    },
    handleCurrentChange(val) {
      this.index = val;
      this.getRegister();
      this.getnum();
    },
    deal(id, status) {
      var str = "";
      if (status === 1) {
        str = "通过申请";
      }
      if (status === 2) {
        str = "不通过他的申请";
      }
      this.$confirm(str, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          dealSchoolRegister(id, status).then(res => {
            if (res.data.code === "SUCCESS") {
              this.getRegister();
              this.getnum();
              this.$message({
                type: "success",
                message: "操作成功!"
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消"
          });
        });
    }
  }
};
</script>

<style scoped></style>
