/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.core.exception;

/**
 * @author barmi83
 * @since
 */
public class TechnicalException extends Exception {

    private static final long serialVersionUID = -2039187713496628579L;

    public TechnicalException() {
	super();
    }

    public TechnicalException(String message) {
	super(message);
    }

    public TechnicalException(String message, Throwable e) {
	super(message, e);
    }

}
