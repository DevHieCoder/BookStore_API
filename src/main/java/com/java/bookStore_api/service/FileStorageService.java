package com.java.bookStore_api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.bookStore_api.serviceImp.FileStorageServiceImp;
@Service
public class FileStorageService implements FileStorageServiceImp {
	
	private final Path root = Paths.get("..", "bookStore", "src", "main", "resources", "static", "image");	
	
	
	@Override
	public void init() {
		if (!Files.exists(root)) {
			try {
				Files.createDirectory(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean saveFile(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), 
					StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
