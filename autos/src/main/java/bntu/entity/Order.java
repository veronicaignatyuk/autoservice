package main.java.bntu.entity;

import main.java.bntu.dao.abstr.Identified;

public class Order implements Identified<Integer> {

	private Integer id;
	private Users user;
	private Car car;
	private java.sql.Date dateOrd;
	private String status;
	private Users master;

	public Order() {
	}

	/**
	 * Controller with id
	 * 
	 * @param id
	 * @param user
	 * @param car
	 * @param dateOrd
	 * @param status
	 * @param master
	 */
	public Order(Integer id, Users user, Car car, java.sql.Date dateOrd,
			String status, Users master) {
		this.id = id;
		this.user = user;
		this.car = car;
		this.dateOrd = dateOrd;
		this.status = status;
		this.master = master;
	}

	/**
	 * Controller without id
	 * 
	 * @param user
	 * @param car
	 * @param dateOrd
	 * @param status
	 * @param master
	 */
	public Order(Users user, Car car, java.sql.Date dateOrd, String status,
			Users master) {
		this.user = user;
		this.car = car;
		this.dateOrd = dateOrd;
		this.status = status;
		this.master = master;
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
	 * @return car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * 
	 * @param car
	 *            to set
	 */
	public void setCar(Car car) {
		this.car = car;
	}

	/**
	 * 
	 * @return date
	 */
	public java.sql.Date getDateOrd() {
		return dateOrd;
	}

	/**
	 * 
	 * @param dateOrd
	 *            to set
	 */
	public void setDateOrd(java.sql.Date dateOrd) {
		this.dateOrd = dateOrd;
	}

	/**
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 *            to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return master
	 */
	public Users getMaster() {
		return master;
	}

	/**
	 * 
	 * @param master
	 *            to set
	 */
	public void setMaster(Users master) {
		this.master = master;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", car=" + car
				+ ", dateOrd=" + dateOrd + ", status=" + status + ", master="
				+ master + "]";
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
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((dateOrd == null) ? 0 : dateOrd.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((master == null) ? 0 : master.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Order other = (Order) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (dateOrd == null) {
			if (other.dateOrd != null)
				return false;
		} else if (!dateOrd.equals(other.dateOrd))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (master == null) {
			if (other.master != null)
				return false;
		} else if (!master.equals(other.master))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
