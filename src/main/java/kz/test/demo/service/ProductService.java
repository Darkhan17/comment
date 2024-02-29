package kz.test.demo.service;

import kz.test.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public boolean existsById(String id) {
        return productRepository.existsById(id);
    }
}
