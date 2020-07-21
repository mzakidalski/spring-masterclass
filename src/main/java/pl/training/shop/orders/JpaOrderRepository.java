package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RequiredArgsConstructor
public class JpaOrderRepository implements  OrderRepository {

    @PersistenceContext
    @Setter
    private EntityManager entityManager;

    @Override
    public Order save(Order order) {
        entityManager.persist(order);
        entityManager.flush();
        entityManager.refresh(order);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public void update(Order order) {
        if (entityManager.find(Order.class, order.getId()) != null) {
            entityManager.merge(order);
        }

    }
}
