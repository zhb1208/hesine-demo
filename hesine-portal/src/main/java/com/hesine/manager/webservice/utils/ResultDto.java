package com.hesine.manager.webservice.utils;

import com.hesine.framework.utils.page.Pager;
import com.hesine.manager.vo.User;
import com.hesine.manager.webservice.controller.dto.UserDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Map;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 14-12-9
 */
@XmlRootElement(name = "ResultDto")
public class ResultDto<T> implements Serializable {
    private int status = StatusCode.STATUS_SUCCESS; //执行状态：0 成功 1 失败
    private int errorCode; //错误码
    private String errorMessage; //错误消息
    private T t; //泛型对象
    private Pager<T> pager; //分页对象
    private Map map; //键值对对象

    @XmlElements({
            @XmlElement(name = "user", type = User.class),
            @XmlElement(name = "userDto", type = UserDto.class),
    })
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Pager<T> getPager() {
        return pager;
    }

    public void setPager(Pager<T> pager) {
        this.pager = pager;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
