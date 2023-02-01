package com.kt.code.exception;

public class EmptyColumnsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 132129741242444193L;

	public EmptyColumnsException() {
		super("No columns to render!!");
	}
}
