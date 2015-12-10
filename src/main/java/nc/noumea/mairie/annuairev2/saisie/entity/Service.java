package nc.noumea.mairie.annuairev2.saisie.entity;

import nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity;

import javax.persistence.*;

/**
 * Created by barmi83 on 09/12/15.
 */
@Entity
@Table(name = Service.TABLENAME)
public class Service extends AbstractEntity {

    public static final String TABLENAME = "SERVICE";

    /** {@link #getId()} */
    private Long id;
    public static final String COLUMNNAME_ID = "id";
    public static final String PROPERTYNAME_ID = "id";
    public static final String SEQUENCENAME_ID = "s_service";

    /** {@link #getVersion()} */
    private Integer version;

    /** {@link #getCode()} */
    private String code;
    public static final String COLUMNNAME_CODE = "code";
    public static final String PROPERTYNAME_CODE = "code";

    /** {@link #getLibelle()}  */
    private String libelle;
    public static final String COLUMNNAME_LIBELLE = "LIBELLE";
    public static final String PROPERTYNAME_LIBELLE = "libelle";

    /** {@link #getParent()} */
    private Service parent;
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
     * @return the code
     */
    @Column(name = COLUMNNAME_CODE)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    public Service getParent() {
        return parent;
    }

    public void setParent(Service parent) {
        this.parent = parent;
    }
}
