package com.example.framework.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.framework.DTO.BoardDTO;

@Repository
public class BoardDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insert(BoardDTO boardDTO) {
		return sqlSessionTemplate.insert("board.insert",boardDTO);
	}
	public BoardDTO detail(BoardDTO boardDTO) {
		return sqlSessionTemplate.selectOne("board.select_detail",boardDTO); //board_SQL.xml�� �����ؼ� sql��� ������
	}
	public int update(BoardDTO boardDTO) {
		return sqlSessionTemplate.update("board.updateById",boardDTO); //board_SQL.xml�� �����ؼ� sql��� ������
	}
	public int delete(BoardDTO boardDTO) {
		return sqlSessionTemplate.delete("board.deleteById",boardDTO); //board_SQL.xml�� �����ؼ� sql��� ������
	}
	public List<BoardDTO> selectList() {
		return sqlSessionTemplate.selectList("board.selectList"); //board_SQL.xml�� �����ؼ� sql��� ������
	}
	public List<BoardDTO> selectSearch(String searchKey) {
		return sqlSessionTemplate.selectList("board.selectSearch",searchKey); //board_SQL.xml�� �����ؼ� sql��� ������
	}


}