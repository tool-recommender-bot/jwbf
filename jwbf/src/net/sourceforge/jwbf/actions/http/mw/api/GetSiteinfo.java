package net.sourceforge.jwbf.actions.http.mw.api;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

import org.apache.commons.httpclient.methods.GetMethod;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import net.sourceforge.jwbf.actions.http.mw.MWAction;
import net.sourceforge.jwbf.bots.MediaWikiBot;
import net.sourceforge.jwbf.contentRep.mw.SimpleArticle;
import net.sourceforge.jwbf.contentRep.mw.Siteinfo;

public class GetSiteinfo extends MWAction {
	
	
	private Siteinfo site = new Siteinfo();
	
	public GetSiteinfo() {
		msgs.add(new GetMethod("/api.php?action=query&meta=siteinfo&format=xml"));
	}
	
	/**
	 * @param s
	 *            the returning text
	 * @return empty string
	 * 
	 */
	public String processAllReturningText(final String s) {
		parse(s);
		return "";
	}
	
	private void parse(final String xml){
		SAXBuilder builder = new SAXBuilder();
		Element root = null;
		try {
			Reader i = new StringReader(xml);
			Document doc = builder.build(new InputSource(i));
			
			root = doc.getRootElement();

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		findContent(root);
	}
	
	@SuppressWarnings("unchecked")
	private void findContent(final Element root) {
		
		Iterator<Element> el = root.getChildren().iterator();
		while (el.hasNext()) {
			Element element = (Element) el.next();
			if (element.getQualifiedName().equalsIgnoreCase("general")) {
				
				
				site.setMainpage(encodeUtf8(element.getAttributeValue("mainpage")));
				site.setBase(encodeUtf8(element.getAttributeValue("base")));
				site.setSitename(encodeUtf8(element.getAttributeValue("sitename")));
				site.setGenerator(encodeUtf8(element.getAttributeValue("generator")));
				site.setCase(encodeUtf8(element.getAttributeValue("case")));
				site.setRights(encodeUtf8(element.getAttributeValue("rights")));
				
				
				
				
				
			} else {
				findContent(element);
			}
			
		}
	}
	
	public Siteinfo getSiteinfo() {
		return site;
	}

}
