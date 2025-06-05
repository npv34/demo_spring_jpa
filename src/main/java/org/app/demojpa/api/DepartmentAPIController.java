package org.app.demojpa.api;

import jakarta.validation.Valid;
import org.app.demojpa.dto.DepartmentDetailDto;
import org.app.demojpa.dto.DepartmentDto;
import org.app.demojpa.dto.UserDto;
import org.app.demojpa.entities.Department;
import org.app.demojpa.entities.User;
import org.app.demojpa.response.APIResponse;
import org.app.demojpa.services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentAPIController {
    private final DepartmentService departmentService;

    public DepartmentAPIController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("")
    public ResponseEntity<APIResponse> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
//        List<DepartmentDto> departmentDtos = departments.stream()
//                .map(department -> new DepartmentDto(department.getId(), department.getName()))
//                .toList();

        List<DepartmentDto> departmentDtos = new ArrayList<>();
        for (Department department : departments) {
            DepartmentDto dto = new DepartmentDto(department.getId(), department.getName());
            departmentDtos.add(dto);
        }

        APIResponse response = new APIResponse();
        response.setMessage("success");
        response.setData(departmentDtos);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<APIResponse> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        APIResponse response = new APIResponse();
        try {
            Department department = new Department();
            department.setName(departmentDto.getName());
            departmentService.createDepartment(department);

            response.setMessage("Department created successfully");
            response.setSuccess(true);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            response.setMessage("Error creating department: " + e.getMessage());
            response.setSuccess(false);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<APIResponse> getUserByDepartmentId(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        APIResponse response = new APIResponse();
        if (department == null) {
            response.setMessage("Department not found");
            response.setSuccess(false);
            return ResponseEntity.status(404).body(response);
        }

        DepartmentDetailDto departmentDetail = getDepartmentDetailDto(department);

        response.setMessage("success");
        response.setData(departmentDetail);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    private static DepartmentDetailDto getDepartmentDetailDto(Department department) {
        List<User> users = department.getUsers();

        // map users to DTOs
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
            userDtos.add(userDto);
        }

        // map departmentDetail to DTO
        DepartmentDetailDto departmentDetail = new DepartmentDetailDto();
        departmentDetail.setId(department.getId());
        departmentDetail.setName(department.getName());
        departmentDetail.setUsers(userDtos);
        return departmentDetail;
    }
}
