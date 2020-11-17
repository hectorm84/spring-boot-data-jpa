package net.hectorm.springbootdatajpa.service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileUploadService {

    public String copy(MultipartFile file) throws IOException;

    public boolean delete(String fileName);

    public void deleteAll();

    public void init() throws IOException;
}
