public abstract class Vehicle {
    protected String type;
    protected int horsePower;

    public Vehicle(int horsePower) {
        this.horsePower = horsePower;
    }

    public abstract void getDetails();
}
