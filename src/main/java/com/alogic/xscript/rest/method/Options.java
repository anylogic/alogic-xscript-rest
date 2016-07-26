package com.alogic.xscript.rest.method;

import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpUriRequest;

import com.alogic.xscript.Logiclet;
import com.alogic.xscript.rest.RestBase;

/**
 * Options操作
 * 
 * @author duanyy
 *
 */
public class Options extends RestBase {
	public Options(String tag, Logiclet p) {
		super(tag, p);
	}

	@Override
	protected HttpUriRequest newHttpRequest(String uri) {
		return new HttpOptions(uri);
	}

}
