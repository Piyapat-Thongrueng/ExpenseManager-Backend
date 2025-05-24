package com.piyapatdev.expenseapi.service;

import com.piyapatdev.expenseapi.dto.ProfileDTO;

public interface ProfileService {

    /**
     * It will save the user details to the database
     * @param profileDTO
     * @return profileDTO
     * */
    ProfileDTO createProfile(ProfileDTO profileDTO);
}
