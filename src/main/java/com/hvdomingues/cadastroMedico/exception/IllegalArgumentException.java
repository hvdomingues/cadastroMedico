package com.hvdomingues.cadastroMedico.exception;



public class IllegalArgumentException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public IllegalArgumentException() {
        super();
    }
    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
    public IllegalArgumentException(String message) {
        super(message);
    }
    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }

}
