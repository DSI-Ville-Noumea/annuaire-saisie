/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.core.security;

/*
 * #%L
 * Gestion des Guest et Locality
 * %%
 * Copyright (C) 2015 Mairie de Nouméa
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

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
