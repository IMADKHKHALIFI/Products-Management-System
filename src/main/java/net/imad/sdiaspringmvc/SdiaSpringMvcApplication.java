package net.imad.sdiaspringmvc;

import net.imad.sdiaspringmvc.entities.Product;
import net.imad.sdiaspringmvc.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
// ca just pour deactivé la securité Spring !!
@SpringBootApplication
public class SdiaSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdiaSpringMvcApplication.class, args);
    }

        @Bean
    public CommandLineRunner start(ProductRepository repository, ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                            .name("Computer")
                            .price(5400)
                            .quantity(13)
                    .build());
            productRepository.save(Product.builder()
                    .name("Clavier")
                    .price(300)
                    .quantity(23)
                    .build());
            productRepository.save(Product.builder()
                    .name("Smart phone")
                    .price(200)
                    .quantity(43)
                    .build());
            productRepository.findAll().forEach(p -> {
                System.out.println(p.toString());


            });
        };
        }

}
