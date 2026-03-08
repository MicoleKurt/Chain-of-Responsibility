package factoryPattern;
import java.util.Scanner;

public class Clinic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pet pet = null;

        while (true) {
            System.out.println("\nChoose a pet:");
            System.out.println("1. Cat");
            System.out.println("2. Dog");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                pet = new Cat(9); // Default 9 lives
                pet.makeSound();
                pet.play();
                System.out.println("This cat has " + ((Cat) pet).getLives() + " lives.");
            } else if (choice == 2) {
                pet = new Dog("Golden Retriever"); // Example breed
                pet.makeSound();
                pet.play();
                System.out.println("This dog is a " + ((Dog) pet).getBreed() + ".");
            } else if (choice == 3) {
                System.out.println("Exiting program...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}

