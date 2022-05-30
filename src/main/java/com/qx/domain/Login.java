package com.qx.domain;/**
 * @Author: ZedQ
 * @Date: 2022/5/30 16:07
 * @Description:
 */

/**
 * @author ZedQ
 * @date 2022年05月30日 16:07 
 * @Description:
 */

public class Login {
    private String name;
    private String pwd;

    public Login() {
    }

    public Login(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
