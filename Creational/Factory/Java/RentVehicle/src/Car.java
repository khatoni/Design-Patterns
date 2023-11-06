public class Car extends Vehicle {

    public Car(int horsePower) {
        super(horsePower);
        this.type = "car";
    }

    @Override
    public void getDetails() {
        System.out.println("I am a " + type + " with " + horsePower + "hp");
    }
}
