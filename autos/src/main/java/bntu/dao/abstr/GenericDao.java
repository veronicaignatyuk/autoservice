package main.java.bntu.dao.abstr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import main.java.bntu.dao.exception.ConnectionPoolException;
import main.java.bntu.dao.exception.PersistException;

/**
 * Interface with generic methods for all entities
 * 
 * @author Veronika
 *
 * @param <T>
 * @param <PK>
 */

public interface GenericDao<T extends Identified<Integer>> {
	public int getId();

	/**
	 * Create new entity
	 * 
	 * @param transientObject
	 * @return list of entities
	 * @throws PersistException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ConnectionPoolException
	 */

	// int
	int create(T transientObject) throws PersistException,
			FileNotFoundException, IOException, ConnectionPoolException;

	/**
	 * get entity by key
	 * 
	 * @param key
	 * @return list of entities
	 * @throws PersistException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ConnectionPoolException
	 */
	T getByPK(int key) throws PersistException, FileNotFoundException,
			IOException, ConnectionPoolException;

	/**
	 * update entity
	 * 
	 * @param transientObject
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	void update(T transientObject) throws PersistException,
			ConnectionPoolException;

	/**
	 * delete entity
	 * 
	 * @param key
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	void delete(int key) throws PersistException, ConnectionPoolException;

	/**
	 * get all list of entuties
	 * 
	 * @return list of entities
	 * @throws PersistException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ConnectionPoolException
	 */
	List<T> getAll() throws PersistException, FileNotFoundException,
			IOException, ConnectionPoolException;

}
