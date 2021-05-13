package com.siggraph.web.admin.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.siggraph.web.entity.Notice;
import com.siggraph.web.entity.NoticeView;
import com.siggraph.web.service.NoticeService;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	private String prefixMap = "admin.board.notice.";
	
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
		
		return prefixMap + "list";
	}
	
	@RequestMapping("detail")
	public String detail(@RequestParam(value="id", required = false) Integer id, Model model) {
		
		NoticeView notice = service.getView(id);
		Notice nextNotice = service.getNext(id);
		Notice prevNotice = service.getPrev(id);

		model.addAttribute("notice", notice);
		model.addAttribute("nextNotice", nextNotice);
		model.addAttribute("prevNotice", prevNotice);
		
		return prefixMap + "detail";
	}
	
	@RequestMapping("reg")
	public String reg() {
		
		return prefixMap + "reg";
	}

	@PostMapping("reg")
	public RedirectView reg(
			@RequestParam(value="title") String title,
			@RequestParam(value="content") String content,
			@RequestParam(value="file", defaultValue = "") String file,
			@RequestParam(value="open", defaultValue = "0") Boolean open
			) {
		
		
		Notice notice = new Notice();
		notice.setMemberId(1);
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(open);
		service.insert(notice);
		
		
		return new RedirectView("list");
	}
	
	@RequestMapping("del")
	public RedirectView del(@RequestParam(value="id", required=false) Integer id) {
		
		service.delete(id);
		
		return new RedirectView("list");
	}
	
	@RequestMapping("edit")
	public String edit(@RequestParam(value="id", required=false) Integer id, Model model) {
		
		NoticeView notice = service.getView(id);
		
		model.addAttribute("notice", notice);
		
		return prefixMap + "edit";
	}

	@PostMapping("edit")
	public RedirectView edit(
			@RequestParam(value="id", required=false) Integer id,
			@RequestParam(value="title") String title,
			@RequestParam(value="content") String content,
			@RequestParam(value="file", defaultValue = "") String file,
			@RequestParam(value="open", defaultValue = "0") Boolean open
			) {
		
		
		Notice notice = new Notice();
		notice.setId(id);
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(open);
		service.update(notice);
		
		
		return new RedirectView("detail?id="+id);
	}
	
	@PostMapping("pubAll")
	public RedirectView pubAll(
			@RequestParam(value = "open-id", defaultValue = "") int[] openIds,
			@RequestParam(value = "del-id", defaultValue = "") int[] delIds,
			@RequestParam(value="cmd") String cmd) {
		
		switch (cmd) {
		case "선택공개":
			
			service.updatePubAll(openIds, delIds);
			
			for(Integer id : openIds)
				System.out.println(id);
			break;

		case "선택삭제" :
			
			service.deleteAll(delIds);
							
			break;
		}
				
		return new RedirectView("list");
	}
}
