package com.infinityappsolutions.webdesigner.exceptions;

import java.sql.SQLException;

public class DBException extends WebDesignerException {
	private static final long serialVersionUID = -6554118510590118376L;
	private SQLException sqlException = null;

	public DBException(SQLException e) {
		super("A database exception has occurred. Please see the log in the console for stacktrace");
		this.sqlException = e;
	}

	/**
	 * @return The SQL Exception that was responsible for this error.
	 */
	public SQLException getSQLException() {
		return sqlException;
	}

	@Override
	public String getExtendedMessage() {
		if (sqlException != null)
			return sqlException.getMessage();
		else
			return super.getExtendedMessage();
	}
}
