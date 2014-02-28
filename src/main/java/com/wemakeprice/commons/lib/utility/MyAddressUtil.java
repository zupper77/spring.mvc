package com.wemakeprice.commons.lib.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddressUtil {

	/**
	 * 나의 아이피 주소 확인 
	 * @return String address
	 * @throws Exception
	 */	
	public static String myAddress( ) throws Exception {
		try {
			InetAddress local = InetAddress.getLocalHost();
			return local.getHostAddress();
		} catch(UnknownHostException e) {
			return "127.0.0.1";
		}
		
	}

	/**
	 * 나의 호스트명 확인 
	 * @return String address
	 * @throws Exception
	 */	
	public static String myHostname( ) throws Exception {
		try {
			InetAddress local = InetAddress.getLocalHost();
			return local.getHostName();
		} catch(UnknownHostException e) {
			return "127.0.0.1";
		}
	}

}
