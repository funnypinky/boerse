package org.funnypinky.boerse.db;

public class DBActionListener implements DBEventListener{

	@Override
	public void DBActionReceived(DBEvent event) {
		switch (event.status()) {
		case ADD:
			System.out.println("Dataset add to DB");
			break;
		case CLOSED:
			System.out.println("DB connection is closed!");
			break;
		case CONNECTED:
			System.out.println("DB is connected!");
			break;
		case ERROR:
			System.err.println("Error in DB connection");
			break;
		case IDLE:
			System.out.println("DB is idle!");
			break;
		case PENDING:
			System.out.println("DB is pending!");
			break;
		case REMOVE:
			System.out.println("Dataset remove from DB");
			break;
		case UPDATED:
			System.out.println("Dataset updated in DB");
			break;
		default:
			break;
		
		}
		
	}
	

}
