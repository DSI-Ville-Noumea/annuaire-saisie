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

/**
 * Created by barmi83 on 10/12/15.
 */
public class AnnuaireSaisiePerm {

    public static final String USER_ADMIN = "USER_ADMIN";

    /** Consultation du GUEST */
    public static final String GUEST_CONSULT = "GUEST_CONSULT";

    /** Création du GUEST */
    public static final String GUEST_ADD = "GUEST_ADD";

    /** Suppression du GUEST */
    public static final String GUEST_DEL = "GUEST_DEL";

    /** Consultation du Lo */
    public static final String LOCALITY_CONSULT = "LOCALITY_CONSULT";

    /** Création du LOCALITY */
    public static final String LOCALITY_ADD = "LOCALITY_ADD";

    /** Suppression du LOCALITY */
    public static final String LOCALITY_DEL = "LOCALITY_DEL";

    
    private AnnuaireSaisiePerm() {
        throw new UnsupportedOperationException();
    }
    
    
}
