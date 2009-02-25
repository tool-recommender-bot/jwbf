/*
 * Copyright 2007 Thomas Stock.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Contributors:
 * 
 */
package net.sourceforge.jwbf.actions.mw.util;

import net.sourceforge.jwbf.actions.ContentProcessable;
import net.sourceforge.jwbf.actions.mw.HttpAction;

import org.apache.commons.httpclient.Cookie;

/**
 * @author Thomas Stock
 *
 */
public abstract class MWAction implements ContentProcessable {

	private boolean hasMore = true;
	

	/**
	 * 
	 * 
	 */
	public MWAction() {


	}



	public boolean hasMoreMessages() {
		final boolean b = hasMore;
		hasMore = false;
		return b;
	}

	/**
	 * @param s
	 *            the returning text
	 * @param hm
	 *            the method object
	 * @return the returning text
	 * @throws ProcessException on processing problems
	 * 
	 */
	public String processReturningText(final String s, final HttpAction hm) throws ProcessException {
		return processAllReturningText(s);
	}

	/**
	 * @param cs
	 *            a
	 * @param hm
	 *            the method object
	 * @throws CookieException
	 *             never
	 * 
	 */
	public void validateReturningCookies(Cookie[] cs, HttpAction hm)
			throws CookieException {
		validateAllReturningCookies(cs);

	}

	/**
	 * @param cs
	 *            a
	 * @throws CookieException
	 *             never
	 * 
	 */
	public void validateAllReturningCookies(final Cookie[] cs)
			throws CookieException {
		// do nothing
	}

	/**
	 * @param s
	 *            the returning text
	 * @return the returning text
	 * @throws ProcessException never
	 * 
	 */
	public String processAllReturningText(final String s) throws ProcessException {
		return s;
	}
	
	
	/**
	 * helper method generating a namespace string as required by the MW-api.
	 *
	 * @param namespaces
	 *            namespace as
	 * @return with numbers seperated by |
	 */
	protected static String createNsString(int... namespaces) {

		String namespaceString = "";

		if (namespaces != null && namespaces.length != 0) {
			for (int nsNumber : namespaces) {
				namespaceString += nsNumber + "|";
			}
			// remove last '|'
			if (namespaceString.endsWith("|")) {
				namespaceString = namespaceString.substring(0, namespaceString
						.length() - 1);
			}
		}
		return namespaceString;
	}
	
}