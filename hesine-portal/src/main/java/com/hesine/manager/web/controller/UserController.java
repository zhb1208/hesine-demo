package com.hesine.manager.web.controller;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.hesine.framework.utils.Servlets;
import com.hesine.framework.utils.page.Pager;
import com.hesine.manager.service.UserService;
import com.hesine.manager.utils.Constants;
import com.hesine.manager.utils.CookieUtils;
import com.hesine.manager.vo.User;
import com.hesine.manager.web.model.UserModel;
import com.hesine.passport.client.PassportClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 13-11-4
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/portal/user")
@Component
public class UserController {

    private static Map<String, String> allStatus = Maps.newHashMap();
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    static {
        allStatus.put("enabled", "有效");
        allStatus.put("disabled", "无效");
    }

    @Autowired
    private UserService userService;


    /**
     * 登出: 删除用户SESSION信息，删除Cookie： ticketGrantingTicketId
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        try {
            HttpServletRequest request = servletRequest;
            HttpSession session = request.getSession();
            HttpServletResponse response = servletResponse;

            //对服务器端发出登出指令
            PassportClient client = new PassportClient(request);
            client.logout();

            // 获取当前用户
            String user = (String) session.getAttribute(Constants.HTTP_SESSION_LOGIN_USER);

            //删除本地相关数据
            session.removeAttribute(Constants.HTTP_SESSION_LOGIN_USER);
            session.removeAttribute("com.hesine.passport.receipt");
            session.removeAttribute("com.hesine.passport.user");
            session.removeAttribute("com.hesine.passport.didGateway");
            session.setAttribute("passport_logout", "true");
            request.removeAttribute("ticket");
            session.removeAttribute("loginRedirectUrl");

            //删除所有网站相关Cookie
            Cookie cookies[] = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookietemp = cookies[i];
                    CookieUtils.deleteCookie(response, cookietemp, Constants.DOMAIN);
                }
            }
            logger.info("user {} logout success", user);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return "user/logout";
    }

    /**
     * 获取列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(ServletRequest request) {
        ModelMap model = new ModelMap();
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        System.out.println(searchParams);
        List<User> users = userService.list(searchParams);
        model.addAttribute("users", users);
        return new ModelAndView("user/list", model);
    }

    /**
     * 获取分页列表
     *
     * @param userModel
     * @return
     */
    @RequestMapping(value = "pagelist")
    public ModelAndView list(UserModel userModel) {
        ModelMap model = new ModelMap();
        userModel.setPageSize(UserModel.DEFAULT_PAGE_SIZE);
        if (userModel != null && StringUtils.isNotBlank(userModel.getLoginName())) {
            userModel.setLoginName(userModel.getLoginName().trim());
        }
        if (userModel != null && StringUtils.isNotBlank(userModel.getEmail())) {
            userModel.setEmail(userModel.getEmail().trim());
        }
        Pager<User> pager = userService.pageList(userModel);
        model.addAttribute("pager", pager);
        logger.info(" 获取用户列表成功 ");
        return new ModelAndView("user/pagelist", model);
    }


    @RequestMapping(value = "add")
    public String add() {
        return "user/add";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id) {
        Assert.notNull(id, "用户id不能为空");
        ModelMap model = new ModelMap();
        Gson gson = new Gson();
        if (id != null) {
            User user = userService.getUser(id);
            if (user == null) {
                logger.error("用户不能为空");
            }
            model.addAttribute("user", user);
            logger.info(" 编辑 user {}", gson.toJson(user, User.class));
        } else {
            logger.error("用户id不能为空");
        }
        return new ModelAndView("user/edit", model);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("user") User user, BindingResult result) {
        ModelMap model = new ModelMap();
        Gson gson = new Gson();
        try {
            // jsr303 验证
            if (result.hasErrors()) {//这个是spring validation校验后返回的结果对象
                //如果校验失败，你要做什么事情
                String code = result.getFieldError().getCode();//验证出错的那个类型名称 比如NotEmpty
                result.getFieldError().getDefaultMessage();//出错的信息
                model.addAttribute("user", user);
                logger.info(" 保存用户验证错误 {}", gson.toJson(user, User.class));
                return new ModelAndView("user/add", model);
            } else {
                Assert.notNull(user, "用户不能为空");
                if (user != null) {
                    if (user.getId() != null) {
                        User nowUser = userService.getUser(user.getId());
                        if (StringUtils.isNotBlank(user.getLoginName())) {
                            nowUser.setLoginName(user.getLoginName().trim());
                        } else {
                            nowUser.setLoginName(user.getLoginName());
                        }
                        if (StringUtils.isNotBlank(user.getName())) {
                            nowUser.setName(user.getName().trim());
                        } else {
                            nowUser.setName(user.getName());
                        }
                        if (StringUtils.isNotBlank(user.getPassword())) {
                            nowUser.setPassword(user.getPassword().trim());
                        } else {
                            nowUser.setPassword(user.getPassword());
                        }
                        if (StringUtils.isNotBlank(user.getEmail())) {
                            nowUser.setEmail(user.getEmail().trim());
                        } else {
                            nowUser.setEmail(user.getEmail());
                        }
                        userService.update(nowUser);
                        logger.info(" 更新用户 {}", gson.toJson(nowUser, User.class));
                    } else {
                        user.setSalt("1");
                        user.setStatus("1");
                        userService.saveUser(user);
                        logger.info(" 新增用户 {}", gson.toJson(user, User.class));
                    }
                } else {
                    logger.error("用户不能为空");
                }
                return new ModelAndView("redirect:/portal/user/pagelist", model);
            }
        } catch (Exception e) {
            logger.error("保存用户异常", e);
            return new ModelAndView("user/add", model);
        }
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("id") Long id) {
        Assert.notNull(id, "用户id不能为空");
        ModelMap model = new ModelMap();
        Gson gson = new Gson();
        if (id != null) {
            User user = userService.getUser(id);
            if (user == null) {
                logger.error("用户不能为空");
            }
            model.addAttribute("user", user);
            logger.info(" 查看用户 {}", gson.toJson(user, User.class));
        } else {
            logger.error("用户id不能为空");
        }
        return new ModelAndView("user/view", model);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Long id) {
        Assert.notNull(id, "用户id不能为空");
        ModelMap model = new ModelMap();
        if (id != null) {
            logger.info(" delete id {}", id);
            userService.delete(id);
        } else {
            logger.error("用户id不能为空");
        }
        String targetUrl =  "redirect:/portal/user/pagelist";
        return new ModelAndView(targetUrl);
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public ModelAndView remove(@PathVariable("id") Long id) {
        Assert.notNull(id, "用户id不能为空");
        ModelMap model = new ModelMap();
        if (id != null) {
            logger.info(" remove id {}", id);
            userService.remove(id);
        }
        String targetUrl =  "redirect:/portal/user/pagelist";
        return new ModelAndView(targetUrl);
    }

    /**
     * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
     * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("user", userService.getUser(id));
        }
    }

}
