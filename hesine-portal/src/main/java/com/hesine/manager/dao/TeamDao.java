package com.hesine.manager.dao;

import com.hesine.framework.dao.MyBatisRepository;
import com.hesine.manager.vo.Team;
import org.springframework.stereotype.Repository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 *
 * @author jason
 */
@MyBatisRepository
@Repository
public interface TeamDao {

    Team getWithDetail(Long id);
}
