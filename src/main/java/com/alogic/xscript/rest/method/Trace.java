package com.alogic.xscript.rest.method;

import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;

import com.alogic.xscript.Logiclet;
import com.alogic.xscript.rest.RestBase;

/**
 * Trace操作
 * 
 * @author duanyy
 *
 */
public class Trace extends RestBase {

	public Trace(String tag, Logiclet p) {
		super(tag, p);
	}

	@Override
	protected HttpUriRequest newHttpRequest(String uri) {
		return new HttpTrace(uri);
	}

}
