package org.example.gb.lesson3.hw;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class Homework {

    /**
     * Написать класс с двумя методами:
     * 1. принимает объекты, имплементирующие интерфейс serializable, и сохраняющие их в файл.
     *      Название файл - class.getName() + "_" + UUID.randomUUID().toString()
     * 2. принимает строку вида class.getName() + "_" + UUID.randomUUID().toString() и загружает объект из файла и удаляет этот файл.
     *
     * Что делать в ситуациях, когда файла нет или в нем лежит некорректные данные - подумать самостоятельно.
     */

    public static void main(String[] args) {
//        SerializeClasses.serialize(new Car("Toyota"));
        Car toyota = (Car) SerializeClasses.deSerialize("Car_4dd3988c-3535-42e1-a1d5-31a2453875eb");
        System.out.println(toyota);
    }

    static class SerializeClasses{
        static private Path repoName = Path.of("classesRepo");
        static private Path classRepoName;

        static {
            if (Files.notExists(repoName)) {
                try {
                    Files.createDirectory(repoName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        static <T extends Serializable> void serialize(T obj) {
            classRepoName = Path.of(String.valueOf(repoName),
                    obj.getClass().getSimpleName() + "_" + UUID.randomUUID());
            try (OutputStream outputStream = Files.newOutputStream(classRepoName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                objectOutputStream.writeObject(obj);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        static Object deSerialize(String className) {
            Object obj;
            classRepoName = Path.of(String.valueOf(repoName), className);
            try (InputStream inputStream = Files.newInputStream(classRepoName);
                 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
                obj = objectInputStream.readObject();
                Files.delete(classRepoName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return obj;
        }
    }

    static class Car implements Serializable {
        private String model;

        @Override
        public String toString() {
            return "Car {" +
                    "model='" + model + '\'' +
                    '}';
        }

        public Car(String model) {
            this.model = model;
        }
    }
}
