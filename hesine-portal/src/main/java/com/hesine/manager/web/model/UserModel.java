package com.hesine.manager.web.model;

import com.hesine.framework.model.BaseModel;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 13-11-12
 * Time: 上午11:20
 * To change this template use File | Settings | File Templates.
 */
public class UserModel extends BaseModel {
    private String loginName;
    private String plainPassword;
    private String password;
    private String salt;
    private String name;
    private String email;
    private String status;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
