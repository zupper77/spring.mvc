package com.wemakeprice.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wemakeprice.vo.board.BoardVO;
import com.wemakeprice.vo.info.Info;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@ModelAttribute("searchTeamList")
	public String[] getSearchTeamList(){
		return new String[]{"개발1팀","개발2팀","개발3팀"};
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		
		
		return "index";
	}
	 
	
	 @RequestMapping(value="/mappingJacksonJsonView", method = {RequestMethod.GET,RequestMethod.POST})
	 public List<BoardVO> mappingJacksonJsonView_Example(HttpServletRequest request, HttpServletResponse response , Model model){
	  System.out.println("----- mappingJacksonJsonView_Example -----");
	  
	  List<BoardVO> list = new ArrayList<BoardVO>();
	  ModelAndView modelAndView = new ModelAndView();
	  
	  
	  BoardVO b_vo1 = new BoardVO();
	  b_vo1.setId(1);
	  b_vo1.setTitle("test1");
	  b_vo1.setWriter("testtest1");
	  BoardVO b_vo2 = new BoardVO();
	  b_vo2.setId(2);
	  b_vo2.setTitle("test2");
	  b_vo2.setWriter("testtest2");
	  BoardVO b_vo3 = new BoardVO();
	  b_vo3.setId(3);
	  b_vo3.setTitle("test3");
	  b_vo3.setWriter("testtest3");
	  
	  list.add(b_vo1);
	  list.add(b_vo2);
	  list.add(b_vo3);
	  
	  
	  
	  //modelAndView.addObject("dataList", list);
	  
	  
	  
	  return list;
	 }
	 
	 
	 @RequestMapping(value="/castorMarshaller", method = {RequestMethod.GET,RequestMethod.POST})
	 public ModelAndView castorMarshaller_Example(HttpServletRequest request, HttpServletResponse response , Model model){
	  System.out.println("----- castorMarshallerXmlView_Example -----");
	  
	  List<Info> list = new ArrayList<Info>();
	  
	  Info vo1 = new Info();
	  Info vo2 = new Info();
	  Info vo3 = new Info();
	  
	  vo1.setId(1);
	  vo1.setTitle("test1");
	  vo1.setWriter("value1");
	  
	  vo1.setId(2);
	  vo2.setTitle("test2");
	  vo2.setWriter("value2");
	  
	  vo3.setId(3);
	  vo3.setTitle("test3");
	  vo3.setWriter("value3");
	  
	  
	  list.add(vo1);
	  list.add(vo2);
	  list.add(vo3);
	  
	  Map<String,Object> map = new HashMap();
	  map.put("*-info", list);

	  //new ModelAndView(viewName, modelName, modelObject)
	  
	  return new ModelAndView("", map);	  
	  //return new ModelAndView(map);
	 }
	 
	
	
	
	
	
}
