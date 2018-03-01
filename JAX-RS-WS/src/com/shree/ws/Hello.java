package com.shree.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "/rest")
public class Hello {

	@GET
	@Path(value = "/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHello() {
		return Response.status(200).entity("Hello Shreeshail").build();
	}

	@GET
	@Path(value = "/hello2")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello2() {
		return "Hello Gautam";
	}

	// This method is called if XML is requested
	@GET
	@Path(value = "/helloXml")
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Shreeshail" + "</hello>";
	}

	// This method is called if HTML is requested
	@GET
	@Path(value = "/helloHtml")
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello Shreeshail" + "</title>" + "<body><h1>" + "Hello Jersey HTML" + "</h1></body>"
				+ "</html> ";
	}

}
