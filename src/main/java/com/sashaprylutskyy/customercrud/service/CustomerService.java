package com.sashaprylutskyy.customercrud.service;

import com.sashaprylutskyy.customercrud.model.dto.CustomerRequestDTO;
import com.sashaprylutskyy.customercrud.model.dto.CustomerResponseDTO;
import com.sashaprylutskyy.customercrud.model.entity.Customer;
import com.sashaprylutskyy.customercrud.model.mapper.CustomerMapper;
import com.sashaprylutskyy.customercrud.repository.CustomerRepository;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public CustomerResponseDTO create(@Valid CustomerRequestDTO dto) {
        Long currentTime = System.currentTimeMillis();
        Customer customer = CustomerMapper.toEntity(dto);

        customer.setCreated(currentTime);
        customer.setUpdated(currentTime);
        customer = repo.save(customer);
        return CustomerMapper.toResponse(customer);
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        return repo.findAll().stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }

    public List<CustomerResponseDTO> getAllActiveCustomers() {
        return repo.findAllActiveCustomers().stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }

    private Customer getCustomerById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("Customer N.%d is not found.", id)));
    }

    public CustomerResponseDTO getActiveCustomerById(Long id) {
        Customer customer = getCustomerById(id);
        if (!customer.isActive()) {
            throw new RuntimeException(String.format("Customer N.%d is deleted.", id));
        }
        return CustomerMapper.toResponse(customer);
    }

    public CustomerResponseDTO updateCustomer(@Valid CustomerRequestDTO dto, Long urlId) throws AccessDeniedException {
        if (!Objects.equals(dto.getId(), urlId)) {
            throw new AccessDeniedException("Access denied: path id and user id don't match.");
        }
        Customer customer = getCustomerById(dto.getId());

        customer.setFullName(dto.getFullName());
        customer.setPhone(dto.getPhone());
        customer.setUpdated(System.currentTimeMillis());

        customer = repo.save(customer);
        return CustomerMapper.toResponse(customer);
    }

    public void deleteCustomerById(Long id) {
        Customer customer = getCustomerById(id);
        customer.setActive(false);
        repo.save(customer);
    }
}
