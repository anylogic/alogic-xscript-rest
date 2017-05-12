package com.alogic.xscript.rest;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alogic.xscript.AbstractLogiclet;
import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.doc.XsObject;
import com.alogic.xscript.doc.json.JsonObject;
import com.anysoft.util.BaseException;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;

/**
 * 服务域操作基类
 * 
 * @author duanyy
 *
 */
public abstract class DomainOperation extends AbstractLogiclet {
	protected String pid = "$rest-domain";
	public DomainOperation(String tag, Logiclet p) {
		super(tag, p);
	}
	
	public void configure(Properties p){
		super.configure(p);
		pid = PropertiesConstants.getString(p,"pid", pid);
	}
	
	@Override
	protected void onExecute(XsObject root,XsObject current, LogicletContext ctx,
			ExecuteWatcher watcher) {
		String base = ctx.getObject(pid);
		if (StringUtils.isEmpty(base)){
			throw new BaseException("core.no_base","It must be in a domain context,check your together script.");
		}
		
		onExecute(base,root,current,ctx,watcher);
	}

	@SuppressWarnings("unchecked")
	protected void onExecute(String base, XsObject root,XsObject current, LogicletContext ctx,
			ExecuteWatcher watcher){
		if (current instanceof JsonObject){
			onExecute(base,(Map<String,Object>)root.getContent(),(Map<String,Object>)current.getContent(),ctx,watcher);
		}		
	}
	
	protected void onExecute(String base, Map<String, Object> root, Map<String, Object> current, LogicletContext ctx,
			ExecuteWatcher watcher) {
		throw new BaseException("core.not_supported",
				String.format("Tag %s does not support protocol %s",this.getXmlTag(),root.getClass().getName()));	
	}
}
