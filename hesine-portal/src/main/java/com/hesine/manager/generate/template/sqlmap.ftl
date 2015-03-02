<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace必须指向Dao接口 -->
<mapper namespace="${packageName}<#if moduleName!=''>.${moduleName}</#if>.dao.${ClassName}Dao">

    <!--
        获取对象: 输出直接映射到对象, login_name列要"as loginName"以方便映射 ,team_id as "team.id"创建team对象并赋值
    -->
    <select id="get" parameterType="int"
            resultType="${packageName}<#if moduleName!=''>.${moduleName}</#if>.vo.${ClassName}">
        select
        <#list fileds as filed>
            <#if !filed_has_next>
                ${filed.dbName} as ${filed.name}
            <#else >
                ${filed.dbName} as ${filed.name},
            </#if>
        </#list>
        from
            ${tableName}
        where
            id=${r"#{id}"}
    </select>


    <!-- 查询所有数据,演示: 1.输入用map传入多个参数 2.<where>语句, 智能添加where和and关键字 3.输出直接映射对象 -->
    <select id="list" parameterType="map"
            resultType="${packageName}<#if moduleName!=''>.${moduleName}</#if>.vo.${ClassName}">
        select
        <#list fileds as filed>
            <#if !filed_has_next>
            ${filed.dbName} as ${filed.name}
            <#else >
            ${filed.dbName} as ${filed.name},
            </#if>
        </#list>
        from
            ${tableName}
        <where>
            1 = 1
            <#--<if test="loginName != null and loginName != ''" >-->
                <#--and login_name=#{loginName}-->
            <#--</if>-->
        </where>
    </select>

    <!-- 查询分页数据,演示: 1.输入用map传入多个参数 2.<where>语句, 智能添加where和and关键字 3.输出直接映射对象 -->
    <select id="pageList" parameterType="${packageName}<#if moduleName!=''>.${moduleName}</#if>.web.model.${ClassName}Model"
            resultType="${packageName}<#if moduleName!=''>.${moduleName}</#if>.vo.${ClassName}">
        select
        <#list fileds as filed>
            <#if !filed_has_next>
            ${filed.dbName} as ${filed.name}
            <#else >
            ${filed.dbName} as ${filed.name},
            </#if>
        </#list>
        from
            ${tableName}
        <where>
            1 = 1
            <#--<if test="loginName != null and loginName != ''" >-->
                <#--and login_name=#{loginName}-->
            <#--</if>-->
        </where>
        order by id desc
        <if test="beginIndex!=null and pageSize!=null">
            limit ${r"#{beginIndex}"},${r"#{pageSize}"}
        </if>
    </select>

    <!-- 获取数据总数 状态是个列表 -->
    <select id="listCount" parameterType="${packageName}<#if moduleName!=''>.${moduleName}</#if>.web.model.${ClassName}Model"
            resultType="int">
        SELECT
        count(id)
        FROM ${tableName}
        <where>
            1 = 1
            <#--<if test="loginName != null and loginName != ''" >-->
                <#--and login_name=#{loginName}-->
            <#--</if>-->
        </where>
    </select>

    <!-- 插入用户： 1.由数据库生成id并赋值到数据对象 2.输入用对象, 嵌套属性表达式 -->
    <insert id="save" parameterType="${packageName}<#if moduleName!=''>.${moduleName}</#if>.vo.${ClassName}" useGeneratedKeys="true" keyProperty="id">
        <selectKey resultType="long" keyProperty="id">
            SELECT LAST_INSERT_ID()
        </selectKey>
        insert into ${tableName} (
        <#list fileds as filed>
            <#if !filed_has_next>
            ${filed.dbName}
            <#else >
            ${filed.dbName},
            </#if>
        </#list>
        )
        values (
        <#list fileds as filed>
            <#if !filed_has_next>
                ${r"#{t."}${filed.name}${r"}"}
            <#else >
                ${r"#{t."}${filed.name}${r"},"}
            </#if>
        </#list>
        )
    </insert>

    <!-- 修改数据 -->
    <update id="update"  parameterType="${packageName}<#if moduleName!=''>.${moduleName}</#if>.vo.${ClassName}" >
        update ${tableName}
        set
        <#assign fcount = 0/>
        <#list fileds as filed>
            <#if filed.name != 'id'>
                <#assign fcount = fcount + 1/>
                <#if fcount == 1>
                ${filed.name} = ${r"#{t."}${filed.name}${r"}"}
                <#else >
                , ${filed.name} = ${r"#{t."}${filed.name}${r"}"}
                </#if>
            </#if>
        </#list>
        where id = ${r"#{t.id}"}
    </update>

    <!-- 删除数据 -->
    <delete id="delete" parameterType="int">
        delete from ${tableName} where id=${r"#{id}"}
    </delete>
</mapper> 
