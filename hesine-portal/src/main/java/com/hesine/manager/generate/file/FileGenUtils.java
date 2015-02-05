package com.hesine.manager.generate.file;

import com.hesine.manager.utils.FreeMarkers;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 15/1/21
 */
public class FileGenUtils {

    private static Logger logger = LoggerFactory.getLogger(FileGenUtils.class);
    // 获取文件分隔符
    private static final String separator = File.separator;
    public static final String ENTITY_FTL = "entity.ftl";
    public static final String SQLMAP_FTL = "sqlmap.ftl";
    public static final String MODEL_FTL = "model.ftl";
    public static final String DAO_FTL = "dao.ftl";
    public static final String SERVICE_FTL = "service.ftl";
    public static final String SERVICEIMPL_FTL = "serviceImpl.ftl";
    public static final String CONTROLLER_FTL = "controller.ftl";

    /**
     * 获取模板
     * @param tplPath
     * @param name
     * @return
     */
    public static Template getTemplate(String tplPath, String name) {
        // 代码模板配置
        Configuration cfg = new Configuration();
        try {
            cfg.setDirectoryForTemplateLoading(new File(tplPath));
            Template template = cfg.getTemplate(name);
            return template;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("");
        }
        return null;
    }

    /**
     * 生成Entity
     * @param tplPath 模板路径
     * @param filePath 生成文件路径
     * @param model 填充对象
     */
    public static void generateEntity(String tplPath,
                                      String filePath,
                                      Map<String, Object> model) {
        // 生成 Entity
        Template template = getTemplate(tplPath, FileGenUtils.ENTITY_FTL);
        String content = FreeMarkers.renderTemplate(template, model);
        String genfilePath = filePath+separator+model.get("moduleName")+separator+"vo"+separator
                +model.get("ClassName")+".java";
        writeFile(content, genfilePath);
        logger.info("Entity: {}", genfilePath);
    }

    /**
     * 生成sqlMap
     * @param tplPath 模板路径
     * @param filePath 生成文件路径
     * @param model 填充对象
     */
    public static void generateSqlMap(String tplPath,
                                      String filePath,
                                      Map<String, Object> model) {
        // 生成 Entity
        Template template = getTemplate(tplPath, FileGenUtils.SQLMAP_FTL);
        String content = FreeMarkers.renderTemplate(template, model);
        String genfilePath = filePath+separator+model.get("ClassName")+".xml";
        writeFile(content, genfilePath);
        logger.info("Xml: {}", genfilePath);
    }

    /**
     * 生成Model
     * @param tplPath 模板路径
     * @param filePath 生成文件路径
     * @param model 填充对象
     */
    public static void generateModel(String tplPath,
                                      String filePath,
                                      Map<String, Object> model) {
        // 生成 Model
        Template template = getTemplate(tplPath, FileGenUtils.MODEL_FTL);
        String content = FreeMarkers.renderTemplate(template, model);
        String genfilePath = filePath+separator+model.get("moduleName")+separator
                +"web"+separator+"model"+separator
                +model.get("ClassName")+"Model.java";
        writeFile(content, genfilePath);
        logger.info("Model: {}", genfilePath);
    }

    /**
     * 生成Dao
     * @param tplPath 模板路径
     * @param filePath 生成文件路径
     * @param model 填充对象
     */
    public static void generateDao(String tplPath,
                                     String filePath,
                                     Map<String, Object> model) {
        // 生成 Dao
        Template template = getTemplate(tplPath, FileGenUtils.DAO_FTL);
        String content = FreeMarkers.renderTemplate(template, model);
        String genfilePath = filePath+separator+model.get("moduleName")+separator
                +"dao"+separator+model.get("ClassName")+"Dao.java";
        writeFile(content, genfilePath);
        logger.info("Dao: {}", genfilePath);
    }

    /**
     * 生成Service
     * @param tplPath 模板路径
     * @param filePath 生成文件路径
     * @param model 填充对象
     */
    public static void generateService(String tplPath,
                                   String filePath,
                                   Map<String, Object> model) {
        // 生成 Service
        Template template = getTemplate(tplPath, FileGenUtils.SERVICE_FTL);
        String content = FreeMarkers.renderTemplate(template, model);
        String genfilePath = filePath+separator+model.get("moduleName")+separator+"service"+
                separator+model.get("ClassName")+"Service.java";
        writeFile(content, genfilePath);
        logger.info("Service: {}", genfilePath);
    }

    /**
     * 生成ServiceImpl
     * @param tplPath 模板路径
     * @param filePath 生成文件路径
     * @param model 填充对象
     */
    public static void generateServiceImpl(String tplPath,
                                       String filePath,
                                       Map<String, Object> model) {
        // 生成 ServiceImpl
        Template template = getTemplate(tplPath, FileGenUtils.SERVICEIMPL_FTL);
        String content = FreeMarkers.renderTemplate(template, model);
        String genfilePath = filePath+separator+model.get("moduleName")+separator+"service"+
                separator+"impl"+separator+model.get("ClassName")+"ServiceImpl.java";
        writeFile(content, genfilePath);
        logger.info("ServiceImpl: {}", genfilePath);
    }

    /**
     * 生成Controller
     * @param tplPath 模板路径
     * @param filePath 生成文件路径
     * @param model 填充对象
     */
    public static void generateController(String tplPath,
                                           String filePath,
                                           Map<String, Object> model) {
        // 生成 Controller
        Template template = getTemplate(tplPath, FileGenUtils.CONTROLLER_FTL);
        String content = FreeMarkers.renderTemplate(template, model);
        String genfilePath = filePath+separator+model.get("moduleName")+separator
                +"web"+separator+"controller"+separator
                +model.get("ClassName")+"Controller.java";
        writeFile(content, genfilePath);
        logger.info("Controller: {}", genfilePath);
    }


    /**
     * 将内容写入文件
     * @param content
     * @param filePath
     */
    public static void writeFile(String content, String filePath) {
        try {
            if (com.hesine.manager.utils.FileUtils.createFile(filePath)){
                FileWriter fileWriter = new FileWriter(filePath, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(content);
                bufferedWriter.close();
                fileWriter.close();
            }else{
                logger.info("生成失败，文件已存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
