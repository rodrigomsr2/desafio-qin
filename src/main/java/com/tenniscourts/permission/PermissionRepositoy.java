package com.tenniscourts.permission;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepositoy extends CrudRepository<Permission, Long> {

}
