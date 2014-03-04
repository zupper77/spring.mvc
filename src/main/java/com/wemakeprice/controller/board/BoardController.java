package com.wemakeprice.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wemakeprice.controller.BaseController;
import com.wemakeprice.service.board.BoardService;
import com.wemakeprice.vo.board.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController extends BaseController{
	
	@Autowired
	BoardService boardService;
	
	@ModelAttribute("searchTeamList")
	public String[] getSearchTeamList(){
		return new String[]{"개발1팀","개발2팀","개발3팀"};
	}
	
	/**
	 * String 으로 반환할 경우, View Name으로 매핑.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model, BoardVO boardVO, HttpServletRequest request, HttpServletResponse response){
		try{
			log.debug("getBoardList");
			
			List<BoardVO> boardList = boardService.getBoardList(boardVO);
			model.addAttribute("boardList", boardList);
        } catch (Exception e) {
           	log.error(e.getMessage());
        }
		return "board/boardList";
	}
	
	/**
	 * ModelAndView 를 반환할 경우, Object를 명시적으로 담아서 매핑.
	 * @param boardVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getBoardInfo")
	public ModelAndView getBoardInfo(BoardVO boardVO, HttpServletRequest request, HttpServletResponse response){
		log.debug("request객체에서 받아온 값:"+request.getParameter("id"));
		log.debug("BoardVO객체에서 받아온 값:"+boardVO.getId());
		BoardVO resultVO = boardService.getBoardInfo(boardVO);
		return new ModelAndView("/board/boardView").addObject("boardVO", resultVO);
	}
	
	
	
	
	@RequestMapping("/dummy")
	public void getTestPage(Model model, BoardVO boardVO){
		log.debug("return형이 void 이면 RequestToViewNameResolver(Default) 전략에 의해 @RequestMapping에 정의된 명칭으로 뷰 이름이 만들어진다.");
	}

	/**
	 * @PathVariable("id") String id
	 * @param boardVO
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getBoardInfo2/{id}", method=RequestMethod.GET)
	public String getBoardInfo2(Model model, BoardVO boardVO, @PathVariable("id") String id){
		log.debug("The client locale is {}.", id);
		model.addAttribute("boardVO", boardService.getBoardInfo(boardVO));
		return ("/board/boardView");
	}

	
	/**
	 * 요청방식이 GET방식일 경우 매핑.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addBoard", method=RequestMethod.GET)
	public String addBoard_1(HttpServletRequest request, HttpServletResponse response){
		log.debug("addBoard GET type");
		return "board/boardWrite";
	}
	
	/**
	 * 요청방식이 POST방식일 경우 매핑.
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/addBoard", method=RequestMethod.POST)
	public void addBoard_2(Model model, BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.debug("addBoard POST type");
		//model.addAttribute("resultCode", boardService.addBoardInfo(boardVO));
		request.setAttribute("resultCode",boardService.addBoardInfo(boardVO));
		//redirect를 시키게 되면 모든 요청정보와 응답정보는 초기화됩니다.
		response.sendRedirect(request.getContextPath()+"/board/getBoardList.do");
	}
	
	/**
	 * 파라미터 값이 없을 경우 매핑.
	 * 더 상세한 조건이 우선순위를 가짐.
	 * @param boardVO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/removeBoard")
	public void removeBoard_1(BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.debug("기본으로 삭제합니다.");
		log.debug("삭제할 아이디: "+ request.getParameter("id"));
		log.debug("resultCode: "+ boardService.removeBoardInfo(boardVO));
		response.sendRedirect(request.getContextPath()+"/board/getBoardList.do");
	}
	
	/**
	 * 파라미터 값으로 매핑.
	 * @param boardVO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/removeBoard", params="type=admin")
	public void removeBoard_2(BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.debug("삭제할 아이디: "+ boardVO.getId());
		log.debug("관리자 권한으로 삭제합니다.");
		log.debug("resultCode: "+ boardService.removeBoardInfo(boardVO));
		response.sendRedirect(request.getContextPath()+"/board/getBoardList.do");
	}
	
	/**
	 * 파라미터 값으로 매핑.
	 * @param boardVO
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/removeBoard", params="type=customer")
	public void removeBoard_3(BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws IOException{
		log.debug("삭제할 아이디: "+ boardVO.getId());
		log.debug("관리자만 삭제할 수 있습니다.");
		//log.debug("resultCode: "+ boardService.removeBoardInfo(boardVO));
		response.sendRedirect(request.getContextPath()+"/board/getBoardList.do");
	}
	
}
