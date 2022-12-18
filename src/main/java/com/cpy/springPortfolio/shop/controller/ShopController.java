package com.cpy.springPortfolio.shop.controller;

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
import com.cpy.springPortfolio.product.model.dao.ProductDAO;
import com.cpy.springPortfolio.product.model.dto.ProductDTO;
import com.cpy.springPortfolio.product.service.ProductService;
import com.cpy.springPortfolio.product.util.ProductUtil;


@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@Inject
	ProductDAO productDao;
	@Inject
	ProductService productService;
	
//	@Autowired
//	private HttpSession session;
	
	ProductUtil util = new ProductUtil();
	
	String forwardPage = "_main/main";
	
	@RequestMapping("/list")
	public String list(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
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
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('로그인 후 이용해 주세요');</script>");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		
		String[] searchInfo = util.getSearchCheck(request);
		String searchGubun = searchInfo[0];
		String searchData = searchInfo[1];
		
		
		int pageNumber = util.getNumberCheck(pageNumber_, 1);
		
		int pageSize = 10;
		int blockSize = 10;
		int totalRecord = productDao.getTotalRecord(searchGubun, searchData);
		int[] pageArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
		int jj = pageArray[0];
		int startRecord = pageArray[1];
		int lastRecord = pageArray[2];
		int totalPage = pageArray[3];
		int startPage = pageArray[4];
		int lastPage = pageArray[5];
		
		List<ProductDTO> list = productDao.getSelectAll(searchGubun, searchData, startRecord, lastRecord);
		
		int cell_counter = 3; //가로하나에 배열한 열 갯수 (열 td)
		
		int imsi_mok = list.size() / cell_counter;
		int imsi_na = list.size() % cell_counter;
		
		int row_counter = imsi_mok; //가로인 행 갯수 (행 tr)
		
		if(imsi_na > 0){
			row_counter++;
		}
		
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
		
		model.addAttribute("cell_counter", cell_counter);
		model.addAttribute("row_counter", row_counter);
		
		model.addAttribute("folderName", "shop");
		model.addAttribute("fileName", "list");
		
		}
		
		return forwardPage;
	}
	
	@RequestMapping("/view")
	public String view(
			Model model,
			HttpServletRequest request
		) {
		String productNo_ = request.getParameter("productNo");
		int productNo = util.getNumberCheck(productNo_, 0);
		
		ProductDTO arguDto = new ProductDTO();
		arguDto.setProductNo(productNo);
		
		ProductDTO dto = productDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);
		
		model.addAttribute("folderName", "shop");
		model.addAttribute("fileName", "view");
		
		return forwardPage;
	}
	
	@RequestMapping("/chuga")
	public String chuga(
			Model model
		) {

		model.addAttribute("folderName", "product");
		model.addAttribute("fileName", "chuga");
		
		return forwardPage;
	}
	
	@RequestMapping("/chugaAttach")
	public String chugaAttach(
			Model model
		) {

		model.addAttribute("folderName", "product");
		model.addAttribute("fileName", "chugaAttach");
		
		return forwardPage;
	}
	
	@RequestMapping("/sujung")
	public String sujung(
			Model model,
			HttpServletRequest request
		) {
		String productNo_ = request.getParameter("productNo");
		int productNo = util.getNumberCheck(productNo_, 0);
		
		ProductDTO arguDto = new ProductDTO();
		arguDto.setProductNo(productNo);
		
		ProductDTO dto = productDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);

		model.addAttribute("folderName", "product");
		model.addAttribute("fileName", "sujung");
		
		return forwardPage;
	}
	
	@RequestMapping("/sakje")
	public String sakje(
			Model model,
			HttpServletRequest request
		) {
		String productNo_ = request.getParameter("productNo");
		int productNo = util.getNumberCheck(productNo_, 0);
		
		ProductDTO arguDto = new ProductDTO();
		arguDto.setProductNo(productNo);
		
		ProductDTO dto = productDao.getSelectOne(arguDto);
		model.addAttribute("dto", dto);

		model.addAttribute("folderName", "product");
		model.addAttribute("fileName", "sakje");
		
		return forwardPage;
	}
	
	
	
	@RequestMapping("/chugaProc")
	public String chugaProc(
			Model model,
			HttpServletRequest request
			//@ModelAttribute ProductDTO dto
		) {
		String[] serverInfo = util.getServerInfo(request);
//		String referer = serverInfo[0];
		String path = serverInfo[1];
//		String url = serverInfo[2];
//		String uri = serverInfo[3];
//		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
		
		String productName = request.getParameter("productName");
		
		String vendor = request.getParameter("vendor");
		
		String productPrice_ = request.getParameter("productPrice");
		int productPrice = util.getNumberCheck(productPrice_, 0);
		
		String productDescription = request.getParameter("productDescription");
		String attachInfo = "-|-|-"; 
		
		ProductDTO dto = new ProductDTO();
		dto.setProductName(productName);
		dto.setVendor(vendor);
		dto.setProductPrice(productPrice);
		dto.setProductDescription(productDescription);
		dto.setAttachInfo(attachInfo);
		
		int result = productService.setInsert(dto);
		
		String moveUrl = "/product/chuga";
		if(result > 0) {
			moveUrl = "/product/list";
		} 
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/chugaAttachProc")
	public String chugaAttachProc(
			Model model,
			HttpServletRequest request,
			@RequestParam("file") List<MultipartFile> multiFileList
			//@ModelAttribute ProductDTO dto
		) {
		String[] serverInfo = util.getServerInfo(request);
//		String referer = serverInfo[0];
		String path = serverInfo[1];
//		String url = serverInfo[2];
//		String uri = serverInfo[3];
//		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
		
		String productName = request.getParameter("productName");
		
		String vendor = request.getParameter("vendor");
		
		String productPrice_ = request.getParameter("productPrice");
		int productPrice = util.getNumberCheck(productPrice_, 0);
		
		String productDescription = request.getParameter("productDescription");
		
		
		MultipartUpload mu = new MultipartUpload();
		List<String> list = mu.attachProc(multiFileList, path + "/product");
		  
		String attachInfo = ""; 
		for(int i=0; i<list.size(); i++) { 
			attachInfo += "|" + list.get(i);
		} 
		attachInfo = attachInfo.substring(1);
		
		ProductDTO dto = new ProductDTO();
		dto.setProductName(productName);
		dto.setVendor(vendor);
		dto.setProductPrice(productPrice);
		dto.setProductDescription(productDescription);
		dto.setAttachInfo(attachInfo);
		
		int result = productService.setInsert(dto);
		
		String moveUrl = "/product/chugaAttach";
		if(result > 0) {
			moveUrl = "/product/list";
		} 
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/sujungProc")
	public String sujungProc(
			Model model,
			HttpServletRequest request,
			@RequestParam("file") List<MultipartFile> multiFileList
			//ProductDTO dto
		) {
		String[] serverInfo = util.getServerInfo(request);
//		String referer = serverInfo[0];
		String path = serverInfo[1];
//		String url = serverInfo[2];
//		String uri = serverInfo[3];
//		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
		
		String attachPath = "C:/CPY/attach";
		String savePath = path + "/product";
		String uploadPath = attachPath + savePath;
		
		String productNo_ = request.getParameter("productNo");
		int productNo = util.getNumberCheck(productNo_, 0);
		
		String productName = request.getParameter("productName");
		
		String vendor = request.getParameter("vendor");
		
		String productPrice_ = request.getParameter("productPrice");
		int productPrice = util.getNumberCheck(productPrice_, 0);
		
		String productDescription = request.getParameter("productDescription");
		
		MultipartUpload mu = new MultipartUpload();
		List<String> list = mu.attachProc(multiFileList, path + "/product");
		  
		String attachInfo = ""; 
		for(int i=0; i<list.size(); i++) { 
			attachInfo += "|" + list.get(i);
		} 
		attachInfo = attachInfo.substring(1);
		
		ProductDTO arguDto = new ProductDTO();
		arguDto.setProductNo(productNo);
		
		ProductDTO oldDto = productService.getSelectOne(arguDto);
		String[] oldList = oldDto.getAttachInfo().split("[|]");
		String[] newList = attachInfo.split("[|]");
		
		System.out.println("oldList[0] : " + oldList[0]);
		System.out.println("oldList[1] : " + oldList[1]);
		System.out.println("oldList[2] : " + oldList[2]);
		
		System.out.println("newList[0] : " + newList[0]);
		System.out.println("newList[1] : " + newList[1]);
		System.out.println("newList[2] : " + newList[2]);
		
		
		String attachInfoResult = ""; 
		for(int i=0; i<list.size(); i++) {
			if(newList[i].equals("-")) {
				newList[i] = oldList[i];
			}
		
			if(!attachInfoResult.equals("")){
	
				attachInfoResult += "|";
			}
			attachInfoResult += newList[i];
		}
		
		System.out.println("attachInfoResult : " + attachInfoResult);
		
		ProductDTO dto = new ProductDTO();
		dto.setProductNo(productNo);
		dto.setProductName(productName);
		dto.setVendor(vendor);
		dto.setProductPrice(productPrice);
		dto.setProductDescription(productDescription);
		dto.setAttachInfo(attachInfoResult);
		
		int result = productService.setUpdate(dto);
		
		
		String[] attachInfoEquals;
		if(result > 0 ) {
			oldList = oldDto.getAttachInfo().split("[|]");
			newList = attachInfo.split("[|]");
			attachInfoEquals = attachInfoResult.split("[|]");

			
			String imsiSavedName = "";
			for(int i=0; i<oldList.length; i++) {
				
				if(!(oldList[i].equals(attachInfoEquals[i]))) {
					if(newList[i].equals(attachInfoEquals[i])) {
						if(!oldList[i].equals("-")) {
							String[] imsiOldList = oldList[i].split(",");
							
							imsiSavedName = imsiOldList[1];
							
							File f1 = new File(uploadPath + "/" + imsiSavedName);
							
							if(f1.exists()) {
								f1.delete();
								System.out.println("파일삭제성공");
							}else {
								System.out.println("파일존재안함");
							}
						}
					}
				}
				
			}
		}
		
		String moveUrl = "/product/sujung?productNo=" + productNo;
		if(result > 0) {
			moveUrl = "/product/view?productNo=" + productNo;
		} 
		return "redirect:" + moveUrl;
	}
	
	@RequestMapping("/sakjeProc")
	public String sakjeProc(
			Model model,
			HttpServletRequest request
			//ProductDTO dto
		) {
		String[] serverInfo = util.getServerInfo(request);
//		String referer = serverInfo[0];
		String path = serverInfo[1];
//		String url = serverInfo[2];
//		String uri = serverInfo[3];
//		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
		
		String attachPath = "C:/CPY/attach";
		String savePath = path + "/product";
		String uploadPath = attachPath + savePath;
		
		String productNo_ = request.getParameter("productNo");
		int productNo = util.getNumberCheck(productNo_, 0);
		
		ProductDTO dto = new ProductDTO();
		dto.setProductNo(productNo);
		
		ProductDTO oldDto = productService.getSelectOne(dto);
		
		String[] oldList = oldDto.getAttachInfo().split("[|]");
		
		System.out.println("oldList[0] : " + oldList[0]);
		System.out.println("oldList[1] : " + oldList[1]);
		System.out.println("oldList[2] : " + oldList[2]);
		
		System.out.println("oldList.length : " + oldList.length);
		
		int result = productService.setDelete(dto);
		
		if(result > 0 && !oldDto.getAttachInfo().equals("-")) {
			oldList = oldDto.getAttachInfo().split("[|]");
			
			String imsiSavedName = "";
			for(int i=0; i<oldList.length; i++) {
				if(!oldList[i].equals("-")) {
					String[] imsiList = oldList[i].split(",");
					imsiSavedName = imsiList[1];
					
					File f1 = new File(uploadPath + "/" + imsiSavedName);
					
					if(f1.exists()) {
						f1.delete();
						System.out.println("파일삭제성공");
					}else {
						System.out.println("파일존재안함");
					}
					
				}else {
					System.out.println("파일존재안함");
				}
			}
		}
		
		String moveUrl = "/product/sakje?productNo=" + productNo;
		if(result > 0) {
			moveUrl = "/product/list";
		} 
		return "redirect:" + moveUrl;
	}
}
