package com.wemakeprice.commons.lib.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import com.wemakeprice.vo.organization.OrganizationDTO;


/**
 * 2014.03.14
 * @author 개발3팀 유창근
 * 계층데이터 JSON으로 파싱하기위한 유틸 클래스
 */
public class FansyTreeJsonParsingUtil {
	
	private JSONArray jsonArray = new JSONArray();
	
	private Log log = LogFactory.getLog(this.getClass());
	
	
	public FansyTreeJsonParsingUtil(){
		super();
		//this.jsonArray = new JSONArray();
	}
	
	/**
	 * 데이터를 하위 구조에 맞게끔 삽입
	 * 조 건은 쿼리나 자바를 이용하여 하이라키 구조 데이터 순차적으로 뽑은후 해당 메서드 사용 할 것
	 * @param orgDto
	 */
	public void add(OrganizationDTO orgDto){
		
		if(orgDto.getParentSeq() == -1 || !this.fansyTreeChildCheck(this.jsonArray,orgDto)){
			fansyTreeParent_add(orgDto);
		}else{
			fansyTreeChild_add(orgDto , this.jsonArray);
		}
		
	}
	
	/**
	 * 해당 노드위에 상위 노드가 있는지 체크 
	 * @param jArray
	 * @param orgDto
	 * @return
	 */
	private boolean fansyTreeChildCheck(JSONArray jArray,OrganizationDTO orgDto) {
		// TODO Auto-generated method stub
		boolean flag = false;
		long childKey = orgDto.getParentSeq();
		
		Iterator<JSONObject> iter = jArray.iterator();
		while(iter.hasNext()){
			JSONObject jobj = iter.next();  
			long parentKey = Long.parseLong(jobj.get("key").toString());
			if(childKey == parentKey) {
				flag = true;
				break;
			}	
		}
		
		//한번더 검사를 진행해야 한다 즉 가장 상위노드에서는 JSONArray를 하나더 가지고 있기 때문에 그 객체까지 검사해야한다(재귀호출 방식 사용)
		Iterator<JSONObject> iter2 = jArray.iterator();
		while(iter2.hasNext()){
			JSONObject jobj = iter2.next();  
			JSONArray j_arr = (JSONArray)jobj.get("children");
			if(j_arr != null){
				this.fansyTreeChildCheck(j_arr,orgDto);
				flag = true;
			}
		}
		return flag;
	}
	
	
	
	private void fansyTreeParent_add(OrganizationDTO orgDto) {
		JSONObject o = new JSONObject();
		o.put("key", orgDto.getSeq());
		o.put("organizationCode", orgDto.getOrganizationCode());
		o.put("title", orgDto.getOrganizationName());
		o.put("parentCode", orgDto.getParentCode());
		o.put("parentKey", orgDto.getParentSeq());
		o.put("status", orgDto.getStatus());
		this.jsonArray.add(o);
	}
	
	/**
	 * 상위 노드가 존재하는 경우라면 해당 노드에
	 * 데이터를 JSONArray로 삽입해 줘야한다
	 * @param orgDto
	 * @param jArray
	 */
	private void fansyTreeChild_add(OrganizationDTO orgDto, JSONArray jArray) {
		long childKey = orgDto.getParentSeq();
		JSONArray new_jArray = null;
		JSONObject new_jobj = null;
		Iterator<JSONObject> iter = jArray.iterator();
		
		while(iter.hasNext()){
			JSONObject jobj = iter.next();
			long parentKey = Long.parseLong(jobj.get("key").toString());
			if(childKey == parentKey) {
				//log.info("====>" + jobj.toJSONString());
				new_jobj = new JSONObject();
				
				new_jobj.put("key", orgDto.getSeq());
				new_jobj.put("organizationCode", orgDto.getOrganizationCode());
				new_jobj.put("title", orgDto.getOrganizationName());
				new_jobj.put("parentCode", orgDto.getParentCode());
				new_jobj.put("parentKey", orgDto.getParentSeq());
				new_jobj.put("status", orgDto.getStatus());
				
				//먼저 상위 노드 데이터 folder true 추가후 JSONArray 객체 있는지 확인하여 없다면 생성
				jobj.put("folder", true);
				
				//상위노드 JSONArray생성 되어있는지 확인
				JSONArray parentArray = (JSONArray)jobj.get("children");
				
				if(parentArray == null){
					new_jArray = new JSONArray();
					new_jArray.add(new_jobj);
					jobj.put("children", new_jArray);//하위노드 담을 JSONArray생성
				}else{
					parentArray.add(new_jobj);
				}
				break;
			}
		}
		//다시한번 재귀로 호출 자식노드가 있다면 다시 해당 노드부터 데이터를 JSONArray로 삽입해 줘야 하므로
		
			Iterator<JSONObject> iter2 = jArray.iterator();
			while(iter2.hasNext()){
				JSONObject jobj = iter2.next();  
				JSONArray j_arr = (JSONArray)jobj.get("children");
				if(j_arr != null){
					this.fansyTreeChild_add(orgDto, j_arr);
				}
			}	
			
	}



	public int size(){
		return this.jsonArray.size();
	}
	
	
	public String toJSONString(){
		return this.jsonArray.toJSONString();
	}
	
	
	
	
	
	
	
	
	/*
	public void testAdd(){
		JSONObject jobj = new JSONObject();
		jobj.put("key", 1);
		jobj.put("title", "test1");
		
		log.info("add Hashcode==>" + jobj.hashCode());
		
		this.jsonArray.add(jobj);
		log.info("add jsonArray toString=>" + this.jsonArray.toJSONString());
	}   
	
	public void testGet(){
		log.info("=================>" + this.jsonArray.size());	
		JSONObject jobj = (JSONObject)this.jsonArray.get(0);
		log.info("get Hashcode==>" + jobj.hashCode());
		
		jobj.put("title", "test22222");
		
		this.jsonArray.remove(jobj);
		this.jsonArray.add(jobj);
		         
		log.info("get jsonArray toString=>" + this.jsonArray.toJSONString());
		
	}
	*/
	
}
