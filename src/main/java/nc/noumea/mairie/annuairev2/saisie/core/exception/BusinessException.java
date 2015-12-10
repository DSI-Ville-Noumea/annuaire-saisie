/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.core.exception;

/**
 * @author barmi83
 * @since
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = -2130502406634041665L;

    public BusinessException() {
	super();
    }

    public BusinessException(String message) {
	super(message);
    }
}
