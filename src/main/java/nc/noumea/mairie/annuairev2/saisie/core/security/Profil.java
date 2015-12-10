package nc.noumea.mairie.annuairev2.saisie.core.security;


import nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Profil.TABLENAME)
public class Profil extends AbstractEntity {

    private static final long serialVersionUID = -7315296392316499948L;

    public static final String TABLENAME = "PROFIL";

    /**
     * {@link #getId()}
     */
    private long id;
    public static final String COLUMNNAME_ID = "ID";
    public static final String PROPERTYNAME_ID = "id";
    public static final String SEQUENCENAME_ID = "s_profil";

    /** {@link #getVersion()} */
    private Integer version;

    /**
     * {@link #getNom()}
     */
    private CodeProfil nom;
    public static final String COLUMNNAME_NOM = "NOM";
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
    @JoinTable(name = "PROFIL_PERM", joinColumns = @JoinColumn(name = "ID_PROFIL"), inverseJoinColumns = @JoinColumn(name = "ID_PERMISSION"))
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
