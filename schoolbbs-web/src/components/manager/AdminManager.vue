<template>
  <div class="AdminManager">
    <el-tabs :tab-position="tabPosition" >
      <el-tab-pane label="版主管理">
        <el-table :data="plateAdmin" style="width: 540px">
          <el-table-column prop="username" label="用户名" width="180">
          </el-table-column>
          <el-table-column prop="name" label="板块名" width="180">
          </el-table-column>
          <el-table-column label="操作" width="180">
            <template slot-scope="scope">
              <el-button size="mini" @click="handleDelete(scope.row.id)"
                >解除版主</el-button
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
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { deleteAdmin, listAdmin } from "../../network/plateadmin";
import { getcount } from "../../network/plateadmin";

export default {
  name: "AdminManager",
  data() {
    return {
      tabPosition: "left",
      plateAdmin: [],
      index: 1,
      count: 0
    };
  },
  created() {
    this.getnum();
    this.getadmin();
  },
  methods: {
    getadmin() {
      listAdmin(this.index).then(res => {
        if (res.data.code === "SUCCESS") {
          this.plateAdmin = res.data.data;
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
      this.getnum();
      this.getadmin();
    },
    prevclick() {
      this.index = this.index - 1;
      this.getnum();
      this.getadmin();
    },
    handleCurrentChange(val) {
      this.index = val;
      this.getnum();
      this.getadmin();
    },
    handleDelete(id) {
      this.$confirm("确定解除该用户版主职位", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteAdmin(id).then(res => {
            if (res.data.code === "SUCCESS") {
              this.getnum();
              this.getadmin();
              this.$message({
                type: "success",
                message: "解除成功!"
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
    },
    submit() {}
  }
};
</script>

<style scoped></style>
