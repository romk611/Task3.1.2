package com.example.task312.dao;




import com.example.task312.model.Role;

import java.util.Set;

public interface RoleDAO {
    public Set<Role> getAllRole();

    public Role getRole(String userRole);

    public Role getRole(long roleId);

    public void addRole(Role role);
}
