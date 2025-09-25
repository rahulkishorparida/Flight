package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "girl")
public class Girl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private Long phone;
	private String email;


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
}

//CREATE TABLE cart (
//	    id INT AUTO_INCREMENT PRIMARY KEY,
//	    user_id INT NOT NULL,
//	    product_id INT NOT NULL,
//	    quantity INT NOT NULL,
//	    CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES users(id),
//	    CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES product(id)
//	);

