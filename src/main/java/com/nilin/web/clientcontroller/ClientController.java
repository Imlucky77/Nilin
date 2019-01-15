package com.nilin.web.clientcontroller;

import com.nilin.model.Client;
import com.nilin.services.clientservice.ClientService;
import com.nilin.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService service;

    /*
     * MultipartFile Upload
     */
    @PostMapping(value = "/clients")
    public String saveClient(@RequestParam("uploadfile") MultipartFile file, Client client) {
        try {
            // save file to H2
            //Client filemode = new Client(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            LocalDate birthday = client.getBirthday();
            Client profileMode = new Client(client.getFirstName(), client.getLastName(),
                    birthday, file.getBytes());
            service.save(profileMode);
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        } catch (Exception e) {
            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
        }
    }

    /*// -------------------Create a Client-------------------------------------------
    @RequestMapping(value = "/profiles", method = RequestMethod.POST)
    public ResponseEntity<?> createProfile(@RequestBody Client profile) {
        service.save(profile);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }*/

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
/*
    // ------------------- Update a Client ------------------------------------------------
    @RequestMapping(value = "/profiles/{profileId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateClient(@PathVariable("profileId") long id, @RequestBody Client profile) {

        Client currentProfile = service.findByProfileId(id);

        if (currentProfile == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Client with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentProfile.setFirstName(profile.getFirstName());
        currentProfile.setLastName(profile.getLastName());
        currentProfile.setBirthday(profile.getBirthday());
        currentProfile.setType(profile.getType());
        currentProfile.setPic(profile.getPic());

        service.updateClient(currentProfile);
        return new ResponseEntity<>(currentProfile, HttpStatus.OK);
    }*/

}
