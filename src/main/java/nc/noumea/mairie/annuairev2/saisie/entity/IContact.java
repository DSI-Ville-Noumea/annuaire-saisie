/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nc.noumea.mairie.annuairev2.saisie.entity;

/*
 * #%L
 * Gestion des Guest et Locality
 * %%
 * Copyright (C) 2015 - 2016 Mairie de Nouméa
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
 *
 * @author barmi83
 */
public interface IContact extends Comparable<IContact>{
    
    public static final String TYPE_GUEST = "guest";
    public static final String TYPE_LOCALITY = "locality";
    
    public String getNom();

    public void setNom(String nom);

    public Sectorisation getService();

    public void setService(Sectorisation service);
   
    public String getPoste();

    public void setPoste(String poste);

    public String getLigneDirecte();

    public void setLigneDirecte(String ligneDirecte);

    public String getFax();

    public void setFax(String fax);
    
    public String getFullName();
    
    public String getIdentifiant();
    
    public String getType();

    
}
