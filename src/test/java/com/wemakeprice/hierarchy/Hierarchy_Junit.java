package com.wemakeprice.hierarchy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.wemakeprice.hierarchy.dao.BaseDao;
import com.wemakeprice.hierarchy.vo.OrganizationDTO;

/**
 * 개발3팀 유창근
 * Junit 단위 테스트 개발 클래스
 * @author youchangkeun
 *
 */
@ContextConfiguration(locations = {
"classpath:com/wemakeprice/hierarchy/context-jta.xml",
"classpath:com/wemakeprice/hierarchy/servlet-context.xml",
"classpath:com/wemakeprice/hierarchy/mybatis-context.xml"
})
public class Hierarchy_Junit extends Hierachy_Junit_Abstract {
	
	private ArrayList<OrganizationDTO> dataList = null;
	private ArrayList seqs = null;
	
	
	public void start(){
		this.dataList = new ArrayList<OrganizationDTO>();
		this.seqs = new ArrayList();
		logger.info("=======================================Hierarchy Data Console Print Start=======================================");
	}
	
	public void run_Test(){
		//logger.info("test.." + this.baseDao.getClass().getName());
		//HashMap params = new HashMap();
		//params.put("id", 0);
		long seq = 0;
		
		List<OrganizationDTO> dataList_v = this.hieraryDataList(seq);
		
		Iterator<OrganizationDTO> iter = dataList_v.iterator();
		while(iter.hasNext()){
			OrganizationDTO resultDto = iter.next();		
			this.hierarchyData(resultDto);
			this.seqs.removeAll(this.seqs);
		}
		
		//System.out.println(dataList.toString());
		
	}
	
	/**
	 * 재귀적 함수 호출을 통하여 hierarchy 데이터를 그려낸다
	 * @param dataDto
	 */
	private void hierarchyData(OrganizationDTO dataDto){			
		this.dataList.add(dataDto);

		long seq = dataDto.getSeq();
		//seq를 이용하여 하위 데이터가 먼저 있는지 검사해야 한다.
		int hierary_chk = (int)super.baseDao.selectOne("org.selectHierary_chk", seq);
		//logger.info("yn====>" + hierary_chk);
		//하이라키 데이터가 존재한다면
		if(hierary_chk > 0){
			
			List<OrganizationDTO> h_dataList = this.hieraryDataList(seq);
			Iterator<OrganizationDTO> h_iter = h_dataList.iterator();//FOR문으로 변경@@@@@@@@@@@
			int child_chk = 0;
			int last_chk = 0;
			int parent_cnt = 1;//배열의 다음번째 수 참조
			
			while(h_iter.hasNext()){
				OrganizationDTO h_resultDto = h_iter.next();	
				//다시한번 자식 노드가 있는지 확인
				child_chk = (int)super.baseDao.selectOne("org.selectHierary_chk", h_resultDto.getSeq());
				
				if(child_chk > 0){//다시 자식노드가 있을경우에는 dataList에 담긴 seq 전역배열에 저장후 해당 객체는 다시 재귀호출로 넘긴다 
					//h_dataList.remove(0);//첫번째 데이터는 삭제한다 재귀호출을 할 것이기 떄문에	
					
					for(int i = parent_cnt; i < h_dataList.size(); i++){
						OrganizationDTO parentOrgDto = h_dataList.get(i);	
						this.seqs.add(parentOrgDto.getSeq());
					}
					
					this.hierarchyData(h_resultDto);//while루프 탈출 재귀호출
					
				}else{//데이터 담기 시작한다
						OrganizationDTO lastOrgDto = h_dataList.get(last_chk);
						this.dataList.add(lastOrgDto);
						//h_dataList.remove(0);//삽입한 데이터는 반드시 지워준다
				}
				last_chk++;
				parent_cnt++;
			}
				
		}
		//펑션의 기능이 끈나는 시점에서는 다시 상위 데이터를 바로 보기 때문에 다시 루프를 돌면서 재귀호출이 가능함..
	}
	
	// 데이터 리턴
	private List<OrganizationDTO> hieraryDataList(long seq){
		return super.baseDao.selectList("org.selectOrgInfo", seq);
	}
	
	
	
	
	public void end(){
		logger.info("=========================================Hierarchy Data Console Print End=========================================" + this.dataList.toString());
	}
	
	@PostConstruct
	public void test(){
		logger.info("@PostConstruct");
	}
	
	
	  
	
	
}
