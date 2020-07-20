package com.spring.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.spring.springboard2.BoardVO;

public interface BoardMapper {
	ArrayList<BoardVO> getArticles(@Param("start") int start, @Param("end") int end);
	int getArticleCount();
	String getMaxNum();
	BoardVO selectArticle(int a);
	String getPasswd(int a);
	int insertArticle(BoardVO boardvo);
	int updateReStep(BoardVO boardvo);
	int updateArticle(BoardVO boardvo);
	void updateReadCount(BoardVO boardvo);
	int deleteArticle(int a);
	BoardVO content(HttpServletRequest request); 
	//List<BoardVO> articles(Map<String, Object> map);
	ArrayList<BoardVO> articlesWriter(String keyword);
	ArrayList<BoardVO> articlesSubject(String keyword);
	ArrayList<BoardVO> articlesContent(String keyword);
}
