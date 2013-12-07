package util;

import java.util.ArrayList;
import java.util.List;

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
		vidList = new ArrayList<StringBuffer>();
		getPageNum();
		getAllPage();
	}

	public void getAllPage() {
		// TODO Auto-generated method stub
		
		for(int i=0;i<pagenum;i ++){
			System.out.println("begin deal the "+i+" th page in tv");
			
			StringBuffer index = new StringBuffer(
					getpage.getPage(baseurl.toString()+i, getpage.GET));
			
			//System.out.println(index.substring(13,index.length()-1));
			
			JSONObject json = (JSONObject) JSON.parse(index.substring(13,index.length()-1));
			
			JSONArray movies = json.getJSONArray("movies");
			System.out.println("this page costtime:  "+json.getIntValue("costtime") );
			System.out.println("this page has "+movies.size()+"  movies");
			
			for(int j=0;j<movies.size();j ++){
				//get all tvs
				JSONObject movie = movies.getJSONObject(j);
				JSONArray vids = movie.getJSONArray("video");
				
				//get all vid in the tv
				System.out.println("this tv has "+vids.size()+"  video");
				for(int z=0;z<vids.size();z ++){
					JSONObject vid = vids.getJSONObject(z);
					//System.out.println(vid.toJSONString());
					vidList.add(new StringBuffer(vid.getString("vid")));
					
				}
			}
		}
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
		
		System.out.println("TV has "+pagenum+"  pages");
	}

	@Override
	public void getTrueUrl() {
		// TODO Auto-generated method stub
		String base = "http://vv.video.qq.com/geturl?otype=xml&platform=1&" +
				"ran=0%2E9652906153351068&vid=";
		for(int i=0;i<vidList.size();i ++){
			
		}
	}
	
}
