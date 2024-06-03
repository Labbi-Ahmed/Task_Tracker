package com.labbi.taskTracker.model;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserCridential {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
