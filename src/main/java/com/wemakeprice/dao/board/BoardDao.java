package com.wemakeprice.dao.board;

import java.util.List;

import com.wemakeprice.vo.board.BoardVO;

public interface BoardDao {
	
	List<BoardVO> selectBoardList(BoardVO boardVO);
	
	BoardVO selectBoardInfo(BoardVO boardVO);
	
	int insertBoard(BoardVO boardVO);
	
	int deleteBoard(BoardVO boardVO);
	
	int updateBoard(BoardVO boardVO);

}
