package com.wemakeprice.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wemakeprice.controller.BaseController;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	/**
	 * @ModelAttribute 모델로 사용되는 오브젝트 (커맨드 오브젝트)
	 * 별도의 설정없이 뷰에 전달하는 모델 오브젝트
	 * 다른 Controller 호출 시 @ModelAttribute 어테이션 선언된 메서드도 실행 후 생성된 객체가 함께 뷰로 전달된다.
	 * @ModelAttribute 어노테이션이 적용된 메서드가 @RequestMapping 어노테이션 적용된 메서드 보다 먼저 실행되기 때문에, 
	 * 커맨드 객체에 대한 초기화 작업이 필요하다면 커맨드 객체와 동일한 이름을 같는 @ModelAttribute 어노테이션을 적용된 메서드를 이용하여 초기화 작업을 수행시킨다.
	 * 단지 요청 파라미터를 메소드 파라미터에서 1:1 로 받으면 @RequestParam 이고, 도메인 오브젝트나 DTO 의 프로퍼티에 바인딩해서 한 번에 받으면 @ModelAttribute 라고 볼 수 있다.
	 * @return
	 */
	@ModelAttribute("/getUserList")
	public String[] getPopularQueryList(){
		return new String[] {"위메프테스트1","위메프테스트2"};
	}
}
