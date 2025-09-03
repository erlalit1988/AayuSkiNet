package com.aayuskinet.infrastructure.data;
import com.aayuskinet.core.repositories.GenericRepository;
import com.aayuskinet.core.interfaces.ISpecification;
import com.aayuskinet.core.entities.AppUser;
import com.aayuskinet.core.interfaces.IAppUserRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class AppUserRepositoryImpl extends GenericRepository<AppUser> implements IAppUserRepository {
    public AppUserRepositoryImpl() {
        super();
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM AppUser u WHERE u.email = :email", AppUser.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<AppUser> findByEmailWithAddress(String email) {
        return entityManager.createQuery(
                "SELECT u FROM AppUser u LEFT JOIN FETCH u.address WHERE u.email = :email", AppUser.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    // Stub implementations for IGenericRepository
    @Override
    public Optional<AppUser> getById(int id) { return Optional.empty(); }
    @Override
    public List<AppUser> listAll() { return List.of(); }
    @Override
    public Optional<AppUser> getEntityWithSpec(ISpecification<AppUser> spec) { return Optional.empty(); }
    @Override
    public List<AppUser> list(ISpecification<AppUser> spec) { return List.of(); }
    @Override
    public int count(ISpecification<AppUser> spec) { return 0; }
    @Override
    public void add(AppUser entity) {}
    @Override
    public void update(AppUser entity) {}
    @Override
    public void remove(AppUser entity) {}
    @Override
    public boolean exists(int id) { return false; }
}
