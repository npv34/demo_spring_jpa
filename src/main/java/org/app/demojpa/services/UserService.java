package org.app.demojpa.services;

import org.app.demojpa.entities.Department;
import org.app.demojpa.entities.User;
import org.app.demojpa.repositories.DepartmentRepository;
import org.app.demojpa.repositories.UserRepository;
import org.app.demojpa.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public UserService(UserRepository userRepository,
                       DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void createUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        // get Department by ID and set it to user
        Long departmentId = userRequest.getDepartmentId();
        if (departmentId != null) {
            Department department = departmentRepository
                    .findById(departmentId).orElse(null);
            if (department != null) {
                user.setDepartment(department);
            }
        }

        userRepository.save(user);
    }
}
