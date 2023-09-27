package com.porus.CacheDemo.services;

import com.porus.CacheDemo.models.Employee;
import com.porus.CacheDemo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;



    public Employee addEmployee(Employee employee) {
        System.out.println("========Adding Employee============");
        return employeeRepository.save(employee);
    }

    @Cacheable(
            value = "emp", key = "#id", condition = "#id > 3" // Only Caching if id > 3 and others will always be fetched from DB.
    )
    public Employee fetchEmployeeById(Long id) {
        System.out.printf(String.format("======== Searching Employee in DB by %d ============", id));
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Employee> fetchAll(){
        System.out.println("======== Searching Employees in DB ============");
        return (List<Employee>) employeeRepository.findAll();
    }

}
