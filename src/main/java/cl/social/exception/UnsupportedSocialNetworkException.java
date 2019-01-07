package cl.social.exception;

public class UnsupportedSocialNetworkException extends Exception{
    
    private static final long serialVersionUID = 1L;

    public UnsupportedSocialNetworkException() {
        super();
    }
    
    public UnsupportedSocialNetworkException(String message) {
        super(message);
    }
}
