package factoryPattern;
public class Cat implements Pet {
    private int lives;

    public Cat(int lives) {
        this.lives = lives;
    }

    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }

    @Override
    public void play() {
        System.out.println("The cat is playing with yarn.");
    }

    public int getLives() {
        return lives;
    }
}
