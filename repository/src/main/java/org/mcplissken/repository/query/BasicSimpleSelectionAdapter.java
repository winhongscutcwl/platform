/**
 * 
 */
package org.mcplissken.repository.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 	Sherief Shawky
 * @email 	sherif.shawki@mubasher.info
 * @date 	Aug 31, 2014
 */
public abstract class BasicSimpleSelectionAdapter<A, T, O> implements SimpleSelectionAdapter<A> {

	private ArrayList<T> criteria;

	/**
	 * 
	 */
	public BasicSimpleSelectionAdapter() {

		criteria = new ArrayList<>();
	}

	/* (non-Javadoc)
	 * @see org.mcplissken.repository.query.SimpleSelectionAdapter#eq(java.lang.String, java.lang.Object)
	 */
	@Override
	public SimpleSelectionAdapter<A> eq(String name, Object value) {

		criteria.add(doEq(name, value));

		return this;
	}

	/* (non-Javadoc)
	 * @see org.mcplissken.repository.query.SimpleSelectionAdapter#gt(java.lang.String, java.lang.Object)
	 */
	@Override
	public SimpleSelectionAdapter<A> gt(String name, Object value) {

		criteria.add(doGt(name, value));

		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.mcplissken.repository.query.SimpleSelectionAdapter#gte(java.lang.String, java.lang.Object)
	 */
	@Override
	public SimpleSelectionAdapter<A> gte(String name, Object value) {
		
		criteria.add(doGte(name, value));

		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.mcplissken.repository.query.SimpleSelectionAdapter#and()
	 */
	@Override
	public SimpleSelectionAdapter<A> and() {

		doAnd(criteria.iterator());

		criteria.clear();

		return this;
	}

	/* (non-Javadoc)
	 * @see org.mcplissken.repository.query.SimpleSelectionAdapter#or()
	 */
	@Override
	public SimpleSelectionAdapter<A> or() {

		doOr(criteria.iterator());

		criteria.clear();

		return this;

	}

	/* (non-Javadoc)
	 * @see org.mcplissken.repository.query.SimpleSelectionAdapter#page(int)
	 */
	@Override
	public SimpleSelectionAdapter<A> page(int size) {

		if(criteria.size() > 0){
			doLimit(criteria.get(0), size);
		} else {
			doLimit(size);
		}

		return this;
	}

	/* (non-Javadoc)
	 * @see org.mcplissken.repository.query.SimpleSelectionAdapter#orderBy(boolean, java.lang.String[])
	 */
	@Override
	public SimpleSelectionAdapter<A> orderBy(boolean asc, String... columns) {

		ArrayList<O> orderings = new ArrayList<O>(columns.length);

		for(int i = 0; i < columns.length; i++){
			orderings.add(order(asc, columns[i])); 
		}
		
		orderBy(orderings);
		
		return this;
	}

	@Override
	public SimpleSelectionAdapter<A> lt(String name, Object value){
		
		criteria.add(doLt(name, value));
		
		return this;
	}
	
	protected Iterator<T> criteria(){
		
		return criteria.iterator();
	}
	
	protected abstract T doEq(String name, Object value);

	protected abstract T doGt(String name, Object value);

	protected abstract T doGte(String name, Object value);
	
	protected abstract O order(boolean asc, String column);
	
	protected abstract void orderBy(List<O> orderings);
	
	protected abstract void doAnd(Iterator<T> clauses);

	protected abstract void doOr(Iterator<T> clauses);

	protected abstract void doLimit(T clause, int size);

	protected abstract void doLimit(int size);
	
	protected abstract T doLt(String name, Object value);
}