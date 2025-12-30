package pack1;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private List<User> users = new ArrayList<>();
    private User loggedInUser = null;

    private LoginPanel loginPanel;
    private SignUpPanel signUpPanel;
    private MainPanel panelAfterLogin;

    
    private final Color bgBlue = new Color(230, 240, 255);

    public MainFrame() {
        setTitle("Car Gallery Management System");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loginPanel = new LoginPanel(this);
        signUpPanel = new SignUpPanel(this);
        panelAfterLogin = new MainPanel(this);

        mainPanel.add(loginPanel, "login");
        mainPanel.add(signUpPanel, "signup");
        mainPanel.add(panelAfterLogin, "main");

        mainPanel.setBackground(bgBlue);   

        add(mainPanel);
        cardLayout.show(mainPanel, "login");
    }

    public List<User> getUsers() {
        return users;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public MainPanel getPanelAfterLogin() {
        return panelAfterLogin;
    }

    public void showCard(String name) {
        cardLayout.show(mainPanel, name);
    }
}
