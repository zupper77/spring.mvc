package com.wemakeprice.common;

import java.util.Enumeration;
import java.util.Vector;

public class test {
	
	public static void main(String[]args){
		
		Vector<String> vc = new Vector<String>();
		
	    vc.add("Vector Object 1");
	    vc.add("Vector Object 2");
	    vc.add("Vector Object 3");
	    vc.add("Vector Object 4");
	    vc.add("Vector Object 5");
		
	    Enumeration<String> enu = vc.elements();
	    
	    while(enu.hasMoreElements()){
	    	String data = enu.nextElement();
	    	System.out.println(data);
	    	
	    }
	    
	    
	    
	    
	}
	

	
    
	
	
}
