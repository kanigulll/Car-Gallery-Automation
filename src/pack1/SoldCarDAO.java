package pack1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoldCarDAO {

    
    public static int addSoldCar(SoldCar soldCar) {
        String sql = "INSERT INTO sold_cars (brand, model, km, fault_status, customer_name, customer_surname, customer_email, customer_phone, sale_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            Car car = soldCar.getCar();
            Customer cust = soldCar.getCustomer();

            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getKm());
            stmt.setString(4, car.getFaultStatus());
            stmt.setString(5, cust.getName());
            stmt.setString(6, cust.getSurname());
            stmt.setString(7, cust.getEmail());
            stmt.setString(8, cust.getPhone());
            stmt.setDouble(9, soldCar.getSalePrice());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Satış eklenemedi, etkilenen satır yok.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    soldCar.setId(generatedId); 
                    return generatedId;
                } else {
                    throw new SQLException("Satış id'si alınamadı.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static List<SoldCar> getAllSoldCars() {
        List<SoldCar> list = new ArrayList<>();
        String sql = "SELECT * FROM sold_cars";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Car car = new Car(
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getInt("km"),
                    rs.getString("fault_status"),
                    "", 
                    0 
                );
                Customer cust = new Customer(
                    rs.getString("customer_name"),
                    rs.getString("customer_surname"),
                    rs.getString("customer_email"),
                    rs.getString("customer_phone")
                );
                SoldCar soldCar = new SoldCar(car, cust, rs.getDouble("sale_price"));
                soldCar.setId(rs.getInt("id")); 
                list.add(soldCar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void deleteSoldCarById(int soldCarId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM sold_cars WHERE id = ?")) {
            stmt.setInt(1, soldCarId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteSoldCar(SoldCar soldCar) {
        String sql = "DELETE FROM sold_cars WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, soldCar.getId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}


