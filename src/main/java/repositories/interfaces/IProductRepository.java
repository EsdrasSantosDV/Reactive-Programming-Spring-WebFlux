package repositories.interfaces;

import domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductRepository {

    Mono<Product> findById(Integer id);

    Flux<Product> findAll();

}
