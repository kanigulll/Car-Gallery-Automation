package pack1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MainPanel extends JPanel {
    private JTable carTable;
    private DefaultTableModel carTableModel;

    private JTable soldCarTable;
    private DefaultTableModel soldCarTableModel;

    private JButton addCarButton;
    private JButton sellCarButton;
    private JButton carInfoButton;
    private JButton deleteCarButton;
    private JButton logoutButton;

    private JTextField searchField;

    private List<Car> cars = new ArrayList<>();
    private List<SoldCar> soldCars = new ArrayList<>();

    private JLabel welcomeLabel;
    private JLabel soldCarLabel;

    private MainFrame mainFrame;

    private JScrollPane carTableScrollPane;
    private JScrollPane soldCarScrollPane;

    private JPanel tablePanel;

    
    private final Color mainDark = new Color(38, 70, 83);
    private final Color softYellow = new Color(233, 196, 106);
    private final Color bgBlue = new Color(230, 240, 255);
    private final Color tableBorder = new Color(220, 220, 220);

    public MainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        initializeComponents();
        layoutComponents();
        setupListeners();

        cars = CarDAO.getAllCars();
        soldCars = SoldCarDAO.getAllSoldCars();

        refreshCarTable();
        refreshSoldCarTable();
    }

    private void initializeComponents() {
        setBackground(bgBlue);

        Font headerFont = new Font("SansSerif", Font.BOLD, 16);
        Font defaultFont = new Font("SansSerif", Font.PLAIN, 14);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);

        welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(headerFont);
        welcomeLabel.setForeground(mainDark);

        searchField = new JTextField(20);
        searchField.setFont(defaultFont);

        addCarButton = new JButton("Add Car");
        sellCarButton = new JButton("Sell Car");
        carInfoButton = new JButton("Car Info");
        deleteCarButton = new JButton("Delete Car");
        logoutButton = new JButton("Logout");

        JButton[] buttons = {addCarButton, sellCarButton, carInfoButton, deleteCarButton};
        for (JButton b : buttons) {
            b.setBackground(mainDark);
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setFont(buttonFont);
            b.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        }

        logoutButton.setBackground(new Color(143, 45, 45));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setFont(buttonFont);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

       
        carTableModel = new DefaultTableModel(
                new Object[]{"Brand", "Model", "KM", "Condition", "Sale Price (₺)"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        carTable = new JTable(carTableModel);
        carTable.setFillsViewportHeight(true);
        carTable.setGridColor(tableBorder);
        carTable.setShowGrid(true);
        carTable.setRowHeight(25);
        carTable.setBackground(bgBlue);
        carTable.setSelectionBackground(softYellow);
        carTable.setSelectionForeground(Color.BLACK);

        DefaultTableCellRenderer carCellRenderer = new DefaultTableCellRenderer();
        carCellRenderer.setFont(new Font("Verdana", Font.BOLD, 15));
        carCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < carTable.getColumnCount(); i++) {
            carTable.getColumnModel().getColumn(i).setCellRenderer(carCellRenderer);
        }

        carTable.getTableHeader().setPreferredSize(new Dimension(0, 36));
        carTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));

        carTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(mainDark);
                label.setForeground(Color.WHITE);
                label.setFont(new Font("SansSerif", Font.BOLD, 18));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, tableBorder));
                return label;
            }
        });

        carTableScrollPane = new JScrollPane(carTable);
        carTableScrollPane.setBorder(BorderFactory.createLineBorder(tableBorder, 1));
        carTableScrollPane.getViewport().setBackground(bgBlue);

        soldCarTableModel = new DefaultTableModel(
                new Object[]{"Brand", "Model", "KM", "Condition", "Customer Name", "Customer Surname", "Email", "Phone", "Sale Price"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        soldCarTable = new JTable(soldCarTableModel);
        soldCarTable.setFillsViewportHeight(true);
        soldCarTable.setGridColor(tableBorder);
        soldCarTable.setShowGrid(true);
        soldCarTable.setRowHeight(25);
        soldCarTable.setBackground(bgBlue);
        soldCarTable.setSelectionBackground(softYellow);
        soldCarTable.setSelectionForeground(Color.BLACK);

        DefaultTableCellRenderer soldCellRenderer = new DefaultTableCellRenderer();
        soldCellRenderer.setFont(new Font("Verdana", Font.BOLD, 15));
        soldCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < soldCarTable.getColumnCount(); i++) {
            soldCarTable.getColumnModel().getColumn(i).setCellRenderer(soldCellRenderer);
        }

        soldCarTable.getTableHeader().setPreferredSize(new Dimension(0, 36));
        soldCarTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));

        soldCarTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(mainDark);
                label.setForeground(Color.WHITE);
                label.setFont(new Font("SansSerif", Font.BOLD, 18));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, tableBorder));
                return label;
            }
        });

        soldCarScrollPane = new JScrollPane(soldCarTable);
        soldCarScrollPane.setPreferredSize(new Dimension(0, 150));
        soldCarScrollPane.setBorder(BorderFactory.createLineBorder(tableBorder, 1));
        soldCarScrollPane.getViewport().setBackground(bgBlue);

        soldCarLabel = new JLabel("Sold Cars List");
        soldCarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        soldCarLabel.setFont(headerFont);
        soldCarLabel.setForeground(mainDark);

        tablePanel = new JPanel(new GridLayout(2, 1));
        tablePanel.setBackground(bgBlue);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(bgBlue);
        topPanel.add(welcomeLabel);
        topPanel.add(new JLabel("Search Car:"));
        topPanel.add(searchField);
        topPanel.add(addCarButton);
        topPanel.add(sellCarButton);
        topPanel.add(carInfoButton);
        topPanel.add(deleteCarButton);
        topPanel.add(logoutButton);
        add(topPanel, BorderLayout.NORTH);

        JPanel soldCarPanel = new JPanel(new BorderLayout());
        soldCarPanel.setBackground(bgBlue);
        soldCarPanel.add(soldCarLabel, BorderLayout.NORTH);
        soldCarPanel.add(soldCarScrollPane, BorderLayout.CENTER);

        tablePanel.add(carTableScrollPane);
        tablePanel.add(soldCarPanel);
        add(tablePanel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        addCarButton.addActionListener(e -> {
            AddCarDialog dialog = new AddCarDialog(mainFrame);
            dialog.setVisible(true);
            Car newCar = dialog.getCar();
            if (newCar != null) {
                CarDAO.addCar(newCar);
                cars = CarDAO.getAllCars();
                refreshCarTable();
            }
        });

        sellCarButton.addActionListener(e -> {
            int selectedRow = carTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(MainPanel.this, "Please select a car to sell.");
                return;
            }

            Car selectedCar = cars.get(selectedRow);
            SellCarDialog sellDialog = new SellCarDialog(mainFrame);
            sellDialog.setVisible(true);
            Customer customer = sellDialog.getCustomer();

            if (customer != null) {
                double price = sellDialog.getSalePrice();
                SoldCar soldCar = new SoldCar(selectedCar, customer, price);

                SoldCarDAO.addSoldCar(soldCar);
                CarDAO.deleteCar(selectedCar);

                cars = CarDAO.getAllCars();
                soldCars = SoldCarDAO.getAllSoldCars();

                refreshCarTable();
                refreshSoldCarTable();
            }
        });

        carInfoButton.addActionListener(e -> {
            int selectedRow = carTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(MainPanel.this, "Please select a car to view details.");
                return;
            }
            Car selectedCar = cars.get(selectedRow);
            double salePrice = selectedCar.getSalePrice();

            CarDetailDialog detailDialog = new CarDetailDialog(mainFrame, selectedCar, salePrice);
            detailDialog.setVisible(true);
        });

        deleteCarButton.addActionListener(e -> {
            int selectedCarRow = carTable.getSelectedRow();
            int selectedSoldCarRow = soldCarTable.getSelectedRow();

            if (selectedCarRow == -1 && selectedSoldCarRow == -1) {
                JOptionPane.showMessageDialog(MainPanel.this, "Please select a car (in either table) to delete.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(MainPanel.this,
                    "Are you sure you want to delete the selected car?", "Confirm",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (selectedCarRow != -1) {
                    Car selectedCar = cars.get(selectedCarRow);
                    boolean success = CarDAO.deleteCar(selectedCar);
                    if (success) {
                        cars = CarDAO.getAllCars();
                        refreshCarTable();
                    } else {
                        JOptionPane.showMessageDialog(MainPanel.this, "Failed to delete the car.");
                    }
                } else {
                    SoldCar selectedSoldCar = soldCars.get(selectedSoldCarRow);
                    boolean success = SoldCarDAO.deleteSoldCar(selectedSoldCar);
                    if (success) {
                        soldCars = SoldCarDAO.getAllSoldCars();
                        refreshSoldCarTable();
                    } else {
                        JOptionPane.showMessageDialog(MainPanel.this, "Failed to delete the sold car record.");
                    }
                }
            }
        });

        logoutButton.addActionListener(e -> mainFrame.showCard("login"));

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                filterCarTable(searchField.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                filterCarTable(searchField.getText());
            }

            public void changedUpdate(DocumentEvent e) {
                filterCarTable(searchField.getText());
            }
        });

        carTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && carTable.getSelectedRow() != -1) {
                soldCarTable.clearSelection();
            }
        });

        soldCarTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && soldCarTable.getSelectedRow() != -1) {
                carTable.clearSelection();
            }
        });
    }

    private void filterCarTable(String query) {
        String lowerQuery = query.toLowerCase();
        carTableModel.setRowCount(0);
        for (Car car : cars) {
            if (car.getBrand().toLowerCase().contains(lowerQuery) || car.getModel().toLowerCase().contains(lowerQuery)) {
                carTableModel.addRow(new Object[]{
                        car.getBrand(), car.getModel(), car.getKm(), car.getFaultStatus(), car.getSalePrice() 
                });
            }
        }
    }

    private void refreshCarTable() {
        carTableModel.setRowCount(0);
        for (Car car : cars) {
            carTableModel.addRow(new Object[]{
                    car.getBrand(), car.getModel(), car.getKm(), car.getFaultStatus(), car.getSalePrice() 
            });
        }
    }

    private void refreshSoldCarTable() {
        soldCarTableModel.setRowCount(0);
        for (SoldCar soldCar : soldCars) {
            soldCarTableModel.addRow(new Object[]{
                    soldCar.getCar().getBrand(),
                    soldCar.getCar().getModel(),
                    soldCar.getCar().getKm(),
                    soldCar.getCar().getFaultStatus(),
                    soldCar.getCustomer().getName(),
                    soldCar.getCustomer().getSurname(),
                    soldCar.getCustomer().getEmail(),
                    soldCar.getCustomer().getPhone(),
                    soldCar.getSalePrice()
            });
        }
    }

    public void updateWelcomeMessage(String username) {
        welcomeLabel.setText("Welcome, " + username + "!");
    }
}
