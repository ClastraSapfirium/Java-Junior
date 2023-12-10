import java.io.*;

public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student user1 = new Student("Владислав", 21, 7.3);
        Student user2 = new Student("Виктор", 19, 6.6);
        Student user3 = new Student("Анна", 20, 8.6);

        try(FileOutputStream fileOutputStream = new FileOutputStream("HW_3.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(user1);
            System.out.println("\nОбъект Пользователь_1 сериализован.");
            objectOutputStream.writeObject(user2);
            System.out.println("\nОбъект Пользователь_2 сериализован.");
            objectOutputStream.writeObject(user3);
            System.out.println("\nОбъект Пользователь_3 сериализован.");
        }

        try(FileInputStream fileInputStream = new FileInputStream("HW_3.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            user1 = (Student)objectInputStream.readObject();
            System.out.println("\nОбъект Пользователь_1 десериализован.");
            user2 = (Student)objectInputStream.readObject();
            System.out.println("\nОбъект Пользователь_2 десериализован.");
            user3 = (Student)objectInputStream.readObject();
            System.out.println("\nОбъект Пользователь_3 десериализован.");
        }

        System.out.println("\nОбъект Пользователь_1:");
        System.out.println("Имя:          " + user1.getName());
        System.out.println("Возраст:      " + user1.getAge());
        System.out.println("Средний балл: " + user1.getGPA());

        System.out.println("\nОбъект Пользователь_2:");
        System.out.println("Имя:          " + user2.getName());
        System.out.println("Возраст:      " + user2.getAge());
        System.out.println("Средний балл: " + user2.getGPA());

        System.out.println("\nОбъект Пользователь_3:");
        System.out.println("Имя:          " + user3.getName());
        System.out.println("Возраст:      " + user3.getAge());
        System.out.println("Средний балл: " + user3.getGPA());
    }
}