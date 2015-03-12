/**
 * There are framework code generation model.ftl
 */
package ${packageName}<#if moduleName!=''>.${moduleName}</#if>.web.model;

import com.hesine.framework.model.BaseModel;
import java.util.Date;

/**
* ${functionName} model
* @author ${classAuthor}
* @version ${classVersion}
*/
public class ${ClassName}Model extends BaseModel {
    private static final long serialVersionUID = 1L;
    <#list fileds as filed>
    private ${filed.dataType} ${filed.name};
    </#list>

    public ${ClassName}Model() {
        super();
    }

    <#list fileds as filed>
    public ${filed.dataType} get${filed.methodName}() {
        return ${filed.name};
    }

    public void set${filed.methodName}(${filed.dataType} ${filed.name}) {
        this.${filed.name} = ${filed.name};
    }

    </#list>
}
