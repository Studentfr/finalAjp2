package com.example.finalproj.util;

import com.example.finalproj.repository.dto.Account;
import com.example.finalproj.repository.dto.Question;
import com.example.finalproj.rest.dto.QuestionDto;
import com.example.finalproj.rest.dto.QuestionWebDto;
import com.example.finalproj.rest.dto.UserDto;


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
