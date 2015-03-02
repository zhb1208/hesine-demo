package com.hesine.manager.dao;

import com.hesine.framework.dao.BaseDao;
import com.hesine.framework.dao.MyBatisRepository;
import com.hesine.manager.vo.User;
import com.hesine.manager.web.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 *
 * @author jason
 */
@MyBatisRepository
@Repository
public interface UserDao extends BaseDao<User> {

    /**
     * 获取用户
     * @param loginName
     * @return
     */
    public User getUserByLoginName(@Param("loginName") String loginName);

    /**
     * 按分页查询数据
     * @param userModel
     * @return
     */
    public List<User> pageList(UserModel userModel);

    /**
     * 查询用户条数
     *
     * @param userModel 查询条件
     * @return 用户条数
     */
    public int listCount(UserModel userModel);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public int remove(Long id);
}
