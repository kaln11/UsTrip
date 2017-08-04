package com.ustrip.service.board.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ustrip.common.Search;
import com.ustrip.service.board.BoardDao;
import com.ustrip.service.board.BoardService;
import com.ustrip.service.domain.Board;
import com.ustrip.service.domain.Travel;

@Service("boardServiceImpl")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	@Qualifier("boardDaoImpl")
	private BoardDao boardtDao;
	public void setBoardDao(BoardDao boardtDao) {
		this.boardtDao = boardtDao;
	}

	public BoardServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}

	@Override
	public int addBoard(Board board) throws Exception {
		// TODO Auto-generated method stub
		return boardtDao.addBoard(board);
	}

	@Override
	public List<Board> listBoard(Search search) throws Exception {
		// TODO Auto-generated method stub		
		List<Board> list = new ArrayList<Board>();
		List<Board> listNoncheck = new ArrayList<Board>();
		if(search.getOrder().equals("DESC")){
			listNoncheck= boardtDao.listBoardDESC(search);
			for(Board b : listNoncheck){
				if(b.getDeleteFlag() == 0){
					list.add(b);
				}
			}
			int size = list.size();
			for(int i = 0; i < list.size() ; i++){
				list.get(i).setNumbering(size-i);
			}			
		}else{
			listNoncheck= boardtDao.listBoardASC(search);
			for(Board b : listNoncheck){
				if(b.getDeleteFlag() == 0){
					list.add(b);
				}
			}
			for(int i = 0; i < list.size() ; i++){
				list.get(i).setNumbering(i+1);
			}
		}		
		return list;
	}

	@Override
	public int updateBoard(Board board) throws Exception {
		// TODO Auto-generated method stub
		return boardtDao.updateBoard(board);
	}

	@Override
	public int deleteBoard(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		return boardtDao.deleteBoard(boardNo);
	}

	@Override
	public Board getBoard(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		return boardtDao.getBoard(boardNo);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return boardtDao.getTotalCount(search);
	}

	@Override
	public List<Travel> addBoardForm(String userId) throws Exception {
		// TODO Auto-generated method stub
		return boardtDao.addBoardForm(userId);
	}

}
