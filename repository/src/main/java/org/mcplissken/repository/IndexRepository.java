/**
 * 
 */
package org.mcplissken.repository;

import java.util.List;

import org.mcplissken.repository.index.CoreAnnotationIsNotPresent;
import org.mcplissken.repository.index.IndexException;
import org.mcplissken.repository.index.IndexPorter;
import org.mcplissken.repository.index.IndexQueryAdapter;
import org.mcplissken.repository.key.RestSearchKey;

/**
 * @author 	Sherief Shawky
 * @email 	mcrakens@gmail.com
 * @date 	Nov 19, 2014
 */
public interface IndexRepository {

	public enum CoreLanguage {

		ENGLISH("en"),  ARABIC("ar");

		public String value;

		CoreLanguage(String value){
			this.value = value;
		}
	}
	
	public <T> List<T> readIndex(RestSearchKey key) throws IndexException;
	
	public <T> void writeIndex(T model) throws IndexException;
	
	public <T> void writeIndex(List<T> models) throws IndexException;
	
	public <T> void deleteIndex(T model) throws IndexException;
	
	public <T> void deleteIndex(List<T> model) throws IndexException;
	
	public <T> IndexPorter<T> indexPorter(Class<T> type) throws CoreAnnotationIsNotPresent;

	public <T> IndexPorter<T> indexPorter(Class<T> type, CoreLanguage language) throws CoreAnnotationIsNotPresent;

	public <T> IndexQueryAdapter<T> queryAdapter(String coreName);

	public <T> void registerIndexMapper(String modelName, BasicRowMapper<T> mapper);
}