package com.example.framework.Service;

import java.util.List;

import com.example.framework.DTO.BoardDTO;

public interface BoardService {
	String create(BoardDTO boardDTO);
	BoardDTO select_detail(BoardDTO boardDTO);
	int deleteById(BoardDTO boardDTO);
	int updateById(BoardDTO boardDTO);
	List<BoardDTO> selectBoards();
	List<BoardDTO> selectSearch(String searchKey);

}
