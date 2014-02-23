package com.infinityappsolutions.server.lib.login;

import java.io.IOException;
import java.util.Arrays;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.eclipse.jetty.jaas.callback.DefaultCallbackHandler;
import org.eclipse.jetty.jaas.callback.ObjectCallback;
import org.eclipse.jetty.jaas.callback.RequestParameterCallback;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.util.security.Password;

public class IASLoginCallbackHandler extends DefaultCallbackHandler {

	private Request _request;

	@Override
	public void setRequest(Request request) {
		this._request = request;
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof NameCallback) {
				((NameCallback) callbacks[i]).setName(getUserName());
			} else if (callbacks[i] instanceof ObjectCallback) {
				((ObjectCallback) callbacks[i]).setObject(getCredential());
			} else if (callbacks[i] instanceof PasswordCallback) {
				if (getCredential() instanceof Password)
					((PasswordCallback) callbacks[i]).setPassword(((Password) getCredential()).toString().toCharArray());
				else if (getCredential() instanceof String) {
					((PasswordCallback) callbacks[i]).setPassword(((String) getCredential()).toCharArray());
				} else
					throw new UnsupportedCallbackException(callbacks[i], "User supplied credentials cannot be converted to char[] for PasswordCallback: try using an ObjectCallback instead");
			} else if (callbacks[i] instanceof RequestParameterCallback) {
				RequestParameterCallback callback = (RequestParameterCallback) callbacks[i];
				callback.setParameterValues(Arrays.asList(_request.getParameterValues(callback.getParameterName())));
			} else
				throw new UnsupportedCallbackException(callbacks[i]);
		}

	}
}
