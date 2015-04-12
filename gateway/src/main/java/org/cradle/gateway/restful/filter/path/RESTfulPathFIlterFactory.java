/**
 * 
 */
package org.cradle.gateway.restful.filter.path;

import org.cradle.gateway.restful.filter.RESTfulFilter;
import org.cradle.gateway.restful.filter.RESTfulFilterFactory;

/**
 * @author 	Sherief Shawky
 * @email 	mcrakens@gmail.com
 * @date 	Nov 10, 2014
 */
public class RESTfulPathFIlterFactory implements RESTfulFilterFactory {

	/* (non-Javadoc)
	 * @see org.cradle.gateway.restful.filter.RESTfulFilterFactory#createFilter()
	 */
	@Override
	public RESTfulFilter createFilter() {
		return new RESTfulPathFilter();
	}

}