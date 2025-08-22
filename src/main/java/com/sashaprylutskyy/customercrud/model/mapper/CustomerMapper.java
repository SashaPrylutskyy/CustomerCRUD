package com.sashaprylutskyy.customercrud.model.mapper;

import com.sashaprylutskyy.customercrud.model.dto.CustomerRequestDTO;
import com.sashaprylutskyy.customercrud.model.dto.CustomerResponseDTO;
import com.sashaprylutskyy.customercrud.model.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDTO dto) {
        /* customer.setId(dto.getId());
        customer.setFullName(dto.getFullName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());*/

        return new Customer(
                dto.getId(),
                dto.getFullName(),
                dto.getEmail(),
                dto.getPhone()
        );
    }

    public static CustomerResponseDTO toResponse(Customer entity) {
        return new CustomerResponseDTO(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getPhone()
        );
    }

    //todo should I add this method???
    /*public static void updateEntity(Customer entity, CustomerRequestDTO dto) {
        entity.setUpdated(System.currentTimeMillis());
        entity.setFullName(dto.getFullName());
        entity.setPhone(dto.getPhone());
//        entity.setActive();
    }*/

}
