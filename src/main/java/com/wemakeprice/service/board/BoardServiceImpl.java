package com.wemakeprice.service.board;

import java.util.HashMap;
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

	@Override
	public void txTransactionTest() throws Exception {
		BoardVO boardVO = new BoardVO();
		for(int i = 0; i < 10; i++){
			boardVO.setTitle("Transaction1-"+i+1);
			boardVO.setWriter("김한주");
			
			if(i == 5){
				//throw new Exception("트랜잭션테스트");
			}
			boardDao.transactionTest(boardVO);
		}
		
		for(int i = 0; i < 10; i++){
			boardVO.setTitle("Transaction2-"+i+1);
			boardVO.setWriter("김한주");
			if(i == 5){
				//throw new Exception("트랜잭션테스트");
			}
			boardDao.transactionTest(boardVO);
		}
		
		for(int i = 0; i < 10; i++){
			boardVO.setTitle("Transaction3-"+i+1);
			boardVO.setWriter("김한주");
			if(i == 5){
				throw new Exception("트랜잭션테스트");
			}
			boardDao.transactionTest(boardVO);
		}		
	}
}
