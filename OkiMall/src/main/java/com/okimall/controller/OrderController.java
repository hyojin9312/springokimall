package com.okimall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.okimall.domain.OrderDetailVOList;
import com.okimall.domain.OrderListVO;
import com.okimall.domain.OrderVO;
import com.okimall.domain.ProductVO;
import com.okimall.dto.MemberDTO;
import com.okimall.service.MemberService;
import com.okimall.service.OrderService;
import com.okimall.service.ProductService;

@Controller
@RequestMapping(value = "/order/*")
public class OrderController {
	
	@Inject
	private OrderService service;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	// 상세페이지 구매
	@RequestMapping(value = "buy", method = RequestMethod.GET)
	public void buyGET(@RequestParam int ord_amount, @RequestParam int gds_no, Model model, HttpSession session)throws Exception{
		
		logger.info("======buyGET execute....");
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		productList.add(productService.readProduct(gds_no));
		amountList.add(ord_amount);
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		model.addAttribute("user", memberService.readUser(dto.getMb_id()));
		
	}
	// 장바구니에서 구매
	@RequestMapping(value = "buyFromCart", method = RequestMethod.GET)
	public String buyFromCartGET(@RequestParam int ord_amount, 
								@RequestParam int gds_no, Model model, HttpSession session) throws Exception{
		
		logger.info("======buyFromCartGET execute....");
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		productList.add(productService.readProduct(gds_no));
		amountList.add(ord_amount);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		model.addAttribute("user", memberService.readUser(dto.getMb_id()));
		
		return "/order/buyFromCart";
		
	}
	
	// 장바구니 체크상품 구매
	@RequestMapping(value = "buyFromCart", method = RequestMethod.POST)
	public void buyFromCartPOST(@RequestParam("check") List<Integer> checkList,
						@RequestParam("gds_no") List<Integer> gds_noList, @RequestParam("cart_amount") List<Integer> cart_amountList,
						@RequestParam("cart_code") List<Integer> cart_codeList, Model model, HttpSession session) throws Exception{
		
		logger.info("======buyFromCartpost execute....");
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		for(int i=0; i<cart_codeList.size(); i++) {
			for(int j=0; j<checkList.size(); j++) {
				if(cart_codeList.get(i) == checkList.get(j)) {
					productList.add(productService.readProduct((int)gds_noList.get(i)));
					amountList.add(cart_amountList.get(i));
					continue;
				} else {
					continue;
				}
			}
		}
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		model.addAttribute("user", memberService.readUser(dto.getMb_id()));
	}
	
	
	// 상품상세에서 구매
	@RequestMapping(value = "order", method = RequestMethod.POST)
	public String orderPOST(OrderVO order, OrderDetailVOList orderDetailList, 
							Model model, HttpSession session) throws Exception{
		
		logger.info("======orderPOST execute....");
		
		logger.info("======OrderVO(주문자정보):" + order.toString());
		logger.info("======OrderDetail(주문내역):" + orderDetailList.toString());
		
		service.addOrder(order, orderDetailList);
		
		return "/order/orderComplete";
		
	}
	
	// 장바구니에서 구매
	@RequestMapping(value = "orderFromCart", method = RequestMethod.POST)
	public String orderFromCartPOST(OrderVO order, OrderDetailVOList orderDetailList, HttpSession session) throws Exception{
		
		logger.info("======orderFromCartPost execute....");
		
		logger.info("======OrderVO(주문자정보):" + order.toString());
		logger.info("======OrderDetail(주문내역):" + orderDetailList.toString());
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		service.addOrderCart(order, orderDetailList, dto.getMb_id());
		logger.info("orderDetailList"+ orderDetailList);
		
		return "/order/orderComplete";
		
	}
	
	// 주문조회 작업
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void listGET(Model model, HttpSession session) throws Exception{
		
		logger.info("======listGet execute....");
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		List<OrderListVO> list = service.orderList(dto.getMb_id());
		
		model.addAttribute("orderList", list);
		
	}
	
	// 주문상세 조회
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void readGET(int ord_no, Model model, HttpSession session) throws Exception{
		
		logger.info("======readGet execute....");
		
		model.addAttribute("orderList", service.readOrder(ord_no));
		model.addAttribute("order", service.getOrder(ord_no));
		
	}
	

}
