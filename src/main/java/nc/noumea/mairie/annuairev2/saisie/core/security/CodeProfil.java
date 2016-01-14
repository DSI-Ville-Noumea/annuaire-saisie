/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.core.security;

/*
 * #%L
 * nppb-v2
 * $Id:$
 * $HeadURL:$
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
 * Enum listant l'ensemble des profils supportés par l'application
 * 
 * @author barmi83
 * @since 0.2.0
 */
public enum CodeProfil {
	ADMIN("Administrateur"),
	GESTIONNAIRE("Gestionnaire"),
        GESTIONNAIRE_GUEST("Gestionnaire Guest"),
        GESTIONNAIRE_LOCALITY("Gestionnaire Locality"),
	CONSULTANT("Consultant");
	
	
	private final String descr;
	
	private CodeProfil(final String description){
		this.descr = description;
	}
	
	@Override
    public String toString() {
        return descr;
    }

    public static String valueOf(CodeProfil e) {
        return e.toString();
    }
    
    public static List<CodeProfil> listAll(){
    	return Arrays.asList(values());
    }
}
