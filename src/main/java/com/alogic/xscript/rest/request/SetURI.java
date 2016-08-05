package com.alogic.xscript.rest.request;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alogic.xscript.ExecuteWatcher;
import com.alogic.xscript.Logiclet;
import com.alogic.xscript.LogicletContext;
import com.alogic.xscript.rest.RequestHandler;
import com.anysoft.util.Pair;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;
import com.anysoft.util.XmlElementProperties;
import com.anysoft.util.XmlTools;

/**
 * 重新设置uri
 * 
 * @author duanyy
 *
 */
public class SetURI extends RequestHandler {
	
	/**
	 * 路径
	 */
	protected String path;
	
	/**
	 * encoding
	 */
	protected String encoding = "utf-8";
	
	/**
	 * 参数
	 */
	protected List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	
	public SetURI(String tag, Logiclet p) {
		super(tag, p);
	}
	
	@Override
	public void configure(Properties p){
		super.configure(p);
		path = PropertiesConstants.getRaw(p,"path","");
		encoding = PropertiesConstants.getString(p,"http.encoding",encoding);
	}
	
	@Override
	public void configure(Element element, Properties props) {
		XmlElementProperties p = new XmlElementProperties(element, props);

		NodeList nodeList = XmlTools.getNodeListByPath(element, "param");
		for (int i = 0 ;i < nodeList.getLength() ; i ++){
			Node node = nodeList.item(i);
			
			if (Node.ELEMENT_NODE != node.getNodeType()){
				continue;
			}
			
			Element e = (Element)node;
			
			String id = e.getAttribute("id");
			String value = e.getAttribute("value");
			if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(value)){
				params.add(new Pair.Default<String, String>(id, value));
			}
		}
		
		configure(p);
	}		
	

	@Override
	protected void onExecute(String base, HttpClient httpClient, HttpRequest httpRequest, Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx, ExecuteWatcher watcher) {
		if (httpRequest instanceof HttpRequestBase){
			HttpRequestBase request = (HttpRequestBase)httpRequest;
			
			String p = ctx.transform(base + "/" + path);
			if (StringUtils.isEmpty(p)){
				p = request.getURI().toString();
			}
			
			if (!params.isEmpty()){
				StringBuffer buffer = new StringBuffer();
				for (Pair<String,String> pair:params){
					String value = ctx.transform(pair.value());
					if (StringUtils.isNotEmpty(value)){
						try {
							buffer.append("&").append(pair.key()).append("=").append(URLEncoder.encode(value,encoding));
						} catch (UnsupportedEncodingException e) {
							logger.error(e);
						}
					}
				}
				
				if (p.indexOf("?") < 0){
					p = p + "?";
				}
				
				p = p + buffer.toString();
			}
			
			request.setURI(URI.create(p));
		}
	}

}
