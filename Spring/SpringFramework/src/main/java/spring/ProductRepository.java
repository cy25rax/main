package spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

import java.util.List;

@Component
public class ProductRepository {

    private final List<Product> productlist = new ArrayList<>();

    public List<Product> getProductlist() {
        return productlist;
    }

    public Product getProductlist(int id) {
        return productlist.get(id);
    }

    @PostConstruct
    public void init() {
                productlist.add(new Product("BMW", 10));
                productlist.add(new Product("Lada", 5));
                productlist.add(new Product("Mercedes", 20));
                productlist.add(new Product("Toyota", 13));
                productlist.add(new Product("KAI", 17));
    }
}
