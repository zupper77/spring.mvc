package com.wemakeprice.commons.lib.messages.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.wemakeprice.commons.lib.messages.MessageService;


@Repository("MessageCommonSetter") 
public class MessageSetter {
	
	@Resource(name = "MessageCommonServiceImpl")
	private MessageService messageService;
	
	private Map<?, ?> messages;

	/**
	 * 메세지 세팅
	 * @param messages
	 */
	public void setMessages(Map<?, ?> messages) {
		// 셋팅은 사용하지 말라야 함.. 저장할 필요성 없음.. 
		//this.messages = messages;
	}

	/**
	 * 메세지 리턴
	 * @param code
	 * @return
	 */
	public String getMessages(String code) {
		return (String)messageService.getString(code);
		//return (String) this.messages.get(code);
	}

	/**
	 * 0000 메세지 세팅 ( JSON )
	 * @param model
	 * @return
	 */
	public void set0000(Model model) {
		String code = "0000";
		model.addAttribute("result_no", code);
		model.addAttribute("error_desc", "");
	}

	/**
	 * 0000 메세지 세팅 ( JSON )
	 * @param model
	 * @return
	 */
	public void set0000(Model model, String log_seq ) {
		String code = "0000";
		model.addAttribute("result_no", code);
		model.addAttribute("log_seq", log_seq);
		model.addAttribute("error_desc", "");
	}
	
	/**
	 * 공통 메세지 세팅 ( 코드, 컨트롤러명  )
	 * @return
	 * @throws Exception
	 */
	public Map<String, ?> setCommonMessage(String code, String controllerName) throws Exception {
		return (Map<String, ?>) setModel(code); 
	}	

	/**
	 * 공통 메세지 세팅 ( 코드, 컨트롤러명  )
	 * @return
	 * @throws Exception
	 */
	public Map<String, ?> setCommonMessage(String code, String controllerName, Long log_seq) throws Exception {
		return (Map<String, ?>) setModel(code, log_seq); 
	}	
	

	/**
	 * 1000 메세지 세팅 ( NullDataException )
	 * @return
	 * @throws Exception
	 */
	public Map<String, ?> set1000() throws Exception {
		return (Map<String, ?>) setModel("1000"); 
	}

	/**
	 * 1000 메세지 세팅 ( NullDataException )
	 * @return
	 * @throws Exception
	 */
	public Map<String, ?> set1000(String code) throws Exception {
		return (Map<String, ?>) setModel("1000"); 
	}
	
	/**
	 * 1001 메세지 세팅 ( ParameterException )
	 * @return
	 * @throws Exception
	 */
	public Map<String, ?> set1001() throws Exception {
		return (Map<String, ?>) setModel("1001"); 
	}

	/**
	 * 1001 메세지 세팅 ( ParameterException )
	 * @return
	 * @throws Exception
	 */
	public Map<String, ?> set1001(String code) throws Exception {
		return (Map<String, ?>) setModel("1001", code); 
	}
	
	/**
	 * 1002 메세지 세팅
	 * @return
	 * @throws Exception
	 */
	public Map<String, ?> set1002() throws Exception {
		return (Map<String, ?>) setModel("1002"); 
	}
	
	/**
	 * 1003 메세지 세팅
	 * @return
	 * @throws Exception
	 */
	public Map<String, ?> set1003() throws Exception {
		return (Map<String, ?>) setModel("1003");
	}


	/**
	 * 2000 메세지 세팅
	 * @return
	 * @throws Exception
	 */
	public Map<String, ?> set2000() throws Exception {
		return (Map<String, ?>) setModel("2000"); 
	}

	/**
	 * 2000 메세지 세팅
	 * @return
	 * @throws Exception
	 */
	public Map<String, ?> set2000(String code) throws Exception {
		return (Map<String, ?>) setModel("2000", code); 
	}
	
	/**
	 * 일반 Exception 메세지 세팅 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public Map<String, ?> setException(String code) throws Exception {
		
		String msg = this.getMessages(code);
		//String msg = code;
		if(msg != null && !"".equals(msg)) {
			code = "9999";
			return (Map<String, ?>) setModelWithMessage(code, msg);
		} else {
			return null;
		}
	}
	
	/**
	 * 리턴 모델에 code 세팅
	 * @param code
	 * @return
	 */
	 public ModelMap setModel(String code) {
		ModelMap model = new ModelMap();
		model.addAttribute("result_no", code);
		model.addAttribute("error_desc", messageService.getString(code));
		model.addAttribute("data", "null");
		return model;
	}
	 
		/**
		 * 리턴 모델에 code 세팅
		 * @param code
		 * @return
		 */
	 public ModelMap setModel(String code, long log_seq ) {
		ModelMap model = new ModelMap();
		model.addAttribute("result_no", code);
		model.addAttribute("error_desc", messageService.getString(code));
		model.addAttribute("log_seq", String.valueOf(log_seq));
		model.addAttribute("data", "null");
		return model;
	}
	
	/**
	 * 리턴 모델에 code 세팅
	 * @param code
	 * @return
	 */
	private ModelMap setModel(String code, String key) {
		ModelMap model = new ModelMap();
		List<HashMap<String, String>> struct = new ArrayList< HashMap<String, String> >(); 
		model.addAttribute(key, struct);
		return model;
	}	
	
	/**
	 * 리턴 모델에 code와 message 세팅
	 * @param code
	 * @param msg
	 * @return
	 */
	public ModelMap setModelWithMessage(String code, String msg) {
		ModelMap model = new ModelMap();
		model.addAttribute("result_no", code);
		model.addAttribute("error_desc", msg);
		model.addAttribute("data", "null");
		return model;
	}

	/**
	 * 지정된 코드에 대한 파라미터 모델에 메세지 세팅 ( model )
	 * @param Model model
	 * @param String code
	 * @return
	 */
	public void setMethod(Model model, String code) {
		model.addAttribute("result_no", code);
		model.addAttribute("error_desc", messageService.getString(code));
	}	
		
	
}


