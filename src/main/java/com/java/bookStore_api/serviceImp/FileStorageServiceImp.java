package com.java.bookStore_api.serviceImp;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageServiceImp {
	public void init();
	public boolean saveFile(MultipartFile file);
}
