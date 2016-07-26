package com.alogic.xscript.rest.request;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;

import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.rest.RequestHandler;
import com.alogic.xscript.util.MapProperties;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;

/**
 * 设置http头
 * 
 * @author duanyy
 *
 */
public class SetHeader extends RequestHandler {

	protected String name;
	protected String value;
	
	public SetHeader(String tag, Logiclet p) {
		super(tag, p);
	}
	
	@Override
	public void configure(Properties p){
		super.configure(p);
		
		name = PropertiesConstants.getString(p,"name","",true);
		value = p.GetValue("value", "", false,true);
	}
	

	@Override
	protected void onExecute(String base, HttpClient httpClient, HttpRequest httpRequest, Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx, ExecuteWatcher watcher) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value)){
			MapProperties p = new MapProperties(current,ctx);
			if (httpRequest != null){
				httpRequest.addHeader(name, p.transform(value));
			}
		}
	}

}
