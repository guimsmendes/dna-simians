package br.com.guimsmendes.dnasimians.usecase.exception;

public class UseCaseException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2271690326153159469L;


    public static class NoRecordsFound extends RuntimeException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 50293939377715639L;

		public NoRecordsFound(String message) {
            super(message);
        }
    }

	public static class InputOutOfBounds extends RuntimeException {
		/**
		 *
		 */
		private static final long serialVersionUID = 50293939377715639L;

		public InputOutOfBounds(String message) {
			super(message);
		}
	}
}
