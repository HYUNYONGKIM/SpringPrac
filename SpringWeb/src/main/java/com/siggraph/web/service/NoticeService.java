package com.siggraph.web.service;

import java.util.List;

import com.siggraph.web.entity.Notice;
import com.siggraph.web.entity.NoticeView;

public interface NoticeService {

	//-페이지를 요청할 때
	List<NoticeView> getViewList();
	//-검색을 요청할 때
	List<NoticeView> getViewList(String field, String query);
	List<NoticeView> getViewList(int page, String field, String query);
	
	//공개된 게시판 요청할 때
	List<NoticeView> getPubViewList(boolean pub);
	//공개된 게시판 검색을 요청할 때
	List<NoticeView> getPubViewList(String field, String query, boolean pub);
	List<NoticeView> getPubViewList(int page, String field, String query, boolean pub);
	
	
	int getCount();
	int getCount(String field, String query);
	
	int getPubCount(boolean pub);
	int getPubCount(String field, String query, boolean pub);
	
	//-자세한 페이지 요청할 때
	NoticeView getView(int id);
	Notice getNext(int id);
	Notice getPrev(int id);
	
	Notice getPubNext(int id, boolean pub);
	Notice getPubPrev(int id, boolean pub);

	//-일괄공개를 요청할 때
	int updatePubAll(int[] pubIds, int[] closeIds);
	//-선택삭제를 요청할 때
	int deleteAll(int[] ids);
	//-선택공개를 요청할 때
	int pubSelected(int[] pubIds, boolean pub);
	//-선택비공개를 요청할 때
	int closeSelected(int[] closeIds, boolean pub);
	
	//-수정 페이지를 요청할 때
	int update(Notice notice);
	int delete(int id);
	int insert(Notice notice);
}
