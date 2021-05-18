package com.siggraph.web.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siggraph.web.entity.Notice;
import com.siggraph.web.entity.NoticeView;
import com.siggraph.web.service.NoticeService;

@Controller("CustomerNoticeController")
@RequestMapping("/customer/notice/")
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@RequestMapping("list")
	public String list(
			@RequestParam(value="p", defaultValue = "1") Integer page ,
			@RequestParam(value="f", defaultValue = "title") String field,
			@RequestParam(value="q", defaultValue = "") String query,
			Model model) {
		
		int pubCount = service.getPubCount(field, query, true);
		
		List<NoticeView> list = service.getPubViewList(page, field, query, true);
		model.addAttribute("list", list);
		model.addAttribute("count", pubCount);
		
		return "customer.notice.list";
	}
	
	@RequestMapping("detail")
	public String detail(@RequestParam(value="id", required = false) Integer id, Model model) {
		
		NoticeView notice = service.getView(id);
		Notice nextPubNotice = service.getPubNext(id, true);
		Notice prevPubNotice = service.getPubPrev(id, true);

		model.addAttribute("notice", notice);
		model.addAttribute("nextNotice", nextPubNotice);
		model.addAttribute("prevNotice", prevPubNotice);
		
		return "customer.notice.detail";
	}
}
