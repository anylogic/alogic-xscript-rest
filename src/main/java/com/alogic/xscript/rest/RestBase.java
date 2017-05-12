package com.alogic.xscript.rest;


import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.doc.XsObject;
import com.anysoft.util.BaseException;
import com.anysoft.util.IOTools;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;
import com.anysoft.util.XmlElementProperties;
import com.anysoft.util.XmlTools;


/**
 * rest操作基类
 * 
 * @author duanyy
 *
 */
public abstract class RestBase extends DomainOperation{

	/**
	 * request handlers
	 */
	protected List<Logiclet> requestHandler = null;
	
	/**
	 * response handlers
	 */
	protected List<Logiclet> responseHandler = null;
	
	protected String path;
	
	protected String httpClientId = "httpClient";
	
	protected String httpRequestId = "httpRequest";	
	
	protected String httpResponseId = "httpResponse";	
	
	public RestBase(String tag, Logiclet p) {
		super(tag, p);
	}
	
	public void configure(Properties p){
		super.configure(p);
		path = p.GetValue("path", "", false, true);		
		httpClientId = PropertiesConstants.getString(p,"httpClientId",httpClientId,true);
		httpRequestId = PropertiesConstants.getString(p,"httpRequestId",httpRequestId,true);		
		httpResponseId = PropertiesConstants.getString(p,"httpResponseId",httpResponseId);		
	}
	
	protected Logiclet[] getRequestHandlers(){
		return requestHandler == null ? new Logiclet[0] : requestHandler.toArray(new Logiclet[requestHandler.size()]);
	}
	
	protected Logiclet[] getResponseHandlers(){
		return responseHandler == null ? new Logiclet[0] : responseHandler.toArray(new Logiclet[responseHandler.size()]);
	}	

	@Override
	public void configure(Element e, Properties p) {
		Properties props = new XmlElementProperties(e,p);
		configure(props);
		
		Element request = XmlTools.getFirstElementByPath(e, "request");
		if (request != null){
			requestHandler = new ArrayList<Logiclet>();
			
			NodeList children = request.getChildNodes();
			
			for (int i = 0 ; i < children.getLength() ; i ++){
				Node n = children.item(i);
				
				if (n.getNodeType() != Node.ELEMENT_NODE){
					//只处理Element节点
					continue;
				}
				
				Element elem = (Element)n;
				String xmlTag = elem.getNodeName();		
				Logiclet statement = createLogiclet(xmlTag, this);
				
				if (statement != null){
					statement.configure(elem, props);
					requestHandler.add(statement);
				}
			}
		}
		
		Element response = XmlTools.getFirstElementByPath(e, "response");
		if (response != null){
			responseHandler = new ArrayList<Logiclet>();
			
			NodeList children = response.getChildNodes();
			
			for (int i = 0 ; i < children.getLength() ; i ++){
				Node n = children.item(i);
				
				if (n.getNodeType() != Node.ELEMENT_NODE){
					//只处理Element节点
					continue;
				}
				
				Element elem = (Element)n;
				String xmlTag = elem.getNodeName();		
				Logiclet statement = createLogiclet(xmlTag, this);
				
				if (statement != null){
					statement.configure(elem, props);
					responseHandler.add(statement);
				}
			}
		}		
	}

	@Override
	protected void onExecute(String base, XsObject root,XsObject current,
			LogicletContext ctx, ExecuteWatcher watcher) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String uri = ctx.transform(base + "/" + path);
		HttpUriRequest httpRequest = newHttpRequest(uri);
		CloseableHttpResponse httpResponse = null;
		try {
			ctx.setObject(httpClientId,httpclient);
			ctx.setObject(httpRequestId, httpRequest);
			
			Logiclet [] reqHandlers = getRequestHandlers();
			for (Logiclet h:reqHandlers){
				h.execute(root, current, ctx, watcher);
			}
			
			httpResponse = httpclient.execute(httpRequest);
			ctx.setObject(httpResponseId,httpResponse);
			
			Logiclet[] resHandlers = getResponseHandlers();
			for (Logiclet h:resHandlers){
				h.execute(root, current, ctx, watcher);
			}
		} catch (Exception e) {
			throw new BaseException("core.http_failed",e.getMessage());
		} finally {
			ctx.removeObject(httpClientId);
			ctx.removeObject(httpRequestId);
			ctx.removeObject(httpResponseId);
			IOTools.close(httpResponse);
		    IOTools.close(httpclient);
		}
	}	
	
	protected abstract HttpUriRequest newHttpRequest(String uri);
}