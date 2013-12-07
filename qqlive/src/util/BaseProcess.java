package util;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Parser;

public abstract class BaseProcess extends Thread{
	public StringBuffer baseurl;
	public static List<StringBuffer> vidList;
	public int pagenum;
	public Getpageinfo getpage;
	public Parser p;
	
	public BaseProcess(){
		getpage = new Getpageinfo();
		vidList = new ArrayList<StringBuffer>();
	}
	public void run(){
		
	}
	
	public abstract void getAllPage();
	public abstract void getPageNum();
}
