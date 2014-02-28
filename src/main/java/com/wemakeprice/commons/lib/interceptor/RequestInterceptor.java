package com.wemakeprice.commons.lib.interceptor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;                                                   
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.ui.ModelMap;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.google.gson.Gson;
import com.wemakeprice.commons.lib.interceptor.exception.NullDataException;
import com.wemakeprice.commons.lib.interceptor.exception.ParameterException;
import com.wemakeprice.commons.lib.messages.common.MessageSetter;
import com.wemakeprice.commons.lib.utility.LogUtil;

@Aspect
public class RequestInterceptor {

	private Logger logger = Logger.getLogger(this.getClass());

//	@Resource(name = "MessageCommonSetter")
//	private MessageSetter messageSetter;
	
	/**
	 * 모든 Exception에 대한 pointcut ( 샘플도 임시로 포함 )
	 */
	@Pointcut("execution(* com.wemakeprice.platform..*Controller*.*(..)) || execution(* com.wemakeprice.wallet..*Controller*.*(..)) || execution(* com.wemakeprice.live..*Controller*.*(..)) ")
	public void pointcut4throwingLog() {}


	/**
	 * 모든 controller에 대한 pointcut ( 샘플도 임시로 포함 )
	 */
	@Pointcut("execution(* com.wemakeprice.live..*Controller*.*(..)) || execution(* com.wemakeprice.wallet..*Controller*.*(..)) || execution(* com.wemakeprice.sample..*Controller*.*(..)) ")
	public void call4ControllerLog() {}

	
	/**
	 * 모든 controller에 대한 pointcut ( 샘플도 임시로 포함 )
	 */
	@Pointcut(" execution(* com.wemakeprice.sample..*Controller*.*(..)) ")
	public void beforeController() {}
	
	/**
	 * 모든 controller의 호출에 대한 return log
	 * @param joinPoint
	 */
	@Before("beforeController()")
	public void _beforeLog(JoinPoint joinPoint) throws Exception {
	
		Object[] args = joinPoint.getArgs();
		HttpSession _session = null;
		
		for (Object arg : args) {
			
			if(arg instanceof HttpSession) {
				_session = (HttpSession) arg;
			} 
		}
		
	}
	
	
	/**
	 * 모든 controller의 호출에 대한 return log
	 * @param joinPoint
	 */
	@AfterReturning("call4ControllerLog()")
	public void _afterLog(JoinPoint joinPoint) {
		
		Locale _locale = null;
		BindingAwareModelMap _model = null;
		HttpServletRequest _request = null;
		HttpServletResponse _response = null;
		HttpSession _session = null;
		Logger _logger = logger;
		
		Object[] args = joinPoint.getArgs();
	
		for (Object arg : args) {
			
			if(arg instanceof BindingAwareModelMap) {
				_model = (BindingAwareModelMap) arg;
			} else if(arg instanceof HttpServletRequest) {
				_request = (HttpServletRequest) arg;
				_locale = _request.getLocale();
			} else if(arg instanceof HttpServletResponse) {
				_response = (HttpServletResponse) arg;
			} else if(arg instanceof HttpSession) {
				_session = (HttpSession) arg;
			} 
		}
		
		try {
			
//			collector.collecteProcessLog( _session, _request, _response, _model );
//			collector.getSesionOrder_id();
//			recoder.loggerSet(collector);
//			//System.out.println( collector.toString() );
//			collector.distroyCollector();
			
		} catch (Exception e) {
			LogUtil.infoPrintStackTrace(logger, e);
		} finally {
			args = null;
		}
		
	}	
	
	

	/**
	 * 모든 호출에 대한 exception
	 * @param joinPoint
	 * @param error
	 * @throws Exception
	 */
	@AfterThrowing(pointcut = "pointcut4throwingLog()", throwing = "error")
	public void _throwing(JoinPoint joinPoint, Throwable error) throws Exception {
		
		Locale _locale = null;
		BindingAwareModelMap _model = null;
		HttpServletRequest _request = null;
		HttpServletResponse _response = null;
		HttpSession _session = null;
		
		Logger _logger = logger;
		Logger _logger_error = logger;
		
		String request_viewer ="";
		long log_seq = 0;
		
		
		
		Object[] args = joinPoint.getArgs();
		
		try {
			
			for (Object arg : args) {
				
				if(arg instanceof BindingAwareModelMap) {
					_model = (BindingAwareModelMap) arg;
				} else if(arg instanceof HttpServletRequest) {
					_request = (HttpServletRequest) arg;
					_locale = _request.getLocale();
					
					String _format = (String) _request.getParameter("format");

					if( null != _format ){
						if( _format.toUpperCase().equals("JSON") ){
							request_viewer = "jsonViewer";
						} else if( _format.toUpperCase().equals("XML") ){
							request_viewer = "xmlViewer";
						} else if( _format.toUpperCase().equals("JSONP") ){
							request_viewer = "jsonpViewer";
						} else {
							request_viewer = "";
						}
					}
				} else if(arg instanceof HttpServletResponse) {
					_response = (HttpServletResponse) arg;
				} else if(arg instanceof HttpSession) {
					_session = (HttpSession) arg;
				} 
				
							
			}
		} catch (Exception e) {
			LogUtil.infoPrintStackTrace(logger, e);
		} finally {
			args = null;
		}

		Map<String, ?> map = null;
		
		if(error instanceof NullDataException) {
			// 1000
			//map = messageSetter.setCommonMessage( "A000", joinPoint.getSignature().getName());
		} else if(error instanceof ParameterException) {
			// 1001
			//map = messageSetter.setCommonMessage( "A001", joinPoint.getSignature().getName());
		} else if(error instanceof IllegalStateException ){
			// 입력 파라미터가  JSON 데이터가 아님 
			// 1002
			//map = messageSetter.setCommonMessage( "A002", joinPoint.getSignature().getName());
			LogUtil.infoThrow(_logger_error, joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), error);
		} else {
//			map = messageSetter.setException(error.getMessage());
//			if( null == map ) {
				// 2000
			//map = messageSetter.setCommonMessage( "B000", joinPoint.getSignature().getName());
				LogUtil.infoThrow(_logger_error, joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), error);
//			}
		}
		// 에러발생시 로그처리용 
		//LogUtil.infoReturn(_logger, joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), this.getReturn(map));
		
		// 로그처리용   
		try {
//			
//			collector.collecteProcessLog( _session, _request, _response, _model );
//			
			
			
		} catch (Exception e) {
			LogUtil.infoPrintStackTrace(logger, e);
		} finally {
			args = null;
		}	
		
//		collector.setExceptionMap(map);
//		recoder.loggerSet(collector);
		//System.out.println( collector.toString() );
		_session.invalidate();
		//collector.distroyCollector();
		
		throw new ModelAndViewDefiningException( new ModelAndView(request_viewer, map) );
		

	}
	

	
	/**
	 * return parsing : 리턴데이터를 로그에 남기기 위함
	 * @param map
	 * @return
	 */
	private String getReturn(Map<?, ?> map) {
		
		StringBuffer sb = new StringBuffer();
		Entry<?, ?> entry;
		Set<?> set;
		Iterator<?> it;
		Object value = null;
		String val = null;
		int len = 1000;
		
		try {
			set = map.entrySet();
			it = set.iterator();
			
			while(it.hasNext()) {
				entry = (Entry<?, ?>) it.next();
				value = entry.getValue();

				if(value instanceof String) {
					if(((String) value).length() > len) {
						value = ((String) value).substring(0, len);
					}
					sb.append("   " + entry.getKey() + ": " + value + "\n");
				} else {
					val = new Gson().toJson(value);
					
					if(val.length() > len) {
						val = val.substring(0, len);
					}
					sb.append("   class: "
							+ value.getClass()
							+ "\n"
							+ "   "
							+ val);
				}
			}
			
			
			
		} catch(Exception e) {
			LogUtil.infoPrintStackTrace(logger, e);
		} finally {
			entry = null;
			set = null;
		}
		return sb.toString();
	}

	public static String getBody(HttpServletRequest request) throws IOException {

	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}
}