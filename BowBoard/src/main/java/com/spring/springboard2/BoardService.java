package com.spring.springboard2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BoardService {
	public ArrayList<BoardVO> getArticles(int start, int end);
	public int getArticleCount();
	public String getMaxNum();
	public BoardVO selectArticle(int a);
	public String getPasswd(int a);
	public int insertArticle(BoardVO boardvo);
	public int updateReStep(BoardVO boardvo);
	public int updateArticle(BoardVO boardvo);
	public void updateReadCount(BoardVO boardvo);
	public int deleteArticle(int a);
	
	public ArrayList<BoardVO> articles(String keyword, String search_option);
	
}
