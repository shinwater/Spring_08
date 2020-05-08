package com.board.model;

import java.util.List;

public interface BoardDAO {
	List<BoardDTO> getBoardList();
	
	int insertBoard(BoardDTO dto);
	BoardDTO boardCont(int no);
	
	void readCount(int hit,int no);
	int updateBoard(BoardDTO dto);
	int deleteBoard(int no);

	List<BoardDTO> searchList(String s1,String s2);
}
