import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {
    private List<T> quantiti;

    public Box() {
        this.quantiti = new ArrayList<>();
    }

    public float getWeight(){
        float summ = 0.0f;
        for (int i = 0; i < quantiti.size(); i++) {
            summ = summ + quantiti.get(i).getWeight();
        }
        return summ;
    }

    public boolean compare(Box<?> another) {
        return Math.abs(this.getWeight() - another.getWeight()) < 0.0001;
    }

    public void add (T b){
        quantiti.add(b);
    }

    public void join (Box<T> another){
        for (int i = 0; i < another.quantiti.size(); ) {
             this.quantiti.add(another.quantiti.get(i));
            another.quantiti.remove(i);
        }
    }
}
