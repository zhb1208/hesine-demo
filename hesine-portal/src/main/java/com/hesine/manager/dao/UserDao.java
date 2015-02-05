package com.hesine.manager.dao;

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
public interface UserDao {

    /**
     * 根据id获得用户
     * @param id
     * @return
     */
    public User get(@Param("id") Long id);

    /**
     * 获取用户
     * @param loginName
     * @return
     */
    public User getUserByLoginName(@Param("loginName") String loginName);

    /**
     * 列表
     * @param parameters
     * @return
     */
    public List<User> list(Map<String, Object> parameters);

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
     * 保存
     * @param user
     */
    public long save(User user);

    /**
     * 更新
     * @param user
     * @return
     */
    public int update(User user);

    /**
     * 物理删除
     * @param id
     * @return
     */
    public int delete(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public int remove(Long id);
}
