package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Pattern(regexp = "^[A-Z]\\w+")
	@Column(nullable = false)
	private String firstName;

	@NotBlank
	@Pattern(regexp = "^[A-Z]\\w+")
	@Column(nullable = false)
	private String lastName;

	@NotBlank
	@Pattern(regexp = "\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")
	@Column(nullable = false)
	private String address;

	@Pattern(regexp = "[0-9]{10}")
	@Column(nullable = false)
	private Integer phone;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8}$")
	@Column(nullable = false)
	private String password;

	@Min(1)
	@Column(nullable = false)
	private Double initialDeposit;

	@Column(nullable = false)
	private Double balance;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Transaction> transactions = new ArrayList<>();

	public Customer() {

	}

	public Customer(Integer id, @NotBlank @Pattern(regexp = "^[A-Z]\\w+") String firstName,
			@NotBlank @Pattern(regexp = "^[A-Z]\\w+") String lastName,
			@NotBlank @Pattern(regexp = "\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)") String address,
			@Pattern(regexp = "[0-9]{10}") Integer phone,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8}$") String password,
			@Min(1) Double initialDeposit, Double balance) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.password = password;
		this.initialDeposit = initialDeposit;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getInitialDeposit() {
		return initialDeposit;
	}

	public void setInitialDeposit(Double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", phone=" + phone + ", password=" + password + ", initialDeposit=" + initialDeposit + ", balance="
				+ balance + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, balance, firstName, id, initialDeposit, lastName, password, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && Objects.equals(balance, other.balance)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(initialDeposit, other.initialDeposit) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(phone, other.phone);
	}
	
	

}
