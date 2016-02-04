package nc.noumea.mairie.annuairev2.saisie.core.entity;

/*
* #%L
 * * Gestion des Guest et Locality
 * *
 * %%
 * Copyright (C) 2015 Mairie de Nouméa
 * *
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

import javax.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author barmi83
 */
public abstract class AbstractEntity implements Serializable {
    
    private static final long serialVersionUID = -2930543864114152660L;
    
    public static final String COLUMNNAME_ID = "id";
    public static final String PROPERTYNAME_ID = "id";
    
    public static final String COLUMNNAME_VERSION = "version";
    public static final String PROPERTYNAME_VERSION = "version";
    
    /**
     * Renvoie l'id technique de l'objet
     *
     * @return
     */
    @Id
    public abstract Long getId();
    
    /**
     * Renvoie la version hibernate de l'objet
     *
     * @return
     */
    public abstract Integer getVersion();
    
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result
//                + ((getId() == null) ? super.hashCode() : getId().hashCode());
//        return result;
//    }
    
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        AbstractEntity other = (AbstractEntity) obj;
//        
//        if (getId() == null && other.getId() == null) {
//            return super.equals(obj);
//        }
//        
//        // cas où les 2 possèdent un id : ils sont considérés égaux si c'est le même id
//        if (getId() != null && other.getId() != null) {
//            return getId().equals(other.getId());
//        }
//        
//        // un id est null, l'autre non
//        return false;
//    }
    
}
