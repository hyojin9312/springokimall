package com.okimall.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

public class FileUtils {
	
	public static String uploadFile(String uploadPath, String originName, byte[] fileData) throws Exception{
		
		System.out.println("======uploadFile execute....");
		
		// 파일명 설정 ex) uuid_파일명
		UUID uuid = UUID.randomUUID(); // 파일명 중복방지 목적
		String savedName = uuid.toString() + "_" + originName;
		// 파일경로 설정  ex)날짜경로
		String savedPath = calcPath(uploadPath);
		// 설정한 정보로 빈 파일생성
		File target = new File(uploadPath + savedPath, savedName);
		// 만든 파일에 데이터씀
		FileCopyUtils.copy(fileData, target);
		
		// 확장자 명만 가져옴
		String formatName = originName.substring(originName.lastIndexOf(".") +1);
		String uploadFileName = null;
		
		// 이미지파일인지 일반 파일인지 확인, 이미지파일이면 썸네일 생성
		if(MediaUtils.getMediaType(formatName) != null) {
			uploadFileName = makeThumbNail(uploadPath, savedPath, savedName);
		} else {
			//일반파일이면 아이콘생성
			uploadFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		
		
		return uploadFileName;
	}

	// 날짜 폴더 경로 설정
	private static String calcPath(String uploadPath) {
		
		Calendar cal = Calendar.getInstance();
		
		// 년/월/일 형태의 날짜 경로
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		//월
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		//일
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		System.out.println("======calcPath result:" + datePath);
		
		// 경로별 모든 폴더 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}

	private static void makeDir(String uploadPath, String... paths) {
		
		// 마지막 매개변수의 폴더가 존재하는지 확인
		if(new File(paths[paths.length - 1]).exists()) {
			return;
		}
		
		// 매개변수로 들어온 경로의 모든폴더 생성
		for(String path: paths) {
			File dirPath = new File(uploadPath + path);
			// 해당폴더가 존재하지 않으면
			if(!dirPath.exists()) {
				dirPath.mkdir(); //폴더 생성
			}
		}
	}
	
	private static String makeThumbNail(String uploadPath, String path, String fileName) throws Exception {
		
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,120);
		
		// 썸네일 생성준비
		String thumbNailName = uploadPath + path + File.separator + "s_" + fileName;
		File newFile = new File(thumbNailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		// 썸네일 생성
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		// 생성한 썸네일 경로 반환
		System.out.println("====makeThumbNail thumbNail:" + thumbNailName);
		return thumbNailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		
		String iconName = uploadPath + path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	public static ResponseEntity<byte[]> getFile(String uploadPath, String fileName) throws Exception{
		
		// 입력스트림 객체
		InputStream in = null;
		
		byte[] fileData = null; // 이미지파일을 읽어오는 저장소
		ResponseEntity<byte[]> entity = null;
		
		// 파일이 존재하지 않을 경우에는 예외발생, 예외처리 구문
		try {
			//파일의 확장자로 파일종류 확인
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			// 파일명이 이미지 종류인지 체크
			MediaType type = MediaUtils.getMediaType(formatName);
			
			// 파일 헤더 설정
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(type);
			
			// 파일 가져옴
			in = new FileInputStream(uploadPath + fileName);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		
		return entity;
	}
	
	// 이미지 파일 삭제
	public static void deleteFile(String uploadPath, String fileName) {
		
		// 날짜경로 + 파일이름
		String front = fileName.substring(0, 12); //날짜경로
		String end = fileName.substring(14); //파일이름
		String origin = front + end;
		
		new File(uploadPath+origin.replace('/', File.separatorChar)).delete(); //원본파일 삭제
		new File(uploadPath+fileName.replace('/', File.separatorChar)).delete();
		
	}
	
	// 썸네일 파일명 -> 원래 파일명
	public static String thumbToOriginName(String thumbnailName) {
		String front = thumbnailName.substring(0, 12);
		String end = thumbnailName.substring(14);
		
		return front+end;
		
	}
	
}
