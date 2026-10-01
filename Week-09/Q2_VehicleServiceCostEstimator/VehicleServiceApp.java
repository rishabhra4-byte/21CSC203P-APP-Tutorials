import javax.swing.SwingUtilities;

/** Entry point: wires Model, View and Controller together. */
public class VehicleServiceApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VehicleServiceModel model = new VehicleServiceModel();
            VehicleServiceView view = new VehicleServiceView();
            new VehicleServiceController(model, view);
            view.setVisible(true);
        });
    }
}
