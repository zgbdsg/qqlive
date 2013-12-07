package util;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/*
 * @autor zgb
 * 
 * we consider the url for tv or movie as a base url
 * and the source in the page is a item
 * this class can be used for initialize those resourses
 */

public class QQliveParser {
	public static Getpageinfo getpage;       //tool for get a page 
	public static List<StringBuffer> pageList;   //all the base page list
	public static List<StringBuffer> itemDatabase;     // all the item in the base url
	
	public QQliveParser(){
		getpage = new Getpageinfo();
		pageList = new ArrayList<StringBuffer>();
		initPageList();
		initItemUrlDataBase();
	}

	private void initPageList() {
		// TODO Auto-generated method stub
		StringBuffer index = new StringBuffer(
				getpage.getPage("http://cache.tv.qq.com/bigportal/index.html", getpage.GET));
		pageList = getListFromIndexPage(index);
		
	}
	
	//get all the base url
	//example: movie  tv music .....
	
	public static List<StringBuffer> getListFromIndexPage(StringBuffer page){
		
		try {
			Parser p = new Parser(page.toString());
			p.setEncoding("utf-8");
			NodeFilter filter = new HasAttributeFilter("data-curtab");
			NodeList list = p.extractAllNodesThatMatch(filter);
			
			System.out.println(list.size());
			
			List<StringBuffer> result = new ArrayList<StringBuffer>();
			for(int i=0;i<list.size();i ++){
				Node tmp = list.elementAt(i);
				TagNode tmptag = new TagNode();
				tmptag.setText(tmp.toHtml());
				
				System.out.println(tmptag.getAttribute("href"));
				
				result.add(new StringBuffer(tmptag.getAttribute("href")));
			}
			
			return result;
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//get all the item url
	//example: movie  tv music .....
	
	public static List<StringBuffer> getItemFromBaseUrl(StringBuffer page){
		
		try {
			Parser p = new Parser(page.toString());
			p.setEncoding("utf-8");
			NodeFilter filter = new HasAttributeFilter("class","img_wrapper");
			NodeList list = p.extractAllNodesThatMatch(filter);
			
			System.out.println(list.size());
			
			List<StringBuffer> result = new ArrayList<StringBuffer>();
			for(int i=0;i<list.size();i ++){
				Node tmp = list.elementAt(i);
				TagNode tmptag = new TagNode();
				tmptag.setText(tmp.toHtml());
				
				System.out.println(tmptag.getAttribute("href"));
				
				result.add(new StringBuffer(tmptag.getAttribute("href")));
			}
			
			return result;
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	private List<StringBuffer> initItemUrlDataBase(){
		
		//iterator all the base url
		for(int i=0;i < pageList.size();i ++){
			StringBuffer url = pageList.get(i);
			
			//dont deal the index page
			if(url.equals("http://cache.tv.qq.com/bigportal/index.html"))
				continue;
			
			//not a index page , has item resourses
			String tmp = url.toString();
			if(tmp.indexOf("movie") != -1){
				
			}else if(tmp.indexOf("tv") != -1){
				TVprocess tv = new TVprocess();
			}else if(tmp.indexOf("cartoon") != -1){
				
			}else if(tmp.indexOf("variety") != -1){
				
			}else if(tmp.indexOf("mv") != -1){
				
			}else if(tmp.indexOf("information") != -1){
				
			}else if(tmp.indexOf("sports") != -1){
				
			}else if(tmp.indexOf("game") != -1){
				
			}else if(tmp.indexOf("live") != -1){
				
			}else if(tmp.indexOf("doco") != -1){
				
			}else if(tmp.indexOf("film") != -1){
				
			}else if(tmp.indexOf("2012") != -1){
				
			}else if(tmp.indexOf("2013") != -1){
				
			}
			
		}
		return null;
	}

	private void dealBaseUrl(StringBuffer url) {
		// TODO Auto-generated method stub
		
	}
}
