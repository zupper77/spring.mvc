package com.wemakeprice.service.organization;

import java.util.List;

import com.wemakeprice.vo.organization.OrganizationDTO;

public interface OrganizationService {

	public List<OrganizationDTO> getOrganizationTreeList(long seq) throws Exception;

}
