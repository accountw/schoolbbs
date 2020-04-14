<template>
  <div class="UserIndex">
    <el-card body-style="padding:0">
      <el-container>
        <el-header
          style="height: 100%;background: url(/background/2.png);padding-top: 10px;padding-bottom: 10px"
        >
          <el-container>
            <el-aside width="135px"
              ><div class="head"><img :src="user.head" id="image" /></div
            ></el-aside>
            <el-main>
              <div style="font-size: 20px">
                {{ user.username }}
                <el-link
                  type="danger"
                  :underline="false"
                  :href="/userset/ + this.userid"
                  v-if="self"
                  >个人设置</el-link
                >
                <el-link
                  type="danger"
                  :underline="false"
                  v-if="isadmin"
                  @click="dialogVisible = true"
                  >封禁该用户</el-link
                >
                <el-dialog
                  title="封禁"
                  :visible.sync="dialogVisible"
                  width="30%"
                >
                  <el-form label-width="80px">
                    <el-form-item label="封禁时间">
                      <el-radio-group v-model="date">
                        <el-radio :label="1">1天</el-radio>
                        <el-radio :label="3">3天</el-radio>
                        <el-radio :label="7">7天</el-radio>
                      </el-radio-group>
                    </el-form-item>
                    <el-form-item label="封禁理由">
                      <el-radio-group v-model="reason">
                        <el-radio label="有不当的发言">有不当的发言</el-radio>
                        <el-radio label="恶意水贴">恶意水贴</el-radio>
                        <el-radio label="有攻击性或侮辱性发言"
                          >有攻击性或侮辱性发言</el-radio
                        >
                        <el-radio label="违反了论坛规则"
                          >违反了论坛规则</el-radio
                        >
                      </el-radio-group>
                    </el-form-item>
                  </el-form>
                  <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="ban">确 定</el-button>
                  </span>
                </el-dialog>
              </div>

              <br />
              <div style="font-size: 13px">{{ user.sign }}</div>
              <div>
                <span>帖子:{{ user.count }} </span> <span>粉丝:5465</span>
              </div></el-main
            >
          </el-container>
        </el-header>
        <el-main style="padding-top: 5px"
          ><template>
            <el-tabs v-model="activeName">
              <el-tab-pane label="帖子" name="first"
                ><UserTopic
                  v-bind:userid="userid"
                  v-bind:count="user.count"
                  @update="getuser"
                ></UserTopic
              ></el-tab-pane>
              <el-tab-pane label="收藏板块" name="second">配置管理</el-tab-pane>
              <el-tab-pane label="圈子" name="third">配置管理</el-tab-pane>
            </el-tabs>
          </template></el-main
        >
      </el-container>
    </el-card>
  </div>
</template>

<script>
import { getUserByid } from "../../network/User";
import UserTopic from "./UserTopic";
import { addBan } from "../../network/ban";

export default {
  components: {
    UserTopic
  },
  name: "UserIndex",
  data() {
    return {
      user: [],
      userid: this.$route.params.userid,
      activeName: "first",
      dialogVisible: false,
      reason: "有不当的发言",
      date: 1
    };
  },
  created() {
    this.getuser();
  },
  computed: {
    self() {
      if (localStorage.getItem("id") == this.userid) {
        return true;
      }
      return false;
    },
    isadmin() {
      if (
        localStorage.getItem("role") == "ROLE_MANAGER" &&
        localStorage.getItem("id") != this.userid
      ) {
        return true;
      }
      return false;
    }
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
    ban() {
      const bandto = {
        uid: this.userid,
        dealer: localStorage.getItem("id"),
        reason: this.reason,
        date: this.date
      };
      addBan(bandto).then(res => {
        this.dialogVisible = false;
        if (res.data.code === "SUCCESS") {
          this.$message({
            message: "提交成功",
            type: "success"
          });
        }
        if (res.data.code === "ERROR") {
          this.$message({
            message: "该用户已被封禁",
            type: "error"
          });
        }
      });
    }
  }
};
</script>

<style scoped>
.head {
  margin-top: 5px;
}
#image {
  width: 120px;
  height: 120px;
  border: 2px solid #dcdfe6;
  padding: 1px;
}
</style>
