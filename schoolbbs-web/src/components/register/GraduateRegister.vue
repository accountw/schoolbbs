<template>
  <div class="GraduateRegister">
    <span
      >此注册方法适用于没有校园邮箱的已毕业的用户，提交申请后，管理员审核通过会通过邮箱提醒注册成功</span
    >
    <el-divider></el-divider>
    <el-form
      ref="form"
      :model="form"
      label-width="100px"
      label-position="left"
      :rules="rules"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="form.password"
          type="password"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmpd">
        <el-input type="password" v-model="form.confirmpd"></el-input>
      </el-form-item>
      <el-form-item label="生日" prop="birth">
        <el-date-picker v-model="form.birth" type="date" placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-radio-group v-model="form.gender">
          <el-radio label="0">男</el-radio>
          <el-radio label="1">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="邮箱" prop="mail">
        <el-input v-model="form.mail"></el-input>
      </el-form-item>
      <el-form-item label="验证码" v-if="form.mail" prop="code">
        <el-input v-model="form.code" id="code"></el-input>
        <el-button type="primary" @click="sendcode" v-if="show"
          >发送验证码</el-button
        >
        <el-button type="primary" disabled v-if="!show" style="width: 80px"
          >{{ num }}s</el-button
        >
      </el-form-item>
      <el-form-item label="年级" prop="grade">
        <el-input v-model="form.grade"></el-input>
      </el-form-item>
      <el-form-item label="学院" prop="college">
        <el-input v-model="form.college"></el-input>
      </el-form-item>
      <el-form-item label="专业" prop="major">
        <el-input v-model="form.major"></el-input>
      </el-form-item>
      <el-form-item label="班号" prop="num">
        <el-input v-model="form.num"></el-input>
      </el-form-item>
      <el-form-item label="学号" prop="studentNo">
        <el-input v-model="form.studentNo"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="sumbit('form')">立即申请</el-button>
        <el-button @click="rset('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { studentregister } from "../../network/register";
import { sendMessage } from "../../network/register";
import { selectMail } from "../../network/register";
import { selectName } from "../../network/register";
import { selectCode } from "../../network/register";

export default {
  name: "GraduateRegister",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.form.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    var validateCode = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入验证码"));
      } else {
        if (value !== "") {
          const data = {
            userMail: this.form.mail,
            code: this.form.code
          };
          selectCode(data)
            .then(response => {
              if (response.data.code === "ERROR") {
                callback(new Error("验证码错误"));
              } else {
                callback();
              }
            })
            .catch(function(error) {
              // 请求失败处理
              console.log(error);
            });
        }
      }
    };
    var validateMail = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请填写邮箱"));
      } else {
        if (value !== "") {
          const reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
          if (!reg.test(value)) {
            callback(new Error("请输入有效的邮箱"));
            this.confirm = false;
          } else {
            selectMail(value)
              .then(response => {
                if (response.data.code === "ERROR") {
                  callback(new Error("该邮箱已被注册"));
                  this.confirm = false;
                } else {
                  this.confirm = true;
                  callback();
                }
              })
              .catch(function(error) {
                // 请求失败处理
                console.log(error);
              });
          }
        }
      }
    };
    var validateUsername = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入用户名"));
      } else {
        const reg = /^.{1,8}$/;
        if (!reg.test(value)) {
          callback(new Error("用户名长度小于8"));
        } else {
          selectName(value)
            .then(response => {
              if (response.data.code === "ERROR") {
                callback(new Error("该用户名已被注册"));
              } else {
                callback();
              }
            })
            .catch(function(error) {
              // 请求失败处理
              console.log(error);
            });
        }
      }
    };

    var validatepd = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        const reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{1,16}$/;
        if (!reg.test(value)) {
          callback(new Error("密码必须包括字母和数字，长度为6-16"));
        } else {
          callback();
        }
      }
    };

    var validateGender = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("性别不能为空"));
      } else {
        callback();
      }
    };
    var validateName = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("姓名不能为空"));
      } else {
        callback();
      }
    };
    var validateBirth = (rule, value, callback) => {
      if (value === "" || value === null) {
        callback(new Error("生日不能为空"));
      } else {
        callback();
      }
    };
    var validateReportTime = (rule, value, callback) => {
      if (value === "" || value === null) {
        callback(new Error("年级不能为空"));
      } else {
        callback();
      }
    };
    var validateGraduateTime = (rule, value, callback) => {
      if (value === "" || value === null) {
        callback(new Error("年级不能为空"));
      } else {
        callback();
      }
    };

    var validateCollege = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("学院不能为空"));
      } else {
        callback();
      }
    };
    var validateMajor = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("专业不能为空"));
      } else {
        callback();
      }
    };
    var validateNum = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("班号不能为空"));
      } else {
        callback();
      }
    };
    var validateStudentNo = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("学号不能为空"));
      } else {
        callback();
      }
    };

    return {
      confirm: false,
      show: true,
      num: "0",
      timer: null,
      form: {
        code: "",
        confirmpd: "",
        username: "",
        name: "",
        birth: "",
        password: "",
        gender: "0",
        mail: "",
        reportTime: "",
        graduateTime: "",
        college: "",
        major: "",
        num: "",
        studentNo: ""
      },
      rules: {
        code: [{ validator: validateCode, trigger: "blur" }],
        confirmpd: [{ validator: validatePass, trigger: "blur" }],
        username: [{ validator: validateUsername, trigger: "blur" }],
        name: [{ validator: validateName, trigger: "blur" }],
        birth: [{ validator: validateBirth, trigger: "blur" }],
        gender: [{ validator: validateGender, trigger: "blur" }],
        password: [{ validator: validatepd, trigger: "blur" }],
        mail: [{ validator: validateMail, trigger: "blur" }],
        reportTime: [{ validator: validateReportTime, trigger: "blur" }],
        graduateTime: [{ validator: validateGraduateTime, trigger: "blur" }],
        college: [{ validator: validateCollege, trigger: "blur" }],
        major: [{ validator: validateMajor, trigger: "blur" }],
        num: [{ validator: validateNum, trigger: "blur" }],
        studentNo: [{ validator: validateStudentNo, trigger: "blur" }]
      }
    };
  },
  methods: {
    sumbit(form) {
      //提交表单内容
      this.$refs[form].validate(valid => {
        if (valid) {
          const schoolRegisterDto = {
            username: this.form.username,
            password: this.form.password,
            gender: this.form.gender,
            mail: this.form.mail,
            name: this.form.name,
            birth: this.form.birth,
            reportTime: this.form.reportTime,
            graduateTime: this.form.graduateTime,
            college: this.form.college,
            major: this.form.major,
            num: this.form.num,
            studentNo: this.form.studentNo
          };
          studentregister(schoolRegisterDto)
            .then(response => {
              if (response.data.code === "SUCCESS") {
                this.$message({
                  showClose: true,
                  message: "提交成功，跳转到首页",
                  type: "success"
                });
                this.$router.push("/");
              } else {
                this.$message({
                  showClose: true,
                  message: response.data.message,
                  type: "error"
                });
              }
            })
            .catch(error => {
              // 请求失败处理
              console.log(error);
            });
        } else {
          this.$message({
            showClose: true,
            message: "请认真填写注册信息",
            type: "error"
          });
          return false;
        }
      });
    },
    rset() {
      //重置表单
      this.$refs["form"].resetFields();
      this.form.confirmpd = "";
      this.form.code = "";
    },
    sendcode() {
      //发送验证码
      if (this.confirm === true) {
        sendMessage(this.form.mail)
          .then(response => {
            if (response.data.code === "SUCCESS") {
              this.show = false;
              const TIME_COUNT = 60;
              if (!this.timer) {
                this.num = TIME_COUNT;
                this.timer = setInterval(() => {
                  if (this.num > 0 && this.num <= TIME_COUNT) {
                    this.num--;
                  } else {
                    this.show = true;
                    clearInterval(this.timer);
                    this.timer = null;
                  }
                }, 1000);
              }
            }
          })
          .catch(function(error) {
            // 请求失败处理
            console.log(error);
          });
      } else {
        this.$message({
          showClose: true,
          message: "请填写正确的邮箱",
          type: "error"
        });
      }
    }
  }
};
</script>

<style scoped>
.GraduateRegister {
  width: 60%;
  margin: 0 auto;
}

.el-input {
  width: 300px !important;
}
</style>
