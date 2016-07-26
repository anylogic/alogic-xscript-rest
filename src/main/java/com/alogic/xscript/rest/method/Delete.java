package com.alogic.xscript.rest.method;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpUriRequest;

import com.alogic.xscript.Logiclet;
import com.alogic.xscript.rest.RestBase;


/**
 * Delete 操作
 * @author duanyy
 *
 */
public class Delete extends RestBase {

	public Delete(String tag, Logiclet p) {
		super(tag, p);
	}

	@Override
	protected HttpUriRequest newHttpRequest(String uri) {
		return new HttpDelete(uri);
	}

}
