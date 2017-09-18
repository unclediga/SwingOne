package unclediga.io;

import java.io.*;

public class CopyObjects {

    private static String OUT_FILE = "data/out_obj.txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(OUT_FILE));
             ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(OUT_FILE))) {


            final Parent parent1 = new Parent("Parent1", new Child("Артём"), null);
            System.out.println("write : "+parent1);
            outputStream.writeObject(parent1);
            final Parent parent2 = new Parent("Parent2", new Child("Маша"),new Child("Глаша"));
            System.out.println("write : "+parent2);
            outputStream.writeObject(parent2);

            Object obj = null;
            boolean eof = false;
            while (!eof){
                try {
                    obj = inputStream.readObject();
                    if (obj instanceof Parent){

                        System.out.println((Parent)obj);
                    }
                } catch (EOFException e) {
                    System.out.println("End file");
                    eof = true;
                }

            }


        }


    }

}


class Child implements Serializable{
    private String name = null;

    public Child(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                "}=" + this.hashCode();
    }
}
class Parent implements Serializable {

    private String name = null;
    private Child child1 = null;
    private Child child2 = null;

    public Parent(String name, Child child1, Child child2) {
        this.name = name;
        this.child1 = child1;
        this.child2 = child2;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "name='" + name + '\'' +
                ", child1=" + child1 +
                ", child2='" + child2 + '\'' +
                "} = " + this.hashCode();
    }
}