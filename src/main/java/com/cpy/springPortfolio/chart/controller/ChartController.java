package com.cpy.springPortfolio.chart.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/chart")
public class ChartController {
	
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
			HttpServletResponse response
		) {
		String[] serverInfo = util.getServerInfo(request);
		String ip = serverInfo[4];
		
		String[] sessionArray = util.getSessionCheck(request);
		int sessionNo = Integer.parseInt(sessionArray[0]);
		
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
		
		CartDTO dto = new CartDTO();
		dto.setMemberNo(sessionNo);
		
		List<CartDTO> list = cartDao.getCartAll(dto);
		model.addAttribute("list", list);
		model.addAttribute("ip", ip);
		
		model.addAttribute("folderName", "chart");
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
	
	
	
	@RequestMapping("/barChart")
	public String sujung(
			Model model,
			HttpServletRequest request
		) {
		String[] serverInfo = util.getServerInfo(request);
		String ip = serverInfo[4];
		
		String[] sessionArray = util.getSessionCheck(request);
		int sessionNo = Integer.parseInt(sessionArray[0]);
		
		CartDTO dto = new CartDTO();
		dto.setMemberNo(sessionNo);
		
		List<CartDTO> list = cartDao.getCartAll(dto);
		model.addAttribute("list", list);
		model.addAttribute("ip", ip);

		model.addAttribute("folderName", "chart");
		model.addAttribute("fileName", "barChart");
		
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
