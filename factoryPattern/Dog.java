package factoryPattern;
public class Dog implements Pet {
    private String breed;

    public Dog(String breed) {
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }

    @Override
    public void play() {
        System.out.println("The dog is fetching the ball.");
    }

    public String getBreed() {
        return breed;
    }
}
