import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * VIEW - graphical interface built with JFrame, JTextField, JRadioButton,
 * JCheckBox, JButton and JLabel.
 */
public class VehicleServiceView extends JFrame {
    private final JTextField regNumberField = new JTextField(14);
    private final JRadioButton twoWheelerRadio = new JRadioButton("Two Wheeler", true);
    private final JRadioButton carRadio = new JRadioButton("Car");
    private final List<JCheckBox> serviceBoxes = new ArrayList<>();
    private final JButton calculateButton = new JButton("Calculate Cost");
    private final JLabel resultLabel = new JLabel("Select services and click Calculate Cost");

    public VehicleServiceView() {
        super("Vehicle Service Cost Estimator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        regNumberField.setName("regNumberField");
        carRadio.setName("carRadio");
        twoWheelerRadio.setName("twoWheelerRadio");
        calculateButton.setName("calculateButton");

        JPanel details = new JPanel(new GridLayout(0, 2, 8, 8));
        details.setBorder(BorderFactory.createTitledBorder("Vehicle Details"));
        details.add(new JLabel("Registration Number:"));
        details.add(regNumberField);
        details.add(new JLabel("Vehicle Type:"));
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(twoWheelerRadio);
        typeGroup.add(carRadio);
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        typePanel.add(twoWheelerRadio);
        typePanel.add(carRadio);
        details.add(typePanel);

        JPanel services = new JPanel(new GridLayout(0, 1, 4, 4));
        services.setBorder(BorderFactory.createTitledBorder("Services"));
        for (Map.Entry<String, Integer> entry : VehicleServiceModel.SERVICE_PRICES.entrySet()) {
            JCheckBox box = new JCheckBox(entry.getKey() + " - Rs." + entry.getValue());
            box.setActionCommand(entry.getKey());
            box.setName(entry.getKey());
            serviceBoxes.add(box);
            services.add(box);
        }

        JPanel bottom = new JPanel(new BorderLayout(6, 6));
        JPanel buttonRow = new JPanel();
        buttonRow.add(calculateButton);
        bottom.add(buttonRow, BorderLayout.NORTH);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 14f));
        bottom.add(resultLabel, BorderLayout.CENTER);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        root.add(details, BorderLayout.NORTH);
        root.add(services, BorderLayout.CENTER);
        root.add(bottom, BorderLayout.SOUTH);
        setContentPane(root);
        pack();
        setLocationRelativeTo(null);
    }

    public String getRegistrationNumber() { return regNumberField.getText().trim(); }

    public String getVehicleType() { return carRadio.isSelected() ? "Car" : "Two Wheeler"; }

    public List<String> getSelectedServices() {
        List<String> selected = new ArrayList<>();
        for (JCheckBox box : serviceBoxes) {
            if (box.isSelected()) selected.add(box.getActionCommand());
        }
        return selected;
    }

    public void showResult(String text) { resultLabel.setText(text); }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Required", JOptionPane.WARNING_MESSAGE);
    }

    public void addCalculateListener(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }
}
