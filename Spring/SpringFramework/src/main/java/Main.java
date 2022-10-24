import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.Cart;
import spring.ProductRepository;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("spring");

        ProductRepository productRepository = context.getBean(ProductRepository.class);
        Cart cart = context.getBean(Cart.class);

        System.out.println();

        System.out.println("all Products in ProductRepository");
        System.out.println(productRepository.getProductlist());

        System.out.println("add Product with id = 2");
        cart.setProducts(productRepository.getProductlist(1));
        System.out.println(cart.getProducts());
        System.out.println();

        System.out.println("add Product with id = 4");
        cart.setProducts(productRepository.getProductlist(3));
        System.out.println(cart.getProducts());
        System.out.println();

        System.out.println("add Product with id = 5");
        cart.setProducts(productRepository.getProductlist(4));
        System.out.println(cart.getProducts());
        System.out.println();

        System.out.println("delete Product with id = 4");
        System.out.println(cart.delProducts(4));
    }

}
