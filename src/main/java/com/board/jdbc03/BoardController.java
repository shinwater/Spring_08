package com.board.jdbc03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

@Controller
public class BoardController {

	
	@Autowired
	private BoardDAO dao;
	
	@RequestMapping("/board_list.do")
	public String list(Model model) {
		List<BoardDTO> list =dao.getBoardList();
		model.addAttribute("List",list);
		return "board_list";
	}//list가져오는 멧ㄷ 끝
	
	@RequestMapping("/board_write.do")
	public String write() {
		return "board_writeForm";
	}
	
	@RequestMapping("/board_writeOk.do")
	public void writeOk(BoardDTO dto, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int res = dao.insertBoard(dto);
		
		System.out.println("BoardController : 글쓰기 하고 나온값" + res);
		if(res ==1) {
			out.println("<script>");
			out.println("alert('글쓰기성공')");
			out.println("location.href='board_list.do");
			out.println("</script>");
			
		}else {
			out.println("<script>");
			out.println("alert('글쓰기실패')");
			out.println("history.go(-1)");
			out.println("</script>");
		}
		
	}//writeOk() end;
	
	
	@RequestMapping("/board_cont.do")
	public String cont(int no,Model model) {
		BoardDTO dto = dao.boardCont(no);
		dao.readCount(dto.getBoard_hit()+1,no);
		
		model.addAttribute("dto", dto);
		
	
		return "board_cont";
	}//cont() 끝
	
	@RequestMapping("/board_update.do")
	public String update(int no,Model model) {
		BoardDTO dto = dao.boardCont(no);
		model.addAttribute("dto", dto);
		
		return "board_updateForm";
	}//update()
	
	@RequestMapping("/board_updateOk.do")
	public void updateOk(BoardDTO dto,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=utf-8");
		int res = dao.updateBoard(dto);
		System.out.println("BoardController: 업데이트하고 나온값!" + res);
		
		PrintWriter out = response.getWriter();
		if(res ==1) {
			out.println("<script>");
			out.println("alert('수정성공')");
			out.println("location.href='board_cont.do?no="+dto.getBoard_no()+"'");
			out.println("</script>");
			
		}else {
			out.println("<script>");
			out.println("alert('수정실패')");
			out.println("history.go(-1)");
			out.println("</script>");
		}
	}//updateOk() end;
	
	@RequestMapping("/board_delete.do")
	public void delete(int no,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=utf-8");
		int res = dao.deleteBoard(no);
		System.out.println("BoardController: 삭제하고 나온값!" + res);
		
		PrintWriter out = response.getWriter();
		if(res ==1) {
			out.println("<script>");
			out.println("alert('삭제성공')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
			
		}else {
			out.println("<script>");
			out.println("alert('삭제실패')");
			out.println("history.go(-1)");
			out.println("</script>");
		}
	}//delete*(
	
	@RequestMapping("/board_validation.do")
	public String check(@RequestParam("no") int board_no, @RequestParam("check_no") int chk,Model model) {
		model.addAttribute("no", board_no);
		model.addAttribute("check_no", chk);
		
		BoardDTO dto=dao.boardCont(board_no);
		model.addAttribute("check_pwd", dto.getBoard_pwd());
		
		return "board_validation";
	}
	
	@RequestMapping("/board_validationOk.do")
	public void checkOk(
			@RequestParam("board_no") int board_no,@RequestParam("chk_no") int no,
			@RequestParam("check_pwd") String c_pwd,@RequestParam("board_pwd") String b_pwd,
			HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(b_pwd.equals(c_pwd)) {
			out.println("<script>");
			out.println("alert('확인성공')");
			if(no==1) {
				out.println("location.href='board_update.do?no="+board_no+"'");
			}else {
				out.println("location.href='board_delete.do?no="+board_no+"'");
			}
			out.println("</script>");
			
		}else {
			out.println("<script>");
			out.println("alert('비밀번호를 다시 확인해주세요!')");
			out.println("history.back()");
			out.println("</script>");
		}
	}//checkOk() end;
	
	@RequestMapping("/board_search.do")
	public String search(@RequestParam("search1") String s1,@RequestParam("search2") String s2,Model model) {
		
		System.out.println("검색할 것:"+s1+","+s2);
		List<BoardDTO> list =dao.searchList(s1, s2);
		model.addAttribute("List",list);
		
		return "board_list";
	}
}
