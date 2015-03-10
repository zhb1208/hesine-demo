package com.hesine.manager.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * 模板操作工具
 *
 * @version 2015-3-9
 * @author jason
 */
public class TemplateHelper {

	private VelocityEngine ve = null;
	private static final String CONTENT_ENCODING = "UTF-8";
	private String templateDir;

	public TemplateHelper(String templateDir) {
		this.templateDir = templateDir;
		try {
			Properties properties = new Properties();
			properties.setProperty(Velocity.RESOURCE_LOADER, "file");
			properties.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
			properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, templateDir);
			properties.setProperty(Velocity.FILE_RESOURCE_LOADER_CACHE, "true");
			properties.setProperty("file.resource.loader.modificationCheckInterval", "30");
			properties.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
			properties.setProperty("runtime.log.logsystem.log4j.logger", "org.apache.velocity");
			properties.setProperty("directive.set.null.allowed", "true");
			ve = new VelocityEngine();
			ve.init(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeTemplate(String templateFile, String outPutFile, Map<String, Object> model) throws Exception {
		File file = new File(outPutFile);
		FileOutputStream fos = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, CONTENT_ENCODING));
		VelocityEngineUtils.mergeTemplate(ve, templateFile.replace(templateDir, ""), CONTENT_ENCODING, model, writer);
		writer.flush();
		writer.close();
		fos.close();
	}
}
