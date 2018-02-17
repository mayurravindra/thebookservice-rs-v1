package com.subconscious.book.service.poxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.subconscious.constants.BookServiceConstants;
import com.subconscious.model.Book;

/**
 * 
 * @author mayoor
 * Proxy class to delegate DBMS queries to BookServiceAdapter
 */
public class BookServiceProxy 
{
	public BookServiceProxy()
	{
		
	}
	
	public List<Book> retrieveAllBooks(Connection conn) throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(BookServiceConstants.RETRIEVE_QUERY_ALL);
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
	
	public Book retrieveBooksByName(Connection conn, String name) throws SQLException
	{
		String retrieve_query = BookServiceConstants.RETRIEVE_QUERY_BOOK_NAME + name;
		PreparedStatement stmt = conn.prepareStatement(retrieve_query);
		ResultSet rs = stmt.executeQuery();
		Book b = new Book();
		if(rs.next())
		{
			setModelAttributes(b, rs);
		}
		return b;
	}
	
	public void addBook(Connection conn, Book b) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement(BookServiceConstants.INSERT_QUERY);
		setDBAttributes(stmt, b);
		stmt.executeUpdate();
	}
	
	

	public void deleteBook(Connection conn, int id) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement(BookServiceConstants.DELETE_QUERY);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}

	public Book retrieveBooksById(Connection conn, int id) throws SQLException 
	{
		PreparedStatement stmt = conn.prepareStatement(BookServiceConstants.RETRIEVE_QUERY_ID);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		Book b = new Book();
		if(rs.next())
		{
			setModelAttributes(b, rs);
		}
		return b;
	}

	private void setModelAttributes(Book b, ResultSet rs)
	{
		try 
		{
			b.setId(rs.getInt(1));
			b.setName(rs.getString(2));
			b.setAuthor(rs.getString(3));
			b.setPrice(rs.getFloat(4));
			b.setPublicationYear(rs.getInt(5));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

	public void updateBook(Connection conn, Book b) throws SQLException 
	{
		PreparedStatement stmt = conn.prepareStatement(BookServiceConstants.UPDATE_QUERY);
		setDBAttributes(stmt, b);
		stmt.setInt(6, b.getId());
		stmt.executeUpdate();	
	}
	
	private void setDBAttributes(PreparedStatement stmt, Book b) 
	{
		try 
		{
			stmt.setInt(1, b.getId());
			stmt.setString(2, b.getName());
			stmt.setString(3, b.getAuthor());
			stmt.setFloat(4, b.getPrice());
			stmt.setInt(5, b.getPublicationYear());
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
