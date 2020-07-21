package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.training.shop.common.PagedResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
public class JpaProductsRepository implements ProductRepository {

    @PersistenceContext
    @Setter
    private EntityManager entityManager;

    @Override
    public Product save(Product product) {
        entityManager.persist(product);
        entityManager.flush();
        entityManager.refresh(product);
        return null;
    }

    @Override
    public PagedResult<Product> findAll(int pageNumber, int pageSize) {
        List<Product> products = entityManager.createNamedQuery(Product.SELECT_PRODUCTS, Product.class)
                .setFirstResult(pageNumber*pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        return new PagedResult<>(products, pageNumber, -1);
    }
}
