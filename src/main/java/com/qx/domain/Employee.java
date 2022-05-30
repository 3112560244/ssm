package com.qx.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Timestamp;
import java.util.Date;


public class Employee {
    @NotBlank(message = "员工编号不能为空！")
    private String id;//员工编号
    @NotBlank(message = "员工姓名不能为空！")
    private String name;//员工姓名
    private boolean gender;//员工性别
    @NotNull(message = "日期不能为空！")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "员工的入职日期必须是一个过去的时间")
    private Date hireDate;//入职日期
    @NotNull(message = "工资不能为空！")
    @Min(value = 2000,message = "员工的最低工资为2000")
    private Integer salary;//员工工资
    private Department dept;//员工所属的部门
    private String img;//员工所属的部门
    private Timestamp update_date;
    private Timestamp create_date;

    public Timestamp getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Timestamp update_date) {
        this.update_date = update_date;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    private MultipartFile multipartFile;
    //提供对应的setter/getter方法


    public Employee() {
    }

    public Employee(@NotBlank(message = "员工编号不能为空！") String id, @NotBlank(message = "员工姓名不能为空！") String name, boolean gender, @NotNull(message = "日期不能为空！") @Past(message = "员工的入职日期必须是一个过去的时间") Date hireDate, @NotNull(message = "工资不能为空！") @Min(value = 2000, message = "员工的最低工资为2000") Integer salary, Department dept, String img, Timestamp update_date, Timestamp create_date, MultipartFile multipartFile) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.hireDate = hireDate;
        this.salary = salary;
        this.dept = dept;
        this.img = img;
        this.update_date = update_date;
        this.create_date = create_date;
        this.multipartFile = multipartFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

