package com.okimall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.okimall.domain.AdminVO;
import com.okimall.domain.MemberVO;
import com.okimall.domain.OrderListVO;
import com.okimall.dto.AdminDTO;
import com.okimall.service.AdminService;
import com.okimall.service.MemberService;
import com.okimall.service.OrderService;
import com.okimall.util.Criteria;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@Inject
	private MemberService memberService;
	
	@Inject
	private OrderService orderService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	// 관리자 메인
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String adminMain() {
		return "admin/main";
	}
	
	// 관리자 로그인
	@RequestMapping(value = "login", method=RequestMethod.POST)
	public String loginPost(AdminDTO dto, RedirectAttributes redirect, HttpSession session, Model model) throws Exception {
		
		logger.info("======Adminlogin excute()");
		logger.info("======AdminDTO:" + dto.toString());
		
		AdminVO vo = null;
		vo = service.login(dto);
		String msg = "";
		
		
		if(vo != null) {
			session.setAttribute("admin", vo);
			model.addAttribute("admin", dto.getAdmin_id());
			msg = "LOGIN_SUCCESS";
		} else {
			msg = "LOGIN_FAIL";
		}
		redirect.addFlashAttribute("msg", msg);
		return "redirect:/admin/main";
	}
	
	// 로그아웃
	@RequestMapping(value = "logout", method=RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes redirect) {
		
		logger.info("====== logout excute");
		
		session.invalidate();
		redirect.addFlashAttribute("msg", "LOGOUT_SUCCESS");
		
		return "redirect:/admin/main";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(MemberVO vo, Model model) throws Exception{
		
		logger.info("====== list execute....");
		
		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		memberList.addAll(memberService.memberRead(vo));
		model.addAttribute("memberList", memberList);
		logger.info("==== memberList"+ memberList);
	
		return "/admin/admemberlist";
	}
	
	@RequestMapping(value = "orderlist", method = RequestMethod.GET)
	public String orderlist(@ModelAttribute("cri") Criteria cri, OrderListVO vo, Model model)throws Exception{
		
		logger.info("======admin orderlist execute.....");
		
		List<OrderListVO> orderList = new ArrayList<OrderListVO>();
		orderList.addAll(orderService.adorderList(vo));
		model.addAttribute("orderList", orderList);
		logger.info("======orderList" + orderList);
		
		
		return "/admin/orderlist";
		
	}
}
