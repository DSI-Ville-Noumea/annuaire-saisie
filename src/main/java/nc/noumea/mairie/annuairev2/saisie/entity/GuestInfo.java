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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity;
import static nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity.COLUMNNAME_ID;
import org.zkoss.bind.annotation.Immutable;

/**
 *
 * @author barmi83
 */
@Entity
@Table(name = GuestInfo.TABLENAME)
@Immutable
public class GuestInfo extends AbstractEntity implements IContact {
    
    public static final String TABLENAME = Guest.TABLENAME;
    
    private Long id;
    
    /** {@link #getNom()} */
    private String nom;
    public static final String COLUMNNAME_NOM = "nom";
    public static final String PROPERTYNAME_NOM = "nom";

    /** {@link #getPrenom()} */
    private String prenom;
    public static final String COLUMNNAME_PRENOM = "prenom";
    public static final String PROPERTYNAME_PRENOM = "prenom";

    /** {@link #getFonction()} */
    private String fonction;
    public static final String COLUMNNAME_FONCTION = "fonction";
    public static final String PROPERTYNAME_FONCTION = "fonction";
    
    /** {@link #getLigneDirecte()}  */
    private String ligneDirecte;
    public static final String COLUMNNAME_LIGNEDIRECTE = "lignedirecte";
    public static final String PROPERTYNAME_LIGNEDIRECTE = "ligneDirecte";

    /** {@link #getFax()}   */
    private String fax;
    public static final String COLUMNNAME_FAX = "fax";
    public static final String PROPERTYNAME_FAX = "fax";

    /** {@link #getService()} */
    private Sectorisation service;
    public static final String JOIN_COLUMNNAME_SERVICE = "id_service";
    public static final String PROPERTYNAME_SERVICE = "service";

    /** {@link #getPoste()} */
    private String poste;
    public static final String COLUMNNAME_POSTE = "poste";
    public static final String PROPERTYNAME_POSTE = "poste";
    
    /** {@link #getVersion()} */
    private Integer version;
    
    
    @Id
    @Column(name = COLUMNNAME_ID)
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = COLUMNNAME_PRENOM)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Column(name = COLUMNNAME_FONCTION)
    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    
    @Column(name = COLUMNNAME_NOM)
    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @ManyToOne
    @JoinColumn(name = JOIN_COLUMNNAME_SERVICE)
    @Override
    public Sectorisation getService() {
        return service;
    }

    @Override
    public void setService(Sectorisation service) {
        this.service = service;
    }

    @Column(name = COLUMNNAME_POSTE)
    @Override
    public String getPoste() {
        return poste;
    }

    @Override
    public void setPoste(String poste) {
        this.poste = poste;
    }
    
    @Override
    @Column(name = COLUMNNAME_VERSION)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version){
        this.version = version;
    }
    
    @Transient
    @Override
    public String getIdentifiant(){
        return "G" + String.format(Guest.IDENTIFIANT_FORMAT, getId());
    }

    @Transient
    @Override
    public String getFullName(){
        if(nom != null && prenom != null)
            return nom + " " + prenom;
        
        return "";
    }

    @Override
    public String getLigneDirecte() {
        return ligneDirecte;
    }

    @Override
    public void setLigneDirecte(String ligneDirecte) {
        this.ligneDirecte = ligneDirecte;
    }

    @Override
    public String getFax() {
        return fax;
    }

    @Override
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    @Override
    public int compareTo(IContact o) {
	return getFullName().compareTo(o.getFullName());
    }
    
    @Override
    @Transient
    public String getType() {
        return IContact.TYPE_GUEST;
    }
}
