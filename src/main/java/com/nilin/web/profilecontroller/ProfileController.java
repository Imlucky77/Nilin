package com.nilin.web.profilecontroller;

import com.nilin.exception.BusinessException;
import com.nilin.model.Profile;
import com.nilin.repositories.profilerepository.ProfileRepository;
import com.nilin.services.profileservice.ProfileService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "Profile Management System", description = "Operations pertaining to profile in Profile Management System")
public class ProfileController {

    @Autowired
    private ProfileService service;
    @Autowired
    ProfileRepository profileRepository;

    @ApiOperation(value = "Add a profile")
    @PostMapping(path = "/profiles")
    public ResponseEntity<?> createProfile(
            @ApiParam(value = "Profile object store in database table", required = true) @Valid
            @RequestBody Profile profile) {
        try {
            service.save(profile);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getStatus()));
        }
    }

    /*@ApiOperation(value = "Add an profile")
    @PostMapping(value = "/profiles")
    public ResponseEntity<?> save(
            @ApiParam(value = "Profile object store in database table", required = true)
            @Valid @RequestBody Profile profile,
            HttpServletRequest request, @RequestParam("profile") MultipartFile[] fileUpload) {

        profile.setFirstName(profile.getFirstName());
        profile.setLastName(profile.getLastName());
        profile.setBirthday(profile.getBirthday());
        profile.setMobile(profile.getMobile());
        profile.setEmail(profile.getEmail());
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        File uploadRootDir = new File(uploadRootPath);

        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        List<File> uploadedFiles = new ArrayList<>();
        for (MultipartFile fileData : fileUpload) {

            // Profile File Name
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
                    profile.setPic(serverFile.getAbsolutePath());
                } catch (Exception e) {
                    System.out.println("Error Write file: " + username);
                }
            }

        }
        service.save(profile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/

    // -------------------Retrieve All profiles---------------------------------------------
    @ApiOperation(value = "View a list of available profiles", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/profiles")
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = service.findAll();
        if (profiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    // -------------------Retrieve Single Profile------------------------------------------
    @ApiOperation(value = "Get an profile by Id")
    @GetMapping(value = "/profiles/{profileId}")
    public ResponseEntity<?> getProfileById(@ApiParam(value = "Profile id from which profile object will retrieve", required = true)
                                            @PathVariable("profileId") long id) {
        Profile profile = service.findAllById(id);
        if (profile == null) {
            return new ResponseEntity<>(new BusinessException("Profile with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    // ------------------- Update a Profile ------------------------------------------------
    @RequestMapping(value = "/profiles/{profileId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfileById(@PathVariable("profileId") long id,
                                               @RequestBody Profile profile) {

        Profile currentProfile = service.findAllById(id);

        if (currentProfile == null) {
            return new ResponseEntity<>(new BusinessException("Unable to update. Profile with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentProfile.setFirstName(profile.getFirstName());
        currentProfile.setLastName(profile.getLastName());
        currentProfile.setBirthday(profile.getBirthday());
        currentProfile.setMobile(profile.getMobile());
        currentProfile.setEmail(profile.getEmail());
        currentProfile.setPic(profile.getPic());

        service.updateProfile(currentProfile);
        return new ResponseEntity<>(currentProfile, HttpStatus.OK);
    }


    /*@PostMapping("/profiles")
    public Profile uploadImage(@RequestParam("file") MultipartFile file, Profile profile) throws IOException {
        Profile img = new Profile(profile.getFirstName(), profile.getLastName(), profile.getBirthday(),
                profile.getMobile(), profile.getEmail());
        final Profile savedImage = profileRepository.save(img);
        return savedImage;
    }*/

    // ------------------- Delete a Profile -----------------------------------------
    @ApiOperation(value = "Delete a profile by Id")
    @DeleteMapping(value = "/profiles/{profileId}")
    public ResponseEntity<?> deleteProfile(
            @ApiParam(value = "Profile Id from which profile object will delete from database table", required = true)
            @PathVariable("profileId") long id
    ) {

        Profile profile = service.findAllById(id);
        if (profile == null) {
            return new ResponseEntity<>("Unable to delete. Profile with id " + id + " not found.",
                    HttpStatus.NOT_FOUND);
        }
        service.deleteProfileById(id);
        return new ResponseEntity<Profile>(HttpStatus.NO_CONTENT);
    }

}
