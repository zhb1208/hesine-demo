package com.hesine.manager.webservice.controller.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserDto {

    private Long id;
    private String loginName;
    private String name;
    private String email;
    private Long teamId;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        id = value;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String value) {
        loginName = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        email = value;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    /**
     * 重新实现toString()函数方便在日志中打印DTO信息.
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
