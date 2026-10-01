import java.util.List;

/**
 * CONTROLLER - processes the user's selections and updates the result in the View.
 */
public class VehicleServiceController {
    private final VehicleServiceModel model;
    private final VehicleServiceView view;

    public VehicleServiceController(VehicleServiceModel model, VehicleServiceView view) {
        this.model = model;
        this.view = view;
        this.view.addCalculateListener(e -> calculate());
    }

    private void calculate() {
        String regNo = view.getRegistrationNumber();
        List<String> services = view.getSelectedServices();

        if (regNo.isEmpty()) {
            view.showError("Please enter the vehicle registration number.");
            return;
        }
        if (services.isEmpty()) {
            view.showError("Please select at least one service.");
            return;
        }

        int cost = model.calculateCost(regNo, view.getVehicleType(), services);
        view.showResult(String.format("<html><center>%s (%s)<br>Total Service Cost: Rs.%,d</center></html>",
                model.getRegistrationNumber(), model.getVehicleType(), cost));
    }
}
