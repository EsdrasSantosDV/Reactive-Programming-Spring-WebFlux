package repositories;

import domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository productRepository=new ProductRepository();

    @Test()
    @DisplayName("Testing Block☺")
    void testingBlock(){
        Mono<Product> monoProduct=this.productRepository.findById(1);
        Product product=monoProduct.block();
        assertNotNull(product);
        System.out.println(product.toString());

    }

    @Test()
    @DisplayName("Testing Subscriber☺")
    void testingSubscriber(){
       this.productRepository.findById(1).subscribe(p->{
            System.out.println(p.toString());
        });
       //MEU REI NEM PRECISO FALAR NADA, QUE TU JA E O CARA
    }



    //ME DE RJXS MEEEE DEEEEEE PAPAI
    //AGORA TAMO EM CASA
    @Test()
    @DisplayName("Testing map")
    void testingMapOperator(){
        this.productRepository.findById(1).map(Product::getName).subscribe(System.out::println);
    }


    @Test()
    @DisplayName("Testing filter")
    void testingFilterOperator(){
        this.productRepository.findAll().filter(p->p.getPrice()<1000).subscribe(System.out::println);
    }



}