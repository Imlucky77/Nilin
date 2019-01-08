package com.nilin.web.profilecontroller;

import com.nilin.model.Profile;
import com.nilin.services.profileservice.ProfileService;
import com.nilin.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService service;

    /*
     * MultipartFile Upload
     */
    @PostMapping(value = "/profiles")
    public String saveProfile(@RequestParam("uploadfile") MultipartFile file, Profile profile) {
        try {
            // save file to H2
            //Profile filemode = new Profile(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            LocalDate birthday = profile.getBirthday();
            Profile profileMode = new Profile(profile.getFirstName(), profile.getLastName(),
                    birthday, profile.getType(), file.getBytes());
            service.save(profileMode);
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        } catch (Exception e) {
            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
        }
    }

    /*// -------------------Create a Profile-------------------------------------------
    @RequestMapping(value = "/profiles", method = RequestMethod.POST)
    public ResponseEntity<?> createProfile(@RequestBody Profile profile) {
        service.save(profile);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }*/

    // -------------------Retrieve All Profiles---------------------------------------------
    @RequestMapping(value = "/profiles", method = RequestMethod.GET)
    public ResponseEntity<List<Profile>> listAllProfiles() {
        List<Profile> profiles = service.findAll();
        if (profiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    // -------------------Retrieve Single Profile------------------------------------------

    @RequestMapping(value = "/profiles/{profileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfile(@PathVariable("profileId") long id) {
        Profile profile = service.findAllById(id);
        if (profile == null) {
            return new ResponseEntity<>(new CustomErrorType("Profile with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
/*
    // ------------------- Update a Profile ------------------------------------------------
    @RequestMapping(value = "/profiles/{profileId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfile(@PathVariable("profileId") long id, @RequestBody Profile profile) {

        Profile currentProfile = service.findByProfileId(id);

        if (currentProfile == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Profile with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentProfile.setFirstName(profile.getFirstName());
        currentProfile.setLastName(profile.getLastName());
        currentProfile.setBirthday(profile.getBirthday());
        currentProfile.setType(profile.getType());
        currentProfile.setPic(profile.getPic());

        service.updateProfile(currentProfile);
        return new ResponseEntity<>(currentProfile, HttpStatus.OK);
    }*/

}
