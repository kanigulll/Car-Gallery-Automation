package pack1;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class LoginPanel extends JPanel {
    private MainFrame parent;

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton goToSignUpButton;

    public LoginPanel(MainFrame parent) {
        this.parent = parent;
        setLayout(null);
        setBackground(Color.WHITE);

        Color greenColor = new Color(34, 139, 34);
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font titleFont = new Font("Arial", Font.BOLD, 20);

        JLabel titleLabel = new JLabel("Car Gallery Login");
        titleLabel.setBounds(300, 160, 250, 30);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(greenColor);
        add(titleLabel);

        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(260, 210, 100, 25);
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(greenColor);
        add(emailLabel);

        
        emailField = new JTextField(20);
        emailField.setBounds(360, 210, 180, 25);
        emailField.setBorder(new LineBorder(greenColor, 1));
        add(emailField);

       
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(260, 250, 100, 25);
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(greenColor);
        add(passwordLabel);

        
        passwordField = new JPasswordField(20);
        passwordField.setBounds(360, 250, 180, 25);
        passwordField.setBorder(new LineBorder(greenColor, 1));
        add(passwordField);

        
        loginButton = new JButton("Login");
        loginButton.setBounds(260, 300, 130, 30);
        loginButton.setBackground(greenColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(labelFont);
        loginButton.setFocusPainted(false);
        add(loginButton);

        
        goToSignUpButton = new JButton("Sign Up");
        goToSignUpButton.setBounds(410, 300, 130, 30);
        goToSignUpButton.setBackground(Color.WHITE);
        goToSignUpButton.setForeground(greenColor);
        goToSignUpButton.setFont(labelFont);
        goToSignUpButton.setBorder(new LineBorder(greenColor, 2));
        goToSignUpButton.setFocusPainted(false);
        add(goToSignUpButton);

        
        loginButton.addActionListener(e -> attemptLogin());
        goToSignUpButton.addActionListener(e -> {
            clearFields();
            parent.showCard("signup");
        });
    }

    private void attemptLogin() {
        String email = emailField.getText().trim().toLowerCase();
        String password = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password.");
            return;
        }

        boolean success = UserDAO.login(email, password);

        if (success) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            parent.showCard("main");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password.");
        }
    }

    public void clearFields() {
        emailField.setText("");
        passwordField.setText("");
        emailField.requestFocus();
    }
}
