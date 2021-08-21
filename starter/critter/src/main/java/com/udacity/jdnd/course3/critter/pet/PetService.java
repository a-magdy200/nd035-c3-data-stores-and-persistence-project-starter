package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public PetDTO save(PetDTO petDTO) {

        return petDTO;
    }
}
