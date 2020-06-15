package org.example.product.catalog.resource;

import org.example.product.catalog.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RepositoryRestResource(path = "products")
public interface ProductResource extends JpaRepository<Product, Long> {
}
