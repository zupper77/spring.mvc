package com.wemakeprice.dao.organization;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.wemakeprice.dao.BaseDao;
import com.wemakeprice.vo.organization.OrganizationDTO;

@Repository
public class OrganizationDaoImpl extends BaseDao implements OrganizationDao  {

	@Override
	public int selectChildNodeCheck(String queryId, long seq) throws Exception {
		// TODO Auto-generated method stub
		int nodeCnt = (int)super.selectOne(queryId, seq);
		return nodeCnt;
	}

	@Override
	public List<OrganizationDTO> selectOrganization_List(String queryId,long seq) throws Exception {
		// TODO Auto-generated method stub
		return super.selectList(queryId, seq);
	}
	
	
	
}
