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
                &nbsp;
                <el-link
                  type="danger"
                  :underline="false"
                  v-if="isfocus && !self"
                  @click="focus"
                  >关注</el-link
                >
                <el-link
                  type="danger"
                  :underline="false"
                  v-if="!isfocus && !self"
                  @click="removefocus"
                  >取消关注</el-link
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

              <i
                class="el-icon-male"
                style="color: blue"
                v-if="user.gender == 0"
              ></i>
              <i
                class="el-icon-female"
                style="color: deeppink"
                v-if="user.gender == 1"
              ></i>
              <div style="font-size: 13px">{{ user.sign }}</div>
              <div>
                <span>帖子:{{ user.count }} </span>
                <span>粉丝:{{ this.count }}</span>
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
              <el-tab-pane label="圈子" name="second">配置管理</el-tab-pane>
              <el-tab-pane label="关注/粉丝" name="third"
                ><div>
                  <div style="color: hotpink">关注:</div>
                  <span v-for="user in user1" :key="user.id">
                    <el-tooltip
                      class="item"
                      effect="light"
                      :content="user.username"
                      placement="bottom-start"
                      :enterable="false"
                    >
                      <el-link :underline="false" :href="/user/ + user.id">
                        <img
                          :src="user.head"
                          style="width: 80px;height: 80px;border: 2px solid #dcdfe6;margin-right: 4px"
                        />
                      </el-link> </el-tooltip
                  ></span>
                </div>
                <el-divider></el-divider>
                <div>
                  <div style="color: hotpink">粉丝:</div>
                  <span v-for="user in user2" :key="user.id">
                    <el-tooltip
                      class="item"
                      effect="light"
                      :content="user.username"
                      placement="bottom-start"
                      :enterable="false"
                    >
                      <el-link :underline="false" :href="/user/ + user.id">
                        <img
                          :src="user.head"
                          style="width: 80px;height: 80px;border: 2px solid #dcdfe6;margin-right: 4px"
                        />
                      </el-link>
                    </el-tooltip>
                  </span></div
              ></el-tab-pane>
            </el-tabs> </template
        ></el-main>
      </el-container>
    </el-card>
  </div>
</template>

<script>
import { getFansList, getFocusList, getUserByid } from "../../network/User";
import UserTopic from "./UserTopic";
import { addBan } from "../../network/ban";
import { addFocus, deleteFocus, getcount, isFocus } from "../../network/focus";

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
      date: 1,
      count: 0,
      f: true,
      user1: [],
      user2: []
    };
  },
  created() {
    this.getuser();
    this.getnum();
    this.isfocu();
  },
  computed: {
    self() {
      if (localStorage.getItem("id") == this.userid) {
        return true;
      }
      return false;
    },
    isfocus() {
      if (this.f) {
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
  watch: {
    activeName: function(val) {
      //监听切换状态-计划单
      if (val === "third") {
        this.getfanslist();
        this.getfouslist();
      }
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
    isfocu() {
      isFocus(localStorage.getItem("id"), this.userid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.f = false;
        }
        if (res.data.code === "ERROR") {
          this.f = true;
        }
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
    },
    getnum() {
      getcount(this.userid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.count = res.data.data;
        }
      });
    },
    focus() {
      const focusDto = {
        userId: this.$store.state.id,
        focusUserId: this.userid
      };
      addFocus(focusDto).then(res => {
        if (res.data.code === "SUCCESS") {
          this.getnum();
          this.isfocu();
        }
        if (res.data.code === "ERROR") {
          this.$message({
            message: "已关注该用户",
            type: "info"
          });
          this.getnum();
          this.isfocu();
        }
      });
    },
    removefocus() {
      deleteFocus(localStorage.getItem("id"), this.userid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.$message({
            message: "已取关该用户",
            type: "success"
          });
          this.getnum();
          this.isfocu();
        }
      });
    },
    getfouslist() {
      getFocusList(this.userid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.user1 = res.data.data;
        }
      });
    },
    getfanslist() {
      getFansList(this.userid).then(res => {
        if (res.data.code === "SUCCESS") {
          this.user2 = res.data.data;
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
