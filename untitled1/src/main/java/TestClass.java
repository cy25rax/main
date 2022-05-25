import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestClass {

    public static void main(String[] args) {

        start(Simple.class);

    }


    public static void start(Class testClass) {
            // массив  значений порядкового номера метода который написа в value
            ArrayList<Integer> arrayList = new ArrayList<>();
            Method[] methods = testClass.getDeclaredMethods();

            for (Method o : methods) {
                if(o.getAnnotation(Test.class) != null) {
                    arrayList.add(o.getAnnotation(Test.class).value());
                } else if(o.getAnnotation(AfterSuite.class) != null) {

                    for (int i = 0; i < arrayList.size(); i++) {
                        if (arrayList.get(i) == methods.length-1) {
                            throw new RuntimeException("метод AfterSuite в 2х экземплярах");
                        }
                    }
                    arrayList.add(methods.length-1);

                } else if (o.getAnnotation(BeforeSuite.class) != null) {

                    for (int i = 0; i < arrayList.size(); i++) {
                        if (arrayList.get(i) == 0) {
                            throw new RuntimeException("метод BeforeSuite в 2х экземплярах");
                        }
                    }
                    arrayList.add(0);

                    // если метод без аннотации то не будем его выполнять
                } else arrayList.add(-1);
            }

            for (int i = 0; i < methods.length; i++) {
                System.out.print(methods[i].getName()+" +++++ "+ arrayList.get(i));
                System.out.println();
            }


        for (int i = 0; i < methods.length; i++) {
            try {
                for (int j = 0; j < methods.length; j++) {

                    if (arrayList.get(j) == i) {

                        methods[j].invoke(testClass.newInstance());

                    }
                }
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }

            }
        }
    }
