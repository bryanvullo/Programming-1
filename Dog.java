public class Dog{
    private String noise = "Woof";

    public void bark(){
        System.out.println(noise);
    }

    public static void main(String[] args) {
        Dog d = new Dog();
        d.bark();
        d.bark();
    }
}