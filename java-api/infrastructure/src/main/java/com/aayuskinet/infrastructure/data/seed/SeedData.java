package com.aayuskinet.infrastructure.data.seed;

//import com.aayuskinet.core.entities.DeliveryMethod;
//import com.aayuskinet.core.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//import java.io.InputStream;
//import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    // Repositories will be injected here
    // private final ProductRepository productRepository;
    // private final DeliveryMethodRepository deliveryMethodRepository;

    // public SeedData(ProductRepository productRepository, DeliveryMethodRepository deliveryMethodRepository) {
    //     this.productRepository = productRepository;
    //     this.deliveryMethodRepository = deliveryMethodRepository;
    // }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Seed Products
        // if (productRepository.count() == 0) {
        //     try (InputStream inputStream = getClass().getResourceAsStream("/seeddata/products.json")) {
        //         List<Product> products = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, Product.class));
        //         productRepository.saveAll(products);
        //     }
        // }

        // Seed Delivery Methods
        // if (deliveryMethodRepository.count() == 0) {
        //     try (InputStream inputStream = getClass().getResourceAsStream("/seeddata/delivery.json")) {
        //         List<DeliveryMethod> deliveryMethods = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, DeliveryMethod.class));
        //         deliveryMethodRepository.saveAll(deliveryMethods);
        //     }
        // }
    }
}
