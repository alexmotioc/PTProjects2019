package model;

public class Client {
	private int idClient;
	private String name;
	private int age;
	private String address;
	public Client(int idClient, String name, int age, String address) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public Client() {
		
	}
	

	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}

}
