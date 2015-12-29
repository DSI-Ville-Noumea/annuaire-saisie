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

import nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity;

import javax.persistence.*;

/**
 * @author barmi83
 */
@Entity
@Table(name = Permission.TABLENAME)
public class Permission extends AbstractEntity {

    private static final long serialVersionUID = 2664564584860478499L;

    public static final String TABLENAME = "permission";

    /** {@link #getId()} */
    private long id;
    public static final String COLUMNNAME_ID = "id";
    public static final String PROPERTYNAME_ID = "id";
    public static final String SEQUENCENAME_ID = "s_permission";

    /** {@link #getVersion()} */
    private Integer version;

    /** {@link #getCode()} */
    private CodePermission code;
    public static final String COLUMNNAME_CODE = "code";
    public static final String PROPERTYNAME_CODE = "code";

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
     * @return the code
     */
    @Enumerated(EnumType.STRING)
    @Column(name = COLUMNNAME_CODE)
    public CodePermission getCode() {
	return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(CodePermission code) {
	this.code = code;
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

}
