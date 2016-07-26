package com.alogic.xscript.rest.method;

import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpUriRequest;

import com.alogic.xscript.Logiclet;
import com.alogic.xscript.rest.RestBase;

/**
 * patch 操作
 * 
 * @author duanyy
 *
 */
public class Patch extends RestBase {

	public Patch(String tag, Logiclet p) {
		super(tag, p);
	}

	@Override
	protected HttpUriRequest newHttpRequest(String uri) {
		return new HttpPatch(uri);
	}

}
