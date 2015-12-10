package nc.noumea.mairie.annuairev2.saisie.entity;


import nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity;
import nc.noumea.mairie.annuairev2.saisie.core.security.Profil;

import javax.persistence.*;

/**
 * Created by barmi83 on 09/12/15.
 */
@Entity
@Table(name = Utilisateur.TABLENAME)
public class Utilisateur extends AbstractEntity {

    public static final String TABLENAME = "UTILISATEUR";

    /** {@link #getId()} */
    private Long id;
    public static final String COLUMNNAME_ID = "ID";
    public static final String PROPERTYNAME_ID = "id";
    public static final String SEQUENCENAME_ID = "s_utilisateur";

    /** {@link #getIdentifiant()} */
    private String identifiant;
    public static final String COLUMNNAME_IDENTIFIANT = "IDENTIFIANT";
    public static final String PROPERTYNAME_IDENTIFIANT = "identifiant";

    /** {@link #getNom()} */
    private String nom;
    public static final String COLUMNNAME_NOM = "nom";
    public static final String PROPERTYNAME_NOM = "nom";

    /** {@link #getPrenom()} */
    private String prenom;
    public static final String COLUMNNAME_PRENOM = "prenom";
    public static final String PROPERTYNAME_PRENOM = "prenom";

    /** {@link #isActif()} */
    private boolean isActif;
    public static final String COLUMNNAME_ACTIF = "ACTIF";
    public static final String PROPERTYNAME_ACTIF = "actif";

    /** {@link #getProfil()} */
    private Profil profil;
    public static final String JOIN_COLUMNNAME_PROFIL = "ID_PROFIL";

    /** {@link #getVersion()} */
    private Integer version;

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

    @Column(name = COLUMNNAME_IDENTIFIANT)
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    @Column(name = COLUMNNAME_NOM)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Column(name = COLUMNNAME_PRENOM)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Column(name = COLUMNNAME_ACTIF)
    public boolean isActif() {
        return isActif;
    }

    public void setActif(boolean active) {
        isActif = active;
    }

    @ManyToOne
    @JoinColumn(name = JOIN_COLUMNNAME_PROFIL)
    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
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
