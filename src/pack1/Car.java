package pack1;

public class Car {
    private String brand, model, faultStatus;
    private int km;
    private String imagePath;
    private double salePrice;

    public Car(String brand, String model, int km, String faultStatus, String imagePath, double salePrice) {
        this.brand = brand;
        this.model = model;
        this.km = km;
        this.faultStatus = faultStatus;
        this.imagePath = imagePath;
        this.salePrice = salePrice;
    }
 
    public Car(String brand, String model, int km, String faultStatus, String imagePath) {
        this(brand, model, km, faultStatus, imagePath, 0.0);
    }

    
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getKm() { return km; }
    public String getFaultStatus() { return faultStatus; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public double getSalePrice() { return salePrice; }
    public void setSalePrice(double salePrice) { this.salePrice = salePrice; }

	public Object getDamage() {
		
		return null;
	}

	


}

