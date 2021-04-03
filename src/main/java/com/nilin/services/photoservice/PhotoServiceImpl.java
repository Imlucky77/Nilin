package com.nilin.services.photoservice;

import com.nilin.exception.BusinessException;
import com.nilin.model.Photo;
import com.nilin.repositories.photorepository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new BusinessException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Photo dbFile = new Photo(fileName, file.getContentType(), file.getBytes());

            return photoRepository.save(dbFile);
        } catch (IOException ex) {
            throw new BusinessException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Photo findById(String fileId) {
        return null;
    }

    @Override
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public Photo findAllById(Long id) {
        return photoRepository.findAllById(id);
    }

    @Override
    public void deletePhotoById(Long id) {
        photoRepository.deleteById(id);
    }
}
