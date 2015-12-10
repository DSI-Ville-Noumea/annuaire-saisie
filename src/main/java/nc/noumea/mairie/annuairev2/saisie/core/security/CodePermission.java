/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.core.security;

import java.util.Arrays;
import java.util.List;

/**
 * Enum listant l'ensemble des permissions supportées par l'application
 * 
 * @author barmi83
 * @since 0.2.0
 */
public enum CodePermission {

    USER("Utilisateur authentifié de l'application"),

    // ******* Gestion GUEST ********
    GUEST_ADD("Création du guest"),
    GUEST_CONSULT("Consultation du guest"),
    GUEST_DEL("Suppression du guest"),

    // ******* Gestion LOCALITY ********
    LOCALITY_ADD("Création de locality"),
    LOCALITY_CONSULT("Consultation de locality"),
    LOCALITY_DEL("Suppression du locality"),

    // ****** Gestion des utilisateurs ********
    USER_ADMIN("Gestion des utilisateurs");

    private final String descr;

    /**
     * Libellé
     * 
     * @param description
     */
    private CodePermission(final String description) {
	this.descr = description;
    }

    @Override
    public String toString() {
	return descr;
    }

    public static String valueOf(CodePermission e) {
	return e.toString();
    }

    public static List<CodePermission> listAll() {
	return Arrays.asList(values());
    }

}
