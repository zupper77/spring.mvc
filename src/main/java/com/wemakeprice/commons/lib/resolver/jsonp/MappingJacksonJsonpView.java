package com.wemakeprice.commons.lib.resolver.jsonp;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * JSONP 요청시 응답 
 * @author user
 *
 */
public class MappingJacksonJsonpView extends MappingJacksonJsonView {

	public static final String DEFAULT_CONTENT_TYPE = "application/javascript";

	@Override
	public String getContentType() {
		return DEFAULT_CONTENT_TYPE;
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if("GET".equals(request.getMethod().toUpperCase())) {

			@SuppressWarnings("unchecked")
			Map<String, String[]> params = request.getParameterMap();

			if(params.containsKey("callback")) {
				response.getOutputStream().write(new String(params.get("callback")[0] + "(").getBytes());
				super.render(model, request, response);
				response.getOutputStream().write(new String(");").getBytes());
				response.setContentType(DEFAULT_CONTENT_TYPE);
			} else {
				super.render(model, request, response);
			}
		} else {
			super.render(model, request, response);
		}
	}

}
