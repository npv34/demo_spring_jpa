package org.app.demojpa.dto;

import java.util.List;

public class DepartmentDetailDto {
    private Long id;
    private String name;
    private List<UserDto> users;

    public DepartmentDetailDto() {
    }

    public DepartmentDetailDto(Long id, String name, List<UserDto> users) {
        this.id = id;
        this.name = name;
        this.users = users;
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

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
