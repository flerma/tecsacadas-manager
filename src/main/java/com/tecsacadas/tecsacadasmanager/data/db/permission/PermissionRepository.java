package com.tecsacadas.tecsacadasmanager.data.db.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PermissionRepository {

    private final PermissionDao permissionDao;
}
