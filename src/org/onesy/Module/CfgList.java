package org.onesy.Module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.onesy.Utills.Collections;
import org.onesy.Utills.FileUtil;

public class CfgList {
	// 配置文件基目录
	public static String BasePath = null;
	// 根配置文件的目录的路径
	public static String RootDirPath = null;
	// 根配置文件的路径
	public static String RootCfg = null;
	// 本地cube配置文件目录路径
	public static String LocalCubeDir = null;
	// 本地cube配置文件路径
	public static String LocalCube = null;
	// 远程cube配置文件目录路径
	public static String RemoteCubeDir = null;
	// 远程cube根配置文件路径
	public static String Remotecube = null;
	// 远程cube支配置文件路径
	public static ArrayList<String> RmtCfgList = new ArrayList<String>();
	// plugin根配置文件目录路径
	public static String PluginDir = null;
	// pulgin根配置文件路径
	public static String Plugin = null;
	// pugins文件路径集合
	public static ArrayList<String> PluginCfgList = new ArrayList<String>();
	// 存储cube的信息
	@SuppressWarnings("rawtypes")
	public static HashMap AllCfg = new HashMap();

	/*
	 * 配置文件加载顺序 1.首先确认BasePath，读取根配置文件 2.根据根配置文件读取到的初始化其他的根文件目录
	 */

	public CfgList() {
		// 初始化配置文件
		boolean flg = CfgLoad();
		if (!flg) {
			// 初始化错误
			System.exit(-1);
		}
	}

	@SuppressWarnings("unchecked")
	public boolean CfgLoad() {

		boolean okflg = true;
		Properties rootCfgCube = null;
		Properties localCfgCube = null;
		Properties rmtCfg = null;
		ArrayList<Properties> rmtCfgCubes = new ArrayList<Properties>();
		Properties pluginCfg = null;

		// 初始化根配置文件
		BasePath = System.getProperty("user.home") + java.io.File.separator
				+ ".cmcs";
		RootDirPath = BasePath + java.io.File.separator + "root";
		RootCfg = RootDirPath + java.io.File.separator + "rootcfg.properties";
		rootCfgCube = FileUtil.LoadProperty(RootCfg, false);

		// 初始化插件配置文件
		Plugin = BasePath + File.separator
				+ rootCfgCube.getProperty("plugincfg");
		PluginDir = new File(Plugin).getParent();
		pluginCfg = FileUtil.LoadProperty(Plugin, false);

		// 初始化本地cube
		LocalCube = BasePath + File.separator
				+ rootCfgCube.getProperty("localcfg");
		LocalCubeDir = new File(LocalCube).getParent();
		localCfgCube = FileUtil.LoadProperty(LocalCube, false);
		if (pluginCfg.get("single").equals("true")) {
			AllCfg.put("pluginCfg", pluginCfg);
			AllCfg.put("rootCfgCube", rootCfgCube);
			AllCfg.put("localCfgCube", localCfgCube);
			return true;
		} else {
			/**
			 * this block of code contain some deadly error!
			 * on data struct
			 */
			// 初始化远程cube
			Remotecube = BasePath + File.separator
					+ rootCfgCube.getProperty("remotecfg");
			RemoteCubeDir = new File(Remotecube).getParent();
			rmtCfg = FileUtil.LoadProperty(Remotecube, false);
			@SuppressWarnings("rawtypes")
			ArrayList rmtcubecfg = Collections.MapToArrayOrcdBool(rmtCfg, true,
					"true");
			for (Object rmtcube : rmtcubecfg) {
				Properties rmtProper = new Properties();
				try {
					rmtProper.load(new FileInputStream(new File(BasePath
							+ File.separator + (String) rmtcube)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					okflg = false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					okflg = false;
				}
				rmtCfgCubes.add(rmtProper);
			}

			AllCfg.put("pluginCfg", pluginCfg);
			AllCfg.put("rmtCfgCubes", rmtCfgCubes);
			AllCfg.put("rootCfgCube", rootCfgCube);
			AllCfg.put("localCfgCube", localCfgCube);

			// 校验是不是所有的过程都是正常的
			if (BasePath != null && RootDirPath != null && RootCfg != null
					&& localCfgCube != null && okflg)
				return true;
			else
				return false;
		}
	}

}
