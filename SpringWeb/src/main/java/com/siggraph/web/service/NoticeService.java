package com.siggraph.web.service;

import java.util.List;

import com.siggraph.web.entity.Notice;
import com.siggraph.web.entity.NoticeView;

public interface NoticeService {

	//-페이지를 요청할 때
	List<NoticeView> getViewList(boolean pub);
	//-검색을 요청할 때
	List<NoticeView> getViewList(String field, String query, boolean pub);
	List<NoticeView> getViewList(int page, String field, String query, boolean pub);
	
	int getCount(boolean pub);
	int getCount(String field, String query, boolean pub);
	
	//-자세한 페이지 요청할 때
	NoticeView getView(int id);
	Notice getNext(int id);
	Notice getPrev(int id);

	//-일괄공개를 요청할 때
	int updatePubAll(int[] pubIds, int[] closeIds);
	//-일괄삭제를 요청할 때
	int deleteAll(int[] ids);
	
	//-수정 페이지를 요청할 때
	int update(Notice notice);
	int delete(int id);
	int insert(Notice notice);
}
