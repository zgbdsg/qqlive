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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TVprocess extends BaseProcess{
	public StringBuffer baseurl;
	public static List<StringBuffer> vidList;
	public int pagenum;
	
	public TVprocess(){
		super();
		
		baseurl = new StringBuffer("http://sns.video.qq.com/fcgi-bin/dlib/dataout_pc?" +
				"auto_id=367&mi_type=-1&mi_area=-1&mi_year=-1&mi_show_type=0&mi_sort=0&pagesize=36&" +
				"otype=json&_=1386404857236&pagenum=");
		
		getPageNum();
		getAllPage();
	}

	public void getAllPage() {
		// TODO Auto-generated method stub
		
		for(int i=0;i<pagenum;i ++){
			StringBuffer index = new StringBuffer(
					getpage.getPage(baseurl.toString()+"i", getpage.GET));
			
			//System.out.println(index.substring(13,index.length()-1));
			
			JSONObject json = (JSONObject) JSON.parse(index.substring(13,index.length()-1));
			
			pagenum = json.getIntValue("total");
			
			JSONArray movies = json.getJSONArray("");
			System.out.println(json.getIntValue("costtime"));
		}
/*		try {
			p = new Parser(index.toString());
			p.setEncoding("utf-8");
			NodeFilter filter = new HasAttributeFilter("id","pager_last_0");
			NodeList list = p.extractAllNodesThatMatch(filter);
			
			System.out.println(list.size());
			
			if(list.size() != 1)
				System.out.println("number filter error");
			Node tmp = list.elementAt(0);
			TagNode tmptag = new TagNode();
			tmptag.setText(tmp.toHtml());
			
			System.out.println(tmptag.getAttribute("title"));

		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	@Override
	public void getPageNum() {
		// TODO Auto-generated method stub
		StringBuffer index = new StringBuffer(
				getpage.getPage(baseurl.toString()+"0", getpage.GET));
		
		//System.out.println(index.substring(13,index.length()-1));
		
		JSONObject json = (JSONObject) JSON.parse(index.substring(13,index.length()-1));
		
		int total = json.getIntValue("total");
		pagenum = total / 36 + 1;
	}
	
	 
}
