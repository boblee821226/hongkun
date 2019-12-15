package com.yyuap.mobile.train;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.yonyou.uap.um.context.util.UmContextUtil;
import com.yonyou.uap.um.controller.AbstractUMController;

public class CheckController extends AbstractUMController {

	public String login(String arg0)throws Exception{
		
		JSONObject jsonObj = new JSONObject(arg0);
		Map<String,String> map = UmContextUtil.transJsonToMap(jsonObj);
		
		
		
		return null;
		
	}
	
	@Override
	public String load(String s) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String save(String s) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

}
