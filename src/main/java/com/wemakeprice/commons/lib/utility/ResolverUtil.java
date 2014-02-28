package com.wemakeprice.commons.lib.utility;

public class ResolverUtil {

	/**
	 * 요청한  response_type 으로 Viewer를 결정함 
	 * @param String response_type
	 * @return String viewerString
	 */
    public static String selectViewer( String response_type ){  
       
    	if( response_type.matches( ".*jsonp.*") ){
    		return "jsonpViewer";
    	} else if( response_type.matches( ".*xml.*") ){
    		return "xmlViewer";
    	}
        return "jsonViewer";  
    }

}
