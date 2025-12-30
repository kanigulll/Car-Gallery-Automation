package pack1;

import javax.swing.*;
import java.awt.*;

public class CarDetailDialog extends JDialog {

    public CarDetailDialog(JFrame parent, Car car, double salePrice) {
        super(parent, "Car Details", true);

        setSize(400, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        Color green = new Color(0, 153, 76);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font valueFont = new Font("Segoe UI", Font.PLAIN, 14);

        
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        infoPanel.setBackground(Color.WHITE);

        
        JLabel brandLabel = new JLabel("Brand:");
        brandLabel.setFont(labelFont);
        brandLabel.setForeground(green);
        JLabel brandValue = new JLabel(car.getBrand());
        brandValue.setFont(valueFont);

        
        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setFont(labelFont);
        modelLabel.setForeground(green);
        JLabel modelValue = new JLabel(car.getModel());
        modelValue.setFont(valueFont);

        
        JLabel kmLabel = new JLabel("Kilometers:");
        kmLabel.setFont(labelFont);
        kmLabel.setForeground(green);
        JLabel kmValue = new JLabel(String.valueOf(car.getKm()));
        kmValue.setFont(valueFont);

        
        JLabel faultLabel = new JLabel("Fault Status:");
        faultLabel.setFont(labelFont);
        faultLabel.setForeground(green);
        JLabel faultValue = new JLabel(car.getFaultStatus());
        faultValue.setFont(valueFont);

        
        JLabel priceLabel = new JLabel("Sale Price:");
        priceLabel.setFont(labelFont);
        priceLabel.setForeground(green);
        String priceText = salePrice > 0 ? String.format("%.2f ₺", salePrice) : "-";
        JLabel priceValue = new JLabel(priceText);
        priceValue.setFont(valueFont);

        
        infoPanel.add(brandLabel);
        infoPanel.add(brandValue);
        infoPanel.add(modelLabel);
        infoPanel.add(modelValue);
        infoPanel.add(kmLabel);
        infoPanel.add(kmValue);
        infoPanel.add(faultLabel);
        infoPanel.add(faultValue);
        infoPanel.add(priceLabel);
        infoPanel.add(priceValue);

        add(infoPanel, BorderLayout.CENTER);

        
        if (car.getImagePath() != null && !car.getImagePath().isEmpty()) {
            ImageIcon icon = new ImageIcon(car.getImagePath());
            Image scaledImage = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            add(imageLabel, BorderLayout.NORTH);
        }

        
        JButton closeButton = new JButton("Close");
        closeButton.setBackground(green);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
