package com.subconscious.book.resource;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.subconscious.book.service.BooksServiceAdapter;
import com.subconscious.model.Book;

@Path("books")
@Resource
public class MyResource {

	BooksServiceAdapter booksServiceAdapter;
	
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public List<Book> getAllBooks() throws SQLException, ClassNotFoundException 
    {
    	booksServiceAdapter = new BooksServiceAdapter();
    	List<Book> bookList = booksServiceAdapter.getAllBooks();
    	return bookList;
    }
    
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_XML)
    public Book getByName(@PathParam("name") String name) throws SQLException, ClassNotFoundException
    {
    	booksServiceAdapter = new BooksServiceAdapter();
    	return booksServiceAdapter.getBookByName(name);
    }
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public void addBook(Book b) throws ClassNotFoundException, SQLException
    {
    	booksServiceAdapter = new BooksServiceAdapter();
    	booksServiceAdapter.addBook(b);
    	System.out.println(b);
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Book deleteBook(@PathParam("id") int id) throws ClassNotFoundException, SQLException
    {
    	booksServiceAdapter = new BooksServiceAdapter();
    	Book b = booksServiceAdapter.getBookById(id);
    	if(b.getId() != 0)
    		booksServiceAdapter.deleteBook(id);
    	System.out.println("Book deleted");
		return null;
    }
    
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public void updateBook(Book b) throws SQLException, ClassNotFoundException
    {
    	booksServiceAdapter = new BooksServiceAdapter();
    	booksServiceAdapter.updateBook(b);
    }
}
