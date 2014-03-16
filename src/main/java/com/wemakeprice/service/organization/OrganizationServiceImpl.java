package com.wemakeprice.service.organization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemakeprice.dao.organization.OrganizationDao;
import com.wemakeprice.vo.organization.OrganizationDTO;

@Service
public class OrganizationServiceImpl implements OrganizationService {
		
	//트리를 그리기위한 전역 ArrayList변수
	private ArrayList<OrganizationDTO> dataList = null;
	//트리의 상위노드 체크
	private ArrayList seqs = null;
	
	@Autowired
	private OrganizationDao orgranizationDao;
	
	
	@Override
	public synchronized List<OrganizationDTO> getOrganizationTreeList(long seq) throws Exception {
		// TODO Auto-generated method stub
		this.dataList = new ArrayList<OrganizationDTO>();
		this.seqs = new ArrayList();
		
		List<OrganizationDTO> dataList_v = this.hieraryDataList(seq);
		
		Iterator<OrganizationDTO> iter = dataList_v.iterator();
		while(iter.hasNext()){
			OrganizationDTO resultDto = iter.next();		
			this.hierarchyData(resultDto);
			this.seqs.removeAll(this.seqs);
		}
		
		return this.dataList;
	}
	
	
	
	/**
	 * 재귀적 함수 호출을 통하여 hierarchy 데이터를 그려낸다
	 * @param dataDto
	 */
	private void hierarchyData(OrganizationDTO dataDto) throws Exception {			
		this.dataList.add(dataDto);

		long seq = dataDto.getSeq();
		//seq를 이용하여 하위 데이터가 먼저 있는지 검사해야 한다.
		int hierary_chk = (int)this.orgranizationDao.selectChildNodeCheck("org.selectHierary_chk", seq);
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
				child_chk = (int)this.orgranizationDao.selectChildNodeCheck("org.selectHierary_chk", h_resultDto.getSeq());
				
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
	private List<OrganizationDTO> hieraryDataList(long seq) throws Exception {
		return this.orgranizationDao.selectOrganization_List("org.selectOrgInfo", seq);
	}


	
	
	
}
