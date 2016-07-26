package com.alogic.xscript.rest.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.rest.RequestHandler;
import com.anysoft.util.Properties;
import com.anysoft.util.XmlElementProperties;
import com.anysoft.util.XmlTools;
import com.anysoft.util.Pair;

/**
 * 向HttpRequest中加入form数据
 * 
 * @author duanyy
 *
 */
public class ByForm extends RequestHandler {
	/**
	 * 表单数据
	 */
	protected List<Pair<String,String>> formData = new ArrayList<Pair<String,String>>();
	
	/**
	 * entity builder
	 */
	protected EntityBuilder builder = EntityBuilder.create();	
	
	public ByForm(String tag, Logiclet p) {
		super(tag, p);
	}

	@Override
	public void configure(Element e, Properties p) {
		Properties props = new XmlElementProperties(e,p);
		configure(props);
		
		NodeList nodeList = XmlTools.getNodeListByPath(e, "field");
		
		for (int i = 0 ;i < nodeList.getLength() ; i ++){
			Node n = nodeList.item(i);
			if (n.getNodeType() != Node.ELEMENT_NODE){
				continue;
			}
			
			Element elem = (Element)e;
			String id = elem.getAttribute("id");
			String value = elem.getAttribute("value");
			if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(value)){
				formData.add(new Pair.Default<String,String>(id,value));
			}
		}
	}	
	
	@Override
	protected void onExecute(String base, HttpClient httpClient, HttpRequest httpRequest, Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx, ExecuteWatcher watcher) {
		if (httpRequest instanceof HttpEntityEnclosingRequestBase){
			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
			
			for (Pair<String,String> p:formData){
				String id = p.key();
				String value = p.value();
				nvps.add(new BasicNameValuePair(id,ctx.transform(value)));
			}
			
			HttpEntity entity = builder.setParameters(nvps).build();
			((HttpEntityEnclosingRequestBase)httpRequest).setEntity(entity);
		}
	}

}
