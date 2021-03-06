package com.example.finalproj.rest.dto;

import com.example.finalproj.repository.dto.Account;
import org.springframework.context.annotation.Bean;


public class Converter {
    public UserDto accountToUserDto(Account account) {
        UserDto dto = new UserDto();
        dto.setUserId(account.getUserId());
        dto.setAge(account.getAge());
        dto.setFirstName(account.getFirstName());
        dto.setLastName(account.getLastName());
        dto.setGroupName(account.getGroupName());
        dto.setInterest(account.getInterest());
        dto.setUsername(account.getUsername());
        dto.setRoleId(account.getRole().getRoleId());
        return dto;
    }
}
