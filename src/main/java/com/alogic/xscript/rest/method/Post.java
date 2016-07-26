package com.alogic.xscript.rest.method;


import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

import com.alogic.xscript.Logiclet;
import com.alogic.xscript.rest.RestBase;

/**
 * post操作
 * @author duanyy
 *
 */
public class Post extends RestBase {

	public Post(String tag, Logiclet p) {
		super(tag, p);
	}

	@Override
	protected HttpUriRequest newHttpRequest(String uri) {
		return new HttpPost(uri);
	}
}
