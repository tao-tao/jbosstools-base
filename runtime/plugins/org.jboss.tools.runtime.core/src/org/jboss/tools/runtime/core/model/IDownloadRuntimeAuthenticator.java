/*************************************************************************************
 * Copyright (c) 2013 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.jboss.tools.runtime.core.model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public interface IDownloadRuntimeAuthenticator {
	
	/**
	 * A key to check for a returned url
	 */
	public static final String DOWNLOAD_URL_RETURNED_PROP = "downloadURLReturnedProperty"; //$NON-NLS-1$
		
	
	// Some ID representation for this authenticator type
	public String getAuthenticatorId();
	
	/**
	 * Has the authentication been approved
	 * @return
	 */
	public boolean isAuthenticated();
	
	
	/**
	 * Get a list of required properties that must be set
	 * in order to move to the next step in authentication
	 * 
	 * @return
	 */
	public String[] getRequiredProperties();

	
	/**
	 * Get a list of properties returned from the 
	 * authenticator for what's missing, or other
	 * information that must be displayed or prompted 
	 * to the user.
	 * 
	 * @return
	 */
	public String[] getReturnedProperties();
	
	
	/**
	 * Get the value of a returned property from the 
	 * workflow to be displayed or prompted to the user
	 *  
	 * @param prop
	 * @return
	 */
	public Object getReturnedProperty(String prop);
	
	
	/**
	 * Set the value for a required property
	 * 
	 * @param key
	 * @param value
	 */
	public void setRequiredProperty(String key, Object value);
	
	
	/**
	 * Submit the request, or move on to the next part of the workflow
	 * This method will throw a CoreException if for example
	 * the authentication attempt has failed or the user
	 * does not have the permission required.  
	 * 
	 * @throws CoreException  if the authentication attempt has failed in a non-normal way. 
	 */
	public void submit(IProgressMonitor mon) throws CoreException;
	
	/**
	 * Reset the authenticator's state to a clean state
	 *  
	 * It is up to the authenticator to decide which data
	 * to clear, or whether to respond to this request at all. 
	 * For example, an authenticator who's urls are only good for 
	 * 10 minutes may choose to clear all state, 
	 * but an authenticator who's response is valid permanently
	 * may choose to clear nothing and leave the state as-is 
	 *  
	 */
	public void reset();

}