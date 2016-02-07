package nc.noumea.mairie.annuairev2.saisie.entity;

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

import java.util.Objects;
import nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity;

import javax.persistence.*;

/**
 * Created by barmi83 on 09/12/15.
 */
@Entity
@Table(name = Guest.TABLENAME)
public class Guest extends AbstractEntity implements IContact{

    private static final long serialVersionUID = -3648329055894806197L;

    public static final String TABLENAME = "guest";
    public static final String IDENTIFIANT_FORMAT  = "%04d";


    private Long id;
    public static final String SEQUENCENAME_ID = "s_guest";

    /** {@link #getNom()} */
    private String nom;
    public static final String COLUMNNAME_NOM = "nom";
    public static final String PROPERTYNAME_NOM = "nom";
    public static final int NOM_MAX_LENGTH = 200;

    /** {@link #getPrenom()} */
    private String prenom;
    public static final String COLUMNNAME_PRENOM = "prenom";
    public static final String PROPERTYNAME_PRENOM = "prenom";
    public static final int PRENOM_MAX_LENGTH = 50;

    /** {@link #getFonction()} */
    private String fonction;
    public static final String COLUMNNAME_FONCTION = "fonction";
    public static final String PROPERTYNAME_FONCTION = "fonction";
    public static final int FONCTION_MAX_LENGTH = 255;

    /** {@link #getService()} */
    private Sectorisation service;
    public static final String JOIN_COLUMNNAME_SERVICE = "id_service";
    public static final String PROPERTYNAME_SERVICE = "service";

    /** {@link #getPoste()} */
    private String poste;
    public static final String COLUMNNAME_POSTE = "poste";
    public static final String PROPERTYNAME_POSTE = "poste";
    public static final int POSTE_MAX_LENGTH = 20;

    /** {@link #getLigneDirecte()}  */
    private String ligneDirecte;
    public static final String COLUMNNAME_LIGNEDIRECTE = "lignedirecte";
    public static final String PROPERTYNAME_LIGNEDIRECTE = "ligneDirecte";
    public static final int LIGNE_DIRECTE_MAX_LENGTH = 13;

    /** {@link #getFax()}   */
    private String fax;
    public static final String COLUMNNAME_FAX = "fax";
    public static final String PROPERTYNAME_FAX = "fax";
    public static final int FAX_MAX_LENGTH = 20;
    
    /** {@link #getMobile()}    */
    private String mobile;
    public static final String COLUMNNAME_MOBILE = "mobile";
    public static final String PROPERTYNAME_MOBILE = "mobile";
    public static final int MOBILE_MAX_LENGTH = 13;

    /** {@link #getMail()}    */
    private String mail;
    public static final String COLUMNNAME_MAIL = "mail";
    public static final String PROPERTYNAME_MAIL = "mail";
    public static final int MAIL_MAX_LENGTH = 255;

    /** {@link #getMobilePrive()}    */
    private String mobilePrive;
    public static final String COLUMNNAME_MOBILEPRIVE = "mobileprive";
    public static final String PROPERTYNAME_MOBILEPRIVE = "mobilePrive";
    public static final int MOBILEPRIVE_MAX_LENGTH = 13;

    /** {@link #getTelephoneDomicile()}    */
    private String telephoneDomicile;
    public static final String COLUMNNAME_TELDOMICILE= "telephonedomicile";
    public static final String PROPERTYNAME_TELDOMICILE = "telephoneDomicile";
    public static final int TELDOMICILE_MAX_LENGTH = 13;

    /** {@link #getVersion()} */
    private Integer version;

    @Override
    @Id
    @Column(name = COLUMNNAME_ID)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCENAME_ID)
    @SequenceGenerator(name = SEQUENCENAME_ID, sequenceName = SEQUENCENAME_ID, allocationSize = 1)
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

    @Column(name = COLUMNNAME_MOBILE)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = COLUMNNAME_MAIL)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name = COLUMNNAME_MOBILEPRIVE)
    public String getMobilePrive() {
        return mobilePrive;
    }

    public void setMobilePrive(String mobilePrive) {
        this.mobilePrive = mobilePrive;
    }

    @Column(name = COLUMNNAME_TELDOMICILE)
    public String getTelephoneDomicile() {
        return telephoneDomicile;
    }

    public void setTelephoneDomicile(String telephoneDomicile) {
        this.telephoneDomicile = telephoneDomicile;
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

    /**
     *
     * @return
     */
    @ManyToOne
    @JoinColumn(name = JOIN_COLUMNNAME_SERVICE)
    @Override
    public Sectorisation getService() {
        return service;
    }

    /**
     *
     * @param service
     */
    @Override
    public void setService(Sectorisation service) {
        this.service = service;
    }

    @Column(name = COLUMNNAME_POSTE)
    @Override
    public String getPoste() {
        return poste;
    }

    /**
     *
     * @param poste
     */
    @Override
    public void setPoste(String poste) {
        this.poste = poste;
    }

    @Column(name = COLUMNNAME_LIGNEDIRECTE)
    @Override
    public String getLigneDirecte() {
        return ligneDirecte;
    }

    @Override
    public void setLigneDirecte(String ligneDirecte) {
        this.ligneDirecte = ligneDirecte;
    }

    @Column(name = COLUMNNAME_FAX)
    @Override
    public String getFax() {
        return fax;
    }

    @Override
    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    @Version
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
        return "G" + String.format(IDENTIFIANT_FORMAT, getId());
    }

    @Transient
    @Override
    public String getFullName(){
        if(nom != null && prenom != null)
            return nom + " " + prenom;
        
        return "";
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

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Guest)) {
            return false;
        }
        
        final Guest other = (Guest) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.fonction, other.fonction)) {
            return false;
        }
        if (!Objects.equals(this.poste, other.poste)) {
            return false;
        }
        if (!Objects.equals(this.ligneDirecte, other.ligneDirecte)) {
            return false;
        }
        if (!Objects.equals(this.fax, other.fax)) {
            return false;
        }
        if (!Objects.equals(this.mobile, other.mobile)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.mobilePrive, other.mobilePrive)) {
            return false;
        }
        if (!Objects.equals(this.telephoneDomicile, other.telephoneDomicile)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.service, other.service)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        return true;
    }

    
    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.nom);
        hash = 53 * hash + Objects.hashCode(this.prenom);
        hash = 53 * hash + Objects.hashCode(this.fonction);
        hash = 53 * hash + Objects.hashCode(this.service);
        hash = 53 * hash + Objects.hashCode(this.poste);
        hash = 53 * hash + Objects.hashCode(this.ligneDirecte);
        hash = 53 * hash + Objects.hashCode(this.fax);
        hash = 53 * hash + Objects.hashCode(this.mobile);
        hash = 53 * hash + Objects.hashCode(this.mail);
        hash = 53 * hash + Objects.hashCode(this.mobilePrive);
        hash = 53 * hash + Objects.hashCode(this.telephoneDomicile);
        hash = 53 * hash + Objects.hashCode(this.version);
        return hash;
    }

    
    
    
}
