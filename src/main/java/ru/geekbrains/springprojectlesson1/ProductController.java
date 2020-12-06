package ru.geekbrains.springprojectlesson1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {

        return productRepository.findById(id).get();
    }
    // 1. Обработать запрос вида: GET /products/filtered?min_price=100
    // В результате должен вернуться список товаров, цена которых >= 100
    @GetMapping("/filtered")
    public List<Product> getProductsWithPriceGreaterThan100(@RequestParam Integer min_price) {

        return productRepository.requestProductsPriceGreaterThan(min_price);
    }

    // 2. Обработать запрос вида: GET /products/delete/1
    // В результате должен удалиться товар с соответствющим id
    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {

       productRepository.deleteById(id);
    }

    // 3. * Попробуйте реализовать возможность изменения названия товара по id
    // Что-то вроде: /products/{id}/change_title...
    ///products/{id}/change_title?title=mars
    @PutMapping("/{id}/change_title")
    public Product changeTitle(@PathVariable Long id, @RequestParam String title) {
        Product product = productRepository.findById(id).get();
        product.setTitle(title);
        return productRepository.save(product);
    }

}
