package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.training.shop.common.PagedResult;

@RequiredArgsConstructor
public class HibernateProductRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Product save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long)session.save(product);
        product.setId(id);
        return product;
    }

    @Override
    public PagedResult<Product> findAll(int pageNumber, int pageSize) {
        final Session session = sessionFactory.getCurrentSession();
        var productList = session.createNamedQuery(Product.SELECT_PRODUCTS, Product.class)
                .setFirstResult(pageNumber*pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PagedResult<>(productList, pageNumber, -1);
    }
}
