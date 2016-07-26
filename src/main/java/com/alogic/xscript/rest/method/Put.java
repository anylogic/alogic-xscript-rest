package com.alogic.xscript.rest.method;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;

import com.alogic.xscript.Logiclet;
import com.alogic.xscript.rest.RestBase;

/**
 * Put 操作
 * @author duanyy
 *
 */
public class Put extends RestBase {

	public Put(String tag, Logiclet p) {
		super(tag, p);
	}

	@Override
	protected HttpUriRequest newHttpRequest(String uri) {
		return new HttpPut(uri);
	}

}
