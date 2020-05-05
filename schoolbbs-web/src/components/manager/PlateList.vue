<template>
  <div class="PlateList">
    <el-card>
      <el-table :data="plate" style="width: auto;">
        <el-table-column prop="blockname" label="模块名" width="180">
        </el-table-column>
        <el-table-column prop="name" label="板块名" width="180">
        </el-table-column>
        <el-table-column prop="depict" label="描述" width="450">
        </el-table-column>
        <el-table-column label="操作" width="340">
          <template slot-scope="scope">
            <el-button size="mini" @click="remove(scope.row.id)"
              >删除板块</el-button
            >
            <el-button size="mini" @click="open(scope.row.id)"
              >编辑板块</el-button
            >
            <el-button size="mini" @click="set(scope.row.id)"
              >设置版主</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import { deleteplate, getlist, updateplate } from "../../network/plate";
import { setadmin } from "../../network/plateadmin";

export default {
  name: "PlateList",
  data() {
    return {
      tabPosition: "left",
      plate: []
    };
  },
  created() {
    this.getplate();
  },
  methods: {
    getplate() {
      getlist().then(res => {
        if (res.data.code === "SUCCESS") {
          this.plate = res.data.data;
        }
      });
    },
    remove(id) {
      this.$confirm("确定删除该板块", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteplate(id).then(res => {
            if (res.data.code === "SUCCESS") {
              this.getplate();
              this.$message({
                type: "success",
                message: "删除成功!"
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
    open(id) {
      this.$prompt("请输入描述", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /[\u4e00-\u9fa5_a-zA-Z0-9_]{0,30}/,
        inputErrorMessage: "30字以内"
      })
        .then(({ value }) => {
          if (!value) {
            value = "暂无描述，请联系管理员添加";
          }
          const plateDto = {
            id: id,
            depict: value
          };
          updateplate(plateDto).then(res => {
            if (res.data.code === "SUCCESS") {
              this.getplate();
              this.$message({
                type: "success",
                message: "编辑成功!"
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入"
          });
        });
    },
    set(id) {
      this.$prompt("请输入用户名", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /[\u4e00-\u9fa5_a-zA-Z0-9_]{1,8}/,
        inputErrorMessage: "用户名长度不大于8"
      })
        .then(({ value }) => {
          setadmin(id, value).then(res => {
            if (res.data.code === "SUCCESS") {
              this.getplate();
              this.$message({
                type: "success",
                message: "设置成功!"
              });
            }
            if (res.data.code === "ERROR") {
              this.getplate();
              this.$message({
                type: "info",
                message: res.data.message
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入"
          });
        });
    }
  }
};
</script>

<style scoped></style>
