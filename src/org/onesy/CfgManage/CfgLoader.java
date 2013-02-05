package org.onesy.CfgManage;

import java.util.ArrayList;
import java.util.Properties;

import org.onesy.Module.CfgList;
import org.onesy.Utills.GVL;

public class CfgLoader {

	public static CfgList cfgList = null;

	@SuppressWarnings("unchecked")
	public CfgLoader() {
		// load configure file
		cfgList = new CfgList();
		// register in GVL
		if (((Properties) CfgList.AllCfg.get("pluginCfg")).get("single")
				.equals("true")) {
			GVL.getInstance().load("pluginCfg",
					(Properties) CfgList.AllCfg.get("pluginCfg"));
			GVL.getInstance().load("rootCfgCube",
					(Properties) CfgList.AllCfg.get("rootCfgCube"));
			GVL.getInstance().load("localCfgCube",
					(Properties) CfgList.AllCfg.get("localCfgCube"));
		} else {
			GVL.getInstance().load("pluginCfg",
					(Properties) CfgList.AllCfg.get("pluginCfg"));
			GVL.getInstance().load("rootCfgCube",
					(Properties) CfgList.AllCfg.get("rootCfgCube"));
			GVL.getInstance().load("localCfgCube",
					(Properties) CfgList.AllCfg.get("localCfgCube"));
			GVL.getInstance().load("rmtCfgCubes",
					(ArrayList<Properties>) CfgList.AllCfg.get("rmtCfgCubes"));

		}
	}

}
