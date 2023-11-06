public class Boat extends Vehicle {
    public Boat(int horsepower) {
        super(horsepower);
        this.type = "boat";
    }

    @Override
    public void getDetails() {
        System.out.println("I am a " + type + " with " + horsePower + "hp");
    }
}
