package org.onesy.Utills;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileUtil {
	
	/**
	 * 第三个参数暂时无效
	 * @param propertyPath
	 * @param properties
	 * @param IsIter
	 * @return
	 */
	public static boolean LoadProperty(String propertyPath,Properties properties,boolean IsIter){
		boolean okflg = true;
		if(properties == null){
			okflg = false;
			return okflg;
		}
		try {
			properties.load(new FileInputStream(new File(propertyPath)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			okflg = false;
			e.printStackTrace();
			return okflg;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			okflg = false;
			return okflg;
		}
		return false;
	}

	public static Properties getAsProperties(File file){

		Properties properties = new Properties();

		try {
			if(!file.exists()){
				return null;
			}
			FileInputStream in = new FileInputStream(file);
			properties.load(in);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return properties;
	}
	
	public static boolean ExistDir(File file){
		if(file.exists() && file.isDirectory()){
			return true;
		}
		return false;
		
	}

	/**
	 * @param file
	 * @param folder
	 * @descriptor 总之，创建文件或者文件夹
	 */
	public static boolean FileCheckCreate(File file, boolean folder) {
		if (folder && !file.exists()) {
			file.mkdirs();
			return true;
		} else if (!folder && !file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

}
