package org.kirin.facade.response;

import java.io.Serializable;

/**
 * @author Hinsteny
 * @version $ID: CreateUserReq 2019-03-20 16:49 All rights reserved.$
 */
public class UserInfoResp implements Serializable {

    private String username;

    private String password;

    private Boolean sex;

    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
