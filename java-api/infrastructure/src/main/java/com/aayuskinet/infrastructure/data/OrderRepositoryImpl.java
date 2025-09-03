package com.aayuskinet.infrastructure.data;
import com.aayuskinet.core.repositories.GenericRepository;
import com.aayuskinet.core.interfaces.ISpecification;
import com.aayuskinet.core.entities.orderaggregate.Order;
import java.util.List;
import java.util.Optional;

//import com.aayuskinet.core.entities.orderaggregate.Order;
import com.aayuskinet.core.interfaces.IOrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl extends GenericRepository<Order> implements IOrderRepository {
    public OrderRepositoryImpl() {
        super();
    }
    // Stub implementations for IGenericRepository
    @Override
    public Optional<Order> getById(int id) { return Optional.empty(); }
    @Override
    public List<Order> listAll() { return List.of(); }
    @Override
    public Optional<Order> getEntityWithSpec(ISpecification<Order> spec) { return Optional.empty(); }
    @Override
    public List<Order> list(ISpecification<Order> spec) { return List.of(); }
    @Override
    public int count(ISpecification<Order> spec) { return 0; }
    @Override
    public void add(Order entity) {}
    @Override
    public void update(Order entity) {}
    @Override
    public void remove(Order entity) {}
    @Override
    public boolean exists(int id) { return false; }
}
