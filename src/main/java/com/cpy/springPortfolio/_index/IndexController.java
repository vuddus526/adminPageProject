package com.cpy.springPortfolio._index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpy.springPortfolio._common.Util;



@Controller
public class IndexController {

	String forwardPage = "_main/main";
	
	Util util = new Util();
	
	@RequestMapping("/")
	public String index(
			Model model,
			HttpServletRequest request
		) {
		String[] serverInfo = util.getServerInfo(request);
//		String referer = serverInfo[0];
//		String path = serverInfo[1];
//		String url = serverInfo[2];
//		String uri = serverInfo[3];
		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
		
		model.addAttribute("ip", ip);
		model.addAttribute("folderName", "_index");
		model.addAttribute("fileName", "index");
		
		return forwardPage;
	}
}
