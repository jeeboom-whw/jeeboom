package com.hongwei.auto.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hongwei.common.util.GlobalValue;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHelper {

	public static List<File> findAllFile(File file){
		List<File> files = new ArrayList<File>();
		if( file.listFiles().length > 0 ){
			for(File f: file.listFiles()){
				if( f.isFile() ){
					files.add(f);
				}else{
					files.addAll(findAllFile(f));
				}
			}
		}
		return files;
	}
	
	/**
	 * 
	 * @param fileName 文件全路径名
	 * @throws IOException 
	 */
	public static File makeFile(String fileName) throws IOException{
        File f = new File(fileName);
        if(!f.exists()){
        	f.getParentFile().mkdirs();
        	f.createNewFile();
        }
        return f;
	}
	/**
	 * 将 dir中的${package}替换为相应的属性值
	 * @param dir
	 * @param params
	 * @return
	 */
	public static String genFileDir(String dir,Map<String,Object> params){
		String input = dir;
		Pattern p = Pattern.compile("\\$\\{([^}]*)\\}*");
		Matcher m = p.matcher(input);
		while(m.find()){
			String name = m.group(1);
			String value = "";
			if(name.split("\\\\").length>1){
				JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(params.get("entity")));
				value = jsonObject.getString(name.split("\\\\")[1]);
			}else{
				value  = String.valueOf(params.get(name));
			}
			input = input.replace(m.group(), value);
		}
		return input;
	}

	public static void createFileByTemp(String model,Map<String,Object> map) throws Exception{
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
		String outRoot = GlobalValue.outRoot;
		String basepackage = GlobalValue.basepackage + "." + model;
		String templateDir = FileHelper.class.getClassLoader().getResource("autotemplates").getPath();
		File tdf = new File(templateDir);
		List<File> files = FileHelper.findAllFile(tdf);

		for(File file: files){
			String parentDir = "";
			if( file.getParentFile().compareTo(tdf) != 0 ){
				parentDir = file.getParent().split("autotemplates")[1];
			}
			cfg.setClassForTemplateLoading(FileHelper.class, "/autotemplates" + parentDir);
			Template template = cfg.getTemplate(file.getName());
			template.setEncoding("UTF-8");
			String parentFileDir = FileHelper.genFileDir(parentDir, map);
			parentFileDir = parentFileDir.replace(".", "/");
			String fileName = FileHelper.genFileDir(file.getName(),map).replace(".ftl", ".java").replace(".html",".ftl");
			File newFile = FileHelper.makeFile(outRoot + parentFileDir + "/" + fileName);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream( newFile ), "UTF-8"));
			template.process(map, out);
		}
	}
}
