package com.wemakeprice.commons.views;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import com.wemakeprice.commons.lib.utility.FansyTreeJsonParsingUtil;
import com.wemakeprice.vo.organization.OrganizationDTO;

/**
 * 2014.03.14 fansytree custom jsonview
 * 개발3팀 유창근
 * @author youchangkeun
 *
 */
public class OutputFancyTreeToJson extends AbstractView {
	
	private PrintWriter out;
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		try{
			response.setContentType("application/json;charset=utf-8");//json
			response.setCharacterEncoding("utf-8");
			//response.setContentType("text/plain;charset=utf-8");//json 
			out = response.getWriter();	
			
			//System.out.println("OutToPutJOSNCLASS=" + map.toString());
			//System.out.println("@@@@@=" + createJson((HashMap)map));
			out.println(createJson((HashMap)model));	
			
		}catch(Throwable e){
			log.error(e.getMessage());   
			e.printStackTrace();   
		}finally{    
			out.flush();       
			out.close();        
		}
		
		
	}
	
	private String createJson(HashMap map) {
		// TODO Auto-generated method stub
		ArrayList<OrganizationDTO> dataList = (ArrayList)map.get("dataList");    
		int maxSize = dataList.size();
		FansyTreeJsonParsingUtil futil = new FansyTreeJsonParsingUtil();
		
		for(int i = 0; i < maxSize; i++){
			OrganizationDTO resultDto = dataList.get(i);
			//log.info(resultDto.toString());
			futil.add(resultDto);
			
		}
		//log.info(futil.toJSONString());
		
		return futil.toJSONString();
	}
	
	
	/**
	 * json parsing test code
	 */
	/*
	private String createJson(HashMap map) {
		// TODO Auto-generated method stub
		StringBuilder sb  = new StringBuilder();
		JSONObject jobj_1 = new JSONObject();
		JSONObject jobj_2 = new JSONObject();
		
		JSONArray jarray = new JSONArray();
		
		JSONArray childArr_1 = new JSONArray();
		JSONObject jobj_3 = new JSONObject();
		JSONObject jobj_4 = new JSONObject();
		JSONObject jobj_5 = new JSONObject();
		JSONObject jobj_6 = new JSONObject();
		
		ArrayList<HashMap<String,Object>> dataList = (ArrayList)map.get("dataList");
		
		//sb.append("[");
		//	sb.append("{");
		//		sb.append("\"").append("title").append("\"").append(":").append("\"").append("Node 1").append("\",");
		//		sb.append("\"").append("key").append("\"").append(":").append("\"").append(1).append("\"");
		//	sb.append("},");
		//sb.append("]");
		
		//"lazy": true
		jobj_1.put("title", "Node 1");
		jobj_1.put("testkey1", "testVaule1");
		jobj_1.put("testkey2", "testVaule2");
		jobj_1.put("key", 1);
		
		jobj_2.put("title", "Folder 2");
		jobj_2.put("key", 2);
		jobj_2.put("folder", true);
	
		//layzy모드 사용할 경우
		jobj_5.put("title", "lazy test");
		jobj_5.put("key", 5);
		jobj_5.put("lazy", true);
		jobj_5.put("folder", true);
		
		jobj_3.put("title", "Node 2.1");
		jobj_3.put("key", 3);
		
		jobj_4.put("title", "Node 2.2");           
		jobj_4.put("key", 4);
		
		//하위 데이터가 존재 할 경우
		childArr_1.add(jobj_3);
		childArr_1.add(jobj_4);
		
		
		jobj_2.put("children", childArr_1);
		
		JSONArray getArray = (JSONArray)jobj_2.get("children");
		jobj_6.put("key", 6);
		jobj_6.put("title" , "Node 2.3");
		getArray.add(jobj_6);
		
		
		jarray.add(jobj_1);
		jarray.add(jobj_2);
		jarray.add(jobj_5);
		
		return jarray.toJSONString();
	}
	*/



}
