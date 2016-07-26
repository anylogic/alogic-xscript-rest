package com.alogic.xscript.rest.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.rest.ResponseHandler;
import com.anysoft.util.BaseException;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;
import com.anysoft.util.XmlElementProperties;
import com.jayway.jsonpath.spi.JsonProvider;
import com.jayway.jsonpath.spi.JsonProviderFactory;


/**
 * reponse作为Json文档
 * 
 * @author duanyy
 *
 */
public class AsJson extends ResponseHandler {
	protected String jsonId = "json";
	/**
	 * 子节点
	 */
	protected List<Logiclet> children = new ArrayList<Logiclet>(); // NOSONAR
	protected static JsonProvider provider = null;	
	static {
		provider = JsonProviderFactory.createProvider();
	}
	
	public AsJson(String tag, Logiclet p) {
		super(tag, p);
	}

	public void configure(Properties p){
		super.configure(p);
		jsonId = PropertiesConstants.getString(p,"jsonId",jsonId,true);
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
	protected void onExecute(String base, HttpClient httpClient, HttpResponse httpResponse, Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx, ExecuteWatcher watcher) {
		HttpEntity entity = httpResponse.getEntity();
		
		if (entity != null){
			try {
				@SuppressWarnings("unchecked")
				Map<String,Object> json = (Map<String, Object>) provider.parse(entity.getContent());
				ctx.setObject(jsonId, json);
				
				List<Logiclet> list = children;

				for (int i = 0 ; i < list.size(); i ++){
					Logiclet logiclet = list.get(i);
					if (logiclet != null){
						logiclet.execute(root,current,ctx,watcher);
					}
				}				
				
			}catch (BaseException ex){
				throw ex;
			}catch (Exception ex){
				current.put("error", "Can not parse json content.");
			}finally{
				ctx.removeObject(jsonId);
			}
		}
	}

}
