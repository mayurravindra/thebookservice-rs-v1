package com.subconscious.book.service;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.subconscious.book.service.poxy.BookServiceProxy;
import com.subconscious.constants.BookServiceConstants;
import com.subconscious.model.Book;

public class BooksServiceAdapter 
{
	List<Book> bookList;
	BookServiceProxy booksProxy;
	
	java.sql.Connection conn = null;
	public BooksServiceAdapter() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(BookServiceConstants.URL, BookServiceConstants.USERNAME, BookServiceConstants.PASSWORD);
	}
	
	public List<Book> getAllBooks() throws SQLException
	{
		List<Book> books = booksProxy.retrieveAllBooks(conn);
		System.out.println("Fetched all books...");
    	return books;
	}
	
	public Book getBookByName(String name) throws SQLException
	{
		Book b = booksProxy.retrieveBooksByName(conn, name);
    	return b;
	}
	
	public void addBook(Book b) throws SQLException
	{
		booksProxy.addBook(conn, b);
	}

	public void deleteBook(int id) throws SQLException 
	{
		this.booksProxy.deleteBook(conn,id);
	}

	public Book getBookById(int id) throws SQLException 
	{
		Book b = this.booksProxy.retrieveBooksById(conn, id);
    	return b;
	}

	public void updateBook(Book b) throws SQLException 
	{
		this.booksProxy.updateBook(conn,b);
	}
}
