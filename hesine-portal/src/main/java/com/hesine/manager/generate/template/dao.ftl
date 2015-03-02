/**
 * There are framework code generation
 */
package ${packageName}<#if moduleName!=''>.${moduleName}</#if>.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.hesine.framework.dao.MyBatisRepository;
import ${packageName}<#if moduleName!=''>.${moduleName}</#if>.web.model.${ClassName}Model;
import com.hesine.framework.dao.BaseDao;
import ${packageName}<#if moduleName!=''>.${moduleName}</#if>.vo.${ClassName};

/**
 * ${functionName}Dao接口
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@MyBatisRepository
@Repository
public interface ${ClassName}Dao extends BaseDao<${ClassName}> {

    /**
     * 按分页查询数据
     * @param ${className}Model
     * @return
     */
    public List<${ClassName}> pageList(${ClassName}Model ${className}Model);

    /**
    * 查询数据条数
    *
    * @param ${className}Model 查询条件
    * @return 数据条数
    */
    public int listCount(${ClassName}Model ${className}Model);
	
}
