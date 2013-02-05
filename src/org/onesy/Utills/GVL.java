package org.onesy.Utills;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.onesy.Module.CfgList;

public class GVL {

	private static GVL gvl = null;

	/**
	 * 外层的key存储namespace，内层的key才是真正的key
	 */
	private static HashMap<String, HashMap<String, String>> localCfg = new HashMap<String, HashMap<String, String>>();
	/**
	 * 远程cube cfg配置文件
	 */
	private static HashMap<String, HashMap<String, String>> rmtCfg = new HashMap<String, HashMap<String, String>>();
	/**
	 * default cfg
	 */
	private static HashMap<String, String> defaultCfg = null;
	/**
	 * common cfg
	 */
	private static HashMap<String, String> commonCfg = null;
	
	public String getFDefault(String key){
		return defaultCfg.get(key);
	}
	
	public String getFCommon(String key){
		return commonCfg.get(key);
	}

	private GVL() {
		Properties PluginCfg = FileUtil.LoadProperty(CfgList.Plugin, false);
		defaultCfg = Collections
				.PropertyToHashMap(FileUtil.LoadProperty(CfgList.BasePath 
						+ File.separator + PluginCfg.getProperty("defaultPath"), false));
		commonCfg = Collections.PropertyToHashMap(FileUtil.LoadProperty(
				CfgList.BasePath + File.separator + PluginCfg.getProperty("commonPath"), false));
	}

	public synchronized static GVL getInstance() {
		if (GVL.gvl == null) {
			GVL.gvl = new GVL();
		}
		return GVL.gvl;
	}

	@SuppressWarnings("rawtypes")
	public void load(String namespace, Properties properties) {
		Iterator iter = properties.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();

			GVL.setCfg(localCfg, namespace, (String) entry.getKey(),
					(String) entry.getValue());
		}
	}

	@SuppressWarnings("rawtypes")
	public void load(String namespace, ArrayList<Properties> propertiesArr) {
		for (Properties properties : propertiesArr) {
			Iterator iter = properties.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				GVL.setCfg(rmtCfg, namespace, (String) entry.getKey(),
						(String) entry.getValue());
			}
		}
	}

	public static void setCfg(HashMap<String, HashMap<String, String>> hash,
			String namespace, String key, String value) {
		if (hash.containsKey(namespace)) {
			hash.get(namespace).put(key, value);
		} else {
			hash.put(namespace, new HashMap<String, String>());
			hash.get(namespace).put(key, value);
		}
	}

	public static String getCfg(String namespace, String key) {
		if (GVL.localCfg.containsKey(namespace)) {
			return GVL.localCfg.get(namespace).get(key);
		} else {
			return null;
		}
	}

}
