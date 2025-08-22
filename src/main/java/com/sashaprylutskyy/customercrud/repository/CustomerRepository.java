package com.sashaprylutskyy.customercrud.repository;

import com.sashaprylutskyy.customercrud.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //якщо потрібно отримати всіх активних користувачів при GET:/api/customers
    @Query("SELECT c from Customer c where c.isActive = true")
    List<Customer> findAllActiveCustomers();
}
