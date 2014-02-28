package com.wemakeprice.dao.board;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wemakeprice.dao.BaseDao;
import com.wemakeprice.vo.board.BoardVO;

@Repository
public class BoardDaoImpl extends BaseDao implements BoardDao{
	@Override
	public List<BoardVO> selectBoardList(BoardVO boardVO) {
		return super.selectList("board.selectBoardList", boardVO);
	}

	@Override
	public BoardVO selectBoardInfo(BoardVO boardVO) {
		return (BoardVO) super.selectOne("board.selectBoardInfo", boardVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		return super.insert("board.insertBoard", boardVO);
	}

	@Override
	public int deleteBoard(BoardVO boardVO) {
		return super.delete("board.deleteBoard", boardVO);
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		return super.update("board.updateBoard", boardVO);
	}  
}
