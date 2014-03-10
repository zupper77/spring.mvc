package com.wemakeprice.dao.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wemakeprice.vo.board.BoardVO;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<BoardVO> selectBoardList(BoardVO boardVO) {
		return sqlSession.selectList("board.selectBoardList", boardVO);
	}

	@Override
	public BoardVO selectBoardInfo(BoardVO boardVO) {
		return (BoardVO) sqlSession.selectOne("board.selectBoardInfo", boardVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		return sqlSession.insert("board.insertBoard", boardVO);
	}

	@Override
	public int deleteBoard(BoardVO boardVO) {
		return sqlSession.delete("board.deleteBoard", boardVO);
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		return sqlSession.update("board.updateBoard", boardVO);
	}

	@Override
	public void transactionTest(BoardVO boardVO) throws Exception {
		sqlSession.insert("board.insertBoard", boardVO);		
	}  
}
