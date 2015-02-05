package com.hesine.manager.service.impl;

import com.hesine.common.constant.ExceptionType;
import com.hesine.common.constant.ResponseErrorCode;
import com.hesine.framework.model.BaseModel;
import com.hesine.framework.utils.Encodes;
import com.hesine.framework.utils.page.Pager;
import com.hesine.framework.utils.security.Digests;
import com.hesine.manager.dao.UserDao;
import com.hesine.manager.service.UserService;
import com.hesine.manager.utils.MockSession;
import com.hesine.manager.vo.User;
import com.hesine.manager.web.model.UserModel;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import com.hesine.common.exception.AppException;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 13-11-4
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Override
    public User autherization(String token) throws AppException {
        try {
            //TODO 验证用户
            User user = MockSession.map.get(token);
            return user;
        } catch (Exception e) {
            LOGGER.error("验证用户登录数据错误", e);
            throw new AppException("(UserServiceImpl.autherization) exception",
                    ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    @Override
    public User getUserByLoginName(String loginName) throws AppException {

        try {
            //验证用户
            User user = userDao.getUserByLoginName(loginName);
            return user;
        } catch (Exception e) {
            LOGGER.error("通过登录名获取用户", e);
            throw new AppException("(UserServiceImpl.getUserByLoginName) exception",
                    ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    @Override
    public boolean deleteToken(String token) throws AppException {
        try {
            MockSession.map.remove(token);
            return true;
        } catch (Exception e) {
            LOGGER.error("删除token", e);
            throw new AppException("(UserServiceImpl.deleteToken) exception",
                    ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public boolean saveUser(User user) {
        // 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
        if (StringUtils.isNotBlank(user.getPlainPassword())) {
            entryptPassword(user);
        }
        try {
            long id = userDao.save(user);
            return id > 0?true:false;
        } catch (Exception e) {
            LOGGER.error("保存用户数据错误", e);
            throw new AppException("(UserServiceImpl.saveUser) exception",
                    ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }

    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> list(Map<String, Object> searchParams) {
//        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
//        Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);
//        List<User> userList = userDao.findAll(spec);
        try {
            List<User> userList = userDao.list(searchParams);
        return userList;
        } catch (Exception e) {
            LOGGER.error("获取用户列表错误", e);
            throw new AppException("(UserServiceImpl.list) exception",
                    ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Pager<User> pageList(UserModel userModel) {
        try {
            Pager<User> pager = new Pager<User>();
            pager.setPageSize(userModel.getPageSize());
            // 数据统计
            Integer totalCount = userDao.listCount(userModel);
            pager.setTotalCount(totalCount);
            if (pager.getTotalPages() < userModel.getPageNo()){
                userModel.setPageNo(BaseModel.DEFAULT_PAGE_NO);
            }
            pager.setPageNo(userModel.getPageNo());
            // 列表数据
            List<User> list = userDao.pageList(userModel);
            if (list != null && list.size() > 0){
                pager.setResult(list);
            }
            return pager;
        } catch (Exception e) {
            LOGGER.error(" 获取用户分页列表错误", e);
            throw new AppException("(UserServiceImpl.pageList) exception",
                    ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        try {
            return userDao.get(id);
        } catch (Exception e) {
            LOGGER.error("获取用户", e);
            throw new AppException("(UserServiceImpl.getUser) exception",
                    ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(User user) {
        try {
            int result = userDao.update(user);
            boolean updateResult = result > 0;
            return updateResult;
        } catch (Exception e) {
            LOGGER.error("更新用户", e);
            throw new AppException("(UserServiceImpl.update) exception",
                    ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean delete(Long id) {
        try {
            int result = userDao.delete(id);
            boolean deleteResult = result > 0;
            return deleteResult;
        } catch (Exception e) {
            LOGGER.error("删除用户错误", e);
            throw new AppException("(UserServiceImpl.delete) exception",
                    ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean remove(Long id) {
        try {
            int result = userDao.remove(id);
            boolean removeResult = result > 0;
            return removeResult;
        } catch (Exception e) {
            LOGGER.error("删除用户错误", e);
            throw new AppException("(UserServiceImpl.remove) exception",
                    ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
