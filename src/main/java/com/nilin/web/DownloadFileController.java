package com.nilin.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.nilin.model.FileModel;
import com.nilin.model.View;
import com.nilin.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DownloadFileController {

    @Autowired
    FileService service;

    /*
     * List All Files
     */
    @JsonView(View.FileInfo.class)
    @GetMapping("/api/file/all")
    public List<FileModel> getListFiles() {
        return service.findAll();
    }

    /*
     * Download Files
     */
    @GetMapping("/api/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<FileModel> fileOptional = service.findById(id);

        if (fileOptional.isPresent()) {
            FileModel file = fileOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(file.getPic());
        }

        return ResponseEntity.status(404).body(null);
    }
}
