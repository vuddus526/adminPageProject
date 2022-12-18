package com.cpy.springPortfolio._common;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class MultipartUpload {
	String attachPath = "C:/CPY/attach";	//C:\\CPY\\attach
	int max_upload = 10 * 1024 * 1024;	// 10Mbyte
	
	private int createDirectory(String uploadPath) {
		File isDir = new File(uploadPath);
		if(!isDir.isDirectory()) {
			isDir.mkdirs();
		}
		int result = 0;
		if(isDir.exists()) {
			result++;
		}
		return result;
	}
	
	private String attachFileReName(String originalFileName, byte[] fileData, String uploadPath) {
		String ext = originalFileName.substring(originalFileName.lastIndexOf('.')).toLowerCase(); 
		UUID uuid = UUID.randomUUID();
		String newFileName = uuid.toString() + ext;
		File f1 = new File(uploadPath, newFileName);
		try {
			FileCopyUtils.copy(fileData, f1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newFileName;
	}

	
	public List<String> attachProc(
			List<MultipartFile> multiFileList,
			String savePath
			){
		String uploadPath = attachPath + savePath;
		int createResult = createDirectory(uploadPath);
		
		if(createResult <= 0) {
			System.out.println("디렉토리 존재안함");
		}
		
		List<String> list = new ArrayList<>();
		for(int i=0; i<multiFileList.size(); i++) {
			MultipartFile file = multiFileList.get(i);
			
			String originalFileName = "-";
			String newFileName = "-";
			long fileSize = 0;
			String contentType = "-";
			String mimeType = "-";
			
			int errorCounter = 0;
			try {
				originalFileName = file.getOriginalFilename();
				newFileName = attachFileReName(originalFileName, file.getBytes(), uploadPath);
				fileSize = file.getSize();
				contentType = file.getContentType();
				
				InputStream inputStream;
				inputStream = file.getInputStream();
				Tika tika = new Tika();
				mimeType = tika.detect(inputStream);
			}catch(Exception e) {
				//e.printStackTrace();
				errorCounter++;
			}
			
			File f1 = new File(uploadPath + "/" + newFileName);
			
			if (!contentType.equals(mimeType)) {
				errorCounter++;
			}
			
			if(!(mimeType.equals("image/jpeg") || mimeType.equals("image/jpg") || mimeType.equals("image/png") || mimeType.equals("image/gif"))){
				errorCounter++;
			} // mimeType 의 타입이 jpg, png 등등 이면 동작 아니면 안됨
				
			if(errorCounter == 0) {

    		    String msg = "";
            	msg += originalFileName + ",";
            	msg += newFileName + ",";
            	msg += fileSize + ",";
            	msg += contentType + ",";
            	msg += mimeType;
            	
            	
            	if(msg.trim().equals("-,-,0,-,-")) {
            		msg = "-";
            	}
            	
	            list.add(msg);
    		} else {
    			//파일지우는 기능
    			if(f1.exists()) {
    				f1.delete();
    			}
    			
    			String msg = "-";
    			list.add(msg);
    		}	
		}
		return list;
	}
}
