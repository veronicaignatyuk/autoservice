package main.java.bntu.entity;

import java.util.Date;

import main.java.bntu.dao.abstr.Identified;

public class OrderTable implements Identified<Integer> {

	private Integer id;
	private String login;
	private String brand;
	private String model;
	private String master;
	private Date date;
	private String status;
	private String title;
	private Integer cost;

	/**
	 * Constructor with id
	 * 
	 * @param id
	 * @param login
	 * @param brand
	 * @param model
	 * @param master
	 * @param date
	 * @param status
	 * @param title
	 * @param cost
	 */
	public OrderTable(Integer id, String login, String brand, String model,
			String master, Date date, String status, String title, Integer cost) {
		this.id = id;
		this.login = login;
		this.brand = brand;
		this.model = model;
		this.master = master;
		this.date = date;
		this.status = status;
		this.title = title;
		this.cost = cost;
	}

	/**
	 * Constructor without id
	 * 
	 * @param login
	 * @param brand
	 * @param model
	 * @param master
	 * @param date
	 * @param status
	 * @param title
	 * @param cost
	 */
	public OrderTable(String login, String brand, String model, String master,
			Date date, String status, String title, Integer cost) {
		this.login = login;
		this.brand = brand;
		this.model = model;
		this.master = master;
		this.date = date;
		this.status = status;
		this.title = title;
		this.cost = cost;
	}

	/**
	 * 
	 * @return id
	 */
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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * 
	 * @param login
	 *            to set
	 */
	public void setLogin(String login) {
		this.login = login;
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

	/**
	 * 
	 * @return master
	 */
	public String getMaster() {
		return master;
	}

	/**
	 * 
	 * @param master
	 *            to set
	 */
	public void setMaster(String master) {
		this.master = master;
	}

	/**
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 *            to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 *            to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return cost
	 */
	public Integer getCost() {
		return cost;
	}

	/**
	 * 
	 * @param cost
	 *            to set
	 */
	public void setCost(Integer cost) {
		this.cost = cost;
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
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((master == null) ? 0 : master.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		OrderTable other = (OrderTable) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (master == null) {
			if (other.master != null)
				return false;
		} else if (!master.equals(other.master))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
		return "OrderTable [id=" + id + ", login=" + login + ", brand=" + brand
				+ ", model=" + model + ", master=" + master + ", date=" + date
				+ ", status=" + status + ", title=" + title + ", cost=" + cost
				+ "]";
	}

}
