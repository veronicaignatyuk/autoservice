package main.java.bntu.entity;

import main.java.bntu.dao.abstr.Identified;

public class TechService implements Identified<Integer> {

	private Integer id;
	private String title;
	private Integer cost;

	public TechService() {
	}

	/**
	 * Constructor without id
	 * 
	 * @param title
	 * @param cost
	 */
	public TechService(String title, Integer cost) {
		this.title = title;
		this.cost = cost;
	}

	/**
	 * Constructor with id
	 * 
	 * @param id
	 * @param title
	 * @param cost
	 */
	public TechService(Integer id, String title, Integer cost) {
		this.id = id;
		this.title = title;
		this.cost = cost;
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
	 * @return title
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
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TechService other = (TechService) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "TechService [id=" + id + ", title=" + title + ", cost=" + cost
				+ "]";
	}

}
