package com.okimall.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.okimall.domain.CategoryVO;
import com.okimall.domain.ProductVO;
import com.okimall.service.AdProductService;
import com.okimall.util.FileUtils;
import com.okimall.util.PageMaker;
import com.okimall.util.SearchCriteria;

@Controller
@RequestMapping("/admin/product/*")
public class AdProductController {
	
	@Autowired
	private AdProductService service;
	
	private static final Logger logger = LoggerFactory.getLogger(AdProductController.class);
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@ResponseBody
	@RequestMapping(value = "subCGList/{cate_code}", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryVO>> subCGListPost(@PathVariable("cate_code") String cate_code){
		
		logger.info("====== subCGListGet execute....");
		
		// 2차카테고리 리스트
		ResponseEntity<List<CategoryVO>> entity = null;
		try {
			entity = new ResponseEntity<List<CategoryVO>>(service.subCGList(cate_code), HttpStatus.OK);
			
		}catch(Exception e) {
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
	// 파일출력
	@ResponseBody
	@RequestMapping(value = "displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
		
		return FileUtils.getFile(uploadPath, fileName);
	}
	
	// 이미지파일 삭제
	public void deleteFile(String fileName) {
		logger.info("delete FileName:" + fileName);
		
		FileUtils.deleteFile(uploadPath, fileName);
	}
	
	// 상품등록(GET)
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public void ProductInsert(Model model) throws Exception{
		
		model.addAttribute("cateList", service.mainCGList());
	}
	
	// 상품등록
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String ProductInsertPost(ProductVO vo, RedirectAttributes redirect) throws Exception {
		
		logger.info("======insertPost excute....");
		logger.info(vo.toString());
		
		vo.setGds_imag(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
		
		service.insertProduct(vo);
		redirect.addFlashAttribute("msg", "INSERT_SUCCESS");
		
		return "redirect:/admin/product/list";
	}
	
	@RequestMapping(value = "imgUpload", method=RequestMethod.POST)
	public void imgUpload(HttpServletRequest req, HttpServletResponse res, MultipartFile upload) {
		
		logger.info("====== imgUpload execute....");
		
		OutputStream out = null;
		PrintWriter printwriter = null;
		
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		try {
			//전송할 파일정보 가져옴
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			
			//폴더경로설정
			String uploadPath = req.getSession().getServletContext().getRealPath("/");
			uploadPath = uploadPath + "resources\\upload\\" + fileName;
			
			logger.info("======uploadPath:" + uploadPath);
			
			// 출력스트림
			out = new FileOutputStream(new File(uploadPath));
			out.write(bytes);
			
			// 클라이언트로 보내기위한 정보설정
			printwriter = res.getWriter();
			String fileUrl = "/upload/" + fileName;
			
			// ckEditor로 보내는 기능
			printwriter.println("{\"filename\":\"" + fileName + "\", \"uploaded\":1,\"url\":\""+fileUrl + "\"}");
			printwriter.flush();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			if(out != null) { // 출력스트림 종료
				try {out.close();} catch(Exception e) {e.printStackTrace();}
			}
			if(printwriter != null) {
				try {printwriter.close();}catch(Exception e) {e.printStackTrace();}			
				}
			}
		}
	
	// 상품리스트
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void productList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		
		logger.info("======productList execute....");
		logger.info("======cri:" + cri.toString());
		
		model.addAttribute("productList", service.searchListProduct(cri));
		
		// PageMaker
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		int count = service.searchListCount(cri);
		
		logger.info("======일치하는 상품개수:" + count);
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
	
	}
	
	// 상품 상세정보 페이지 읽기
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void productRead(@ModelAttribute("cri") SearchCriteria cri, @RequestParam("gds_no") int gds_no, Model model) throws Exception{
		
		logger.info("=======productReda execute....");
		
		//상품정보 날짜 변환
		ProductVO vo = service.readProduct(gds_no);
		logger.info("======Produect:" +vo.toString());
		
		// 파일이름수정
		vo.setGds_imag(vo.getGds_imag().substring(vo.getGds_imag().lastIndexOf("_")+1));
		model.addAttribute("vo", vo);
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);
		
	}
	
	//상품수정
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public void productEdit(@ModelAttribute("cri") SearchCriteria cri, @RequestParam("gds_no") int gds_no, Model model) throws Exception{
		
		logger.info("======productEdit execute....");
		
		ProductVO vo = service.readProduct(gds_no);
		logger.info("product :" +vo.toString());
		
		List<CategoryVO> subCateList = service.subCGList(vo.getCate_par());
		String originFile = vo.getGds_imag().substring(vo.getGds_imag().lastIndexOf("_")+1);
		
		model.addAttribute("vo", vo);
		model.addAttribute("originFile", originFile);
		model.addAttribute("cateList", service.mainCGList());
		model.addAttribute("subCateList", service.subCGList(vo.getCate_par()));
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);
		
	}
	
	// 상품수정post
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String productEditPost(ProductVO vo, SearchCriteria cri, RedirectAttributes redirect) throws Exception {
		
		logger.info("======productEditPost execute....");
		logger.info("======editted info:" + vo.toString());
		logger.info("======cri info:" + cri.toString());
		
		if(vo.getFile1().getSize()>0) {
			logger.info("======file not zero size");
			vo.setGds_imag(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
		}
		logger.info("======changed info:" + vo.toString());
		service.editProduct(vo);
		redirect.addFlashAttribute("cri", cri);
		redirect.addFlashAttribute("msg", "EDIT_SUCCESS");
		
		return "redirect:/admin/product/list";
	}
	
	//상품 삭제
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String productDeletePost(SearchCriteria cri, 
									@RequestParam("gds_no") int gds_no, 
									@RequestParam("gds_imag") String gds_imag, RedirectAttributes redirect) throws Exception {
		
		logger.info("======delete executed....");
		
		// 이미지삭제
		deleteFile(gds_imag);
		
		// 상품삭제
		service.deleteProduct(gds_no);
		redirect.addFlashAttribute("cri", cri);
		redirect.addFlashAttribute("msg", "DELETE_SUCCESS");
		
		return "redirect:/admin/product/list";
		
	}
	
	// 선택된 상품수정
	@ResponseBody
	@RequestMapping(value = "editChecked", method = RequestMethod.POST)
	public ResponseEntity<String> editChecked(@RequestParam("checkArr[]") List<Integer> checkArr,
											@RequestParam("oddArr[]") List<Integer> oddArr,
											@RequestParam("buyArr[]") List<String> buyArr){
		logger.info("======editChecked execute....");
		
		ResponseEntity<String> entity = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i=0; i<checkArr.size(); i++) {
				map.put("gds_no", checkArr.get(i));
				map.put("gds_odd", oddArr.get(i));
				map.put("gds_buytf", buyArr.get(i));
				
				service.editChecked(map);
			}
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		entity = new ResponseEntity<String>(HttpStatus.OK);
		return entity;
	}
	
	// 선택된 상품 삭제
	@ResponseBody
	@RequestMapping(value = "deleteChecked", method = RequestMethod.POST)
	public ResponseEntity<String> deleteChecked(@RequestParam("checkArr[]") List<Integer> checkArr,
												@RequestParam("imgArr[]") List<String> imgArr) {
		
		logger.info("====== deleteChecked execute....");
		
		ResponseEntity<String> entity = null;
		try {
			for(int i=0; i<checkArr.size(); i++) {
				deleteFile(imgArr.get(i));
				service.deleteProduct(checkArr.get(i));
			}
			
			entity = new ResponseEntity<String>(HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
