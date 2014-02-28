package com.wemakeprice.service.board;

import java.util.List;

import com.wemakeprice.vo.board.BoardVO;

public interface BoardService {
	
	List<BoardVO> getBoardList(BoardVO boardVO);
	
	BoardVO getBoardInfo(BoardVO boardVO);
	
	int addBoardInfo(BoardVO boardVO);
	
	int removeBoardInfo(BoardVO boardVO);
	
	int editBoardInfo(BoardVO boardVO);
}