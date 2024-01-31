package com.tecsacadas.tecsacadasmanager.data.db.permission;

import com.tecsacadas.tecsacadasmanager.core.permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao  extends JpaRepository<Permission, Long> {
}
