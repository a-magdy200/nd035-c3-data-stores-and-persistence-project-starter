package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;

    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = convertDTOToEntity(customerDTO);
        return convertEntityToDTO(customerRepository.save(customer));
    }

    public CustomerDTO findCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return convertEntityToDTO(customer.get());
        }
        throw new NullPointerException();
    }
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return convertEntityListToDTO(customers);
    }
    public CustomerDTO findCustomerByPetId(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            return convertEntityToDTO(pet.get().getCustomer());
        }
        throw new NullPointerException();

    }


    private static CustomerDTO convertEntityToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }
    private static List<CustomerDTO> convertEntityListToDTO(List<Customer> customers) {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOS.add(convertEntityToDTO(customer));
        }
        return customerDTOS;
    }
    private static Customer convertDTOToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }
}
