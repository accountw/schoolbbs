package cdu.zb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author accountw
 * @since 2020-01-09
 */
@TableName("graduate_register")
public class GraduateRegisterEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 生日
     */
    private LocalDate birth;

    /**
     * 0为男，1为女
     */
    private Integer gender;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String emil;

    /**
     * 入学时间
     */
    private LocalDate reportTime;

    /**
     * 毕业时间
     */
    private LocalDate graduateTime;

    /**
     * 学院
     */
    private String college;

    /**
     * 专业
     */
    private String major;

    /**
     * 班号
     */
    private Integer num;

    /**
     * 申请时间
     */
    private LocalDateTime askTime;

    /**
     * 0为通过,1为不通过
     */
    private Integer status;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil;
    }
    public LocalDate getReportTime() {
        return reportTime;
    }

    public void setReportTime(LocalDate reportTime) {
        this.reportTime = reportTime;
    }
    public LocalDate getGraduateTime() {
        return graduateTime;
    }

    public void setGraduateTime(LocalDate graduateTime) {
        this.graduateTime = graduateTime;
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
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    public LocalDateTime getAskTime() {
        return askTime;
    }

    public void setAskTime(LocalDateTime askTime) {
        this.askTime = askTime;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GraduateRegisterEntity{" +
            "id=" + id +
            ", username=" + username +
            ", name=" + name +
            ", birth=" + birth +
            ", gender=" + gender +
            ", password=" + password +
            ", emil=" + emil +
            ", reportTime=" + reportTime +
            ", graduateTime=" + graduateTime +
            ", college=" + college +
            ", major=" + major +
            ", num=" + num +
            ", askTime=" + askTime +
            ", status=" + status +
        "}";
    }
}
