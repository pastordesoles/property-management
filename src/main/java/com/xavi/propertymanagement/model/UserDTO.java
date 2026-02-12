package com.xavi.propertymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long userId;
    private String ownerName;
    @NotNull(message = "Owner email is mandatory")
    @Size(min = 1, max = 50, message = "Owner email should be between 1 to 50 chars")
    private String ownerEmail;
    private String phone;
    @NotNull(message = "Owner email is mandatory")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
