package com.eray.authservice.dto.role;

import com.eray.commonlib.dto.Dto;

import java.io.Serializable;
import java.util.Objects;

public class RoleDTO extends Dto implements Serializable {

    private static final long serialVersionUID = -3226418379814474427L;

    private Long id;

    private String roleName;

    public RoleDTO() {
        //no-args constructor for jackson
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(id, roleDTO.id) &&
                Objects.equals(roleName, roleDTO.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
