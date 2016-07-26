package com.alogic.xscript.rest.method;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import com.alogic.xscript.Logiclet;
import com.alogic.xscript.rest.RestBase;

/**
 * get 操作
 * 
 * @author duanyy
 *
 */
public class Get extends RestBase {
	public Get(String tag, Logiclet p) {
		super(tag, p);
	}

	@Override
	protected HttpUriRequest newHttpRequest(String uri) {
		return new HttpGet(uri);
	}

}
