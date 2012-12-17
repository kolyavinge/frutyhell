package frutyhell.model;

public class GameBoardException extends RuntimeException {

	public GameBoardException() {
		super();
	}

	public GameBoardException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public GameBoardException(String detailMessage) {
		super(detailMessage);
	}

	public GameBoardException(Throwable throwable) {
		super(throwable);
	}
}
