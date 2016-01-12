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
import java.util.List;

@Entity
@Table(name = Profil.TABLENAME)
public class Profil extends AbstractEntity {

    private static final long serialVersionUID = -7315296392316499948L;

    public static final String TABLENAME = "profil";

    /**
     * {@link #getId()}
     */
    private long id;
    public static final String COLUMNNAME_ID = "id";
    public static final String PROPERTYNAME_ID = "id";
    public static final String SEQUENCENAME_ID = "s_profil";

    /** {@link #getVersion()} */
    private Integer version;

    /**
     * {@link #getNom()}
     */
    private CodeProfil nom;
    public static final String COLUMNNAME_NOM = "nom";
    public static final String PROPERTYNAME_NOM = "nom";

    /**
     * {@link #getPermissions()}
     */
    private List<Permission> permissions;
    public static final String PROPERTYNAME_PERMISSIONS = "permissions";

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

    @Enumerated(EnumType.STRING)
    @Column(name = COLUMNNAME_NOM)
    public CodeProfil getNom() {
	return nom;
    }

    public void setNom(CodeProfil nom) {
	this.nom = nom;
    }

    /**
     * @return the permissions
     */
    @ManyToMany(targetEntity = Permission.class, fetch = FetchType.EAGER)
    @JoinTable(name = "profil_perm", joinColumns = @JoinColumn(name = "id_profil"), inverseJoinColumns = @JoinColumn(name = "id_permission"))
    public List<Permission> getPermissions() {
	return permissions;
    }

    /**
     * @param permissions
     *            the permissions to set
     */
    public void setPermissions(List<Permission> permissions) {
	this.permissions = permissions;
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
