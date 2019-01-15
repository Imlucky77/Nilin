package com.nilin.web.photoController;

import com.nilin.model.Photo;
import com.nilin.services.photoservice.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class PhotoCotroller {

    @Autowired
    private PhotoService photoService;

    /*@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public ResponseEntity<?> handleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile[] fileUpload, Photo photo) throws Exception {

        photo.setDescription(photo.getDescription());
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        File uploadRootDir = new File(uploadRootPath);

        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        //MultipartFile[] fileDatas = photo.getData();

        List<File> uploadedFiles = new ArrayList<>();
        for (MultipartFile fileData : fileUpload) {

            // Client File Name
            String name = fileData.getOriginalFilename();
            photo.setName(name);

            if (name != null && name.length() > 0) {
                try {
                    // Create the file on server
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);


                    // Stream to write data to file in server.
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    //
                    uploadedFiles.add(serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                }
            }
        }
        photoService.save(photo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile[] fileUpload, Photo photo) throws Exception {

        if (fileUpload != null && fileUpload.length > 0) {
            for (MultipartFile aFile : fileUpload) {

                System.out.println("Saving file: " + aFile.getOriginalFilename());

                photo.setName(aFile.getOriginalFilename());
                photo.setData(aFile.getBytes());
                photo.setDescription(photo.getDescription());
                photoService.save(photo);
            }
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
