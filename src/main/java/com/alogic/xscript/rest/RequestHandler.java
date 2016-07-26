package com.alogic.xscript.rest;

import java.util.Map;

import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;

import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.anysoft.util.BaseException;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;


/**
 * http request handler
 * 
 * <p>
 * 用于在http调用之前处理http request,例如设置参数,设置输入文档等。
 * 
 * @author duanyy
 *
 */
public abstract class RequestHandler extends DomainOperation {
	protected String httpClientId = "httpClient";
	protected String httpRequestId = "httpRequest";
	
	public RequestHandler(String tag, Logiclet p) {
		super(tag, p);
	}
	
	@Override
	public void configure(Properties p){
		super.configure(p);
		httpClientId = PropertiesConstants.getString(p,"httpClientId",httpClientId);
		httpRequestId = PropertiesConstants.getString(p,"httpRequestId",httpRequestId);
	}

	protected abstract void onExecute(String base,HttpClient httpClient, HttpRequest httpRequest, Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx, ExecuteWatcher watcher);
	
	@Override
	protected void onExecute(String base, Map<String, Object> root, Map<String, Object> current, LogicletContext ctx,
			ExecuteWatcher watcher) {
		HttpClient httpClient = ctx.getObject(httpClientId);
		HttpRequest httpRequest = ctx.getObject(httpRequestId);
		
		if (httpClient == null || httpRequest == null){
			throw new BaseException("core.no_http_request","It must be in a request context,check your together script.");
		}
		
		onExecute(base,httpClient,httpRequest,root,current,ctx,watcher);
	}
}
