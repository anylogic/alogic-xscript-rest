package com.alogic.xscript.rest.response;

import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.rest.ResponseHandler;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;

/**
 * 输出http结果代码到上下文
 * 
 * @author duanyy
 *
 */
public class Result extends ResponseHandler {
	protected String code = "httpCode";
	protected String reason = "httpReason";
	
	public Result(String tag, Logiclet p) {
		super(tag, p);
	}
	
	@Override
	public void configure(Properties p){
		code = PropertiesConstants.getString(p,"code",code,true);
		reason = PropertiesConstants.getString(p,"reason",reason,true);
	}

	@Override
	protected void onExecute(String base, HttpClient httpClient, HttpResponse httpResponse, Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx, ExecuteWatcher watcher) {
		PropertiesConstants.setInt(ctx, code, httpResponse.getStatusLine().getStatusCode());
		PropertiesConstants.setString(ctx, reason, httpResponse.getStatusLine().getReasonPhrase());
	}

}
