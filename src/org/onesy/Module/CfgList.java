package org.onesy.Module;

import java.util.ArrayList;
import java.util.Properties;

import org.onesy.Utills.FileUtil;

public class CfgList {
	//配置文件基目录
	public static String BasePath = null;
	//根配置文件的目录的路径
	public static String RootDirPath = null;
	//根配置文件的路径
	public static String RootCfg = null;
	//本地cube配置文件目录路径
	public static String LocalCubeDir = null;
	//本地cube配置文件路径
	public static String LocalCube = null;
	//远程cube配置文件目录路径
	public static String RemoteCubeDir = null;
	//远程cube根配置文件路径
	public static String Remotecube = null;
	//远程cube支配置文件路径
	public static ArrayList<String> RmtCfgList = new ArrayList<String>();
	//plugin根配置文件目录路径
	public static String PluginDir = null;
	//pulgin根配置文件路径
	public static String Plugin = null;
	//pugins文件路径集合
	public static ArrayList<String> PluginCfgList = new ArrayList<String>();
	
	/*
	 * 配置文件加载顺序
	 * 1.首先确认BasePath，读取根配置文件
	 * 2.根据根配置文件读取到的
	 */
	
	public CfgList(){
		
	}
	
	public boolean CfgLoad(){
		
		boolean okflg = true;
		
		//初始化根配置文件
		BasePath = System.getProperty("user.home") + java.io.File.separator + ".cmcs";
		RootDirPath = BasePath + java.io.File.separator + "Root";
		RootCfg = RootDirPath + java.io.File.separator + "rootcfg.properties";
		Properties rootcfgProperties = new Properties();
		okflg = FileUtil.LoadProperty(RootCfg, rootcfgProperties, false);
		
		//初始化本地cube
		
		return okflg;
	}

}
