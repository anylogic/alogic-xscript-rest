package com.alogic.xscript.rest;


import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.plugins.Segment;
import com.alogic.xscript.rest.json.Copy;
import com.alogic.xscript.rest.method.Delete;
import com.alogic.xscript.rest.method.Get;
import com.alogic.xscript.rest.method.Options;
import com.alogic.xscript.rest.method.Patch;
import com.alogic.xscript.rest.method.Post;
import com.alogic.xscript.rest.method.Put;
import com.alogic.xscript.rest.method.Trace;
import com.alogic.xscript.rest.request.ByForm;
import com.alogic.xscript.rest.request.ByJson;
import com.alogic.xscript.rest.request.SetHeader;
import com.alogic.xscript.rest.request.SetURI;
import com.alogic.xscript.rest.response.AsJson;
import com.alogic.xscript.rest.response.GetHeader;
import com.alogic.xscript.rest.response.Result;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;

/**
 * 定义一个rest调用的服务域
 * 
 * @author duanyy
 *
 */
public class Domain extends Segment {
	protected String base;
	protected String cid = "$rest-domain";
	public Domain(String tag, Logiclet p) {
		super(tag, p);
		
		registerModule("mGet", Get.class);
		registerModule("mPost",Post.class);
		registerModule("mDelete",Delete.class);
		registerModule("mPatch",Patch.class);
		registerModule("mPut",Put.class);
		registerModule("mOptions",Options.class);
		registerModule("mTrace",Trace.class);
		registerModule("byForm",ByForm.class);
		registerModule("byJson",ByJson.class);
		registerModule("setUri",SetURI.class);
		registerModule("setHeader",SetHeader.class);
		registerModule("getHeader",GetHeader.class);
		registerModule("asJson",AsJson.class);
		registerModule("result",Result.class);
		registerModule("copy",Copy.class);
	}
	
	@Override
	public void configure(Properties p) {
		super.configure(p);
		
		base = PropertiesConstants.getString(p,"base", "");
		cid = PropertiesConstants.getString(p,"cid",cid);
	}	

	@Override
	protected void onExecute(Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx, ExecuteWatcher watcher) {
		if (StringUtils.isNotEmpty(base)){
			try {
				ctx.setObject(cid, base);
				super.onExecute(root, current, ctx, watcher);
			}finally{
				ctx.removeObject(cid);
			}
		}
	}	
	
}
