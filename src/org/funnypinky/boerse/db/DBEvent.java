package org.funnypinky.boerse.db;

import java.util.EventObject;

public class DBEvent extends EventObject{
	
	private DBStatus status;
	
	public DBEvent (Object source, DBStatus status) {
		super(source);
		this.status = status;
	}

	public DBStatus status() {
		return this.status;
	}
}
