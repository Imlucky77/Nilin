package com.nilin.web.profilecontroller;

import com.nilin.model.Profile;
import com.nilin.services.profileservice.ProfileService;
import com.nilin.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // -------------------Create a Profile-------------------------------------------
    @RequestMapping(value = "/profile/save", method = RequestMethod.POST)
    public ResponseEntity<?> createProfile(@RequestBody Profile profile, UriComponentsBuilder ucBuilder) {
        profileService.save(profile);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/profile/{id}").buildAndExpand(profile.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // -------------------Retrieve All Profiles---------------------------------------------
    @RequestMapping(value = "/profile/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Profile>> listAllUsers() {
        List<Profile> profiles = profileService.findAllProfiles();
        if (profiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    // -------------------Retrieve Single Profile------------------------------------------

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfile(@PathVariable("id") long id) {
        Profile profile = profileService.findAllById(id);
        if (profile == null) {
            return new ResponseEntity<>(new CustomErrorType("Profile with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    // ------------------- Update a Profile ------------------------------------------------
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfile(@PathVariable("id") long id, @RequestBody Profile profile) {

        Profile currentProfile = profileService.findAllById(id);

        if (currentProfile == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Profile with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentProfile.setFirstName(profile.getFirstName());
        currentProfile.setLastName(profile.getLastName());
        currentProfile.setBirthday(profile.getBirthday());
        currentProfile.setPic(profile.getPic());

        profileService.updateProfile(currentProfile);
        return new ResponseEntity<>(currentProfile, HttpStatus.OK);
    }

}
