package main.java.bntu.entity;

import main.java.bntu.dao.abstr.Identified;

public class OrderTechServ implements Identified<Integer> {

	private Integer id;
	private Order order;
	private TechService techServ;

	public OrderTechServ() {
	}

	/**
	 * Constructor without id
	 * 
	 * @param order
	 * @param techServ
	 */
	public OrderTechServ(Order order, TechService techServ) {
		this.order = order;
		this.techServ = techServ;
	}

	/**
	 * Constructor with id
	 * 
	 * @param id
	 * @param order
	 * @param techServ
	 */
	public OrderTechServ(Integer id, Order order, TechService techServ) {
		this.id = id;
		this.order = order;
		this.techServ = techServ;
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
	 * @return order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * 
	 * @param order
	 *            to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * 
	 * @return techService
	 */
	public TechService getOperations() {
		return techServ;
	}

	/**
	 * 
	 * @param techServ
	 *            to set
	 */
	public void setOperations(TechService techServ) {
		this.techServ = techServ;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((techServ == null) ? 0 : techServ.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		OrderTechServ other = (OrderTechServ) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (techServ == null) {
			if (other.techServ != null)
				return false;
		} else if (!techServ.equals(other.techServ))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
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
		return "OrderTechServ [id=" + id + ", order=" + order + ", techServ="
				+ techServ + "]";
	}
}
