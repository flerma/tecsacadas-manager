package com.tecsacadas.tecsacadasmanager.data.db.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class PermissionRepositoryImpl implements PermissionRepository {

    private final PermissionDao permissionDao;
}
