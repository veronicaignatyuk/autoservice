package main.java.bntu.entity;

import main.java.bntu.dao.abstr.Identified;

public class Users implements Identified<Integer> {

	private Integer id;
	private String login;
	private String password;

	public Users() {
	}

	/**
	 * Controller without id
	 * 
	 * @param login
	 * @param password
	 */
	public Users(String login, String password) {
		this.login = login;
		this.password = password;
	}

	/**
	 * Controller with id
	 * 
	 * @param id
	 * @param login
	 * @param password
	 */
	public Users(Integer id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 *            to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		Users other = (Users) obj;
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ "]";
	}

}
