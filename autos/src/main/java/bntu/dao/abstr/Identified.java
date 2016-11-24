package main.java.bntu.dao.abstr;

import java.io.Serializable;

/**
 * Identified pk as Serializable
 * 
 * @author Veronika
 *
 * @param <PK>
 */

//it should be CLASS!!!

public interface Identified<PK extends Serializable> {
	/**
	 * 
	 * @return
	 */
	public PK getId();
}
