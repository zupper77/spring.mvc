package com.wemakeprice.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemakeprice.dao.board.BoardDao;
import com.wemakeprice.vo.board.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List<BoardVO> getBoardList(BoardVO boardVO){
		return boardDao.selectBoardList(boardVO);
	}

	@Override
	public BoardVO getBoardInfo(BoardVO boardVO) {
		return boardDao.selectBoardInfo(boardVO);
	}

	@Override
	public int addBoardInfo(BoardVO boardVO) {
		return boardDao.insertBoard(boardVO);
	}

	@Override
	public int removeBoardInfo(BoardVO boardVO) {
		return boardDao.deleteBoard(boardVO);
	}

	@Override
	public int editBoardInfo(BoardVO boardVO) {
		return boardDao.updateBoard(boardVO);
	}
}
