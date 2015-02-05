package com.hesine.manager.webservice.controller;

import com.hesine.framework.utils.mapper.BeanMapper;
import com.hesine.framework.utils.page.Pager;
import com.hesine.manager.service.UserService;
import com.hesine.manager.vo.User;
import com.hesine.manager.web.model.UserModel;
import com.hesine.manager.webservice.controller.dto.UserDto;
import com.hesine.manager.webservice.utils.ResultDto;
import com.hesine.manager.webservice.utils.StatusCode;
import com.hesine.passport.client.utils.MD5;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class UserRestController {

    /**
     * 日志属性
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

    /**
     * 根据id获取用户
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto getUser(HttpServletRequest request, @PathVariable("id") Long id) {
        ResultDto<UserDto> dto = new ResultDto<UserDto>();
        // 验证参数
        if (id == null) {
            dto.setStatus(StatusCode.STATUS_FAIL);
            dto.setErrorCode(StatusCode.ERROR_EMPTY_PARAM);
            return dto;
        }
        try {
            // 设置成功参数
            User userDB = userService.getUser(id);
            UserDto userDto = null;
            if (userDB == null) {
                LOGGER.error("数据库中不存在该用户");
                dto.setStatus(StatusCode.STATUS_FAIL);
                dto.setErrorCode(StatusCode.ERROR_NOT_EXIST);
                return dto;
            } else {
                //使用Dozer转换DTO类，并补充Dozer不能自动绑定的属性
                userDto = BeanMapper.map(userDB, UserDto.class);
                dto.setT(userDto);
            }
            return dto;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            dto.setStatus(StatusCode.STATUS_FAIL);
            dto.setErrorCode(StatusCode.SYSTEM_ERROR);
            return dto;
        }
    }

    /**
     * 获取分页列表
     *
     * @param userModel
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto list(HttpServletRequest request, UserModel userModel) {
        ResultDto<User> dto = new ResultDto<User>();
        userModel.setPageSize(UserModel.DEFAULT_PAGE_SIZE);
        try {
            // 设置成功参数
            if (userModel != null && org.apache.commons.lang.StringUtils.isNotBlank(userModel.getLoginName())) {
                userModel.setLoginName(userModel.getLoginName().trim());
            }
            if (userModel != null && org.apache.commons.lang.StringUtils.isNotBlank(userModel.getEmail())) {
                userModel.setEmail(userModel.getEmail().trim());
            }
            Pager<User> pager = userService.pageList(userModel);
            dto.setPager(pager);
            return dto;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            dto.setStatus(StatusCode.STATUS_FAIL);
            dto.setErrorCode(StatusCode.SYSTEM_ERROR);
            return dto;
        }
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto save(
            HttpServletRequest request,
            User user, String multToken) {

        ResultDto dto = new ResultDto();
        try {
            if (user != null) {
                if (user.getId() != null) {
                    User nowUser = userService.getUser(user.getId());
                    if (StringUtils.isNotBlank(user.getLoginName())) {
                        nowUser.setLoginName(user.getLoginName().trim());
                    } else {
                        nowUser.setLoginName(user.getLoginName().trim());
                    }
                    if (StringUtils.isNotBlank(user.getName())) {
                        nowUser.setName(user.getName().trim());
                    } else {
                        nowUser.setName(user.getName().trim());
                    }
                    if (StringUtils.isNotBlank(user.getPassword())) {
                        nowUser.setPassword(DigestUtils.md5Hex((user.getPassword().trim())));
                    } else {
                        nowUser.setPassword(DigestUtils.md5Hex((user.getPassword().trim())));
                    }
                    if (StringUtils.isNotBlank(user.getEmail())) {
                        nowUser.setEmail(user.getEmail().trim());
                    } else {
                        nowUser.setEmail(user.getEmail().trim());
                    }
                    userService.update(nowUser);
                } else {
                    if (StringUtils.isNotBlank(user.getLoginName())) {
                        user.setLoginName(user.getLoginName().trim());
                    }
                    if (StringUtils.isNotBlank(user.getName())) {
                        user.setName(user.getName().trim());
                    }
                    if (StringUtils.isNotBlank(user.getPassword())) {
                        user.setPassword(MD5.encodeString(user.getPassword().trim()));
                    }
                    if (StringUtils.isNotBlank(user.getEmail())) {
                        user.setEmail(user.getEmail().trim());
                    }
                    user.setSalt("1");
                    user.setStatus("1");
                    userService.saveUser(user);
                }
            } else {
                LOGGER.error("用户不能为空");
                dto.setStatus(StatusCode.STATUS_FAIL);
                dto.setErrorCode(StatusCode.ERROR_NOT_EXIST);
                return dto;
            }

            return dto;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            dto.setStatus(StatusCode.STATUS_FAIL);
            dto.setErrorCode(StatusCode.SYSTEM_ERROR);
            return dto;
        }
    }

    /**
     * 删除用户
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto remove(
            HttpServletRequest request,
            @PathVariable("id") Long id) {
        ResultDto dto = new ResultDto();
        // 验证参数
        if (id == null) {
            dto.setStatus(StatusCode.STATUS_FAIL);
            dto.setErrorCode(StatusCode.ERROR_EMPTY_PARAM);
            return dto;
        }
        try {
            // 设置成功参数
            boolean result = userService.remove(id);
            if (!result) {
                LOGGER.error("删除不成功");
                dto.setStatus(StatusCode.STATUS_FAIL);
                dto.setErrorCode(StatusCode.ERROR_NOT_EXIST);
                return dto;
            }
            return dto;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            dto.setStatus(StatusCode.STATUS_FAIL);
            dto.setErrorCode(StatusCode.SYSTEM_ERROR);
            return dto;
        }
    }

}
