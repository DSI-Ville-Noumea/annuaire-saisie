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

import nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity;

import javax.persistence.*;

/**
 * Created by barmi83 on 09/12/15.
 */
@Entity
@Table(name = Guest.TABLENAME)
public class Guest extends AbstractEntity implements IContact{

    public static final String TABLENAME = "guest";
    public static final String IDENTIFIANT_FORMAT  = "%04d";


    private Long id;
    public static final String SEQUENCENAME_ID = "s_guest";

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

    /** {@link #getService()} */
    private Sectorisation service;
    public static final String JOIN_COLUMNNAME_SERVICE = "id_service";
    public static final String PROPERTYNAME_SERVICE = "service";

    /** {@link #getPoste()} */
    private String poste;
    public static final String COLUMNNAME_POSTE = "poste";
    public static final String PROPERTYNAME_POSTE = "poste";

    /** {@link #getLigneDirecte()}  */
    private String ligneDirecte;
    public static final String COLUMNNAME_LIGNEDIRECTE = "lignedirecte";
    public static final String PROPERTYNAME_LIGNEDIRECTE = "ligneDirecte";

    /** {@link #getFax()}   */
    private String fax;
    public static final String COLUMNNAME_FAX = "fax";
    public static final String PROPERTYNAME_FAX = "fax";

    /** {@link #getMobile()}    */
    private String mobile;
    public static final String COLUMNNAME_MOBILE = "mobile";
    public static final String PROPERTYNAME_MOBILE = "mobile";

    /** {@link #getMail()}    */
    private String mail;
    public static final String COLUMNNAME_MAIL = "mail";
    public static final String PROPERTYNAME_MAIL = "mail";

    /** {@link #getMobilePrive()}    */
    private String mobilePrive;
    public static final String COLUMNNAME_MOBILEPRIVE = "mobileprive";
    public static final String PROPERTYNAME_MOBILEPRIVE = "mobilePrive";

    /** {@link #getTelephoneDomicile()}    */
    private String telephoneDomicile;
    public static final String COLUMNNAME_TELDOMICILE= "telephonedomicile";
    public static final String PROPERTYNAME_TELDOMICILE = "telephoneDomicile";

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
    
    
}
