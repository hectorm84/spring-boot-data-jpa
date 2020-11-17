package net.hectorm.springbootdatajpa.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IFileUploadService{

    private final static String UPLOAD_FOLDER = "uploads";


    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFileName = UUID.randomUUID() + file.getOriginalFilename();

        Path rootPath = getPath(uniqueFileName);

        Files.copy(file.getInputStream(), rootPath);


        return uniqueFileName;
    }

    @Override
    public boolean delete(String fileName) {

        Path rootPath = getPath(fileName);
        Path absolutePath = rootPath.toAbsolutePath();
        File f = absolutePath.toFile();

        if(f.exists() && f.canRead()){
            if(f.delete()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOAD_FOLDER).toFile());
    }

    @Override
    public void init() throws IOException {
        Files.createDirectory(Paths.get(UPLOAD_FOLDER));
    }

    public Path getPath(String fileName){
        return Paths.get(UPLOAD_FOLDER).resolve(fileName);
    }
}
