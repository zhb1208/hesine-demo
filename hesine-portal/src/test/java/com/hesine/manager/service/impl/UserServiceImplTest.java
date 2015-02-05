package com.hesine.manager.service.impl;

import com.hesine.common.constant.ExceptionType;
import com.hesine.common.constant.ResponseErrorCode;
import com.hesine.common.exception.AppException;
import com.hesine.framework.utils.page.Pager;
import com.hesine.manager.dao.UserDao;
import com.hesine.manager.service.UserService;
import com.hesine.manager.vo.User;
import com.hesine.manager.web.model.UserModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 15/2/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/spring-config-service-test.xml")
public class UserServiceImplTest {

    @InjectMocks
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUser() {
        // 准备返回数据
        Long id = 1L;
        Long id2 = 2L;
        User user = new User();
        user.setId(id);
        user.setName("Jason");
        user.setLoginName("jason");
        user.setEmail("jason@hesine.com");

        User suser = new User();
        suser.setId(id);
        suser.setName("Jason");
        suser.setLoginName("jason");

        // 设置期望
        Mockito.when(userDao.get(id)).thenReturn(user);


        // 验证想要的结果
        User ruser = userService.getUser(id);
        assertEquals(ruser, user);

        // 异常单元测试
        AppException appException = new AppException("(UserServiceImpl.getUser) exception",
                ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        Mockito.when(userDao.get(id2)).thenThrow(appException);

        try {
            userService.getUser(id2);
            fail("should get AppException");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("(UserServiceImpl.getUser) exception"));
        }
    }

    @Test
    public void pageList() {
        Long id = 1L;
        UserModel userModel = new UserModel();
        userModel.setPageNo(1);
        userModel.setPageSize(1);
        List<User> list = new ArrayList<User>();
        User user = new User();
        user.setId(id);
        user.setName("Jason");
        user.setLoginName("jason");
        user.setEmail("jason@hesine.com");
        list.add(user);

        Pager<User> pager = null;
        int total = 100;

        // 正常分页
        // 设置期望
        Mockito.when(userDao.listCount(userModel)).thenReturn(total);
        Mockito.when(userDao.pageList(userModel)).thenReturn(list);
        pager = userService.pageList(userModel);

        assertEquals(pager.getResult().get(0), user);

        // 设置期望
        int pageNo = 101;
        userModel.setPageNo(pageNo);
        Mockito.when(userDao.listCount(userModel)).thenReturn(total);
        Mockito.when(userDao.pageList(userModel)).thenReturn(list);
        pager = userService.pageList(userModel);

        assertEquals(pager.getPageNo(), pageNo);

        // 设置期望
        Mockito.when(userDao.listCount(userModel)).thenReturn(total);
        Mockito.when(userDao.pageList(userModel)).thenReturn(null);
        pager = userService.pageList(userModel);

        assertEquals(pager.getPageNo(), pageNo);

        // 异常单元测试
        AppException appException = new AppException("(UserServiceImpl.pageList) exception",
                ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        Mockito.when(userDao.pageList(userModel)).thenThrow(appException);

        try {
            userService.pageList(userModel);
            fail("should get AppException");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("(UserServiceImpl.pageList) exception"));
        }

    }

    @Test
    public void testSaveUser() {
        // 准备返回数据
        Long id = 1L;
        Long id1 = -2L;
        Long id2 = 3L;
        Long id3 = 4L;

        User user = new User();
        user.setId(id);
        user.setName("Jason");
        user.setLoginName("jason");
        user.setEmail("jason@hesine.com");

        User user1 = new User();
        user1.setId(id1);
        user1.setName("Jason1");
        user1.setLoginName("jason1");
        user1.setEmail("jason1@hesine.com");

        User user2 = new User();
        user2.setId(id2);
        user2.setName("Jason2");
        user2.setLoginName("jason2");
        user2.setEmail("jason2@hesine.com");

        User user3 = new User();
        user3.setId(id3);
        user3.setName("Jason3");
        user3.setLoginName("jason3");
        user3.setEmail("jason3@hesine.com");

        // 验证新增成功
        // 设置期望
        Mockito.when(userDao.save(user)).thenReturn(id);

        // 验证想要的结果
        Boolean aBoolean = userService.saveUser(user);
        assertEquals(aBoolean, Boolean.TRUE);

        // 验证新增失败
        Mockito.when(userDao.save(user1)).thenReturn(id1);
        Boolean bBoolean = userService.saveUser(user1);
        assertEquals(bBoolean, Boolean.FALSE);

        // 异常单元测试
        AppException appException = new AppException("(UserServiceImpl.saveUser) exception",
                ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        Mockito.when(userDao.save(user3)).thenThrow(appException);

        try {
            userService.saveUser(user3);
            fail("should get AppException");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("(UserServiceImpl.saveUser) exception"));
        }
    }

    @Test
    public void testUpdate() {
        // 准备返回数据
        Long id = 1L;
        Long id1 = 2L;
        Long id2 = 3L;
        Long id3 = 4L;
        // 返回值
        int returnId1 = 1;
        int returnId2 = 0;
        int returnId3 = -1;

        User user = new User();
        user.setId(id);
        user.setName("Jason");
        user.setLoginName("jason");
        user.setEmail("jason@hesine.com");

        User user1 = new User();
        user1.setId(id1);
        user1.setName("Jason1");
        user1.setLoginName("jason1");
        user1.setEmail("jason1@hesine.com");

        User user2 = new User();
        user2.setId(id2);
        user2.setName("Jason2");
        user2.setLoginName("jason2");
        user2.setEmail("jason2@hesine.com");

        User user3 = new User();
        user3.setId(id3);
        user3.setName("Jason3");
        user3.setLoginName("jason3");
        user3.setEmail("jason3@hesine.com");

        // 验证更新成功
        // 设置期望
        Mockito.when(userDao.update(user)).thenReturn(returnId1);

        // 验证想要的结果
        Boolean aBoolean = userService.update(user);
        assertEquals(aBoolean, Boolean.TRUE);

        // 验证更新失败
        Mockito.when(userDao.update(user1)).thenReturn(returnId2);
        Boolean bBoolean = userService.update(user1);
        assertEquals(bBoolean, Boolean.FALSE);

        // 验证更新失败
        Mockito.when(userDao.update(user2)).thenReturn(returnId3);
        Boolean cBoolean = userService.update(user2);
        assertEquals(cBoolean, Boolean.FALSE);

        // 异常单元测试
        AppException appException = new AppException("(UserServiceImpl.update) exception",
                ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        Mockito.when(userDao.update(user3)).thenThrow(appException);

        try {
            userService.update(user3);
            fail("should get AppException");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("(UserServiceImpl.update) exception"));
        }
    }

    @Test
    public void testDelete() {
        // 准备返回数据
        Long id = 1L;
        Long id2 = 2L;
        Long id3 = 3L;
        Long id4 = 4L;
        // 返回值
        int returnId1 = 1;
        int returnId2 = 0;
        int returnId3 = -1;

        // 验证删除成功
        // 设置期望
        Mockito.when(userDao.delete(id)).thenReturn(returnId1);

        // 验证想要的结果
        Boolean aBoolean = userService.delete(id);
        assertEquals(aBoolean, Boolean.TRUE);

        // 验证删除失败
        Mockito.when(userDao.delete(id2)).thenReturn(returnId2);
        Boolean bBoolean = userService.delete(id2);
        assertEquals(bBoolean, Boolean.FALSE);

        // 验证删除失败
        Mockito.when(userDao.delete(id3)).thenReturn(returnId3);
        Boolean cBoolean = userService.delete(id3);
        assertEquals(cBoolean, Boolean.FALSE);

        // 异常单元测试
        AppException appException = new AppException("(UserServiceImpl.delete) exception",
                ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        Mockito.when(userDao.delete(id4)).thenThrow(appException);

        try {
            userService.delete(id4);
            fail("should get AppException");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("(UserServiceImpl.delete) exception"));
        }
    }

    @Test
    public void testRemove() {
        // 准备返回数据
        Long id = 1L;
        Long id2 = 2L;
        Long id3 = 3L;
        Long id4 = 4L;
        // 返回值
        int returnId1 = 1;
        int returnId2 = 0;
        int returnId3 = -1;

        // 验证删除成功
        // 设置期望
        Mockito.when(userDao.remove(id)).thenReturn(returnId1);

        // 验证想要的结果
        Boolean aBoolean = userService.remove(id);
        assertEquals(aBoolean, Boolean.TRUE);

        // 验证删除失败
        Mockito.when(userDao.remove(id2)).thenReturn(returnId2);
        Boolean bBoolean = userService.remove(id2);
        assertEquals(bBoolean, Boolean.FALSE);

        // 验证删除失败
        Mockito.when(userDao.remove(id3)).thenReturn(returnId3);
        Boolean cBoolean = userService.remove(id3);
        assertEquals(cBoolean, Boolean.FALSE);

        // 异常单元测试
        AppException appException = new AppException("(UserServiceImpl.remove) exception",
                ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        Mockito.when(userDao.remove(id4)).thenThrow(appException);

        try {
            userService.remove(id4);
            fail("should get AppException");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("(UserServiceImpl.remove) exception"));
        }
    }

}
