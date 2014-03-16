package com.wemakeprice.controller.organization;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wemakeprice.controller.HomeController;
import com.wemakeprice.vo.organization.OrganizationDTO;
import com.wemakeprice.service.organization.OrganizationService;
import com.wemakeprice.vo.info.Info;

@Controller
@RequestMapping("/organization")
public class OrganizationController {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private OrganizationService organizationService;
	
	@RequestMapping(value = "/organization_dTreeView", method = RequestMethod.GET)
	public String organizationDtreeView(Locale locale, Model model , HttpServletRequest request, HttpServletResponse response) {
		return "organization/organization_dTree";
	}
	
	 @RequestMapping(value="/organization_dTreeList", method = {RequestMethod.GET,RequestMethod.POST})
	 public List<OrganizationDTO> organizationTreeList(HttpServletRequest request, HttpServletResponse response , Model model){
		 
		long seq = -1;//parameter 로 받을수도 있고 처음루트는 0이므로 그냥 하드코딩
		
		List<OrganizationDTO> organizationTree = null;
		try{
			organizationTree = this.organizationService.getOrganizationTreeList(seq);
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
		}finally{}
		
		log.info("===result===>" + organizationTree.size());
		
	  return organizationTree;
	 }
	
	
	@RequestMapping(value = "/organization_fancytreeView", method = RequestMethod.GET)
	public String organizationjQueryTreeView(Locale locale, Model model , HttpServletRequest request, HttpServletResponse response) {
		return "organization/organization_fancytree";
	}
	 
	 @RequestMapping(value="/organization_fansyTreeList", method = {RequestMethod.GET,RequestMethod.POST})
	 public ModelAndView organization_fansyTreeList(HttpServletRequest request, HttpServletResponse response , Model model){
		 
		long seq = -1;//parameter 로 받을수도 있고 처음루트는 0이므로 그냥 하드코딩
		
		ModelAndView modelView = new ModelAndView();
		HashMap modelMap = new HashMap();
		
		//layzy모드를 했을 경우에만 넘어온다
		System.out.println("key=========>"  + request.getParameter("key"));
		
		List<OrganizationDTO> organizationTree = null;
		try{
			organizationTree = this.organizationService.getOrganizationTreeList(seq);
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
		}finally{}
		
		//log.info("===result===>" + organizationTree.toString());
		modelMap.put("dataList", organizationTree);
		modelView.addAllObjects(modelMap);
		
		modelView.setViewName("fansyTreeToJson");
		
	  return modelView;
	 }
	
	 
	 
	 
	 
	
	
	
}
