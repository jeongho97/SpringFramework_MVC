package com.example.framework.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.framework.DTO.BoardDTO;
import com.example.framework.Service.BoardServieImpl;
@Controller
public class BoardController {

	@Autowired
	BoardServieImpl boardService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		List<BoardDTO> boardList=boardService.selectBoards();
		ModelAndView mav=new ModelAndView();
		mav.addObject("list",boardList);
		mav.setViewName("board/home");
		
		return mav;
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView search(
			@RequestParam(value="searchKey") String searchKey
			) {
		System.out.println(searchKey);
		List<BoardDTO> boardList=boardService.selectSearch(searchKey);
		ModelAndView mav=new ModelAndView();
		mav.addObject("list",boardList);
		mav.setViewName("board/home");
		
		return mav;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "board/create";

	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(
			@RequestParam(value="content") String content, //갖고온다
			@RequestParam(value="title") String title,
			@RequestParam(value="user") String user
			) {
		ModelAndView mav = new ModelAndView();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setContent(content);
		boardDTO.setTitle(title);
		boardDTO.setUser(user);
		
		String boardId=boardService.create(boardDTO); //쿼리 실행
		if(boardId==null) {
			mav.setViewName("redirect:/create"); //성공
		}else {
			mav.setViewName("redirect:/detail?board_id="+boardId); //실패
		}
		return mav;

	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(
			@RequestParam(value="board_id") Integer board_id
			) {
		BoardDTO boardDTO=new BoardDTO();
		boardDTO.setBoard_id(board_id);
		BoardDTO detailMap=boardService.select_detail(boardDTO);
		System.out.println(detailMap.toString());
		ModelAndView mav=new ModelAndView();
		mav.addObject("data",detailMap);
		mav.addObject("board_id",boardDTO.getBoard_id());
		mav.setViewName("/board/detail");
		return mav;

	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(
			@RequestParam(value="board_id") Integer board_id
			) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("board_id",board_id);
		mav.setViewName("/board/update"); //update페이지로 이동
		return mav;

	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePost(
			@RequestParam(value="board_id") Integer board_id,
			@RequestParam(value="content") String content, //갖고온다
			@RequestParam(value="title") String title,
			@RequestParam(value="user") String user
			) {
		ModelAndView mav = new ModelAndView();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBoard_id(board_id);
		boardDTO.setContent(content);
		boardDTO.setTitle(title);
		boardDTO.setUser(user);
		int boardUpdate=boardService.updateById(boardDTO);
		if(boardUpdate==0) {
			mav.setViewName("redirect:/update?board_id="+board_id); //update페이지로 이동
		}else {
			mav.setViewName("redirect:/detail?board_id="+board_id);
		}
		return mav;

	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(
			@RequestParam(value="board_id") Integer board_id
			) {
		ModelAndView mav=new ModelAndView();
		BoardDTO boardDTO=new BoardDTO();
		boardDTO.setBoard_id(board_id);
		int boarddelete=boardService.deleteById(boardDTO);
		
		if(boarddelete==0) {
			System.out.println("삭제 실패");
			mav.setViewName("redirect:/detail?board_id="+board_id);
		}else {
			System.out.println("삭제 성공");
			mav.setViewName("redirect:/create");
		}
		return mav;

	}
}
