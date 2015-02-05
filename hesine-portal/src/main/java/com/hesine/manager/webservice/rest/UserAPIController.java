package com.hesine.manager.webservice.rest;

import com.hesine.framework.utils.mapper.BeanMapper;
import com.hesine.manager.service.UserService;
import com.hesine.manager.vo.User;
import com.hesine.manager.webservice.rest.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/user")
public class UserAPIController {
    private final static Logger logger = LoggerFactory.getLogger(UserAPIController.class);

    @Autowired
    private UserService userService;

    /**
     * 基于ContentNegotiationManager,根据URL的后缀渲染不同的格式
     * eg. /api/user/1.xml  返回xml
     * /api/user/1.json 返回json
     * /api/user/1                  返回xml(why?)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getUser(@PathVariable("id") Long id) {
        Assert.notNull(id, "用户id不能为空");
        User user = userService.getUser(id);
        UserDTO dto = null;
        if (user != null) {
            //使用Dozer转换DTO类，并补充Dozer不能自动绑定的属性
            dto = BeanMapper.map(user, UserDTO.class);
        } else {
            dto = new UserDTO();
            logger.error("user is null");
        }
        return dto;
    }


    @RequestMapping(value = "/userjson", method = RequestMethod.POST)
    @ResponseBody
    public UserDTO addPerson(UserDTO userDTO) {
        long id = userDTO.getId();
        User user = userService.getUser(id);
        UserDTO dto = null;
        if (user != null) {
            //使用Dozer转换DTO类，并补充Dozer不能自动绑定的属性
            dto = BeanMapper.map(user, UserDTO.class);
        } else {
            dto = new UserDTO();
            logger.error("user is null");
        }
        return dto;
    }
}
