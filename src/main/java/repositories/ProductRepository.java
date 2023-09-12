package repositories;

import domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repositories.interfaces.IProductRepository;


public class ProductRepository implements IProductRepository {

    Product cerveja=Product.builder().id(1).price(12.1).description("cerveja boa ").name("Heineken").build();

    Product cerveja2=Product.builder().id(2).price(12.1).description("cerveja boa ").name("Budweiser").build();

    Product cerveja3=Product.builder().id(3).price(12.1).description("cerveja boa ").name("Skol").build();

    Product vinho=Product.builder().id(14).price(1122.1).description("vinhjo boa ").name("Vug VInho").build();

    Product leite=Product.builder().id(15).price(112.11).description("leite bm").name("REi do LEite").build();


    //O MONO E ELE EMITE 0 OU 1 VALOR  OU ERRO
    @Override
    public Mono<Product> findById(Integer id) {
        return Flux.just(cerveja,cerveja2,cerveja3,vinho,leite).collectList().flatMapMany(Flux::fromIterable).filter(p->p.getId()==id).singleOrEmpty();
    }

    //O FLUX ELE EMITE O OU N VALORES OU ERRO MESMO
    @Override
    public Flux<Product> findAll() {
        //SERIA COMO SE TIVESSE REALIZANDO UM OF DO RXJS MEU CARO E DEPOIS COMPLETASSE
        return Flux.just(cerveja,cerveja2,cerveja3,vinho,leite);
    }
}
