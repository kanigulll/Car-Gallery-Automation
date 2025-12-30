package pack1;

public class Customer {
    private String name, surname, email, phone;

    public Customer(String name, String surname, String email, String phone) {
        this.name = name; this.surname = surname; this.email = email; this.phone = phone;
    }

    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

	public int getId() {
		
		return 0;
	}
}
