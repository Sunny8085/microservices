package com.bank.userReg.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bank.userReg.dto.AccountDto;
import com.bank.userReg.exceptionhandler.MissingFileException;
import com.bank.userReg.exceptionhandler.PathNotFoundException;

@Component
public class FileUploadHelper {
	public final String UPLOAD_DIR="C:\\code\\spring_bank\\image\\profile\\";
	public boolean uploadFile(MultipartFile file) {
		boolean f=false;
		try {
//			 InputStream is= file.getInputStream();
//			 byte data[] = new byte[is.available()];
//			 is.read();
//			 FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+"\\"+file.getOriginalFilename());
//			 fos.write(data);
//			 fos.flush();
//			 fos.close();
//			 f=true;
			 Files.copy(file.getInputStream(),Paths.get(UPLOAD_DIR+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			 
			 f=true;
			 
			 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	        try {
	            file.getInputStream().close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}    
		return f;
	}
	public final String FILE_UPLOAD="C:\\code\\spring_bank\\image\\";
	public boolean uploadMultFile(AccountDto accountDto) {
		boolean f=false;
		try {
			MultipartFile files[] = {
			accountDto.getProfile(),
			accountDto.getSignature(),
			accountDto.getAadhaarImage(),
			accountDto.getPanImage(),
			accountDto.getNomineeProof()};
			for (MultipartFile file : files) {
			    if (file == null || file.isEmpty()) {
			        throw new MissingFileException("One or more files are missing.");
			    }
			}
			String[] filenames = {"profile.jpg","signature.jpg","aadhaarcard.jpg",
				    "pancard.jpg","nomineeproof.jpg"};
			//creating folder at run time
			Path folder = Paths.get(FILE_UPLOAD+accountDto.getUserId());
			Files.createDirectories(folder);
			for(int i=0;i < files.length;i++) {
				Path destination = folder.resolve(accountDto.getUserId()+filenames[i]);
				Files.copy(files[i].getInputStream(),destination,StandardCopyOption.REPLACE_EXISTING);
				f=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new PathNotFoundException("Path not found.");
		}  
		return f;
	}
}










