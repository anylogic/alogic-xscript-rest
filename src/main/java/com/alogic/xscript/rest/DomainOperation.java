package com.alogic.xscript.rest;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alogic.xscript.AbstractLogiclet;
import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
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
	protected void onExecute(Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx,
			ExecuteWatcher watcher) {
		String base = ctx.getObject(pid);
		if (StringUtils.isEmpty(base)){
			throw new BaseException("core.no_base","It must be in a domain context,check your together script.");
		}
		
		onExecute(base,root,current,ctx,watcher);
	}

	protected abstract void onExecute(String base, Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx,
			ExecuteWatcher watcher);
}
