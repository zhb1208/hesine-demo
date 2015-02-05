/**
 * There are Framework code generation Controller
 */
package ${packageName}<#if moduleName!=''>.${moduleName}</#if>.web.controller;

import com.google.common.collect.Maps;
import com.hesine.framework.utils.page.Pager;
import com.hesine.framework.model.BaseModel;
import ${packageName}<#if moduleName!=''>.${moduleName}</#if>.service.${ClassName}Service;
import ${packageName}<#if moduleName!=''>.${moduleName}</#if>.vo.${ClassName};
import ${packageName}<#if moduleName!=''>.${moduleName}</#if>.web.model.${ClassName}Model;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;


/**
 * ${functionName}Controller
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@Controller
@RequestMapping("<#if moduleName!=''>/${moduleName}</#if>/${className}")
@Component
public class ${ClassName}Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(${ClassName}Controller.class);

    @Autowired
    private ${ClassName}Service ${className}Service;


    /**
     * 获取分页列表
     * @param ${className}Model
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(${ClassName}Model ${className}Model) {
        ModelMap model = new ModelMap();
        ${className}Model.setPageSize(BaseModel.DEFAULT_PAGE_SIZE);
        // TODO 分页条件
        //if (${className}Model != null && StringUtils.isNotBlank(${className}Model.getName())) {
        //    ${className}Model.setLoginName(${className}Model.getName().trim());
        //}

        Pager<${ClassName}> pager = ${className}Service.pageList(${className}Model);
        model.addAttribute("pager", pager);
        return new ModelAndView("<#if moduleName!=''>${moduleName}</#if>/${className}/list", model);
    }

    /**
     * 新增 ${ClassName}
     * @return
     */
    @RequestMapping(value = "add")
    public ModelAndView add() {
        ModelMap model = new ModelMap();
        ${ClassName} ${className} = new ${ClassName}();
        model.addAttribute("${className}", ${className});
        return new ModelAndView("<#if moduleName!=''>${moduleName}</#if>/${className}/add", model);
    }

    /**
     * 编辑 ${ClassName}
     * @param id
     * @return
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelMap model = new ModelMap();
        if (id != null) {
            ${ClassName} ${className} = ${className}Service.get${ClassName}(id);
            if (${className} == null){
                LOGGER.error("${ClassName}不能为空");
            }
            model.addAttribute("${className}", ${className});
        }else{
            LOGGER.error("${ClassName}的id不能为空");
        }
        return new ModelAndView("<#if moduleName!=''>${moduleName}</#if>/${className}/edit", model);
    }

    /**
     * 保存 ${ClassName}
     * @param ${className}
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("${className}") ${ClassName} ${className}, BindingResult result) {
        ModelMap model = new ModelMap();
        // jsr303 验证
        if(result.hasErrors()){
            //这个是spring validation校验后返回的结果对象
            //如果校验失败，你要做什么事情
            String code = result.getFieldError().getCode();//验证出错的那个类型名称 比如NotEmpty
            result.getFieldError().getDefaultMessage();//出错的信息
            model.addAttribute("${className}", ${className});
            if (${className} != null && ${className}.getId() != null ){
                return new ModelAndView("<#if moduleName!=''>${moduleName}</#if>/${className}/add", model);
            }else{
                return new ModelAndView("<#if moduleName!=''>${moduleName}</#if>/${className}/edit", model);
            }
        }else{
            if (${className} != null) {
                if (${className}.getId() != null ){
                    ${ClassName} cur${ClassName} = ${className}Service.get${ClassName}(${className}.getId());
                    // TODO 服务端验证
                    //if (StringUtils.isNotBlank(${className}.getName())){
                    //    cur${ClassName}.setName(${className}.getName().trim());
                    //} else {
                    //    cur${ClassName}.setName(${className}.getName());
                    //}
                    ${className}Service.update(cur${ClassName});
                }else{
                    // TODO 修改${ClassName}
                    ${className}Service.save(${className});
                }
            }else{
                LOGGER.error("${ClassName}不能为空");
            }
            return new ModelAndView("redirect:<#if moduleName!=''>${moduleName}</#if>/${className}/list", model);
        }
    }

    /**
     * 查看 ${ClassName}
     * @param id
     * @return
     */
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("id") Long id) {
        ModelMap model = new ModelMap();
        if (id != null) {
            ${ClassName} ${className} = ${className}Service.get${ClassName}(id);
            if (${className} == null){
                LOGGER.error("${ClassName}不能为空");
            }
            model.addAttribute("${className}", ${className});
        }else{
            LOGGER.error("${ClassName}的id不能为空");
        }
        return new ModelAndView("<#if moduleName!=''>${moduleName}</#if>/${className}/view", model);
    }

    /**
     * 删除 ${ClassName}
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        ModelMap model = new ModelMap();
        if (id != null) {
            ${className}Service.delete(id);
        }else{
            LOGGER.error("${ClassName}的id不能为空");
        }
        return "redirect:<#if moduleName!=''>${moduleName}</#if>/${className}/list";
    }

}
