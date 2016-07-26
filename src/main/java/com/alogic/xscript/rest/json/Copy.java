package com.alogic.xscript.rest.json;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.rest.JsonHandler;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;
import com.jayway.jsonpath.JsonPath;

/**
 * 将JSON文档指定的节点复制到当前文档
 * 
 * @author duanyy
 *
 */
public class Copy extends JsonHandler {
	protected String jsonPath;
	protected String tag = "data";
	
	public Copy(String tag, Logiclet p) {
		super(tag, p);
	}
	
	@Override
	public void configure(Properties p){
		super.configure(p);
		jsonPath = PropertiesConstants.getString(p, "path", jsonPath);
		tag = PropertiesConstants.getRaw(p, "tag", tag);
	}
	
	@Override
	protected void onExecute(Map<String, Object> json, Map<String, Object> root, Map<String, Object> current,
			LogicletContext ctx, ExecuteWatcher watcher) {
		if (StringUtils.isNotEmpty(jsonPath)){
			Object result = JsonPath.read(json, jsonPath);
			if (result != null){
				String tagValue = ctx.transform(tag);
				current.put(tagValue, result);
			}
		}
	}

}
