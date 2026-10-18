package com.staywise.service;

import com.staywise.dto.ProfileUpdateRequestDto;
import com.staywise.dto.UserDto;
import com.staywise.entity.User;

public interface UserService {

    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
