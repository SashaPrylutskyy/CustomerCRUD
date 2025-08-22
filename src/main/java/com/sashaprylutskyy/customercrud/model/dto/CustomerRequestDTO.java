package com.sashaprylutskyy.customercrud.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerRequestDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String fullName;

    @NotBlank
    @Size(min = 2, max = 100)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", message = "Invalid email")
    private String email;

    @Pattern(regexp = "^\\+\\d{6,14}$", message = "Invalid phone format")
    private String phone;

    public CustomerRequestDTO() {

    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Long getId() {
        return id;
    }
}
