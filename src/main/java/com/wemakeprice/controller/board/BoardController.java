package com.wemakeprice.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	/**
	 * 
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
	
	@RequestMapping("/getBoardInfo")
	public ModelAndView getBoardInfo(BoardVO boardVO, HttpServletRequest request, HttpServletResponse response){
		log.debug("request객체에서 받아온 값:"+request.getParameter("id"));
		log.debug("BoardVO객체에서 받아온 값:"+boardVO.getId());
		BoardVO resultVO = boardService.getBoardInfo(boardVO);
		return new ModelAndView("/board/boardView").addObject("boardVO", resultVO);
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
	public void removeBoard_1(BoardVO boardVO, HttpServletRequest request, HttpServletResponse response){
		log.debug("기본으로 삭제합니다.");
		log.debug("삭제할 아이디: "+ request.getParameter("id"));
	}
	
	/**
	 * 파라미터 값으로 매핑.
	 * @param boardVO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/removeBoard", params="type=admin")
	public void removeBoard_2(BoardVO boardVO, HttpServletRequest request, HttpServletResponse response){
		log.debug("삭제할 아이디: "+ boardVO.getId());
		log.debug("관리자 권한으로 삭제합니다.");
		log.debug("resultCode: "+ boardService.removeBoardInfo(boardVO));
		
	}
	
	/**
	 * 파라미터 값으로 매핑.
	 * @param boardVO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/removeBoard", params="type=customer")
	public void removeBoard_3(BoardVO boardVO, HttpServletRequest request, HttpServletResponse response){
		log.debug("삭제할 아이디: "+ boardVO.getId());
		log.debug("관리자만 삭제할 수 있습니다.");
		//log.debug("resultCode: "+ boardService.removeBoardInfo(boardVO));
	}
	
}
