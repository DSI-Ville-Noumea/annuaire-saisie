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
import static nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity.COLUMNNAME_ID;

/**
 * Created by barmi83 on 09/12/15.
 */
@Entity
@Table(name = Sectorisation.TABLENAME)
public class Sectorisation extends AbstractEntity implements Comparable<Sectorisation>{

    private static final long serialVersionUID = -2415003340280412978L;

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
    @Column(name = COLUMNNAME_ID)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCENAME_ID)
    @SequenceGenerator(name = SEQUENCENAME_ID, sequenceName = SEQUENCENAME_ID, allocationSize = 1)
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoie la version hibernate de l'objet
     *
     * @return
     */
    @Version
    @Column(name = COLUMNNAME_VERSION)
    @Override
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Sectorisation)) {
            return false;
        }
        final Sectorisation other = (Sectorisation) obj;
        if (!Objects.equals(this.code4, other.code4)) {
            return false;
        }
        if (!Objects.equals(this.sigle, other.sigle)) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
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
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.version);
        hash = 89 * hash + Objects.hashCode(this.code4);
        hash = 89 * hash + Objects.hashCode(this.sigle);
        hash = 89 * hash + Objects.hashCode(this.libelle);
        //hash = 89 * hash + Objects.hashCode(this.parent);
        return hash;
    }
    
   

    
}
