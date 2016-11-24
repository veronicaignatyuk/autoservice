package main.java.bntu.entity;

import main.java.bntu.dao.abstr.Identified;

public class UserRole implements Identified<Integer> {

	private Integer id;
	private Users user;
	private Role role;

	public UserRole() {
	}

	/**
	 * Constructor without id
	 * 
	 * @param user
	 * @param role
	 */
	public UserRole(Users user, Role role) {
		this.user = user;
		this.role = role;
	}

	/**
	 * Constructor with id
	 * 
	 * @param id
	 * @param user
	 * @param role
	 */
	public UserRole(Integer id, Users user, Role role) {
		this.id = id;
		this.user = user;
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
	 * @return role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * 
	 * @param role
	 *            to set
	 */
	public void setRole(Role role) {
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
		UserRole other = (UserRole) obj;
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
		return "UserRole [id=" + id + ", user=" + user + ", role=" + role + "]";
	}

}
