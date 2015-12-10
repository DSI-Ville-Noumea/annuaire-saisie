package nc.noumea.mairie.annuairev2.saisie.core.security;

import nc.noumea.mairie.annuairev2.saisie.entity.Utilisateur;
import nc.noumea.mairie.annuairev2.saisie.service.IUtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Overriden Authorities Populator for Ldap authentication with spring security
 * This class loads roles from the database i/o using LDAP groups
 */
@Service
public class AnnuaireAuthoritiesPopulator implements LdapAuthoritiesPopulator {

    private Logger logger = LoggerFactory.getLogger(AnnuaireAuthoritiesPopulator.class);

    @Autowired
    private IUtilisateurService utilisateurService;

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations dirContextOperations,
	    String s) {

	List<GrantedAuthority> roles = new ArrayList<>();

	Utilisateur user = utilisateurService.findByLogin(s);
	if (user == null || !user.isActif()) {
	    if (user == null)
		logger.info("L'utilisateur " + s + ", non enregistré, a tenté de se connecter.");
	    else
		logger.info("L'utilisateur " + s + ", inactif, a tenté de se connecter.");
	    return roles;
	}

	Profil p = user.getProfil();

	if (p == null) {
	    return roles;
	}

	roles.add(new SimpleGrantedAuthority(CodePermission.USER.name()));

	List<Permission> perms = p.getPermissions();
	for (Permission permission : perms) {
	    roles.add(new SimpleGrantedAuthority(permission.getCode().name()));
	}
	return roles;
    }
}
