public class Product {
    private static int idAll;
    private int idThis;
    private String title;
    private int cost;

    public int getId() {
        return idThis;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    public Product(int id, String title, int cost) {
        this.title = title;
        this.cost = cost;
        idAll ++;
        this.idThis = idAll;
    }
}
