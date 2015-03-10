package com.hesine.manager.generate.project;


import com.hesine.manager.utils.JarUtils;
import com.hesine.manager.utils.TemplateHelper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 15/3/9
 */
public class CreateProjectUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(CreateProjectUtils.class);

    /**
     * 项目名称.
     */
    private String projectName;

    /**
     * 项目包路径.
     */
    private String packageName;

    /**
     * 项目模板位置.
     */
    private File templateDir;

    /**
     * base directory
     */
    private File basedir;

    private TemplateHelper templateHelper = null;

    private void init() {
        LOGGER.info("参数初始化");

        if (templateDir == null) {
            String templateDirPath = this.getClass().getClassLoader().getResource("wptemplate/portal").toString();
            templateDir = new File(templateDirPath);
        }

        this.LOGGER.info("项目模板路径: " + templateDir.getAbsolutePath());
    }

    private void makeProjectFramework() throws Exception {
        LOGGER.info("开始创建项目结构");
        File rootPath = new File(basedir.getAbsolutePath() + "/" + projectName);
        if (!rootPath.exists()) {
            LOGGER.info("创建项目根目录：" + rootPath.getAbsolutePath());
            rootPath.mkdir();
        } else {
            LOGGER.info("项目已经存在，如果确实需要创建，请手动删除后再重行运行创建命令");
            return;
        }

        File templateWorkPath = new File(rootPath.getAbsolutePath() + "/template");

        // copy模板文件
        try {
            JarUtils.copyResourcesToDirectory(this.getClass(), "wptemplate/portal", templateWorkPath.getAbsolutePath());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }

        templateHelper = new TemplateHelper(templateWorkPath.getAbsolutePath());

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("genProjectName", projectName);
        model.put("packageName", packageName);

        // 根据项目名称修改生成子项目
        File[] childProjectTemplatePath = templateWorkPath.listFiles();
        for (File srcFile : childProjectTemplatePath) {
            if (srcFile.isDirectory()) {
                File destDir = new File(srcFile.getParentFile().getParentFile().getAbsoluteFile() + "/" + srcFile.getName());
                FileUtils.copyDirectory(srcFile, destDir);
                LOGGER.info("copy dir from [" + srcFile.getAbsolutePath() + "] to [" + destDir.getAbsolutePath() + "]");

                generateFileFromTemplate(srcFile, destDir, srcFile, model);
            } else {
                File destFile = new File(srcFile.getParentFile().getParentFile().getAbsoluteFile() + "/" + srcFile.getName());
                if (destFile.getName().endsWith(".xml")) {
                    templateHelper.writeTemplate(srcFile.getAbsolutePath(), destFile.getAbsolutePath(), model);
                }
            }
        }

        FileUtils.deleteDirectory(templateWorkPath);
    }

    private void generateFileFromTemplate(File projectTemplateDir, File projectDir, File srcDir, Map<String, Object> model) throws Exception {
        File[] files = srcDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                generateFileFromTemplate(projectTemplateDir, projectDir, file, model);
            } else {
                String fileTail = file.getAbsolutePath().replace(projectTemplateDir.getAbsolutePath(), "");
                File destFile = new File(projectDir + "/" + fileTail);
                if (destFile.getName().endsWith(".xml")) {
                    templateHelper.writeTemplate(file.getAbsolutePath(), destFile.getAbsolutePath(), model);
                } else {
                    FileUtils.copyFile(file, destFile);
                }
            }
        }
    }

    public void execute() {
        init();
        try {
            makeProjectFramework();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public File getBasedir() {
        return basedir;
    }

    public void setBasedir(File basedir) {
        this.basedir = basedir;
    }
}
