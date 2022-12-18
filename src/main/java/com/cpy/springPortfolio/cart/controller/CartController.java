package com.cpy.springPortfolio.cart.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cpy.springPortfolio._common.MultipartUpload;
import com.cpy.springPortfolio.cart.model.dao.CartDAO;
import com.cpy.springPortfolio.cart.model.dto.CartDTO;
import com.cpy.springPortfolio.cart.service.CartService;
import com.cpy.springPortfolio.cart.util.CartUtil;



@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Inject
	CartDAO cartDao;
	@Inject
	CartService cartService;
	
//	@Autowired
//	private HttpSession session;
	
	CartUtil util = new CartUtil();
	
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
		} else {
		
		String[] searchInfo = util.getSearchCheck(request);
		String searchGubun = searchInfo[0];
		String searchData = searchInfo[1];
		
		CartDTO dto = new CartDTO();
		dto.setMemberNo(sessionNo);
		
		int pageNumber = util.getNumberCheck(pageNumber_, 1);
		
		int pageSize = 10;
		int blockSize = 10;
		int totalRecord = cartDao.getTotalRecord(dto);
		int[] pageArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
		int jj = pageArray[0];
		int startRecord = pageArray[1];
		int lastRecord = pageArray[2];
		int totalPage = pageArray[3];
		int startPage = pageArray[4];
		int lastPage = pageArray[5];
		
		List<CartDTO> list = cartDao.getSelectAll(dto);
		
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
		
		model.addAttribute("folderName", "cart");
		model.addAttribute("fileName", "list");
		}
		
		return forwardPage;
	}
	
	
	
	@RequestMapping("/sujung")
	public String sujung(
			Model model,
			HttpServletRequest request
		) {
		String productNo_ = request.getParameter("productNo");
		int productNo = util.getNumberCheck(productNo_, 0);
		
		CartDTO arguDto = new CartDTO();
		arguDto.setProductNo(productNo);
		
		CartDTO dto = cartDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);

		model.addAttribute("folderName", "product");
		model.addAttribute("fileName", "sujung");
		
		return forwardPage;
	}
	
	@RequestMapping("/chugaProc")
	public String chugaProc(
			Model model,
			HttpServletRequest request
			//@ModelAttribute CartDTO dto
		) {
		String[] serverInfo = util.getServerInfo(request);
//		String referer = serverInfo[0];
		String path = serverInfo[1];
//		String url = serverInfo[2];
//		String uri = serverInfo[3];
//		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
		
		String[] sessionArray = util.getSessionCheck(request);
		int sessionNo = Integer.parseInt(sessionArray[0]);
		
		String productNo_ = request.getParameter("productNo");
		int productNo = Integer.parseInt(productNo_);
		
		System.out.println("productNo : " + productNo);
		
		String jumun_su_ = request.getParameter("jumun_su");
		int amount = Integer.parseInt(jumun_su_);
		
		int memberNo = sessionNo;
		
		
		CartDTO dto = new CartDTO();
		dto.setProductNo(productNo);
		dto.setAmount(amount);
		dto.setMemberNo(memberNo);
		
		int result = cartService.setInsert(dto);
		
		String moveUrl = "/shop/view?productNo=" + productNo;
		if(result > 0) {
			moveUrl = "/cart/list";
		} 
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/sakjeProc")
	public String sakjeProc(
			Model model,
			HttpServletRequest request
		) {
		String[] sessionArray = util.getSessionCheck(request);
		int sessionNo = Integer.parseInt(sessionArray[0]);
		
		String cartNo_ = request.getParameter("cartNo");
		int cartNo = Integer.parseInt(cartNo_);
		
		CartDTO dto = new CartDTO();
		dto.setMemberNo(sessionNo);
		dto.setCartNo(cartNo);
		
		
		int result = cartDao.setDelete(dto);
		
		String moveUrl = "/cart/list?cartNo=" + cartNo;
		if(result > 0) {
			moveUrl = "/cart/list";
		} 
		return "redirect:" + moveUrl;
	}
	
	
	@RequestMapping("/SuntaekSakjeProc")
	public String SuntaekSakjeProc(
			Model model,
			HttpServletRequest request
		) {
		String[] sessionArray = util.getSessionCheck(request);
		int sessionNo = Integer.parseInt(sessionArray[0]);
		
		int result = 0;
		int cartNo = 0;
		String[] imsiArray = request.getParameterValues("cartNo");
		if(imsiArray == null){
			
		}else{
			for(int i=0; i<imsiArray.length; i++){
				cartNo = Integer.parseInt(imsiArray[i]);
				
				CartDTO dto = new CartDTO();
				dto.setCartNo(cartNo);
				dto.setMemberNo(sessionNo);
				
				result = cartDao.setDelete(dto);
			}
		}
		
		String moveUrl = "/cart/list?cartNo=" + cartNo;
		if(result > 0) {
			moveUrl = "/cart/list";
		} 
		return "redirect:" + moveUrl;
	}
	
	
}
