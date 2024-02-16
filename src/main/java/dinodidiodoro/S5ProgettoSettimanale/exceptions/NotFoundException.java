package dinodidiodoro.S5ProgettoSettimanale.exceptions;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Long id) {
		super(id + " non trovato!");
	}
}
