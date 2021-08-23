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
public class EmployeeService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = convertDTOToEntity(employeeDTO);
        return convertEntityToDTO(employeeRepository.save(employee));
    }

    public EmployeeDTO findEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return convertEntityToDTO(employee.get());
        }
        throw new NullPointerException();
    }


    private static EmployeeDTO convertEntityToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }
    private static List<EmployeeDTO> convertEntityListToDTO(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDTOS.add(convertEntityToDTO(employee));
        }
        return employeeDTOS;
    }
    private static Employee convertDTOToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }
}
