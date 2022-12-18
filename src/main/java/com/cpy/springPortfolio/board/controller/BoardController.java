package com.cpy.springPortfolio.board.controller;

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

import com.cpy.springPortfolio.board.model.dao.BoardDAO;
import com.cpy.springPortfolio.board.model.dto.BoardDTO;
import com.cpy.springPortfolio.board.service.BoardService;
import com.cpy.springPortfolio.board.util.BoardUtil;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Inject
	BoardDAO boardDao;
	@Inject
	BoardService boardService;
	
//	@Autowired
//	private HttpSession session;
	
	BoardUtil util = new BoardUtil();
	
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
		
		String tbl = request.getParameter("tbl");
		tbl = util.getBlankCheck(tbl);
		if(tbl.equals("")) {
			tbl = "imsi"; 
		}
		
		int pageSize = 10;
		int blockSize = 10;
		int totalRecord = boardDao.getTotalRecord(searchGubun, searchData);
		int[] pageArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
		int jj = pageArray[0];
		int startRecord = pageArray[1];
		int lastRecord = pageArray[2];
		int totalPage = pageArray[3];
		int startPage = pageArray[4];
		int lastPage = pageArray[5];
		
		List<BoardDTO> list = boardDao.getSelectAll(searchGubun, searchData, startRecord, lastRecord, tbl);
		model.addAttribute("list", list);
		
		model.addAttribute("ip", ip);
		
		model.addAttribute("sessionNo", sessionNo);
		model.addAttribute("sessionId", sessionId);
		model.addAttribute("sessionName", sessionName);
		
		model.addAttribute("searchGubun", searchGubun);
		model.addAttribute("searchData", searchData);
		
		model.addAttribute("pageNumber", pageNumber);
		
		model.addAttribute("tbl", tbl);
		
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("blockSize", blockSize);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("jj", jj);
		model.addAttribute("startRecord", startRecord);
		model.addAttribute("lastRecord", lastRecord);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("lastPage", lastPage);
		
		model.addAttribute("folderName", "board");
		model.addAttribute("fileName", "list");
		
		return forwardPage;
	}
	
	@RequestMapping("/view")
	public String view(
			Model model,
			HttpServletRequest request
			/*int no,
			String tbl*/
		) {
		String no_ = request.getParameter("no");
		
		int no = util.getNumberCheck(no_, 0);
		
		String passwd = request.getParameter("passwd");
		
		String tbl = request.getParameter("tbl");
		
		BoardDTO arguDto = new BoardDTO();
		arguDto.setNo(no);
		arguDto.setTbl(tbl);
		
		boardDao.setUpdateHit(arguDto);
		
		BoardDTO imsiDto = boardDao.getSelectOne(arguDto);
		
		String secretGubun = imsiDto.getSecretGubun();
		
		if(secretGubun.equals("T") && passwd == null) {
			model.addAttribute("imsiDto", imsiDto);
			model.addAttribute("folderName", "board");
			model.addAttribute("fileName", "passwd");
			
			return forwardPage;
		}
		
		BoardDTO dto = boardDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);
		
		model.addAttribute("folderName", "board");
		model.addAttribute("fileName", "view");
		
		return forwardPage;
	}
	
	@RequestMapping("/chuga")
	public String chuga(
			Model model,
			HttpServletRequest request
			/*int no,
			String tbl*/
		) {
		
		
		String no_ = request.getParameter("no");
		
		int no = util.getNumberCheck(no_, 0);
		
		String tbl = request.getParameter("tbl");
		
		BoardDTO dto = new BoardDTO();
		dto.setNo(no);
		dto.setTbl(tbl);
		
		
		if(no > 0) {
			BoardDTO arguDto = new BoardDTO();
			arguDto.setNo(no);
			arguDto.setTbl(tbl);
			
			dto = boardDao.getSelectOne(arguDto);
			dto.setContent(dto.getContent() + "\n ----- 답글 작성 ----- \n");
		}
		
		model.addAttribute("dto", dto);
		
		model.addAttribute("folderName", "board");
		model.addAttribute("fileName", "chuga");
		
		return forwardPage;
	}
	
	@RequestMapping("/sujung")
	public String sujung(
			Model model,
			HttpServletRequest request
			/*int no,
			String tbl*/
		) {
		String no_ = request.getParameter("no");
		
		int no = util.getNumberCheck(no_, 0);
		
		String tbl = request.getParameter("tbl");
		
		BoardDTO arguDto = new BoardDTO();
		arguDto.setNo(no);
		arguDto.setTbl(tbl);
		
		BoardDTO dto = boardDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);

		model.addAttribute("folderName", "board");
		model.addAttribute("fileName", "sujung");
		
		return forwardPage;
	}
	
	@RequestMapping("/sakje")
	public String sakje(
			Model model,
			HttpServletRequest request
			/*int no,
			String tbl*/
		) {
		String no_ = request.getParameter("no");
		
		int no = util.getNumberCheck(no_, 0);
		
		String tbl = request.getParameter("tbl");
		
		BoardDTO arguDto = new BoardDTO();
		arguDto.setNo(no);
		arguDto.setTbl(tbl);
		
		BoardDTO dto = boardDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);

		model.addAttribute("folderName", "board");
		model.addAttribute("fileName", "sakje");
		
		return forwardPage;
	}
	
	
	@RequestMapping("/chugaProc")
	public String chugaProc(
			Model model,
			HttpServletRequest request
		) {
		String[] serverInfo = util.getServerInfo(request);
//		String referer = serverInfo[0];
		String path = serverInfo[1];
//		String url = serverInfo[2];
//		String uri = serverInfo[3];
		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
		
		String no_ = request.getParameter("no");
		int no = util.getNumberCheck(no_, 0);
		
		String tbl = request.getParameter("tbl");
		tbl = util.getBlankCheck(tbl);
		if(tbl.equals("")) {
			tbl = "imsi"; 
		}
		
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		String noticeGubun = request.getParameter("noticeGubun");
		String secretGubun = request.getParameter("secretGubun");
		
		writer = util.getBlankCheck(writer);
		subject = util.getBlankCheck(subject);
		content = util.getBlankCheck(content);
		email = util.getBlankCheck(email);
		passwd = util.getBlankCheck(passwd);
		
		noticeGubun = util.getBlankCheck(noticeGubun);
		secretGubun = util.getBlankCheck(secretGubun);
		
		int errorCounter = 0;
		
		if(writer.equals("")) { errorCounter++; }
		if(email.equals("")) { errorCounter++; }
		if(passwd.equals("")) { errorCounter++; }
		if(subject.equals("")) { errorCounter++; }
		if(content.equals("")) { errorCounter++; }
		
		if(!(noticeGubun.equals("") || noticeGubun.equals("T"))) { 
			errorCounter++; 
		}
		if(!(secretGubun.equals("") || secretGubun.equals("T"))) { 
			errorCounter++; 
		}
		
		if(noticeGubun.equals("T") && secretGubun.equals("T")) {
			errorCounter++;
			
			String errorCode = "201";
			String errorMent = "공지글 비밀글 중복선택 불가합니다";
			String imsiUrl = path + "/board/chuga?no="+no+"&tbl="+tbl;
			
			model.addAttribute("folderName", "board");
			model.addAttribute("fileName", "error");
			
			model.addAttribute("errorCode", errorCode);
			model.addAttribute("errorMent", errorMent);
			model.addAttribute("imsiUrl", imsiUrl);
			
			return forwardPage;
		}
		
		if(errorCounter > 0) {
			String errorCode = "202";
			String errorMent = "항목을 모두 채워주세요";
			String imsiUrl = path + "/board/chuga?no="+no+"&tbl="+tbl;
			
			model.addAttribute("folderName", "board");
			model.addAttribute("fileName", "error");
			
			model.addAttribute("errorCode", errorCode);
			model.addAttribute("errorMent", errorMent);
			model.addAttribute("imsiUrl", imsiUrl);
			
			return forwardPage;
		}
		
		if(!noticeGubun.equals("T")) {
			noticeGubun = "F";
		}
		
		if(!secretGubun.equals("T")) {
			secretGubun = "F";
		}
		
		String[] sessionArray = util.getSessionCheck(request);
		int sessionNo = Integer.parseInt(sessionArray[0]);
		
		int num = boardService.getMaxNum() + 1;
		
		//새글일때
		int refNo = boardService.getMaxRefNo() + 1;
		int stepNo = 1;
		int levelNo = 1;
		int parentNo = 0;
		
		int hit = 0;
		int memberNo = sessionNo;
		
		int noticeNo = 0;
		if(noticeGubun.equals("T")) {
			noticeNo = boardService.getMaxNoticeNo() + 1;
		}
		
		if(no > 0){		//답변글일 때
			BoardDTO arguDto = new BoardDTO();
			arguDto.setNo(no);
			arguDto.setTbl(tbl);

			BoardDTO parentDto = boardService.getSelectOne(arguDto);
			
			boardService.setUpdateReLevel(parentDto);
			
		 	refNo = parentDto.getRefNo();
			stepNo = parentDto.getStepNo() + 1;
			levelNo = parentDto.getLevelNo() + 1;
			parentNo = parentDto.getNo();
		}
		
		BoardDTO dto = new BoardDTO();
		
		dto.setNum(num);
		dto.setTbl(tbl);
		
		dto.setWriter(writer);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setEmail(email);
		dto.setPasswd(passwd);
		
		dto.setRefNo(refNo);
		dto.setStepNo(stepNo);
		dto.setLevelNo(levelNo);
		dto.setParentNo(parentNo);
		dto.setHit(hit);
		dto.setIp(ip);
		dto.setMemberNo(memberNo);
		dto.setNoticeNo(noticeNo);
		dto.setSecretGubun(secretGubun);
		
		int result = boardService.setInsert(dto);
		
		String moveUrl = "/board/chuga?no=" + no + "&tbl=" + tbl;
		if(result > 0) {
			moveUrl = "/board/list?tbl=" + tbl;
		} 
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/sujungProc")
	public String sujungProc(
			Model model,
			HttpServletRequest request
		) {
		String[] serverInfo = util.getServerInfo(request);
//		String referer = serverInfo[0];
		String path = serverInfo[1];
//		String url = serverInfo[2];
//		String uri = serverInfo[3];
//		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
		
		String no_ = request.getParameter("no");
		int no = util.getNumberCheck(no_, 0);
		
		String tbl = request.getParameter("tbl");
		tbl = util.getBlankCheck(tbl);
		if(tbl.equals("")) {
			tbl = "imsi"; 
		}
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		String noticeGubun = request.getParameter("noticeGubun");
		String secretGubun = request.getParameter("secretGubun");
		String noticeNo_ = request.getParameter("noticeNo");
		int noticeNo = util.getNumberCheck(noticeNo_, 0);
	
		subject = util.getBlankCheck(subject);
		content = util.getBlankCheck(content);
		email = util.getBlankCheck(email);
		passwd = util.getBlankCheck(passwd);
		noticeGubun = util.getBlankCheck(noticeGubun);
		secretGubun = util.getBlankCheck(secretGubun);
	
		int errorCounter = 0;
		
		if(subject.equals("")) { errorCounter++; }
		if(content.equals("")) { errorCounter++; }
		if(email.equals("")) { errorCounter++; }
		if(passwd.equals("")) { errorCounter++; }
		
		if(!(noticeGubun.equals("") || noticeGubun.equals("T"))) { 
			errorCounter++;
		}
		if(!(secretGubun.equals("") || secretGubun.equals("T"))) { 
			errorCounter++; 
		}
		
		if(noticeGubun.equals("T") && secretGubun.equals("T")) {
			errorCounter++;
			
			String errorCode = "201";
			String errorMent = "공지글 비밀글 중복선택 불가합니다";
			String imsiUrl = path + "/board/chuga?no="+no+"&tbl="+tbl;
			
			model.addAttribute("folderName", "board");
			model.addAttribute("fileName", "error");
			
			model.addAttribute("errorCode", errorCode);
			model.addAttribute("errorMent", errorMent);
			model.addAttribute("imsiUrl", imsiUrl);
			
			return forwardPage;
		}
		
		if(errorCounter > 0) {
			String errorCode = "202";
			String errorMent = "항목을 모두 채워주세요";
			String imsiUrl = path + "/board/chuga?no="+no+"&tbl="+tbl;
			
			model.addAttribute("folderName", "board");
			model.addAttribute("fileName", "error");
			
			model.addAttribute("errorCode", errorCode);
			model.addAttribute("errorMent", errorMent);
			model.addAttribute("imsiUrl", imsiUrl);
			
			return forwardPage;
		}
		
		if(noticeNo > 0 && noticeGubun.equals("T")) { //공지글이였는데 체크한거
			noticeNo = noticeNo;
		} else if(noticeNo == 0 && noticeGubun.equals("T")) { //공지글이 아니였는데 체크한거
			noticeNo = boardService.getMaxNoticeNo() + 1;
		}
		
		//공지글인데 체크 푼거 			F를 풀고들어왔으니 조건문 할것없음
		//공지글이 아닌데 체크 안한거		F를 풀고들어왔으니 조건문 할것없음
		
		if(!secretGubun.equals("T")) {
			secretGubun = "F";
		}
		
		BoardDTO dto = new BoardDTO();
		dto.setNo(no);
		dto.setPasswd(passwd);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setEmail(email);
		dto.setNoticeNo(noticeNo);
		dto.setSecretGubun(secretGubun);
		
		
		int result = boardService.setUpdate(dto);
		
		String moveUrl = "/board/sujung?no=" + no + "&tbl=" + tbl;
		if(result > 0) {
			moveUrl = "/board/view?no=" + no + "&tbl=" + tbl;
		} 
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/sakjeProc")
	public String sakjeProc(
			Model model,
			HttpServletRequest request,
			BoardDTO dto
		) {
		String[] serverInfo = util.getServerInfo(request);
//		String referer = serverInfo[0];
		String path = serverInfo[1];
//		String url = serverInfo[2];
//		String uri = serverInfo[3];
//		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
		
		if(dto.getChildCounter() > 0) {
			String errorCode = "203";
			String errorMent = "자식글이 존재하여 삭제가 불가합니다";
			String imsiUrl = path + "/board/sakje?no="+dto.getNo()+"&tbl="+dto.getTbl();
			
			model.addAttribute("folderName", "board");
			model.addAttribute("fileName", "error");
			
			model.addAttribute("errorCode", errorCode);
			model.addAttribute("errorMent", errorMent);
			model.addAttribute("imsiUrl", imsiUrl);
			
			return forwardPage;
		}
		
		int result = boardService.setDelete(dto);
		
		String moveUrl = "/board/sakje?no=" + dto.getNo() + "&tbl=" + dto.getTbl();
		if(result > 0) {
			moveUrl = "/board/list";
		} 
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/passwdProc")
	public String passwdProc(
			Model model,
			HttpServletRequest request
		) {
		String[] serverInfo = util.getServerInfo(request);
//		String referer = serverInfo[0];
		String path = serverInfo[1];
//		String url = serverInfo[2];
//		String uri = serverInfo[3];
//		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
		
		String no_ = request.getParameter("no");
		
		int no = util.getNumberCheck(no_, 0);
		
		String tbl = request.getParameter("tbl");
		
		String passwd = request.getParameter("passwd");
		
		BoardDTO arguDto = new BoardDTO();
		arguDto.setNo(no);
		
		BoardDTO imsiDto = boardDao.getPasswdCheck(arguDto);
		
		String checkPasswd = imsiDto.getPasswd();		
	
		String moveUrl = "/board/list?tbl=" + tbl;
		if(passwd.equals(checkPasswd)){
			moveUrl = "/board/view?no=" + no + "&tbl=" + tbl + "&passwd=" + passwd;
		}
		return "redirect:" + moveUrl;
	}
}
