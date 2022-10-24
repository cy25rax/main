package spring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<Product> products = new ArrayList<>();

    public void setProducts(Product product) {
        this.products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> delProducts(int id) {
        for (Product pr:products) {
            if (pr.getId() == id) {
                products.remove(pr);
                break;
            }
        }
        return products;
    }
}
