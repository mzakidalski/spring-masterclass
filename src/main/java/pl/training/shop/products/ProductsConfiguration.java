package pl.training.shop.products;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class ProductsConfiguration {

    @Bean
    public ProductRepository productRepository() {
        return new JpaProductsRepository();
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

}
