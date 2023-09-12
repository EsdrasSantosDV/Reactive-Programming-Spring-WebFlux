package repositories;

import domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    void testingBlocKTesting(){
       Mono<Product> monoProduct=this.productRepository.findById(14);
       Product product=monoProduct.map(v-> v).block();
        assertThat(product.getPrice()).isEqualTo(1122.1);
       //MEU REI NEM PRECISO FALAR NADA, QUE TU JA E O CARA
    }


    @Test()
    @DisplayName("Testing Subscriber☺")
    void testingStepVerifierCreate(){
        Mono<Product> monoProduct=this.productRepository.findById(14).log();
//       StepVerifier.create(monoProduct).expectNextCount(0).verifyComplete();

        monoProduct.subscribe(
                next->{

                    System.out.println("NEXT");
                },
                error->{
                    System.out.println("DEU ERRO");
                },
                ()->
                {
                    System.out.println("COMPLETOU");
                }

                );

    }


    @Test()
    @DisplayName("Testing Subscriber☺")
    void testingGetById(){
        Mono<Product> monoProduct=this.productRepository.findById(14);
        assertTrue(monoProduct.hasElement().block());
    }

    @Test()
    @DisplayName("Testing Subscriber☺")
    void testingGetByIdNotFound(){
        Mono<Product> monoProduct=this.productRepository.findById(114);
        assertFalse(monoProduct.hasElement().block());
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


    //BLOCKING VC JA COMPLETA
    @Test()
    @DisplayName("Testing block flux")
    void testingBlockFlux(){
        Product product=this.productRepository.findAll().blockFirst();

        System.out.println(product.toString());
    }



    @Test()
    @DisplayName("Testing find Product Not Found")
    void testingFindProductNotFound(){
        Flux<Product> fluxProduct=this.productRepository.findAll();

        Mono<Product> monoProduct=fluxProduct.filter(p->p.getId().equals(1000)).single().doOnError(throwable -> {
            System.out.println("Product not found");
        });

        monoProduct.subscribe(next->{}
                             ,error->{
            System.out.println("Product not found");
                });
    }



    @Test()
    @DisplayName("Testing flux")
    void testingFlux(){
        Flux<Product> fluxProduct=this.productRepository.findAll();

        Mono<List<Product>> products=fluxProduct.collectList();

        products.subscribe(value->{
            value.forEach(System.out::println);
        });

    }
    //IGUAL NO RXJS OS OPERADORES SÃO APLICADOS NUMA OORDEM, SO NÃO HA O PIPE

    @Test()
    @DisplayName("Testing flux FILTER NAME")
    void filterTestingName() {
        Flux<Product> fluxProduct = this.productRepository.findAll();
        fluxProduct.filter(v -> v.getName().equals("Heineken")).collectList().subscribe(value -> {
            value.forEach(System.out::println);
        });
    }




}