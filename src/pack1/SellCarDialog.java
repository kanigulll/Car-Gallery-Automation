package pack1;

import javax.swing.*;
import java.awt.*;

public class SellCarDialog extends JDialog {
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField priceField;

    private JButton sellButton;
    private JButton cancelButton;

    private Customer customer;
    private double salePrice;

    public SellCarDialog(JFrame parent) {
        super(parent, "Car Sale - Customer Information", true);
        setLayout(null);
        setSize(450, 400);
        setLocationRelativeTo(parent);

        
        Color background = new Color(245, 245, 245);
        Color labelColor = new Color(0, 102, 102);
        Color buttonGreen = new Color(0, 153, 76);
        Color buttonGray = new Color(120, 120, 120);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        getContentPane().setBackground(background);

        JLabel nameLabel = new JLabel("Name:");
        JLabel surnameLabel = new JLabel("Surname:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel phoneLabel = new JLabel("Phone:");
        JLabel priceLabel = new JLabel("Sale Price:");

        JLabel[] labels = {nameLabel, surnameLabel, emailLabel, phoneLabel, priceLabel};
        for (JLabel label : labels) {
            label.setFont(labelFont);
            label.setForeground(labelColor);
        }

        nameField = new JTextField(20);
        surnameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        priceField = new JTextField(20);

        JTextField[] fields = {nameField, surnameField, emailField, phoneField, priceField};
        for (JTextField field : fields) {
            field.setFont(fieldFont);
            field.setBackground(Color.WHITE);
            field.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        }

        sellButton = new JButton("Sell");
        sellButton.setBackground(buttonGreen);
        sellButton.setForeground(Color.WHITE);
        sellButton.setFocusPainted(false);
        sellButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(buttonGray);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        
        nameLabel.setBounds(40, 30, 100, 25);
        nameField.setBounds(160, 30, 220, 25);

        surnameLabel.setBounds(40, 70, 100, 25);
        surnameField.setBounds(160, 70, 220, 25);

        emailLabel.setBounds(40, 110, 100, 25);
        emailField.setBounds(160, 110, 220, 25);

        phoneLabel.setBounds(40, 150, 100, 25);
        phoneField.setBounds(160, 150, 220, 25);

        priceLabel.setBounds(40, 190, 100, 25);
        priceField.setBounds(160, 190, 220, 25);

        sellButton.setBounds(90, 260, 120, 35);
        cancelButton.setBounds(230, 260, 120, 35);

        
        add(nameLabel);
        add(nameField);
        add(surnameLabel);
        add(surnameField);
        add(emailLabel);
        add(emailField);
        add(phoneLabel);
        add(phoneField);
        add(priceLabel);
        add(priceField);
        add(sellButton);
        add(cancelButton);

        sellButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String priceText = priceField.getText().trim();

            if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || phone.isEmpty() || priceText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all customer information and the sale price.");
                return;
            }

            try {
                salePrice = Double.parseDouble(priceText);
                if (salePrice < 0) {
                    JOptionPane.showMessageDialog(this, "Sale price cannot be negative.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid sale price.");
                return;
            }

            customer = new Customer(name, surname, email, phone);
            dispose();
        });

        cancelButton.addActionListener(e -> {
            customer = null;
            dispose();
        });
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame app = new MainFrame();
            app.setVisible(true);
        });
    }
}





