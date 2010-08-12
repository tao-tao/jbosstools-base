/*************************************************************************************
 * Copyright (c) 2008-2009 JBoss by Red Hat and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.jboss.tools.runtime;

import java.io.File;

import org.jboss.tools.runtime.bean.ServerBean;

/**
 * @author Snjeza
 *
 */
public class ServerDefinition {

	private String name;
	private String version;
	private String type;
	private File location;
	
	public ServerDefinition(String name, String version, String type,
			File location) {
		super();
		this.name = name;
		this.version = version;
		this.type = type;
		this.location = location;
	}
	
	/**
	 * @param serverBean
	 */
	public ServerDefinition(ServerBean serverBean) {
		this.name = serverBean.getName();
		this.version = serverBean.getVersion();
		this.type = serverBean.getType().getId();
		this.location = new File(serverBean.getLocation());
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public File getLocation() {
		return location;
	}
	public void setLocation(File location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServerDefinition other = (ServerDefinition) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServerDefinition [name=" + name + ", version=" + version
				+ ", type=" + type + ", location=" + location + "]";
	}
	
}