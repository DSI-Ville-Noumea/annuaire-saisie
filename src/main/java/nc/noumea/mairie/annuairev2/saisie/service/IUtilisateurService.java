/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.service;

import nc.noumea.mairie.annuairev2.saisie.core.exception.BusinessException;
import nc.noumea.mairie.annuairev2.saisie.core.security.AnnuaireSaisiePerm;
import nc.noumea.mairie.annuairev2.saisie.entity.Utilisateur;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

/**
 * @author barmi83
 * @since
 */
public interface IUtilisateurService {

    public static final String BEAN_ID = "utilisateurService";

    public Utilisateur findById(Long id);

    public Utilisateur findByLogin(String login);

    public List<Utilisateur> findAll();

    @Secured({ AnnuaireSaisiePerm.USER_ADMIN })
    public Utilisateur createUtilisateur(Utilisateur newUtilisateur) throws BusinessException;

    @Secured({ AnnuaireSaisiePerm.USER_ADMIN })
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) throws BusinessException;

    @Secured({ AnnuaireSaisiePerm.USER_ADMIN })
    public void deleteUtilisateur(Utilisateur utilisateur);
}
