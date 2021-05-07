package com.siggraph.web.admin.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siggraph.web.entity.Notice;
import com.siggraph.web.entity.NoticeView;
import com.siggraph.web.service.NoticeService;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	private String prefix = "admin.board.notice.";
	
	@RequestMapping("list")
	public String list(
			@RequestParam(value="p", defaultValue = "1") Integer page ,
			@RequestParam(value="f", defaultValue = "title") String field,
			@RequestParam(value="q", defaultValue = "") String query,
			Model model) {
		
		int count = service.getCount(field, query);

		List<NoticeView> list = service.getViewList(page, field, query);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		
		return prefix + "list";
	}
	
	@RequestMapping("detail")
	public String detail(@RequestParam(value="id", required = false) Integer id, Model model) {
		
		NoticeView notice = service.getView(id);
		Notice nextNotice = service.getNext(id);
		Notice prevNotice = service.getPrev(id);

		model.addAttribute("notice", notice);
		model.addAttribute("nextNotice", nextNotice);
		model.addAttribute("prevNotice", prevNotice);
		
		return prefix + "detail";
	}
	
	@RequestMapping("reg")
	public String reg() {
		
		return prefix + "reg";
	}
}
