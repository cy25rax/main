package clases;

import javax.persistence.*;

@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_name")
    private String produktName;

    @Column(name = "product_cost")
    private int cost;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    public Basket() {
    }

    @Override
    public String toString() {
        return "Basket{" +
                "produktName='" + produktName + '\'' +
                ", cost=" + cost +
                '}';
    }

    public String getProduktName() {
        return produktName;
    }

    public void setProduktName(String produktName) {
        this.produktName = produktName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
