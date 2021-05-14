package com.siggraph.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.siggraph.web.entity.Notice;
import com.siggraph.web.entity.NoticeView;

@Mapper
public interface NoticeDao {

	List<NoticeView> getViewList(int offset, int size, String field, String query);
	
	int getCount(String field, String query);
		
	NoticeView getView(int id);
	Notice getNext(int id);
	Notice getPrev(int id);
	
	int update(Notice notice);
	int delete(int id);
	int insert(Notice notice);
	
	int deleteAll(int[] ids);
	int updatePubAll(int[] pubIds, int[] closeIds);
	
	int pubSelected(int[] pubIds, boolean pub);
	int closeSelected(int[] closeIds, boolean pub);
	//int deleteSelected(int[] delIds);
	
	

}
