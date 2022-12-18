package com.cpy.springPortfolio.memo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpy.springPortfolio.memo.model.dao.MemoDAO;
import com.cpy.springPortfolio.memo.model.dto.MemoDTO;
import com.cpy.springPortfolio.memo.service.MemoService;
import com.cpy.springPortfolio.memo.util.MemoUtil;

@Controller
@RequestMapping("/memo")
public class MemoController {
	
	@Inject
	MemoDAO memoDao;
	@Inject
	MemoService memoService;
	
//	@Autowired
//	private HttpSession session;
	
	MemoUtil util = new MemoUtil();
	
	String forwardPage = "_main/main";
	
	@RequestMapping("/list")
	public String list(
			Model model,
			HttpServletRequest request,
			@RequestParam( value="pageNumber", defaultValue="1") String pageNumber_
		) {
	
//		String pageNumber_ = request.getParameter("pageNumber");
		
		String[] serverInfo = util.getServerInfo(request);
//		String referer = serverInfo[0];
//		String path = serverInfo[1];
//		String url = serverInfo[2];
//		String uri = serverInfo[3];
		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
//		
//		String[] uriInfo = util.getUrlCheck(uri);
//		String folderName = uriInfo[0];
//		String fileName = uriInfo[1];
		
		String[] sessionArray = util.getSessionCheck(request);
		int sessionNo = Integer.parseInt(sessionArray[0]);
		String sessionId = sessionArray[1];
		String sessionName = sessionArray[2];
		
		String[] searchInfo = util.getSearchCheck(request);
		String searchGubun = searchInfo[0];
		String searchData = searchInfo[1];
		
		
		int pageNumber = util.getNumberCheck(pageNumber_, 1);
		
		int pageSize = 10;
		int blockSize = 10;
		int totalRecord = memoDao.getTotalRecord(searchGubun, searchData);
		int[] pageArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
		int jj = pageArray[0];
		int startRecord = pageArray[1];
		int lastRecord = pageArray[2];
		int totalPage = pageArray[3];
		int startPage = pageArray[4];
		int lastPage = pageArray[5];
		
		List<MemoDTO> list = memoDao.getSelectAll(searchGubun, searchData, startRecord, lastRecord);
		model.addAttribute("list", list);
		
		model.addAttribute("ip", ip);
		
		model.addAttribute("sessionNo", sessionNo);
		model.addAttribute("sessionId", sessionId);
		model.addAttribute("sessionName", sessionName);
		
		model.addAttribute("searchGubun", searchGubun);
		model.addAttribute("searchData", searchData);
		
		model.addAttribute("pageNumber", pageNumber);
		
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("blockSize", blockSize);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("jj", jj);
		model.addAttribute("startRecord", startRecord);
		model.addAttribute("lastRecord", lastRecord);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("lastPage", lastPage);
		
		model.addAttribute("folderName", "memo");
		model.addAttribute("fileName", "list");
		
		return forwardPage;
	}
	
	@RequestMapping("/view")
	public String view(
			Model model,
			int no
		) {
		MemoDTO arguDto = new MemoDTO();
		arguDto.setNo(no);
		
		MemoDTO dto = memoDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);
		
		model.addAttribute("folderName", "memo");
		model.addAttribute("fileName", "view");
		
		return forwardPage;
	}
	
	@RequestMapping("/chuga")
	public String chuga(
			Model model
		) {

		model.addAttribute("folderName", "memo");
		model.addAttribute("fileName", "chuga");
		
		return forwardPage;
	}
	
	@RequestMapping("/sujung")
	public String sujung(
			Model model,
			int no
		) {
		MemoDTO arguDto = new MemoDTO();
		arguDto.setNo(no);
		
		MemoDTO dto = memoDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);

		model.addAttribute("folderName", "memo");
		model.addAttribute("fileName", "sujung");
		
		return forwardPage;
	}
	
	@RequestMapping("/sakje")
	public String sakje(
			Model model,
			int no
		) {
		MemoDTO arguDto = new MemoDTO();
		arguDto.setNo(no);
		
		MemoDTO dto = memoDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);

		model.addAttribute("folderName", "memo");
		model.addAttribute("fileName", "sakje");
		
		return forwardPage;
	}
	
	
	
	@RequestMapping("/chugaProc")
	public String chugaProc(
			Model model,
			@ModelAttribute MemoDTO dto
		) {
		
		//int result = memoDao.setInsert(dto);
		int result = memoService.setInsert(dto);
		
		String moveUrl = "/memo/chuga";
		if(result > 0) {
			moveUrl = "/memo/list"; 
		} 
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/sujungProc")
	public String sujungProc(
			Model model,
			MemoDTO dto
		) {
		
		int result = memoService.setUpdate(dto);
		
		String moveUrl = "/memo/sujung?no=" + dto.getNo();
		if(result > 0) {
			moveUrl = "/memo/view?no=" + dto.getNo();
		} 
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/sakjeProc")
	public String sakjeProc(
			Model model,
			MemoDTO dto
		) {
		
		int result = memoService.setDelete(dto);
		
		String moveUrl = "/memo/sakje?no=" + dto.getNo();
		if(result > 0) {
			moveUrl = "/memo/list";
		} 
		return "redirect:" + moveUrl;
	}
}
