package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.retry.Retry;

@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Retry
    public Product add(Product product) {
        return productRepository.save(product);
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(pageNumber,pageSize));
        return new PagedResult<>(productPage.getContent(), pageNumber, productPage.getTotalPages());
    }

}
