package util;

import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Parser;


public class Getpageinfo {
	CloseableHttpClient client;
	Parser spider;
	final int GET = 1;
	final int POST = 2;
	
	public Getpageinfo(){
		//client = HttpClients.custom()
				//.setDefaultCookieStore(getCookieSetting()).build();
		client = HttpClients.createDefault();
		spider = new Parser();
	}
	
	public String getPage(String url,int type){
		String responseBody = "";
		if(type == GET){
			HttpGet getpage = new HttpGet(url);
			//getpage.addHeader("Host", "cache.tv.qq.com");
			//getpage.addHeader("Proxy-Connection", "keep-alive");
			//getpage.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");
			//getpage.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			//getpage.addHeader("", "");
			System.out.println("executing request" +getpage.getRequestLine());
			try {
	            // Create a custom response handler
	            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

	                public String handleResponse(final HttpResponse response) 
	                		throws ClientProtocolException, IOException {
	                    int status = response.getStatusLine().getStatusCode();
	                    if (status >= 200 && status < 300) {
	                        HttpEntity entity = response.getEntity();
	                        return entity != null ? EntityUtils.toString(entity) : null;
	                    } else {
	                        throw new ClientProtocolException("Unexpected response status: " + status);
	                    }
	                }

	            };
	            responseBody = client.execute(getpage, responseHandler);
	            System.out.println("----------------------------------------");
	            //System.out.println(responseBody);
	            System.out.println("----------------------------------------");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type == POST){
			
		}
		
		return responseBody;
	}
	
	private CookieStore getCookieSetting(){
		// Create a local instance of cookie store
		CookieStore cookieStore = new BasicCookieStore();
		// Populate cookies if needed
		//  
		//pgv_pvid=6335439945; o_cookie=773784752; ts_uid=782639768
		BasicClientCookie cookie = new BasicClientCookie("name", "value");
		cookie.setAttribute("ptui_loginuin", "773784752");
		cookie.setAttribute("pt2gguin", "o0773784752");
		cookie.setAttribute("RK", "TGhOZOnrvP");
		cookie.setAttribute("ptcz", "f02cc4b1804dcda9e7163bf6becd57bd39a7ed0bf30141dff5ea26509d39a431");
		cookie.setAttribute("qqB_short", "1");
		cookie.setAttribute("pgv_info", "ssid=s3455256709");
		cookie.setAttribute("ts_last", "cache.tv.qq.com/bigportal/index.html");
		cookie.setAttribute("pgv_pvid", "6335439945");
		cookie.setAttribute("o_cookie", "773784752");
		cookie.setAttribute("ts_uid", "782639768");

		cookieStore.addCookie(cookie);
		return cookieStore;
	}
}
