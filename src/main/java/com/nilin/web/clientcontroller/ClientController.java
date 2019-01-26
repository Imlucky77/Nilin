package com.nilin.web.clientcontroller;

import com.nilin.model.Client;
import com.nilin.services.clientservice.ClientService;
import com.nilin.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping(value = "/clients")
    public ResponseEntity<?> saveClient(HttpServletRequest request, @RequestParam("client") MultipartFile[] fileUpload, Client client) {

        client.setFirstName(client.getFirstName());
        client.setLastName(client.getLastName());
        client.setBirthday(client.getBirthday());
        client.setMobile(client.getMobile());
        client.setEmail(client.getEmail());
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        File uploadRootDir = new File(uploadRootPath);

        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        List<File> uploadedFiles = new ArrayList<>();
        for (MultipartFile fileData : fileUpload) {

            // Client File Name
            String username = fileData.getOriginalFilename();

            if (username != null && username.length() > 0) {
                try {
                    // Create the file on server
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + username);


                    // Stream to write data to file in server.
                    try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                        stream.write(fileData.getBytes());
                    }
                    //
                    uploadedFiles.add(serverFile);
                    client.setPic(serverFile.getAbsolutePath());
                } catch (Exception e) {
                    System.out.println("Error Write file: " + username);
                }
            }

        }
        service.save(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // -------------------Retrieve All Clients---------------------------------------------
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> listAllClients() {
        List<Client> clients = service.findAll();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // -------------------Retrieve Single Client------------------------------------------

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<?> getClient(@PathVariable("clientId") long id) {
        Client client = service.findAllById(id);
        if (client == null) {
            return new ResponseEntity<>(new CustomErrorType("Client with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

  /*  // ------------------- Update a Client ------------------------------------------------
    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateClient(@PathVariable("clientId") long id,
                                          @RequestBody Client client) {

        Client currentClient = service.findAllById(id);

        if (currentClient == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Client with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentClient.setFirstName(client.getFirstName());
        currentClient.setLastName(client.getLastName());
        currentClient.setBirthday(client.getBirthday());
        currentClient.setMobile(client.getMobile());
        currentClient.setEmail(client.getEmail());
        currentClient.setPic(client.getPic());

        service.updateClient(currentClient);
        return new ResponseEntity<>(currentClient, HttpStatus.OK);
    }*/

}
