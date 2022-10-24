package spring;

public class Product {
    private static int count;
    private int id;
    private String title;
    private int cost;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}'+ "\n";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
        count++;
        this.id = count;
    }
}