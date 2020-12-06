package ru.geekbrains.springprojectlesson1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // select p from Product p where p.title = ?1 and p.price = ?2
    Product findByTitleAndPrice(String title, int price);


    
   //Обработать запрос вида: GET /products/filtered?min_price=100
   //  В результате должен вернуться список товаров, цена которых >= 100
    @Query("select p from Product p where p.price >= ?1")
    List<Product> requestProductsPriceGreaterThan(Integer min_price);

}
