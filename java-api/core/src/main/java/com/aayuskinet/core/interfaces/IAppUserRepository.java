package com.aayuskinet.core.interfaces;

import com.aayuskinet.core.entities.AppUser;
import java.util.Optional;

public interface IAppUserRepository extends IGenericRepository<AppUser> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByEmailWithAddress(String email);
}
 