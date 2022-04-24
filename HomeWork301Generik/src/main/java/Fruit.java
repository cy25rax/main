public class Fruit {
    private int quantiti;
    private final float weight;


    public float getWeight() {
        return quantiti*weight;
    }

//    public int getQuantiti() {
//        return quantiti;
//    }

    public Fruit(int quantiti, float weight) {
        this.quantiti = quantiti;
        this.weight = weight;
    }

}
