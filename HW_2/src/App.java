public class App {
    public static void main(String[] args) {
        Veterinarian veterinarian = new Veterinarian();
        Animal[] animals = new Animal[2];
        animals[0] = new Dog("Каша и кости", "Частный дом");
        animals[1] = new Cat("Молоко и рыба", "Квартира");

        for (Animal animal : animals) {
            veterinarian.treatAnimal(animal);
        }
    }
}