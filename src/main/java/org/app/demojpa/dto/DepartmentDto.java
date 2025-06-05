package org.app.demojpa.dto;

import jakarta.validation.constraints.NotBlank;

public class DepartmentDto {
    private Long id;

    @NotBlank(message = "Department name cannot be blank")
    private String name;

    public DepartmentDto() {
    }

    public DepartmentDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
