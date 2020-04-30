<template>
  <div class="Platenav" style="border-bottom: 1px solid #eeeeee">
    <div style="font-size: 28px">
      {{ this.plate.name }}
      <span style="float: right"
        ><el-button
          type="danger"
          size="medium"
          v-if="collect == false"
          @click="collectThis"
          >收藏本版
        </el-button></span
      >
      <span style="float: right"
        ><el-button
          type="info"
          size="medium"
          v-if="collect == true"
          @click="removeCollect"
          >已收藏
        </el-button></span
      >
      <span v-if="isadmin"
        ><el-link :underline="false" @click="open">设置</el-link></span
      >
    </div>
    <div>
      <span style="color: gray">帖子:</span>
      <span style="color: crimson">{{ count }}&nbsp;&nbsp;</span>
      <span style="color: gray">收藏:</span>
      <span style="color: crimson">{{ this.plate.tcount }}</span>
    </div>
    <div>
      <span style="color: gray"> 版主:</span
      ><span v-for="admin in admins" :key="admin.id">
        <el-link
          style="margin-left: 10px"
          :href="/user/ + admin.userId"
          :underline="false"
          type="danger"
          >{{ admin.username }}</el-link
        ></span
      >
    </div>
    <div style="color: gray">{{ this.plate.depict }}</div>
  </div>
</template>

<script>
import { getPlateByid, updateplate } from "../../network/plate";
import { getTopicCount } from "../../network/topic";
import { getPlateAdmin, isAdmin } from "../../network/plateadmin";
import {
  collectPlate,
  deleteCollectPlate,
  isCollectPlate
} from "../../network/collect";

export default {
  props: ["plateid"],
  name: "Platenav",
  data() {
    return {
      plate: [],
      count: 0,
      admins: [],
      isadmin: false,
      collect: false
    };
  },
  computed: {},
  created() {
    this.getplate();
    this.getcount();
    this.getadmin();
    this.isCollect();
    if (this.$store.state.role == "ROLE_MANAGER") {
      isAdmin(this.plateid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.isadmin = true;
        }
      });
    }
  },
  methods: {
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
    getcount() {
      getTopicCount(this.plateid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.count = res.data.data;
        }
      });
    },
    getadmin() {
      getPlateAdmin(this.plateid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.admins = res.data.data;
        }
      });
    },
    open() {
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
            id: this.plateid,
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
    removeCollect() {
      deleteCollectPlate(this.$store.state.id, this.plateid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.getcount();
          this.getplate();
          this.isCollect();
        }
      });
    },
    collectThis() {
      const collectDto = {
        userId: this.$store.state.id,
        plateId: this.plateid
      };
      collectPlate(collectDto).then(res => {
        if (res.data.code === "SUCCESS") {
          this.getcount();
          this.getplate();
          this.isCollect();
        }
      });
    },
    isCollect() {
      isCollectPlate(this.$store.state.id, this.plateid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.collect = true;
        }
        if (res.data.code === "ERROR") {
          this.collect = false;
        }
      });
    }
  }
};
</script>

<style scoped></style>
