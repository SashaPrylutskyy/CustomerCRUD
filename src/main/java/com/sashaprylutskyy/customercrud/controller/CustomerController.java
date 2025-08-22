package com.sashaprylutskyy.customercrud.controller;

import com.sashaprylutskyy.customercrud.model.dto.CustomerRequestDTO;
import com.sashaprylutskyy.customercrud.model.dto.CustomerResponseDTO;
import com.sashaprylutskyy.customercrud.model.entity.Customer;
import com.sashaprylutskyy.customercrud.model.mapper.CustomerMapper;
import com.sashaprylutskyy.customercrud.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO dto) {
        CustomerResponseDTO customer = service.create(dto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> customers = service.getAllActiveCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getActiveCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@Valid @RequestBody CustomerRequestDTO dto,
                                                              @PathVariable Long id) throws AccessDeniedException {
        CustomerResponseDTO customer = service.updateCustomer(dto, id);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateCustomerById(@PathVariable Long id) {
        service.deactivateCustomerById(id);
        return ResponseEntity.ok(String.format("Customer N.%d is successfully deactivated", id));
    }
}
