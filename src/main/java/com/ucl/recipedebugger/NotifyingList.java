package com.ucl.recipedebugger;

import java.util.ArrayList;
import java.util.List;

public class NotifyingList<T> extends ArrayList<T> {
	List<INotify> subscribers = new ArrayList<INotify>();
	
	public NotifyingList(List newList) {
		super(newList);
	}
	
	public void AddListener(INotify toAdd){
		subscribers.add(toAdd);		
	}

	@Override
	public boolean add(T e) {
		for (INotify subscriber : subscribers) {
			subscriber.NotifyAdded(e);
		}
		return super.add(e);
	}

	@Override
	public boolean remove(Object o) {
		for (INotify subscriber : subscribers) {
			subscriber.NotifyRemoved(o);
		}
		return super.remove(o);
	}
}
