package com.wemakeprice.commons.lib.interceptor.tiles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springbyexample.web.servlet.view.tiles2.TilesUrlBasedViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class GosTilesInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private TilesUrlBasedViewResolver tilesViewResolver;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/*
	 * 기본생성자
	 */
	public GosTilesInterceptor(){
		super();
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
		//System.out.println("Business Intercepotr afterCompletion call");
		return;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler)throws Exception{
		//System.out.println("Business Intercepotr preHandle call");
		//ArrayList list3 = (ArrayList)daoService3.getListResult("neomodule", "test", new HashMap());
		//logger.info("neomodule=" + list3.size());
		
		//System.out.println("preHan");
		logger.info("tilesView DI=>" + this.tilesViewResolver.getClass().getName());
		this.tilesViewResolver.setTilesDefinitionName("default");//tiles 탬플릿적용
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		//System.out.println("Business Intercepotr postHandle call");
		//System.out.println("Business DynamicTiles DI Class=" + this.tilesViewResolver.getClass().getName());
		//System.out.println("postHan");

		
		return;
	}
	
	
}
