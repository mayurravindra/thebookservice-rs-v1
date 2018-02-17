package com.subconscious.constants;

public class BookServiceConstants 
{
	public static final String INSERT_QUERY ="insert into bookstore values (?,?,?,?,?)";
	public static final String RETRIEVE_QUERY_ALL = "select * from bookstore;";
	public static final String RETRIEVE_QUERY_BOOK_NAME = "select * from bookstore where book_name=";
	public static final String DELETE_QUERY = "delete from bookstore where id=?";
	public static final String UPDATE_QUERY = "update bookstore set id=?,book_name=?,author=?,price=?,publication_year=? where id=?";
	public static final String RETRIEVE_QUERY_ID = "select * from bookstore where id=?";
	public static final String URL = "jdbc:mysql://localhost:3306/subconscious";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
}
