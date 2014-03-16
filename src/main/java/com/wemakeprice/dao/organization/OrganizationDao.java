package com.wemakeprice.dao.organization;

import java.util.List;

import com.wemakeprice.vo.organization.OrganizationDTO;

public interface OrganizationDao {

	public int selectChildNodeCheck(String string, long seq) throws Exception;
	
	public List<OrganizationDTO> selectOrganization_List(String queryId, long seq) throws Exception ;

}
