package com.eray.authservice.dto.user;

import com.eray.authservice.dto.role.RoleDTO;
import com.eray.commonlib.dto.Dto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CreateUserDTO extends Dto implements Serializable {

    private static final long serialVersionUID = 8118267902563992430L;

    private Long id;

    private String userName;

    @JsonIgnore
    private String password;

    private List<RoleDTO> roleList;

    public CreateUserDTO(){
        //no-args constructor for Jackson
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleDTO> roleList) {
        this.roleList = roleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserDTO that = (CreateUserDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(roleList, that.roleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, roleList);
    }

    @Override
    public String toString() {
        return "CreateUserDTO{" +
                "userName='" + userName + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
