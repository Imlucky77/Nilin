package com.nilin.web.clientcontroller;

import com.nilin.exception.BusinessException;
import com.nilin.model.Client;
import com.nilin.repositories.clientrepository.ClientRepository;
import com.nilin.services.clientservice.ClientService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Client Management System", description = "Operations pertaining to client in Client Management System")
public class ClientController {

    @Autowired
    private ClientService service;
    @Autowired
    ClientRepository clientRepository;

    /*@ApiOperation(value = "Add an client")
    @PostMapping(value = "/clients")
    public ResponseEntity<?> saveClient(
            @ApiParam(value = "Client object store in database table", required = true)
            @Valid @RequestBody Client client,
            HttpServletRequest request, @RequestParam("client") MultipartFile[] fileUpload) {

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
    }*/

    // -------------------Retrieve All Clients---------------------------------------------
    @ApiOperation(value = "View a list of available clients", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> listAllClients() {
        List<Client> clients = service.findAll();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // -------------------Retrieve Single Client------------------------------------------
    @ApiOperation(value = "Get an client by Id")
    @GetMapping(value = "/clients/{clientId}")
    public ResponseEntity<?> getClient(@ApiParam(value = "Client id from which client object will retrieve", required = true)
                                       @PathVariable("clientId") long id) {
        Client client = service.findAllById(id);
        if (client == null) {
            return new ResponseEntity<>(new BusinessException("Client with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    // ------------------- Update a Client ------------------------------------------------
    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateClient(@PathVariable("clientId") long id,
                                          @RequestBody Client client) {

        Client currentClient = service.findAllById(id);

        if (currentClient == null) {
            return new ResponseEntity<>(new BusinessException("Unable to update. Client with id " + id + " not found."),
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
    }


    @PostMapping("/clients")
    public Client uploadImage(@RequestParam("file") MultipartFile file, Client client) throws IOException {
        Client img = new Client(client.getFirstName(), client.getLastName(), client.getBirthday(),
                client.getMobile(), client.getEmail());
        final Client savedImage = clientRepository.save(img);
        return savedImage;
    }

}
