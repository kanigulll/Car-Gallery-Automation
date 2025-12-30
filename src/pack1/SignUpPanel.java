package pack1;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class SignUpPanel extends JPanel {
    private MainFrame parent;

    private JTextField nameField, surnameField, emailField;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JButton backToLoginButton;

    public SignUpPanel(MainFrame parent) {
        this.parent = parent;
        setLayout(null);
        setBackground(Color.WHITE);

        Color greenColor = new Color(34, 139, 34); 
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font titleFont = new Font("Arial", Font.BOLD, 20);

        JLabel titleLabel = new JLabel("New User Registration");
        titleLabel.setBounds(290, 120, 250, 30);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(greenColor);
        add(titleLabel);

        JLabel nameLabel = new JLabel("First Name:");
        nameLabel.setBounds(250, 170, 100, 25);
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(greenColor);
        add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(350, 170, 180, 25);
        nameField.setBorder(new LineBorder(greenColor, 1));
        add(nameField);

        JLabel surnameLabel = new JLabel("Last Name:");
        surnameLabel.setBounds(250, 210, 100, 25);
        surnameLabel.setFont(labelFont);
        surnameLabel.setForeground(greenColor);
        add(surnameLabel);

        surnameField = new JTextField(20);
        surnameField.setBounds(350, 210, 180, 25);
        surnameField.setBorder(new LineBorder(greenColor, 1));
        add(surnameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(250, 250, 100, 25);
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(greenColor);
        add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(350, 250, 180, 25);
        emailField.setBorder(new LineBorder(greenColor, 1));
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(250, 290, 100, 25);
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(greenColor);
        add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(350, 290, 180, 25);
        passwordField.setBorder(new LineBorder(greenColor, 1));
        add(passwordField);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(250, 340, 130, 30);
        signUpButton.setBackground(greenColor);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(labelFont);
        signUpButton.setFocusPainted(false);
        add(signUpButton);

        backToLoginButton = new JButton("Back");
        backToLoginButton.setBounds(400, 340, 130, 30);
        backToLoginButton.setBackground(Color.WHITE);
        backToLoginButton.setForeground(greenColor);
        backToLoginButton.setFont(labelFont);
        backToLoginButton.setBorder(new LineBorder(greenColor, 2));
        backToLoginButton.setFocusPainted(false);
        add(backToLoginButton);

        
        signUpButton.addActionListener(e -> attemptSignUp());
        backToLoginButton.addActionListener(e -> {
            clearFields();
            parent.showCard("login");
        });
    }

    private void attemptSignUp() {
        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        User newUser = new User(name, surname, email, password);
        boolean success = UserDAO.register(newUser);

        if (success) {
            JOptionPane.showMessageDialog(this, "Registration successful! You can log in now.");
            clearFields();
            parent.showCard("login");
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed! Email might already be registered.");
        }
    }

    private void clearFields() {
        nameField.setText("");
        surnameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        nameField.requestFocus();
    }

}


