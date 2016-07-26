package com.alogic.xscript.rest.response;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.rest.ResponseHandler;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;

/**
 * 获取http头
 * 
 * @author duanyy
 *
 */
public class GetHeader extends ResponseHandler {
	protected String name;
	protected String id;
	public GetHeader(String tag, Logiclet p) {
		super(tag, p);
	}
	
	@Override
	public void configure(Properties p){
		super.configure(p);
		id = PropertiesConstants.getString(p,"id","",true);
		name = PropertiesConstants.getString(p,"name","",true);
	}

	@Override
	protected void onExecute(String base, HttpClient httpClient, HttpResponse httpResponse, Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx, ExecuteWatcher watcher) {
		if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(name)){
			Header header = httpResponse.getFirstHeader(name);
			if (header != null){
				String value = header.getValue();
				if (StringUtils.isNotEmpty(value)){
					ctx.SetValue(id, value);
				}
			}
		}
	}

}
