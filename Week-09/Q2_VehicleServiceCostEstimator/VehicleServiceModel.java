import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * MODEL - holds the service price list and calculates the total service cost.
 */
public class VehicleServiceModel {
    public static final Map<String, Integer> SERVICE_PRICES = new LinkedHashMap<>();
    static {
        SERVICE_PRICES.put("General Service", 1000);
        SERVICE_PRICES.put("Oil Change", 800);
        SERVICE_PRICES.put("Brake Service", 1200);
        SERVICE_PRICES.put("Battery Check", 500);
    }

    private String registrationNumber;
    private String vehicleType;
    private int totalCost;

    public int calculateCost(String registrationNumber, String vehicleType, List<String> selectedServices) {
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        int sum = 0;
        for (String service : selectedServices) {
            sum += SERVICE_PRICES.getOrDefault(service, 0);
        }
        this.totalCost = sum;
        return totalCost;
    }

    public String getRegistrationNumber() { return registrationNumber; }
    public String getVehicleType()        { return vehicleType; }
    public int getTotalCost()             { return totalCost; }
}
