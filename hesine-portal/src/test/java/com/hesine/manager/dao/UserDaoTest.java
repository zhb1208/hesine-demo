package com.hesine.manager.dao;

import com.hesine.manager.vo.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 15/2/2
 */
public class UserDaoTest {

    @Mock
    private UserDao userDao;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUser() {
        // 准备返回数据
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setName("Jason");
        user.setLoginName("jason");

        // 设置期望
        Mockito.when(userDao.get(id)).thenReturn(user);

        // 验证想要的结果
        assertEquals(userDao.get(id), user);
    }
}
