package br.com.produtos.error;

public class ResourceNotFoundException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(Object id) {
		super("Resource Not Found. Id "+ id);
	}
}
