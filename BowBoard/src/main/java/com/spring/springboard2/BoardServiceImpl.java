package com.spring.springboard2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<BoardVO> getArticles(int start, int end) {
		ArrayList<BoardVO> articleList = new ArrayList<BoardVO>();
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		articleList = boardmapper.getArticles(start, end);
		return articleList;
	}
	
	@Override
	public int getArticleCount() {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		int i = boardmapper.getArticleCount();
		return i;
	}
	
	@Override
	public String getMaxNum() {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		String i = boardmapper.getMaxNum();
		return i;
	}
	
	@Override
	public BoardVO selectArticle(int a) {
		BoardVO boardVO = new BoardVO();
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		boardVO = boardmapper.selectArticle(a);
		return boardVO;
	}
	
	@Override
	public String getPasswd(int a) {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		String dbpasswd = boardmapper.getPasswd(a);
		return dbpasswd;
	}
	
	@Override
	public int insertArticle(BoardVO boardvo) {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		int i = boardmapper.insertArticle(boardvo);
		String writer   = boardvo.getWriter();
		String subject  = boardvo.getSubject();
		String content  = boardvo.getContent();
		String password = boardvo.getPasswd();
		
		writer 	 =  writer.replace("<","&1t;");
		subject	 = 	subject.replace("<","&1t;");
		content  = 	content.replace("<","&1t;");
		password =	password.replace("<","&1t;");
		
		writer 	 =  writer.replace(" ","&nbsp'&nbsp;");
		subject	 = 	subject.replace(" ","&nbsp'&nbsp;");
		content  = 	content.replace(" ","&nbsp'&nbsp;");
		password =	password.replace(" ","&nbsp'&nbsp;");
		
		return i; 
	}
	
	@Override
	public int updateReStep(BoardVO boardvo) {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		int i = boardmapper.updateReStep(boardvo);
		return i;
	}
	
	@Override
	public int updateArticle(BoardVO boardvo) {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		int i = boardmapper.updateArticle(boardvo);
		return i;
	}
	
	@Override
	public void updateReadCount(BoardVO boardvo) {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		boardmapper.updateReadCount(boardvo);
	}
	@Override
	public int deleteArticle(int a) {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		int i = boardmapper.deleteArticle(a);
		return i;
	}

	/*
	 * public static void main(String[] args) { int k = 0; int t = 10; k=aa();
	 * System.out.println(k);
	 * 
	 * } private String aa() { return "ssss"; }
	 */
	public ArrayList<BoardVO> articles(String keyword, String search_option){
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		if(search_option.equals("writer")) 
		{
			ArrayList<BoardVO> articles= boardmapper.articlesWriter(keyword);
			return articles;
		}
		else if(search_option.equals("subject"))
		{
			ArrayList<BoardVO> articles= boardmapper.articlesSubject(keyword);
			return articles;
		}
		else if(search_option.equals("content"))
		{
			ArrayList<BoardVO> articles= boardmapper.articlesContent(keyword);
			return articles;
		}else
		{
			return null;
		}
		
		
		
		
	}
}
