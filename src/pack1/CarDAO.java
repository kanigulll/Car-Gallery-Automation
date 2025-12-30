package pack1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    
    public static void addCar(Car car) {
        String sql = "INSERT INTO cars (brand, model, km, fault_status, image_path, sale_price) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getKm());
            stmt.setString(4, car.getFaultStatus());
            stmt.setString(5, car.getImagePath());
            stmt.setDouble(6, car.getSalePrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static boolean deleteCar(Car car) {
        String sql = "DELETE FROM cars WHERE brand = ? AND model = ? AND km = ? LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getKm());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   
    public static List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Car car = new Car(
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getInt("km"),
                    rs.getString("fault_status"),
                    rs.getString("image_path"),
                    rs.getDouble("sale_price")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    
}

