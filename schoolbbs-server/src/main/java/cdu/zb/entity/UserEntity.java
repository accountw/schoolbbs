package cdu.zb.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author accountw
 * @since 2020-01-09
 */
@TableName("user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别0为男，1为女
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDate birth;

    /**
     * 个性签名
     */
    private String sign;

    /**
     * 经验
     */
    private Integer exp;

    /**
     * 电话
     */

    @JsonIgnore
    private String tel;

    /**
     * 邮箱
     */

    private String mail;

    /**
     * 注册日期
     */

    @JsonIgnore
    private LocalDateTime registerTime;

    /**
     * 头像
     */
    private String head;

    /**
     * 年级
     */

    @JsonIgnore
    private String grade;

    /**
     * 学院
     */

    @JsonIgnore
    private String college;

    /**
     * 专业
     */

    @JsonIgnore
    private String major;

    /**
     * 班号
     */

    @JsonIgnore
    private Integer no;

    /**
     * 注册表
     */

    @JsonIgnore
    private String registerId;

    /**
     * 逻辑删除1为删除
     */

    @JsonIgnore
    @TableLogic
    private Integer logicDeleteFlag;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private Integer count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    @JsonIgnore
    public String getMail() {
        return mail;
    }
    @JsonProperty
    public void setMail(String mail) {
        this.mail = mail;
    }
    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }
    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }
    public Integer getLogicDeleteFlag() {
        return logicDeleteFlag;
    }

    public void setLogicDeleteFlag(Integer logicDeleteFlag) {
        this.logicDeleteFlag = logicDeleteFlag;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", gender=" + gender +
            ", birth=" + birth +
            ", sign=" + sign +
            ", exp=" + exp +
            ", tel=" + tel +
            ", mail=" + mail +
            ", registerTime=" + registerTime +
            ", head=" + head +
            ", grade=" + grade +
            ", college=" + college +
            ", major=" + major +
            ", no=" + no +
            ", registerId=" + registerId +
            ", logicDeleteFlag=" + logicDeleteFlag +
        "}";
    }
}
