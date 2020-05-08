<template>
  <div class="AddPlate">
    <el-card>
      <el-form label-width="80px">
        <el-form-item label="模块" style="width:300px">
          <el-select v-model="blockid" placeholder="请选择">
            <el-option
              v-for="block in blocks"
              :key="block.id"
              :label="block.name"
              :value="block.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="板块名" style="width:300px">
          <el-input v-model="name" placeholder="请输入内容"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">确定</el-button>
        </el-form-item></el-form
      >
    </el-card>
  </div>
</template>

<script>
import { getblock } from "../../network/block";
import { addplate } from "../../network/plate";

export default {
  name: "AddPlate",
  data() {
    return {
      blocks: [],
      blockid: "",
      name: ""
    };
  },
  created() {
    this.blocklist();
  },
  methods: {
    blocklist() {
      getblock().then(res => {
        if (res.data.code === "SUCCESS") {
          this.blocks = res.data.data;
        }
      });
    },
    submitForm() {
      if (this.name) {
        const plateDto = {
          name: this.name,
          blockId: this.blockid
        };
        addplate(plateDto).then(res => {
          if (res.data.code === "SUCCESS") {
            this.blockid = "";
            this.name = "";
            this.$message({
              type: "success",
              message: "设置成功!"
            });
          }
        });
      } else {
        this.$message({
          type: "error",
          message: "请按要求提交!"
        });
      }
    }
  }
};
</script>

<style scoped></style>
