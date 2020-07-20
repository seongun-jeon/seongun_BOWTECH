package com.spring.springboard2;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("list.do")
	public String list(
			
			HttpServletRequest request
			,@RequestParam(defaultValue="") String search_option
			,@RequestParam(defaultValue="") String keyword
			,Model model
			
			) throws Exception {
		
		
		int pageSize = 5;
		
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;	
		int endRow = startRow + pageSize - 1;
		int count = 0;
		int number = 0;
		
		ArrayList<BoardVO> articleList = new ArrayList<BoardVO>();
		
		count = boardService.getArticleCount();
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
        		 
		map.put("search_option", search_option);
        map.put("keyword", keyword);
        
        System.out.println(map.get("search_option"));
        System.out.println(map.get("keyword"));
        
        articleList = boardService.articles(keyword, search_option);
    
		if(count < startRow) { 
			currentPage = currentPage - 1;
			startRow = (currentPage - 1) * pageSize + 1;
			endRow = startRow + pageSize - 1;
		}
		if(count > 0) {
			//BoardVO boardvo = new BoardVO();
			//boardvo.setStart(startRow);
			//boardvo.setEnd(endRow);
			articleList = boardService.getArticles(startRow, endRow);
			number = count - (currentPage - 1) * pageSize;
		}
		
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("count", count);
		model.addAttribute("number", number);
		model.addAttribute("articleList", articleList);
		model.addAttribute("pageSize", pageSize);
		
		mav.setViewName("board/liysst"); 
		return "list";
	}
	
	@RequestMapping("/writeform.do")
	public String writeForm(HttpServletRequest request) {
		return "writeForm";
	}
	
	@RequestMapping("/write.do")
	public String insert(BoardVO boardVO, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter writer = response.getWriter();
		boardVO.setReg_date(new Timestamp(System.currentTimeMillis()));
		int re_step = boardVO.getRe_step();
		int re_level = boardVO.getRe_level();
		int number = 0;
		int x;
		String i;
		i = boardService.getMaxNum();
		
		if(i!=null) {
			number = Integer.parseInt(i)+1;
		} else {
			number = 1;
		}
			
		if(boardVO.getNum() != 0) {
			boardService.updateReStep(boardVO);
			
			boardVO.setRe_step(re_step + 1);
			boardVO.setRe_level(re_level + 1);
		} else {
			boardVO.setRef(number);
			boardVO.setRe_step(0);
			boardVO.setRe_level(0);
		}
		
		x = boardService.insertArticle(boardVO);
		if(x==0) {
			writer.write("<script>alert('글작성 실패');location.href='./writeform.do';</script>");
		} else {
			writer.write("<script>location.href='./list.do';</script>");
		}
		
		return null;
	}
	
	@RequestMapping("/content.do")
	public String content(HttpServletRequest request, Model model) throws Exception {
		BoardVO article = new BoardVO();
		int num = Integer.parseInt(request.getParameter("num"));
		int currentPage = Integer.parseInt(request.getParameter("pageNum"));
		int number = Integer.parseInt(request.getParameter("number"));
		article = boardService.selectArticle(num);
		boardService.updateReadCount(article);
		
		model.addAttribute("number", number);
		model.addAttribute("pageNum", currentPage);
		model.addAttribute("article", article);
		return "content";
	}
	
	@RequestMapping("/deleteform.do")
	public String delete(HttpServletRequest request) throws Exception {
		return "deleteForm";
	}
	
	@RequestMapping("/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter writer = response.getWriter();
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int num = Integer.parseInt(request.getParameter("num"));
		
		String dbpasswd = boardService.getPasswd(num);
		if(dbpasswd.equals(request.getParameter("passwd"))) {
			int x = boardService.deleteArticle(num);
			if(x==0) {
				writer.write("<script>alert('삭제 실패');location.href='./list.do?pageNum="+pageNum+"';</script>");
			} else {
				writer.write("<script>alert('삭제 성공');location.href='./list.do?pageNum="+pageNum+"';</script>");
			}
		} else {
			writer.write("<script>alert('패스워드가 틀렸습니다');location.href='./list.do?pageNum="+pageNum+"';</script>");
		}
		
		return null;
	}
	
	@RequestMapping("/updateform.do")
	public String updateform(HttpServletRequest request) throws Exception {
		BoardVO boardVO = boardService.selectArticle(Integer.parseInt(request.getParameter("num")));
		request.setAttribute("boardVO", boardVO);
		
		return "updateForm";
	}
	
	@RequestMapping("/update.do")
	public String update(BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		PrintWriter writer = response.getWriter();
		
		String dbpasswd = boardService.getPasswd(boardVO.getNum());
		if(dbpasswd.equals(boardVO.getPasswd())) {
			int x = boardService.updateArticle(boardVO);
			if(x==1) {
				writer.write("<script>alert('수정 성공');location.href='./list.do?pageNum="+pageNum+"';</script>");
			} else {
				writer.write("<script>alert('수정 실패');location.href='./updateform.do?pageNum="+pageNum+"';</script>");
			}
		} else {
			writer.write("<script>alert('패스워드 불일치');location.href='./updateform.do?pageNum="+pageNum+"';</script>");
		}
	
		return null;
	}
	


}
