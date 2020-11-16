package com.okimall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okimall.domain.CategoryVO;
import com.okimall.domain.ProductVO;
import com.okimall.service.ProductService;
import com.okimall.util.Criteria;
import com.okimall.util.FileUtils;
import com.okimall.util.PageMaker;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	@Inject
	ProductService service;
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	public ResponseEntity<List<CategoryVO>> subCGList(@PathVariable("cate_code") String cate_code){
		
		logger.info("======subCGList execute....");
		
		ResponseEntity<List<CategoryVO>> entity = null;
		try {
			logger.info("====" + service.subCGList(cate_code));
			entity = new ResponseEntity<List<CategoryVO>>(service.subCGList(cate_code), HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
	// 카테고리에 해당하는 상품리스트
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void list(@ModelAttribute("cri") Criteria cri, 
					 @ModelAttribute("cate_code") String cate_code, Model model) throws Exception {
		
		logger.info("======list excuted....");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cate_code", cate_code);
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		
		List<ProductVO> list = service.productList(map);
		model.addAttribute("productList", list);
		logger.info("productList" + list.toString());
		
		model.addAttribute("cate_name", service.getCGName(cate_code));		
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int count = service.productCount(cate_code);
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "displayFile" , method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
		
		return FileUtils.getFile(uploadPath, fileName);
		
	}
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void productRead(@ModelAttribute("cri") Criteria cri, @RequestParam("gds_no") int gds_no, Model model) throws Exception{
		
		logger.info("======productRead execute....");
		
		ProductVO vo = service.readProduct(gds_no);
		
		vo.setGds_imag(FileUtils.thumbToOriginName(vo.getGds_imag()));
		
		logger.info("======product detail:" + vo.toString());
		model.addAttribute("vo", vo);
		
		//pageMaker
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);
		
	}

}
