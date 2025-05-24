package com.piyapatdev.expenseapi.controller;

import com.piyapatdev.expenseapi.dto.ProfileDTO;
import com.piyapatdev.expenseapi.io.ProfileRequest;
import com.piyapatdev.expenseapi.io.ProfileRespond;
import com.piyapatdev.expenseapi.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final ModelMapper modelMapper;
    private final ProfileService profileService;

    /**
     * API endpoint to register a new user
     * @param profileRequest
     * @return profileRespond
     * */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ProfileRespond createProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        log.info("API /register is called {}", profileRequest);
        ProfileDTO profileDTO = maptoProfileDTO(profileRequest);
        profileDTO = profileService.createProfile(profileDTO);
        log.info("Printing the profile dto details {}", profileDTO);
        return mapToProfileRespond(profileDTO);
    }



















    /**
     * Mapper method to map values from profileRequest to profileDTO
     * @param profileRequest
     * @return profileDTO
     * */
    private ProfileDTO maptoProfileDTO(@Valid ProfileRequest profileRequest) {
        return modelMapper.map(profileRequest, ProfileDTO.class);
    }

    /**
     * Mapper method to map values from profileDTO to profileRespond
     * @param profileDTO
     * @return profileRespond
     * */
    private ProfileRespond mapToProfileRespond(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileRespond.class);
    }
}
