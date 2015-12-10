/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.core.security;

import nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity;

import javax.persistence.*;

/**
 * @author barmi83
 */
@Entity
@Table(name = Permission.TABLENAME)
public class Permission extends AbstractEntity {

    private static final long serialVersionUID = 2664564584860478499L;

    public static final String TABLENAME = "PERMISSION";

    /** {@link #getId()} */
    private long id;
    public static final String COLUMNNAME_ID = "ID";
    public static final String PROPERTYNAME_ID = "id";
    public static final String SEQUENCENAME_ID = "s_permission";

    /** {@link #getVersion()} */
    private Integer version;

    /** {@link #getCode()} */
    private CodePermission code;
    public static final String COLUMNNAME_CODE = "CODE";
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
