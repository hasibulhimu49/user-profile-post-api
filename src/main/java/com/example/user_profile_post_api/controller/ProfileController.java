package com.example.user_profile_post_api.controller;

import com.example.user_profile_post_api.dto.request.ProfileCreateRequestDto;
import com.example.user_profile_post_api.dto.response.ProfileResponseDto;
import com.example.user_profile_post_api.dto.update.ProfileUpdateRequestDto;
import com.example.user_profile_post_api.model.enums.Gender;
import com.example.user_profile_post_api.repository.ProfileRepository;
import com.example.user_profile_post_api.repository.projection.ProfileSummary;
import com.example.user_profile_post_api.service.ProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<ProfileResponseDto> profileCreate(@Valid @RequestBody ProfileCreateRequestDto createRequestDto, @PathVariable Long userId) {
        ProfileResponseDto response = profileService.createProfile(userId, createRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<ProfileResponseDto>> getAllProfile() {
        return ResponseEntity.ok(profileService.getAllProfile());

    }


    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDto> getProfileById(@PathVariable Long profileId) {
        return ResponseEntity.ok(profileService.getProfileById(profileId));
    }


    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDto> updateProfile(@PathVariable Long profileId, @RequestBody ProfileUpdateRequestDto updateRequestDto) {
        return ResponseEntity.ok(profileService.updateProfile(profileId, updateRequestDto));
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long profileId) {
        profileService.deleteProfile(profileId);
        return ResponseEntity.noContent().build();
    }





    /*

    @GetMapping("/by-firstnameandgender")
    public ResponseEntity<List<ProfileResponseDto>>
    getProfileByFirstnameAndGender(@RequestParam String firstname,
                                   @RequestParam Gender gender)
    {
        return ResponseEntity.ok(profileService.getProfileByFirstnameAndGender(firstname,gender));
    }

    @GetMapping("by-username")
    public ResponseEntity<Optional<ProfileResponseDto>> findByUsername(@RequestParam String username)
    {
        return ResponseEntity.ok(profileService.findByUsername(username));
    }


    @GetMapping("/paging")
    public Page<ProfileResponseDto> searchByFirstName(@RequestParam String keyword, Pageable pageable) {
        return profileService.searchByFirstName(keyword, pageable);
    }
*/


    //This is not good practice because controller should be clean
    @GetMapping("/search")
    public ResponseEntity<?> searchProfiles(
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String bio,
            @RequestParam(required = false) String keyword, Pageable pageable
    ) {

        // Case 1: username search (unique → highest priority)    for understanding-/api/profiles/search?firstname=Hasib&gender=MALE&username=hasib(firstname + gender wins, username ignored)
        if (username != null && (firstname == null && gender == null)) {
            return profileService.findByUsername(username)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        // Case 2: firstname + gender search
        if (firstname != null && gender != null && username == null) {
            return ResponseEntity.ok(
                    profileService.getProfileByFirstnameAndGender(firstname, gender));
        }
        // Case 3: bio only
        if (bio != null && (firstname == null && gender == null && username == null)) {
            return ResponseEntity.ok(profileService.searchProfileByBio(bio));
        }
        // Case 4: paging keyword search
        if (keyword != null && (bio == null && firstname == null && gender == null && username == null)) {
            return ResponseEntity.ok(profileService.searchByFirstName(keyword, pageable));
        }

        return ResponseEntity.badRequest()
                .body("Provide either username OR firstname + gender");
    }




    //Projection
    @GetMapping("/projection")
    public ResponseEntity<List<ProfileSummary>> searchByLastname(@RequestParam String lastname) {
        return ResponseEntity.ok(profileService.searchByLastname(lastname));
    }





    //Specifications (Dynamic Search)
    @GetMapping("/specification")
    public ResponseEntity<Page<ProfileResponseDto>> search(
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String bio,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) String username,
            Pageable pageable) {
        return ResponseEntity.ok(profileService.search(firstname, lastname, bio, gender, username, pageable));
    }

}
