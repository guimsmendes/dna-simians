package br.com.guimsmendes.dnasimians.dataprovider.exception;

public class DataProviderException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7173671031949332053L;
	DataProviderException() {}
        public static class SqsSenderException extends RuntimeException {
            /**
			 * 
			 */
			private static final long serialVersionUID = 557243477961240398L;

			public SqsSenderException(String s, String sqsEndPoint, String message) {
                super(s + sqsEndPoint+ message);
            }
        }
        public static class UnableToSaveRecord extends RuntimeException{
            /**
			 * 
			 */
			private static final long serialVersionUID = -3286674972659720817L;

			public UnableToSaveRecord(String s) {
                super(s);
            }
        }
    public static class RepositoryException extends RuntimeException{
        /**
		 * 
		 */
		private static final long serialVersionUID = 8648740081626100780L;

		public RepositoryException(String s, String message) {
            super(s + message);
        }
    }

    }

