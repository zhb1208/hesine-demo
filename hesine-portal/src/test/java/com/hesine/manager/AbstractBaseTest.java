package com.hesine.manager;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * junit4&spring-test使用基类.
 *
 * @author zhanghongbing
 * @version 14-11-17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/spring-config-test.xml")
public abstract class AbstractBaseTest {
}
