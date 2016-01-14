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
@Table(name = Sectorisation.TABLENAME)
public class Sectorisation extends AbstractEntity implements Comparable<Sectorisation>{

    public static final String TABLENAME = "service";

    /** {@link #getId()} */
    private Long id;
    public static final String SEQUENCENAME_ID = "s_service";

    /** {@link #getVersion()} */
    private Integer version;

    /** {@link #getCode4()} */
    private String code4;
    public static final String COLUMNNAME_CODE4 = "code4";
    public static final String PROPERTYNAME_CODE4 = "code4";

    /** {@link #getSigle()} */
    private String sigle;
    public static final String COLUMNNAME_SIGLE = "sigle";
    public static final String PROPERTYNAME_SIGLE  = "sigle";

    /** {@link #getLibelle()}  */
    private String libelle;
    public static final String COLUMNNAME_LIBELLE = "libelle";
    public static final String PROPERTYNAME_LIBELLE = "libelle";

    /** {@link #getParent()} */
    private Sectorisation parent;
    public static final String COLUMNNAME_PARENT = "id_parent";
    public static final String PROPERTYNAME_PARENT = "parent";

    @Id
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Renvoie la version hibernate de l'objet
     *
     * @return
     */
    @Version
    @Column(name = COLUMNNAME_VERSION)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the libelle
     */
    @Column(name = COLUMNNAME_LIBELLE)
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * @return the parent
     */
    @ManyToOne
    @JoinColumn(name = COLUMNNAME_PARENT)
    public Sectorisation getParent() {
        return parent;
    }

    public void setParent(Sectorisation parent) {
        this.parent = parent;
    }

    @Column(name = COLUMNNAME_SIGLE)
    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    @Column(name = COLUMNNAME_CODE4)
    public String getCode4() {
        return code4;
    }

    public void setCode4(String code4) {
        this.code4 = code4;
    }

    @Override
    public int compareTo(Sectorisation t) {
           return this.getLibelle().compareTo(t.getLibelle());
    }
}
