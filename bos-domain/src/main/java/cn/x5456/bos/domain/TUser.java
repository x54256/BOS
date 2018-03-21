package cn.x5456.bos.domain;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体
 */

public class TUser implements java.io.Serializable {

    // Fields

    private String id;
    private String username;
    private String password;
    private Double salary;
    private Date birthday;
    private String gender;
    private String station;
    private String telephone;
    private String remark;
    private Set noticebills = new HashSet(0);
    private Set<Role> roles = new HashSet(0);

    public String getBirthdayString() {
        if (birthday != null) {
            String format = new SimpleDateFormat("yyyy-MM-dd").format(birthday);
            return format;
        }
        return "暂无数据";
    }

    public String getRoleName() {
        String roleName = "";
        for (Role r : roles) {
            String name = r.getName();
            roleName += name + "  ";
        }
        return roleName;
    }

    // Constructors

    /**
     * default constructor
     */
    public TUser() {
    }

    /**
     * minimal constructor
     */
    public TUser(String id) {
        this.id = id;
    }

    /**
     * full constructor
     */
    public TUser(String id, String username, String password, Double salary,
                 Date birthday, String gender, String station, String telephone,
                 String remark, Set noticebills, Set roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salary = salary;
        this.birthday = birthday;
        this.gender = gender;
        this.station = station;
        this.telephone = telephone;
        this.remark = remark;
        this.noticebills = noticebills;
        this.roles = roles;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStation() {
        return this.station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set getNoticebills() {
        return this.noticebills;
    }

    public void setNoticebills(Set noticebills) {
        this.noticebills = noticebills;
    }

    public Set getRoles() {
        return this.roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }

}
/*
{"currentPage":1,"detachedCriteria":{"alias":"this"},"pageSize":10,"rows":[{"birthday":{"date":20,"day":2,"hours":0,"minutes":0,"month":2,"nanos":0,"seconds":0,"time":1521475200000,"timezoneOffset":-480,"year":118},"birthdayString":"2018-03-20","gender":"1","id":"1","password":"a89b71bb5227c75d463dd82a03115738","remark":"","roleName":"","salary":0,"station":"","telephone":"","username":"admin"},{"birthday":{"date":20,"day":2,"hours":0,"minutes":0,"month":2,"nanos":0,"seconds":0,"time":1521475200000,"timezoneOffset":-480,"year":118},"birthdayString":"2018-03-20","gender":"1","id":"2","password":"a89b71bb5227c75d463dd82a03115738","remark":"","roleName":"","salary":0,"station":"","telephone":"","username":"x5456"},{"birthday":{"date":26,"day":1,"hours":0,"minutes":0,"month":1,"nanos":0,"seconds":0,"time":1519574400000,"timezoneOffset":-480,"year":118},"birthdayString":"2018-02-26","gender":"男","id":"ff80818162432f2b01624334ade60000","password":"a89b71bb5227c75d463dd82a03115738","remark":"","roleName":"管理员权限  普通用户  ","salary":1000,"station":"分公司","telephone":"18468118715","username":"xxx"}],"total":3}
 */