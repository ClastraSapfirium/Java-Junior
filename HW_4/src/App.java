
import model.Student;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class App {

    private final static Random random = new Random();


    public static void main(String[] args) {

        String url = "jdbc:mysql://students.db:3306/";
        String user = "root";
        String password = "password";

        try(Connection connection = DriverManager.getConnection(url, user, password)){


            createDatabase(connection);
            System.out.println("База данных создана.");

            useDatabase(connection);
            System.out.println("База данных в использовании.");

            createTable(connection);
            System.out.println("Таблица создана.");

            int count = random.nextInt(5, 11);
            for (int i = 0; i < count; i++)
                insertData(connection, Student.create());
            System.out.println("Ввод данных в норме.");

            Collection<Student> students = readData(connection);
            for (var student: students)
                System.out.println(student);
            System.out.println("Чтение данных завершено.");

            for (var student: students) {
                student.updateName();
                student.updateAge();
                updateData(connection, student);
            }
            System.out.println("Обновление данных завершено.");

            students = readData(connection);
            for (var student: students)
                System.out.println(student);
            System.out.println("Чтение данных завершено.");


            for (var student: students)
                deleteData(connection, student.getId());
            System.out.println("Удаление данных завершено");

            connection.close();
            System.out.println("Закрытие соединения завершено.");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }


    private static void createDatabase(Connection connection) throws SQLException {
        String createDatabaseSQL =  "Create Database if not exists studentsDB;";
        try (PreparedStatement statement = connection.prepareStatement(createDatabaseSQL)) {
            statement.execute();
        }
    }

    private static void useDatabase(Connection connection) throws SQLException {
        String useDatabaseSQL =  "Use studentsDB;";
        try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
            statement.execute();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "Create table if not exists students (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), age INT);";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }

    private static void insertData(Connection connection, Student student) throws SQLException {
        String insertDataSQL = "Insert into students (name, age) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.executeUpdate();
        }
    }

    private static Collection<Student> readData(Connection connection) throws SQLException {
        ArrayList<Student> studentsList = new ArrayList<>();
        String readDataSQL = "Select * from students;";
        try (PreparedStatement statement = connection.prepareStatement(readDataSQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                studentsList.add(new Student(id, name, age));
            }
            return studentsList;
        }
    }

    private static void updateData(Connection connection, Student student) throws SQLException {
        String updateDataSQL = "Update students set name=?, age=? WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(updateDataSQL)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, student.getId());
            statement.executeUpdate();
        }
    }

    private static void deleteData(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "Delete from students where id=?;";
        try (PreparedStatement statement = connection.prepareStatement(deleteDataSQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}