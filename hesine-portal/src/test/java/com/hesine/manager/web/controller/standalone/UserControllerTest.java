package com.hesine.manager.web.controller.standalone;

import com.hesine.manager.service.UserService;
import com.hesine.manager.vo.User;
import com.hesine.manager.web.controller.UserController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 用户操作测试用例
 *
 * @author zhanghongbing
 * @version 15/2/3
 */
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // 初始化mock对象
        Mockito.reset(userService); // 重置mock对象
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testView() throws Exception {
        // 数据准备
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setName("Jason");
        user.setLoginName("jason");
        user.setEmail("jason@hesine.com");

        // 正常获取用户
        // 期望
        when(userService.getUser(id)).thenReturn(user);

        // 模拟请求
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/portal/user/view/{id}",id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/view"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertNotNull(result.getModelAndView().getModel().get("user"));

        // 获取用户id null
        when(userService.getUser(id)).thenReturn(null);
        // 模拟请求
        result = mockMvc.perform(MockMvcRequestBuilders.get("/portal/user/view/{id}",id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/view"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("user"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    public void testSave() throws Exception {
        Long id = 1L;
        User user = new User();
        user.setName("Jason");
        user.setLoginName("jason");
        user.setEmail("jason@hesine.com");
        // 期望
        when(userService.saveUser(user)).thenReturn(Boolean.TRUE);

        String targetUrl =  "redirect:/portal/user/pagelist";
        String targetAddUrl =  "user/add";
        // 参数缺少
        // 模拟请求
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/portal/user/save",user)
                .param("name",user.getName())
//                .param("loginName",user.getLoginName())
                .param("email",user.getEmail()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(targetAddUrl))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        // 保存成功
        // 模拟请求
        result = mockMvc.perform(MockMvcRequestBuilders.post("/portal/user/save",user)
                .param("name",user.getName())
                .param("loginName",user.getLoginName())
                .param("email",user.getEmail()))
//                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(targetUrl))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testDelete() throws Exception {
        // 数据准备
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setName("Jason");
        user.setLoginName("jason");
        user.setEmail("jason@hesine.com");

        // 正常获取用户
        // 期望
        when(userService.delete(id)).thenReturn(Boolean.TRUE);

        String targetUrl =  "redirect:/portal/user/pagelist";
        // 模拟请求
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/portal/user/delete/{id}",id))
//                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(targetUrl))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    public void testRemove() throws Exception {
        // 数据准备
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setName("Jason");
        user.setLoginName("jason");
        user.setEmail("jason@hesine.com");

        // 正常获取用户
        // 期望
        when(userService.remove(id)).thenReturn(Boolean.TRUE);

        String targetUrl =  "redirect:/portal/user/pagelist";
        // 模拟请求
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/portal/user/remove/{id}",id))
//                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(targetUrl))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

}
