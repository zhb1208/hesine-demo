/**
 * There are Framework code generation Service
 */
package ${packageName}<#if moduleName!=''>.${moduleName}</#if>.service;

import com.hesine.common.exception.AppException;
import com.hesine.framework.utils.page.Pager;
import ${packageName}<#if moduleName!=''>.${moduleName}</#if>.vo.${ClassName};
import ${packageName}<#if moduleName!=''>.${moduleName}</#if>.web.model.${ClassName}Model;

import java.util.List;
import java.util.Map;

/**
 * ${functionName} Service
 * @author ${classAuthor}
 * @version ${classVersion}
 */
public interface ${ClassName}Service {

    /**
     * 保存${ClassName}
     * @param ${className}
     * @return boolean
     */
    public boolean save(${ClassName} ${className});

    /**
     * 获取${ClassName}
     * @param id
     * @return
     */
    public ${ClassName} get${ClassName}(Long id);

    /**
     * 修改${ClassName}
     * @param ${className}
     * @return boolean
     */
    public boolean update(${ClassName} ${className});

    /**
     * 删除${ClassName}
     * @param id
     * @return boolean
     */
    public boolean delete(Long id);

    /**
    * 查询${ClassName}
    * @param searchParams
    * @return
    */
    public List<${ClassName}> list(Map<String, Object> searchParams);

    /**
    * 分页查询
    * @param ${className}Model
    * @return
    */
    public Pager<${ClassName}> pageList(${ClassName}Model ${className}Model);

}
