package main.java.bntu.entity;

import main.java.bntu.dao.abstr.Identified;

public class Role implements Identified<Integer> {

	private Integer id;
	private String role;

	public Role() {
	}

	/**
	 * Constructor without id
	 * 
	 * @param role
	 */
	public Role(String role) {
		this.role = role;
	}

	/**
	 * Constructor with id
	 * 
	 * @param id
	 * @param role
	 */
	public Role(Integer id, String role) {
		this.id = id;
		this.role = role;
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
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * 
	 * @param role
	 *            to set
	 */
	public void setRole(String role) {
		this.role = role;
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
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
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
		return "Role [id=" + id + ", role=" + role + "]";
	}

}
