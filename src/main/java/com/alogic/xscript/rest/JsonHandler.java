package com.alogic.xscript.rest;

import java.util.Map;

import com.alogic.xscript.AbstractLogiclet;
import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.anysoft.util.BaseException;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;

public abstract class JsonHandler extends AbstractLogiclet {
	protected String jsonId = "json";
	
	public JsonHandler(String tag, Logiclet p) {
		super(tag, p);
	}

	public void configure(Properties p){
		super.configure(p);
		jsonId = PropertiesConstants.getString(p,"jsonId", jsonId);
	}

	@Override
	protected void onExecute(Map<String, Object> root, Map<String, Object> current, LogicletContext ctx,
			ExecuteWatcher watcher) {
		Object json = ctx.getObject(jsonId);
		if (json == null){
			throw new BaseException("core.no_json","It must be in a json context,check your together script.");
		}
		
		onExecute(json,root,current,ctx,watcher);
	}

	protected abstract void onExecute(Object json, Map<String, Object> root, Map<String, Object> current,
			LogicletContext ctx, ExecuteWatcher watcher);
}
