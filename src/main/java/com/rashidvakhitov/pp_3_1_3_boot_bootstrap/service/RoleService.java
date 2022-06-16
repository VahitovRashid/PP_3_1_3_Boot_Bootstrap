package com.rashidvakhitov.pp_3_1_3_boot_bootstrap.service;

import com.rashidvakhitov.pp_3_1_3_boot_bootstrap.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface RoleService {
    List<Role> getAllRoles();

    Set<Role> getSetRoles(String[] role);
}
