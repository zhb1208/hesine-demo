/**
 * There are framework code generation
 */
package ${packageName}<#if moduleName!=''>.${moduleName}</#if>.vo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hesine.framework.vo.IdEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * ${functionName} Vo
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@Entity
@Table(name = "${tableName}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${ClassName} extends IdEntity {
    private static final long serialVersionUID = 1L;
    <#list fileds as filed>
    private ${filed.dataType} ${filed.name};
    </#list>

    public ${ClassName}() {
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

