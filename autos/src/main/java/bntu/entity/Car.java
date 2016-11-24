package main.java.bntu.entity;

import main.java.bntu.dao.abstr.Identified;

public class Car implements Identified<Integer> {

	private Integer id;
	private Users user;
	private String brand;
	private String model;

	public Car() {

	}

	/**
	 * Constructor without id
	 * 
	 * @param user
	 * @param brand
	 * @param model
	 */
	public Car(Users user, String brand, String model) {
		this.user = user;
		this.brand = brand;
		this.model = model;
	}

	/**
	 * Constructor with id
	 * 
	 * @param id
	 * @param user
	 * @param brand
	 * @param model
	 */
	public Car(Integer id, Users user, String brand, String model) {
		this.id = id;
		this.user = user;
		this.brand = brand;
		this.model = model;
	}

	/**
	 * 
	 * @return user
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * 
	 * @param user
	 *            to set
	 */
	public void setUser(Users user) {
		this.user = user;
	}

	/**
	 * 
	 * @return brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * 
	 * @param brand
	 *            to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * 
	 * @return model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * 
	 * @param model
	 *            to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Car [id=" + id + ", user=" + user + ", brand=" + brand
				+ ", model=" + model + "]";
	}

}
