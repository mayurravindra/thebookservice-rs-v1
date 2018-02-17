package com.subconscious.bookrest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.subconscious.model.Book;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("books")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public List<Book> getAllBooks() throws SQLException, ClassNotFoundException 
    {
    	BookRepository b = new BookRepository();
    	List<Book> bookList = new ArrayList<>();
    	bookList = b.getAllBooks();
    	return bookList;
    }
    
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_XML)
    public Book getByName(@PathParam("name") String name) throws SQLException, ClassNotFoundException
    {
    	BookRepository b = new BookRepository();
    	return b.getBookByName(name);
    }
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public void addBook(Book b) throws ClassNotFoundException, SQLException
    {
    	BookRepository br = new BookRepository();
    	br.addBook(b);
    	System.out.println(b);
    }
}
