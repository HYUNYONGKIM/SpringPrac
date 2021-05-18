package com.siggraph.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siggraph.web.dao.NoticeDao;
import com.siggraph.web.entity.Notice;
import com.siggraph.web.entity.NoticeView;

@Service
public class NoticeServiceImp implements NoticeService{

	
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public List<NoticeView> getViewList() {
		// TODO Auto-generated method stub
		return getViewList(1,"title","");
	}
	
	@Override
	public List<NoticeView> getViewList(String field, String query) {
		// TODO Auto-generated method stub
		return getViewList(1, field, query);
	}
	
	@Override
	public List<NoticeView> getViewList(int page, String field, String query) {

		int size = 10;
		int offset = 0+(page-1)*size;
		
		List<NoticeView> list = noticeDao.getViewList(offset, size, field, query);
		
		return list;
	}

	@Override
	public List<NoticeView> getPubViewList(boolean pub) {
		// TODO Auto-generated method stub
		return getPubViewList(1,"title","", pub);
	}

	@Override
	public List<NoticeView> getPubViewList(String field, String query, boolean pub) {
		// TODO Auto-generated method stub
		return getPubViewList(1, field, query, pub);
	}

	@Override
	public List<NoticeView> getPubViewList(int page, String field, String query, boolean pub) {
		int size = 10;
		int offset = 0+(page-1)*size;
		
		List<NoticeView> list = noticeDao.getPubViewList(offset, size, field, query, pub);
		
		return list;
	}
	
	@Override
	public int getCount() {

		return getCount("title", "");
	}
	
	@Override
	public int getCount(String field, String query) {

		return noticeDao.getCount(field, query);
	}
	
	@Override
	public int getPubCount(boolean pub) {
		// TODO Auto-generated method stub
		return getPubCount("title", "", pub);
	}

	@Override
	public int getPubCount(String field, String query, boolean pub) {
		// TODO Auto-generated method stub
		return noticeDao.getPubCount(field, query, pub);
	}
	
	@Override
	public NoticeView getView(int id) {

		NoticeView notice = noticeDao.getView(id);
		
		return notice;
	}

	@Override
	public Notice getNext(int id) {
			
		return noticeDao.getNext(id);
	}

	@Override
	public Notice getPrev(int id) {

		return noticeDao.getPrev(id);
	}
	
	@Override
	public Notice getPubNext(int id, boolean pub) {
		// TODO Auto-generated method stub
		return noticeDao.getPubNext(id);
	}

	@Override
	public Notice getPubPrev(int id, boolean pub) {
		// TODO Auto-generated method stub
		return noticeDao.getPubPrev(id);
	}

	@Override
	public int updatePubAll(int[] pubIds, int[] closeIds) {

		return noticeDao.updatePubAll(pubIds, closeIds);
	}

	@Override
	public int deleteAll(int[] ids) {

		return noticeDao.deleteAll(ids);
	}
	
	

	@Override
	public int update(Notice notice) {

		return noticeDao.update(notice);
	}

	@Override
	public int delete(int id) {
		
		return noticeDao.delete(id);
	}

	@Override
	public int insert(Notice notice) {
		
		return noticeDao.insert(notice);
	}

	@Override
	public int pubSelected(int[] pubIds, boolean pub) {
		
		return noticeDao.pubSelected(pubIds, pub);
	}

	@Override
	public int closeSelected(int[] closeIds, boolean pub) {
		
		return noticeDao.closeSelected(closeIds, pub);
	}




}
