package com.subconscious.bookrest;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.subconscious.model.Book;

public class BookRepository 
{
	List<Book> bookList;
	public final String INSERT_QUERY ="insert into bookstore (?,?,?,?,?) values";  
	public static final String URL = "jdbc:mysql://localhost:3306/subconscious";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	java.sql.Connection conn = null;
	public BookRepository() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public List<Book> getAllBooks() throws SQLException
	{
		String retrieve_query = "select * from bookstore;";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(retrieve_query);
		List<Book> books = new ArrayList<>();
		while(rs.next())
		{
			Book b = new Book();
			b.setId(rs.getInt(1));
			b.setName(rs.getString(2));
			b.setAuthor(rs.getString(3));
			b.setPrice(rs.getFloat(4));
			b.setPublicationYear(rs.getInt(5));
			books.add(b);
		}
    	return books;
	}
	
	public Book getBookByName(String name) throws SQLException
	{
		
		String retrieve_query = "select * from bookstore where book_name="+name;
		PreparedStatement stmt = conn.prepareStatement(retrieve_query);
		ResultSet rs = stmt.executeQuery();
		List<Book> books = new ArrayList<>();
		Book b = new Book();
		if(rs.next())
		{
			
			b.setId(rs.getInt(1));
			b.setName(rs.getString(2));
			b.setAuthor(rs.getString(3));
			b.setPrice(rs.getFloat(4));
			b.setPublicationYear(rs.getInt(5));
			books.add(b);
		}
    	return b;
	}
	
	public void addBook(Book b) throws SQLException
	{
		String INSERT_QUERY ="insert into bookstore values (?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY);
		
		stmt.setInt(1, b.getId());
		stmt.setString(2, b.getName());
		stmt.setString(3, b.getAuthor());
		stmt.setFloat(4, b.getPrice());
		stmt.setInt(5, b.getPublicationYear());
		stmt.executeUpdate();
		
	}
}
