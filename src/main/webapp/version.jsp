<%--
  #%L
  nppb-v2
  $Id:$
  $HeadURL:$
  %%
  Copyright (C) 2015 Mairie de Nouméa
  %%
  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as
  published by the Free Software Foundation, either version 3 of the
  License, or (at your option) any later version.
  
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  
  You should have received a copy of the GNU General Public
  License along with this program.  If not, see
  <http://www.gnu.org/licenses/gpl-3.0.html>.
  #L%
  --%>
<%@page pageEncoding="UTF-8" contentType="text/plain;charset=UTF-8"%>
<%@page import="java.net.InetAddress"%>
${project.artifactId}.version=${project.version}
${project.artifactId}.localhost.hostaddress=<%=InetAddress.getLocalHost().getHostAddress()%>
${project.artifactId}.localhost.canonicalhostname=<%=InetAddress.getLocalHost().getCanonicalHostName()%>
${project.artifactId}.localhost.hostname=<%=InetAddress.getLocalHost().getHostName()%>
<%
	HttpSession theSession = request.getSession(false);

	// print out the session id
	try {
		if (theSession != null) {
			synchronized (theSession) {
				// invalidating a session destroys it
				theSession.invalidate();
			}
		}
	} catch (Exception e) {
		// ignored
	}
%>
