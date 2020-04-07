<template>
  <div class="User">
    <div class="main">
      <div class="head">
        <img :src="user.head" id="image" />
      </div>
      <div>
        <div style="display: inline">{{ user.username }}</div>
        <div style="display: inline" @click="Loginout()">
          <el-link type="primary"> [注销]</el-link>
        </div>
      </div>
      <div>会员等级:{{ this.level }}级</div>

      <div>
        <div>
          <el-progress
            v-if="getpercentage"
            :percentage="getpercentage"
            :format="format"
            :text-inside="true"
            :stroke-width="18"
          ></el-progress>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getUser } from "../../network/User";
import { mapMutations } from "vuex";
import { out } from "../../network/login";

export default {
  name: "User",
  data() {
    return {
      user: "",
      level: 1,
      per: ""
    };
  },
  computed: {
    getpercentage() {
      switch (this.level) {
        case 1:
          return (parseInt(this.user.exp) / 2) * 100;
        case 2:
          return (parseInt(this.user.exp) / 5) * 100;
        case 3:
          return (parseInt(this.user.exp) / 15).toFixed(2) * 100;
        case 4:
          return (parseInt(this.user.exp) / 30) * 100;
        case 5:
          return (parseInt(this.user.exp) / 50) * 100;
        case 6:
          return (parseInt(this.user.exp) / 100) * 100;
        case 7:
          return (parseInt(this.user.exp) / 200) * 100;
        case 8:
          return (parseInt(this.user.exp) / 500) * 100;
        case 9:
          return (parseInt(this.user.exp) / 1000) * 100;
        case 10:
          return (parseInt(this.user.exp) / 2000) * 100;
        case 11:
          return (parseInt(this.user.exp) / 3000) * 100;
        case 12:
          return (parseInt(this.user.exp) / 6000) * 100;
        case 13:
          return (parseInt(this.user.exp) / 10000) * 100;
        case 14:
          return (parseInt(this.user.exp) / 18000) * 100;
        case 15:
          return (parseInt(this.user.exp) / 30000) * 100;
        case 16:
          return (parseInt(this.user.exp) / 60000) * 100;
        case 17:
          return (parseInt(this.user.exp) / 100000) * 100;
        case 18:
          return (parseInt(this.user.exp) / 300000) * 100;
        case 19:
          return (parseInt(this.user.exp) / 600000) * 100;
        case 20:
          return (parseInt(this.user.exp) / 1000000) * 100;
        default:
          return 0;
      }
    }
  },
  created() {
    this.getuser();
  },
  methods: {
    format(percentage) {
      return percentage === 100 ? "满" : `EXP:${this.user.exp}/${this.per}`;
    },
    ...mapMutations(["logout", "setid"]),
    getuser() {
      getUser(this.$store.state.username)
        .then(response => {
          this.user = response.data.data;
          if (this.user.exp < 2) {
            this.per = 2;
            this.level = 1;
          } else if (this.user.exp < 5) {
            this.per = 5;
            this.level = 2;
          } else if (this.user.exp < 15) {
            this.per = 15;
            this.level = 3;
          } else if (this.user.exp < 30) {
            this.per = 30;
            this.level = 4;
          } else if (this.user.exp < 50) {
            this.per = 50;
            this.level = 5;
          } else if (this.user.exp < 100) {
            this.per = 100;
            this.level = 6;
          } else if (this.user.exp < 200) {
            this.per = 200;
            this.level = 7;
          } else if (this.user.exp < 500) {
            this.per = 500;
            this.level = 8;
          } else if (this.user.exp < 1000) {
            this.per = 1000;
            this.level = 9;
          } else if (this.user.exp < 2000) {
            this.per = 2000;
            this.level = 10;
          } else if (this.user.exp < 3000) {
            this.per = 3000;
            this.level = 11;
          } else if (this.user.exp < 6000) {
            this.per = 6000;
            this.level = 12;
          } else if (this.user.exp < 10000) {
            this.per = 10000;
            this.level = 13;
          } else if (this.user.exp < 18000) {
            this.per = 18000;
            this.level = 14;
          } else if (this.user.exp < 30000) {
            this.per = 30000;
            this.level = 15;
          } else if (this.user.exp < 60000) {
            this.per = 60000;
            this.level = 16;
          } else if (this.user.exp < 100000) {
            this.per = 100000;
            this.level = 17;
          } else if (this.user.exp < 300000) {
            this.per = 300000;
            this.level = 18;
          } else if (this.user.exp < 600000) {
            this.per = 600000;
            this.level = 19;
          } else if (this.user.exp < 1000000) {
            this.per = 1000000;
            this.level = 20;
          }
          if (!this.$store.state.id) {
            this.setid(this.user.id);
          }
        })
        .catch(function(error) {
          // 请求失败处理
          console.log(error);
        });
    },
    Loginout() {
      out()
        .then(res => {
          if (res.data.code === "SUCCESS") {
            this.$message({
              showClose: true,
              message: "注销成功",
              type: "success"
            });
            this.logout();
          }
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style scoped>
.User {
  background-color: white;
}
#image {
  width: 130px;
  height: 130px;
  border-radius: 50%;
  background-color: aliceblue;
}
.main {
  width: 170px;
  text-align: center;
  margin: 0 auto;
}
.head {
  height: 140px;
}
</style>
