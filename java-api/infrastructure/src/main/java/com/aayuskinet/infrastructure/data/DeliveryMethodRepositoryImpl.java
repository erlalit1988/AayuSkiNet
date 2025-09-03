package com.aayuskinet.infrastructure.data;
import com.aayuskinet.core.repositories.GenericRepository;
import com.aayuskinet.core.interfaces.ISpecification;
import com.aayuskinet.core.entities.DeliveryMethod;
import java.util.List;
import java.util.Optional;

//import com.aayuskinet.core.entities.DeliveryMethod;
import com.aayuskinet.core.interfaces.IDeliveryMethodRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryMethodRepositoryImpl extends GenericRepository<DeliveryMethod> implements IDeliveryMethodRepository {
    public DeliveryMethodRepositoryImpl() {
        super();
    }

    // Stub implementations for IGenericRepository
    @Override
    public Optional<DeliveryMethod> getById(int id) { return Optional.empty(); }
    @Override
    public List<DeliveryMethod> listAll() { return List.of(); }
    @Override
    public Optional<DeliveryMethod> getEntityWithSpec(ISpecification<DeliveryMethod> spec) { return Optional.empty(); }
    @Override
    public List<DeliveryMethod> list(ISpecification<DeliveryMethod> spec) { return List.of(); }
    @Override
    public int count(ISpecification<DeliveryMethod> spec) { return 0; }
    @Override
    public void add(DeliveryMethod entity) {}
    @Override
    public void update(DeliveryMethod entity) {}
    @Override
    public void remove(DeliveryMethod entity) {}
    @Override
    public boolean exists(int id) { return false; }
}
