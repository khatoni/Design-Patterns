public class Motorcycle extends Vehicle {
    public Motorcycle(int horsepower) {
        super(horsepower);
        this.type = "motor";
    }

    @Override
    public void getDetails() {
        System.out.println("I am a " + type + " with " + horsePower + "hp");
    }
}
