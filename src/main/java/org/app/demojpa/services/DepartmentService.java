package org.app.demojpa.services;

import org.app.demojpa.entities.Department;
import org.app.demojpa.entities.User;
import org.app.demojpa.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public void createDepartment(Department department) {
        departmentRepository.save(department);
    }

}
