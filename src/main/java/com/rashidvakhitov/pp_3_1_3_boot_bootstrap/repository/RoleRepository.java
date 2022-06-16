package com.rashidvakhitov.pp_3_1_3_boot_bootstrap.repository;

import com.rashidvakhitov.pp_3_1_3_boot_bootstrap.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Role getRoleByName(@Param("name") String name);
}
