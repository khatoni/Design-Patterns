
public class Main {
    public static void main(String[] args) {
        Vehicle myBoat = VehicleFactory.createVehicle("boat", 200);
        Vehicle myCar = VehicleFactory.createVehicle("car", 120);
        myBoat.getDetails();
        myCar.getDetails();
    }
}