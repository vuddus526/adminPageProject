package com.cpy.springPortfolio.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpy.springPortfolio.member.model.dto.MemberDTO;
import com.cpy.springPortfolio.member.util.MemberUtil;
import com.cpy.springPortfolio.admin.model.dto.AdminDTO;
import com.cpy.springPortfolio.member.model.dao.MemberDAO;
import com.cpy.springPortfolio.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Inject
	MemberDAO memberDao;
	@Inject
	MemberService memberService;
	
//	@Autowired
//	private HttpSession session;
	
	MemberUtil util = new MemberUtil();
	
	String forwardPage = "_main/main";
	
	@RequestMapping("/list")
	public String list(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam( value="pageNumber", defaultValue="1") String pageNumber_
		){
	
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
		
		if(sessionNo == 0) {
			model.addAttribute("ip", ip);
			model.addAttribute("folderName", "_index");
			model.addAttribute("fileName", "index");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('관리자 로그인 후 이용해 주세요');</script>");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(sessionNo == 999) {
		
			String[] searchInfo = util.getSearchCheck(request);
			String searchGubun = searchInfo[0];
			String searchData = searchInfo[1];
			
			int pageNumber = util.getNumberCheck(pageNumber_, 1);
			
			int pageSize = 10;
			int blockSize = 10;
			int totalRecord = memberDao.getTotalRecord(searchGubun, searchData);
			int[] pageArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int jj = pageArray[0];
			int startRecord = pageArray[1];
			int lastRecord = pageArray[2];
			int totalPage = pageArray[3];
			int startPage = pageArray[4];
			int lastPage = pageArray[5];
			
			List<MemberDTO> list = memberDao.getSelectAll(searchGubun, searchData, startRecord, lastRecord);
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
			
			model.addAttribute("folderName", "member");
			model.addAttribute("fileName", "list");
		} else {
			model.addAttribute("ip", ip);
			model.addAttribute("folderName", "_index");
			model.addAttribute("fileName", "index");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('관리자만 접근 가능합니다');</script>");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return forwardPage;
	}
	
	@RequestMapping("/view")
	public String view(
			Model model,
			int no
		) {
		MemberDTO arguDto = new MemberDTO();
		arguDto.setNo(no);
		
		MemberDTO dto = memberDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);
		
		model.addAttribute("folderName", "member");
		model.addAttribute("fileName", "view");
		
		return forwardPage;
	}
	
	@RequestMapping("/chuga")
	public String chuga(
			Model model
		) {

		model.addAttribute("folderName", "member");
		model.addAttribute("fileName", "chuga");
		
		return forwardPage;
	}
	
	@RequestMapping("/sujung")
	public String sujung(
			Model model,
			int no
		) {
		MemberDTO arguDto = new MemberDTO();
		arguDto.setNo(no);
		
		MemberDTO dto = memberDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);

		model.addAttribute("folderName", "member");
		model.addAttribute("fileName", "sujung");
		
		return forwardPage;
	}
	
	@RequestMapping("/sakje")
	public String sakje(
			Model model,
			int no
		) {
		MemberDTO arguDto = new MemberDTO();
		arguDto.setNo(no);
		
		MemberDTO dto = memberDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);

		model.addAttribute("folderName", "member");
		model.addAttribute("fileName", "sakje");
		
		return forwardPage;
	}
	
	
	
	@RequestMapping("/chugaProc")
	public String chugaProc(
			Model model,
			@ModelAttribute MemberDTO dto
		) {
		
		String jumin = dto.getJumin1() + "-" + dto.getJumin2();
		dto.setJumin(jumin);
		
		//int result = memberDao.setInsert(dto);
		int result = memberService.setInsert(dto);
		
		String moveUrl = "/member/chuga";
		if(result > 0) {
			moveUrl = "/member/list"; //return "redirect:/member/list";
		} 
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/sujungProc")
	public String sujungProc(
			Model model,
			MemberDTO dto
		) {
		
		int result = memberService.setUpdate(dto);
		
		String moveUrl = "/member/sujung?no=" + dto.getNo();
		if(result > 0) {
			moveUrl = "/member/view?no=" + dto.getNo();
		} 
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/sakjeProc")
	public String sakjeProc(
			Model model,
			MemberDTO dto
		) {
		
		int result = memberService.setDelete(dto);
		
		String moveUrl = "/member/sakje?no=" + dto.getNo();
		if(result > 0) {
			moveUrl = "/member/list";
		} 
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/login")
	public String login(
			Model model
		) {
		
		model.addAttribute("folderName", "member");
		model.addAttribute("fileName", "login");
		
		return forwardPage;
	}
	
	@RequestMapping("/loginProc")
	public String loginProc(
			Model model,
			MemberDTO dto,
			HttpSession session
		) {
		MemberDTO resultDto = memberDao.getLogin(dto);
		
		String moveUrl = "/member/login";
		if(resultDto != null) {
			session.setAttribute("sessionNo", resultDto.getNo());
			session.setAttribute("sessionId", resultDto.getId());
			session.setAttribute("sessionName", resultDto.getName());
			moveUrl = "/";
		}
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/adminLoginProc")
	public String adminLoginProc(
			Model model,
			AdminDTO dto,
			HttpSession session
		) {
		System.out.println("dto : " + dto.getId());
		System.out.println("dto : " + dto.getPasswd());
		AdminDTO resultDto = memberDao.getAdminLogin(dto);
		
		System.out.println("resultDto : " + resultDto.getId());
		System.out.println("resultDto : " + resultDto.getPasswd());

		
		String moveUrl = "/member/login";
		if(resultDto != null) {
			session.setAttribute("sessionNo", resultDto.getNo());
			session.setAttribute("sessionId", resultDto.getId());
			session.setAttribute("sessionName", resultDto.getName());
			moveUrl = "/";
		}
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/logout")
	public String logout(
			HttpSession session
		) {
		//session.removeAttribute("sessionNo"); // 세션 하나씩 지우기
		session.invalidate(); // 저장된 세션 한번에 다 날리기
		return "redirect:/";
	}
}
