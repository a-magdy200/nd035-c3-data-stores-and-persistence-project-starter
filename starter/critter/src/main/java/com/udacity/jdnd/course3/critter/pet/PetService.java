package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public PetDTO save(PetDTO petDTO) {
        Pet pet = petRepository.save(convertDTOToEntity(petDTO));
        Customer customer = pet.getCustomer();
        if (customer != null) {
            List<Pet> newCustomerPets = customer.getPets();
            newCustomerPets.add(pet);
            customer.setPets(newCustomerPets);
            customerRepository.save(customer);
        }

        return convertEntityToDTO(pet);
    }
    public  PetDTO findPetById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            return convertEntityToDTO(pet.get());
        }
        throw new NullPointerException();
    }
    public List<PetDTO> findAllPets() {
        List<Pet> pets = petRepository.findAll();
        return convertEntityListToDTO(pets);
    }
    public List<PetDTO> findPetByOwnerId(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return convertEntityListToDTO(petRepository.findAllByCustomerId(id));
        }
        throw new NullPointerException();

    }

    private static PetDTO convertEntityToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }
    private static List<PetDTO> convertEntityListToDTO(List<Pet> pets) {
        List<PetDTO> petDTOS = new ArrayList<>();
        for (Pet pet : pets) {
            petDTOS.add(convertEntityToDTO(pet));
        }
        return petDTOS;
    }
    private static Pet convertDTOToEntity(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }
}
