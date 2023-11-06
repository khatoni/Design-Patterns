public class VehicleFactory {
    public static Vehicle createVehicle(String type, int horsepower) {
        switch (type) {
            case "car":
                return new Car(horsepower);
            case "boat":
                return new Boat(horsepower);
            case "motorcycle":
                return new Motorcycle(horsepower);
            default:
                return null;
        }
    }
}
