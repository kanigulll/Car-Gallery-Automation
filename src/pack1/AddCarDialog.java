package pack1;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AddCarDialog extends JDialog {
    private JTextField brandField;
    private JTextField modelField;
    private JTextField kmField;
    private JComboBox<String> faultComboBox;
    private JTextField salePriceField;
    private JButton addButton;
    private JButton cancelButton;
    private JButton selectImageButton;
    private JLabel imagePreview;

    private Car car;
    private String selectedImagePath;

    public AddCarDialog(JFrame parent) {
        super(parent, "Add New Car", true);
        setLayout(null);
        setSize(500, 450);
        setLocationRelativeTo(parent);

        
        Color green = new Color(0, 153, 76);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        getContentPane().setBackground(Color.WHITE);

       
        JLabel brandLabel = new JLabel("Brand:");
        JLabel modelLabel = new JLabel("Model:");
        JLabel kmLabel = new JLabel("Mileage (KM):");
        JLabel faultLabel = new JLabel("Fault Status:");
        JLabel salePriceLabel = new JLabel("Sale Price (₺):");
        JLabel imageLabel = new JLabel("Car Image:");

        JLabel[] labels = {brandLabel, modelLabel, kmLabel, faultLabel, salePriceLabel, imageLabel};
        for (JLabel label : labels) {
            label.setFont(labelFont);
            label.setForeground(green);
        }

        
        brandField = new JTextField(20);
        modelField = new JTextField(20);
        kmField = new JTextField(20);
        faultComboBox = new JComboBox<>(new String[]{"No Fault", "Faulty"});
        salePriceField = new JTextField(20);

        JTextField[] fields = {brandField, modelField, kmField, salePriceField};
        for (JTextField field : fields) {
            field.setFont(fieldFont);
        }
        faultComboBox.setFont(fieldFont);

        
        selectImageButton = new JButton("Select Image");
        selectImageButton.setBackground(green);
        selectImageButton.setForeground(Color.WHITE);
        selectImageButton.setFont(labelFont);

        imagePreview = new JLabel();
        imagePreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imagePreview.setPreferredSize(new Dimension(100, 75));
        imagePreview.setOpaque(true);
        imagePreview.setBackground(Color.WHITE);

        
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");

        addButton.setBackground(green);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(labelFont);

        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(labelFont);

       
        brandLabel.setBounds(30, 20, 100, 25);
        brandField.setBounds(140, 20, 300, 25);

        modelLabel.setBounds(30, 60, 100, 25);
        modelField.setBounds(140, 60, 300, 25);

        kmLabel.setBounds(30, 100, 100, 25);
        kmField.setBounds(140, 100, 300, 25);

        faultLabel.setBounds(30, 140, 100, 25);
        faultComboBox.setBounds(140, 140, 300, 25);

        salePriceLabel.setBounds(30, 180, 100, 25);
        salePriceField.setBounds(140, 180, 300, 25);

        imageLabel.setBounds(30, 220, 100, 25);
        selectImageButton.setBounds(140, 220, 150, 25);
        imagePreview.setBounds(310, 220, 100, 75);

        addButton.setBounds(140, 320, 100, 35);
        cancelButton.setBounds(260, 320, 100, 35);

        
        add(brandLabel); add(brandField);
        add(modelLabel); add(modelField);
        add(kmLabel); add(kmField);
        add(faultLabel); add(faultComboBox);
        add(salePriceLabel); add(salePriceField);
        add(imageLabel); add(selectImageButton);
        add(imagePreview);
        add(addButton); add(cancelButton);

        
        selectImageButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int res = chooser.showOpenDialog(AddCarDialog.this);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                selectedImagePath = file.getAbsolutePath();
                ImageIcon icon = new ImageIcon(new ImageIcon(selectedImagePath).getImage()
                        .getScaledInstance(100, 75, Image.SCALE_SMOOTH));
                imagePreview.setIcon(icon);
            }
        });

        
        addButton.addActionListener(e -> {
            if (validateInputs()) {
                String brand = brandField.getText().trim();
                String model = modelField.getText().trim();
                int km = Integer.parseInt(kmField.getText().trim());
                String fault = (String) faultComboBox.getSelectedItem();
                double salePrice = Double.parseDouble(salePriceField.getText().trim());

                car = new Car(brand, model, km, fault, selectedImagePath, salePrice);
                dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            car = null;
            dispose();
        });
    }

    private boolean validateInputs() {
        if (brandField.getText().trim().isEmpty() || modelField.getText().trim().isEmpty() ||
            kmField.getText().trim().isEmpty() || salePriceField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return false;
        }
        try {
            Integer.parseInt(kmField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mileage must be a number.");
            return false;
        }
        try {
            Double.parseDouble(salePriceField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Sale price must be a number.");
            return false;
        }
        return true;
    }

    public Car getCar() {
        return car;
    }
}
