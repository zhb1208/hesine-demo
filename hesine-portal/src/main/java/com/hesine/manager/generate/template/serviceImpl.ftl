/**
 * There are Framework code generation ServiceImpl
 */
package ${packageName}<#if moduleName!=''>.${moduleName}</#if>.service.impl;

import com.hesine.common.constant.ExceptionType;
import com.hesine.common.constant.ResponseErrorCode;
import com.hesine.framework.model.BaseModel;
import com.hesine.framework.utils.page.Pager;
import ${packageName}<#if moduleName!=''>.${moduleName}</#if>.dao.${ClassName}Dao;
import ${packageName}<#if moduleName!=''>.${moduleName}</#if>.service.${ClassName}Service;
import ${packageName}<#if moduleName!=''>.${moduleName}</#if>.vo.${ClassName};
import ${packageName}<#if moduleName!=''>.${moduleName}</#if>.web.model.${ClassName}Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import com.hesine.common.exception.AppException;

/**
 * ${functionName} ServiceImpl
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@Service("${className}Service")
@Transactional
public class ${ClassName}ServiceImpl implements ${ClassName}Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(${ClassName}ServiceImpl.class);

    @Resource
    private ${ClassName}Dao ${className}Dao;

    @Override
    @Transactional(readOnly = false)
    public boolean save(${ClassName} ${className}) {
        try {
            long id = ${className}Dao.save(${className});
            return id > 0?true:false;
        } catch (Exception e) {
            LOGGER.error("save ${ClassName} error!", e);
            throw new AppException("(${ClassName}ServiceImpl.save) exception",
            ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ${ClassName} get${ClassName}(Long id) {
        try {
            return ${className}Dao.get(id);
        } catch (Exception e) {
            LOGGER.error("get ${ClassName} error!", e);
            throw new AppException("(${ClassName}ServiceImpl.get${ClassName}) exception",
            ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(${ClassName} ${className}) {
        try {
            int result = ${className}Dao.update(${className});
            boolean updateResult = result > 0;
            return updateResult;
        } catch (Exception e) {
            LOGGER.error("update ${ClassName} error!", e);
            throw new AppException("(${ClassName}ServiceImpl.update) exception",
            ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean delete(Long id) {
        try {
            int result = ${className}Dao.delete(id);
            boolean deleteResult = result > 0;
            return deleteResult;
        } catch (Exception e) {
            LOGGER.error("delete ${ClassName} error!", e);
            throw new AppException("(${ClassName}ServiceImpl.delete) exception",
            ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<${ClassName}> list(Map<String, Object> searchParams) {
        try {
            List<${ClassName}> list = ${className}Dao.list(searchParams);
            return list;
        } catch (Exception e) {
            LOGGER.error("list ${ClassName} error!", e);
            throw new AppException("(${ClassName}ServiceImpl.list) exception",
            ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Pager<${ClassName}> pageList(${ClassName}Model ${className}Model) {
        try {
            Pager<${ClassName}> pager = new Pager<${ClassName}>();
            pager.setPageSize(${className}Model.getPageSize());
            // get totalCount
            Integer totalCount = ${className}Dao.listCount(${className}Model);
            pager.setTotalCount(totalCount);
            if (pager.getTotalPages() < ${className}Model.getPageNo()){
                ${className}Model.setPageNo(BaseModel.DEFAULT_PAGE_NO);
            }
            pager.setPageNo(${className}Model.getPageNo());
            // list data
            List<${ClassName}> list = ${className}Dao.pageList(${className}Model);
            if (list != null && list.size() > 0){
                pager.setResult(list);
            }
            return pager;
        } catch (Exception e) {
            LOGGER.error("pageList ${ClassName} error!", e);
            throw new AppException("(${ClassName}ServiceImpl.pageList) exception",
            ResponseErrorCode.BUSINESSEXCEPTION, ExceptionType.ERROR);
        }
    }
}

