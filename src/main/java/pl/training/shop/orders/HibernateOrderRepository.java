package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

@RequiredArgsConstructor
public class HibernateOrderRepository implements OrderRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Order save(Order order) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long)session.save(order);
        order.setId(id);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(Order.class, id));
    }

    @Override
    public void update(Order order) {
        Session session = sessionFactory.getCurrentSession();
        if (session.load(Order.class, order.getId()) != null) {
            session.update(order);
        }
    }
}
