package com.alogic.xscript.rest;

import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.doc.XsObject;
import com.alogic.xscript.doc.json.JsonObject;
import com.anysoft.util.BaseException;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;

/**
 * http response handler
 * <p>
 * 用于在http调用之后处理http response,例如读取结果等
 * 
 * @author duanyy
 *
 */
public abstract class ResponseHandler extends DomainOperation {
	protected String httpClientId = "httpClient";
	protected String httpResponseId = "httpResponse";
	
	public ResponseHandler(String tag, Logiclet p) {
		super(tag, p);
	}
	
	@Override
	public void configure(Properties p){
		super.configure(p);
		httpClientId = PropertiesConstants.getString(p,"httpClientId",httpClientId);
		httpResponseId = PropertiesConstants.getString(p,"httpResponseId",httpResponseId);
	}

	protected void onExecute(String base,HttpClient httpClient, HttpResponse httpResponse, Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx, ExecuteWatcher watcher){
		throw new BaseException("core.not_supported",
				String.format("Tag %s does not support protocol %s",this.getXmlTag(),root.getClass().getName()));	
	}
	
	@SuppressWarnings("unchecked")
	protected void onExecute(String base,HttpClient httpClient, HttpResponse httpResponse, XsObject root,
			XsObject current, LogicletContext ctx, ExecuteWatcher watcher){
		if (current instanceof JsonObject){
			onExecute(base,httpClient,httpResponse,(Map<String,Object>)root.getContent(),(Map<String,Object>)current.getContent(),ctx,watcher);
		}	
	}

	@Override
	protected void onExecute(String base, XsObject root,XsObject current, LogicletContext ctx,
			ExecuteWatcher watcher) {
		HttpClient httpClient = ctx.getObject(httpClientId);
		HttpResponse httpResponse = ctx.getObject(httpResponseId);
		
		if (httpClient == null || httpResponse == null){
			throw new BaseException("core.no_http_response","It must be in a response context,check your together script.");
		}
		
		onExecute(base,httpClient,httpResponse,root,current,ctx,watcher);
	}

}
