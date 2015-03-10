/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hesine.manager.generate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hesine.manager.generate.db.DBOperator;
import com.hesine.manager.generate.file.FileGenUtils;
import com.hesine.manager.generate.property.SystemConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import com.google.common.collect.Maps;
import com.hesine.manager.utils.DateUtils;
import com.hesine.manager.utils.FileUtils;
import com.hesine.manager.utils.FreeMarkers;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 代码生成器
 * @author ThinkGem
 * @version 2013-06-21
 */
public class Generate {
	
	private static Logger logger = LoggerFactory.getLogger(Generate.class);

    public static void execute(File curProjectPath) {
        // ========== ↓↓↓↓↓↓ 执行前请修改参数，谨慎执行。↓↓↓↓↓↓ ====================

        // 主要提供基本功能模块代码生成。
        // 目录生成结构：{packageName}/{moduleName}/{dao,entity,service,web}/{subModuleName}/{className}

        // packageName 包名，这里如果更改包名，请在applicationContext.xml和srping-mvc.xml中配置base-package、packagesToScan属性，来指定多个（共4处需要修改）。
        String packageName = "com.hesine.manager";

//		String moduleName = "function";			// 模块名，例：sys
        String moduleName = "";			// 模块名，例：sys
        String tableName = "tb_product";			// 模块名，例：sys
        String subModuleName = "";				// 子模块名（可选）
        String className = "product";			// 类名，例：product
        String classAuthor = "Jason";		// 类作者，例：ThinkGem
        String functionName = "产品";			// 功能名，例：用户
        List<Map<String,String>> fileds = new ArrayList<Map<String, String>>();
        // 组装map
        Map<String, String> dataName = new HashMap<String, String>();
        dataName.put("dataType", "String");
        dataName.put("name", "name");
        dataName.put("methodName", "Name");
        dataName.put("comment", "名称");
        dataName.put("dbName", "name");

        Map<String, String> dataDesc = new HashMap<String, String>();
        dataDesc.put("dataType", "String");
        dataDesc.put("name", "description");
        dataDesc.put("methodName", "Description");
        dataDesc.put("comment", "描述");
        dataDesc.put("dbName", "description");

        fileds.add(dataName);
        fileds.add(dataDesc);

        // 是否启用生成工具
        Boolean isEnable = true;

        // ========== ↑↑↑↑↑↑ 执行前请修改参数，谨慎执行。↑↑↑↑↑↑ ====================

        if (!isEnable){
            logger.error("请启用代码生成工具，设置参数：isEnable = true");
            return;
        }

//        StringUtils.isBlank(moduleName) ||
        if ( StringUtils.isBlank(packageName)
                || StringUtils.isBlank(className) || StringUtils.isBlank(functionName)){
            logger.error("参数设置错误：包名、模块名、类名、功能名不能为空。");
            return;
        }

        // 获取文件分隔符
        String separator = File.separator;

        // 获取工程路径
        File projectPath = curProjectPath;
        while(!new File(projectPath.getPath()+separator+"src"+separator+"main").exists()){
            projectPath = projectPath.getParentFile();
        }
        logger.info("Project Path: {}", projectPath);

        // 模板文件路径
        String tplPath = StringUtils.replace(projectPath+"/src/main/java/com/hesine/manager/generate/template", "/", separator);
        logger.info("Template Path: {}", tplPath);

        // Java文件路径
        String javaPath = StringUtils.replaceEach(projectPath+"/src/main/java/"+StringUtils.lowerCase(packageName),
                new String[]{"/", "."}, new String[]{separator, separator});
        logger.info("Java Path: {}", javaPath);

        // Xml文件路径
        String xmlPath = StringUtils.replace(projectPath + "/src/main/resources/mybatis", "/", separator);
        logger.info("Xml Path: {}", xmlPath);

        // 视图文件路径
        String viewPath = StringUtils.replace(projectPath+"/src/main/webapp/WEB-INF/views", "/", separator);
        logger.info("View Path: {}", viewPath);

        // 定义模板变量
        Map<String, Object> model = Maps.newHashMap();
        model.put("packageName", StringUtils.lowerCase(packageName));
        model.put("moduleName", StringUtils.lowerCase(moduleName));
        model.put("subModuleName", StringUtils.isNotBlank(subModuleName)?"."+StringUtils.lowerCase(subModuleName):"");
        model.put("className", StringUtils.uncapitalize(className));
        model.put("ClassName", StringUtils.capitalize(className));
        model.put("classAuthor", StringUtils.isNotBlank(classAuthor)?classAuthor:"Generate Tools");
        model.put("classVersion", DateUtils.getDate());
        model.put("functionName", functionName);
//		model.put("tableName", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
//				?"_"+StringUtils.lowerCase(subModuleName):"")+"_"+model.get("className"));
        model.put("tableName", tableName);

        model.put("urlPrefix", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
                ?"/"+StringUtils.lowerCase(subModuleName):"")+"/"+model.get("className"));
        model.put("viewPrefix", //StringUtils.substringAfterLast(model.get("packageName"),".")+"/"+
                model.get("urlPrefix"));
        model.put("permissionPrefix", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
                ?":"+StringUtils.lowerCase(subModuleName):"")+":"+model.get("className"));
        model.put("fileds", fileds);

//        // 生成 Entity
//        FileGenUtils.generateEntity(tplPath, javaPath, model);
//
//        // 生成 sqlMap
//        FileGenUtils.generateSqlMap(tplPath, xmlPath, model);
//
//        // 生成 Model
//        FileGenUtils.generateModel(tplPath, javaPath, model);
//
//        // 生成 Dao
//        FileGenUtils.generateDao(tplPath, javaPath, model);
//
//        // 生成 Service
//        FileGenUtils.generateService(tplPath, javaPath, model);
//
//        // 生成 ServiceImpl
//        FileGenUtils.generateServiceImpl(tplPath, javaPath, model);
//
//        // 生成 Controller
//        FileGenUtils.generateController(tplPath, javaPath, model);

        // 生成 add.ftl
        FileGenUtils.generateAddFtl(tplPath, viewPath, model);

        // 生成 edit.ftl
        FileGenUtils.generateEditFtl(tplPath, viewPath, model);

        // 生成 list.ftl
        FileGenUtils.generateListFtl(tplPath, viewPath, model);

        logger.info("Generate Success.");
    }

    public static void execute(File curProjectPath,
                               String packageName,
                               String moduleName,
                               Map<String,String> table,
                               List<Map<String,String>> fileds) {
        // ========== ↓↓↓↓↓↓ 执行前请修改参数，谨慎执行。↓↓↓↓↓↓ ====================

        // 主要提供基本功能模块代码生成。
        // 目录生成结构：{packageName}/{moduleName}/{dao,entity,service,web}/{subModuleName}/{className}

        // packageName 包名，这里如果更改包名，请在applicationContext.xml和srping-mvc.xml中配置base-package、packagesToScan属性，来指定多个（共4处需要修改）。
//        String packageName = "com.hesine.manager";

//		String moduleName = "function";			// 模块名，例：sys
//        String moduleName = "";			// 模块名，例：sys
//        String tableName = "tb_product";			// 模块名，例：sys
//        String subModuleName = "";				// 子模块名（可选）
//        String className = "product";			// 类名，例：product
//        String classAuthor = "Jason";		// 类作者，例：ThinkGem
//        String functionName = "产品";			// 功能名，例：用户
        String tableName = table.get("tableName");			// 模块名，例：sys
        String subModuleName = "";				// 子模块名（可选）
        String className = table.get("className");			// 类名，例：product
        String classAuthor = "Jason";		// 类作者，例：ThinkGem
        String functionName = table.get("comment");			// 功能名，例：用户


        // 是否启用生成工具
        Boolean isEnable = true;

        // ========== ↑↑↑↑↑↑ 执行前请修改参数，谨慎执行。↑↑↑↑↑↑ ====================

        if (!isEnable){
            logger.error("请启用代码生成工具，设置参数：isEnable = true");
            return;
        }

//        StringUtils.isBlank(moduleName) ||
        if ( StringUtils.isBlank(packageName)
                || StringUtils.isBlank(className) || StringUtils.isBlank(functionName)){
            logger.error("参数设置错误：包名、模块名、类名、功能名不能为空。");
            return;
        }

        // 获取文件分隔符
        String separator = File.separator;

        // 获取工程路径
        File projectPath = curProjectPath;
//        while(!new File(projectPath.getPath()+separator+"src"+separator+"main").exists()){
//            projectPath = projectPath.getParentFile();
//        }
        logger.info("Project Path: {}", projectPath);

        // 模板文件路径
        String tmpProjectPath = "/Users/zhanghongbing/Workspaces/Projects/hesine-projects/github-framework/hesine-demo/hesine-portal";
        String tplPath = StringUtils.replace(tmpProjectPath+"/src/main/java/com/hesine/manager/generate/template", "/", separator);
//        String tplPath = StringUtils.replace(projectPath+"/src/main/java/com/hesine/manager/generate/template", "/", separator);
        logger.info("Template Path: {}", tplPath);

        // Java文件路径
        String javaPath = StringUtils.replaceEach(projectPath+"/src/main/java/"+StringUtils.lowerCase(packageName),
                new String[]{"/", "."}, new String[]{separator, separator});
        logger.info("Java Path: {}", javaPath);

        // Xml文件路径
        String xmlPath = StringUtils.replace(projectPath + "/src/main/resources/mybatis", "/", separator);
        logger.info("Xml Path: {}", xmlPath);

        // 视图文件路径
        String viewPath = StringUtils.replace(projectPath+"/src/main/webapp/WEB-INF/views", "/", separator);
        logger.info("View Path: {}", viewPath);

        // 定义模板变量
        Map<String, Object> model = Maps.newHashMap();
        model.put("packageName", StringUtils.lowerCase(packageName));
        model.put("moduleName", StringUtils.lowerCase(moduleName));
        model.put("subModuleName", StringUtils.isNotBlank(subModuleName)?"."+StringUtils.lowerCase(subModuleName):"");
        model.put("className", StringUtils.uncapitalize(className));
        model.put("ClassName", StringUtils.capitalize(className));
        model.put("classAuthor", StringUtils.isNotBlank(classAuthor)?classAuthor:"Generate Tools");
        model.put("classVersion", DateUtils.getDate());
        model.put("functionName", functionName);
//		model.put("tableName", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
//				?"_"+StringUtils.lowerCase(subModuleName):"")+"_"+model.get("className"));
        model.put("tableName", tableName);

        model.put("urlPrefix", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
                ?"/"+StringUtils.lowerCase(subModuleName):"")+"/"+model.get("className"));
        model.put("viewPrefix", //StringUtils.substringAfterLast(model.get("packageName"),".")+"/"+
                model.get("urlPrefix"));
        model.put("permissionPrefix", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
                ?":"+StringUtils.lowerCase(subModuleName):"")+":"+model.get("className"));
        model.put("fileds", fileds);

        // 生成 Entity
        FileGenUtils.generateEntity(tplPath, javaPath, model);

        // 生成 sqlMap
        FileGenUtils.generateSqlMap(tplPath, xmlPath, model);

        // 生成 Model
        FileGenUtils.generateModel(tplPath, javaPath, model);

        // 生成 Dao
        FileGenUtils.generateDao(tplPath, javaPath, model);

        // 生成 Service
        FileGenUtils.generateService(tplPath, javaPath, model);

        // 生成 ServiceImpl
        FileGenUtils.generateServiceImpl(tplPath, javaPath, model);

        // 生成 Controller
        FileGenUtils.generateController(tplPath, javaPath, model);
//
//        // 生成 add.ftl
//        FileGenUtils.generateAddFtl(tplPath, viewPath, model);
//
//        // 生成 edit.ftl
//        FileGenUtils.generateEditFtl(tplPath, viewPath, model);

//        // 生成 list.ftl
//        FileGenUtils.generateListFtl(tplPath, viewPath, model);

        logger.info("Generate Success.");
    }

	public static void main(String[] args) throws Exception {
        File projectPath = new DefaultResourceLoader().getResource("").getFile();
        //        Generate.execute(projectPath);

        // 方法二
        String packageName = "com.hesine.manager";
        String moduleName = "";



        // 获取表
        List<Map<String,String>> list = DBOperator.getTables("tb_");
        for(Map<String,String> a: list) {
            System.out.println(a.toString());
            if (a.get("tableName").equals("tb_userinfo")) {
                List<Map<String,String>> listTC =
                        DBOperator.getTableColumns(a.get("tableName"));
                // 获取表字段
                for(Map<String,String> b: listTC) {
                    System.out.println(b.toString());
                    for (String key: b.keySet()) {
                        System.out.println(key + " : " + b.get(key));
                    }

                }
                Generate.execute(projectPath, packageName, moduleName, a, listTC);
                break;
            }
        }

    }
	
}
