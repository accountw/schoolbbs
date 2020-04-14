<template>
  <div class="Userban">
    <el-table :data="ban" style="width: 1080px">
      <el-table-column prop="username" label="用户名" width="180">
      </el-table-column>
      <el-table-column prop="dealUsername" label="处理人" width="180">
      </el-table-column>
      <el-table-column prop="dealTime" label="处理时间" width="180">
      </el-table-column>
      <el-table-column prop="reason" label="封禁理由" width="180">
      </el-table-column>
      <el-table-column prop="freeTime" label="解封时间" width="180">
      </el-table-column>
      <el-table-column label="操作" width="180">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleDelete(scope.row.id)"
            >解封</el-button
          >
        </template>
      </el-table-column>
    </el-table>
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
  </div>
</template>

<script>
import { deleteBan, getBanList, getcount } from "../../network/ban";

export default {
  name: "Userban",
  data() {
    return {
      ban: [],
      index: 1,
      count: 0
    };
  },
  created() {
    this.getBan();
    this.getnum();
  },
  methods: {
    getBan() {
      getBanList(this.index).then(res => {
        if (res.data.code === "SUCCESS") {
          this.ban = res.data.data;
        }
      });
    },
    getnum() {
      getcount(this.index).then(res => {
        if (res.data.code === "SUCCESS") {
          this.count = res.data.data;
        }
      });
    },
    nextclick() {
      this.index = this.index + 1;
      this.getBan();
      this.getnum();
    },
    prevclick() {
      this.index = this.index - 1;
      this.getBan();
      this.getnum();
    },
    handleCurrentChange(val) {
      this.index = val;
      this.getBan();
      this.getnum();
    },
    handleDelete(id) {
      this.$confirm("确定解封该用户", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteBan(id).then(res => {
            if (res.data.code === "SUCCESS") {
              this.getBan();
              this.getnum();
              this.$message({
                type: "success",
                message: "解封成功!"
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
