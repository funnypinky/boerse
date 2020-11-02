package org.funnypinky.boerse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBController {

	private static final DBController dbcontroller = new DBController();
	private static Connection connection;
	private static final String DB_PATH = "database/" + "database.db";

	private DBActionListener listener = new DBActionListener();

	private DBStatus DBAccessStatus = DBStatus.IDLE;

	private List<DBActionListener> DBStateListeners = new ArrayList<DBActionListener>();

	public synchronized void addDbActionListener(DBActionListener l) {
		DBStateListeners.add(l);
	}

	public synchronized void removeDbActionListener(DBActionListener l) {
		DBStateListeners.remove(l);
	}

	private synchronized void fireDbActionEvent(DBStatus AccessStatus) {
		DBAccessStatus = AccessStatus;
		DBEvent status = new DBEvent(this, DBAccessStatus);
		Iterator listeners = DBStateListeners.iterator();
		while (listeners.hasNext()) {
			((DBActionListener) listeners.next()).DBActionReceived(status);
		}
	}

	private static final String createFirstTable = "CREATE TABLE company (\r\n"
			+ "    CompanyName     STRING PRIMARY KEY\r\n" + "                           UNIQUE,\r\n"
			+ "    Waehrung        STRING,\r\n" + "    Land            STRING,\r\n" + "    Sektor          STRING,\r\n"
			+ "    Buchwert        DOUBLE,\r\n" + "    Diviende        DOUBLE,\r\n" + "    Divienderendite DOUBLE\r\n"
			+ ");";

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println("Fehler beim Laden des JDBC-Treibers");
			e.printStackTrace();
		}
	}

	private DBController() {
		this.addDbActionListener(listener);
	}

	public static DBController getInstance() {
		return dbcontroller;
	}

	public void initDBConnection() {
		try {
			if (connection != null)
				return;
			System.out.println("Creating Connection to Database...");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
			connection.setAutoCommit(false);
			if (!connection.isClosed())
				this.fireDbActionEvent(DBStatus.CONNECTED);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					if (!connection.isClosed() && connection != null) {
						connection.close();
						if (connection.isClosed())
							fireDbActionEvent(DBStatus.CLOSED);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
