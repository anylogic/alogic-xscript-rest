package com.alogic.xscript.rest.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.rest.RequestHandler;
import com.anysoft.util.Properties;
import com.anysoft.util.XmlElementProperties;
import com.jayway.jsonpath.spi.JsonProvider;
import com.jayway.jsonpath.spi.JsonProviderFactory;

/**
 * 向Request中加入Json文档
 * 
 * @author duanyy
 *
 */
public class ByJson extends RequestHandler {
	protected static JsonProvider provider = null;	
	static {
		provider = JsonProviderFactory.createProvider();
	}
	
	/**
	 * entity builder
	 */
	protected EntityBuilder builder = EntityBuilder.create();
	
	/**
	 * 子节点
	 */
	protected List<Logiclet> children = new ArrayList<Logiclet>(); // NOSONAR
	
	public ByJson(String tag, Logiclet p) {
		super(tag, p);
	}

	@Override
	public void configure(Element element, Properties props) {
		XmlElementProperties p = new XmlElementProperties(element, props);
		NodeList nodeList = element.getChildNodes();
		
		for (int i = 0 ; i < nodeList.getLength() ; i ++){
			Node n = nodeList.item(i);
			
			if (n.getNodeType() != Node.ELEMENT_NODE){
				//只处理Element节点
				continue;
			}
			
			Element e = (Element)n;
			String xmlTag = e.getNodeName();		
			Logiclet statement = createLogiclet(xmlTag, this);
			
			if (statement != null){
				statement.configure(e, p);
				children.add(statement);
			}
		}
		
		configure(p);
	}		
	
	@Override
	protected void onExecute(String base, HttpClient httpClient, HttpRequest httpRequest, Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx, ExecuteWatcher watcher) {
		if (httpRequest instanceof HttpEntityEnclosingRequestBase){
			Map<String,Object> jsonData = new HashMap<String,Object>();
			
			for (Logiclet child:children){
				child.execute(jsonData, jsonData, ctx, watcher);
			}
			
			String text = provider.toJson(jsonData);
			HttpEntity entity = builder.setText(text).build();
			((HttpEntityEnclosingRequestBase)httpRequest).setEntity(entity);
		}
	}

}
