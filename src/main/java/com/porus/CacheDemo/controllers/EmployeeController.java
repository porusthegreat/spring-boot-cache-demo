package com.porus.CacheDemo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.porus.CacheDemo.services.EmployeeService;
import com.porus.CacheDemo.models.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emp")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;


    @PostMapping()
    public ResponseEntity<Employee> add(@RequestBody Employee emp) {
        return ResponseEntity.ok(service.addEmployee(emp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.fetchEmployeeById(id));
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getEmployeeBy() {
        return ResponseEntity.ok(service.fetchAll());
    }
}
