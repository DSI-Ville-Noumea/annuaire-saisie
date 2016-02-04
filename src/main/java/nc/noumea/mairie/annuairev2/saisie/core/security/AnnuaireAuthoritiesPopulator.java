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
        System.out.println("nc.noumea.mairie.annuairev2.saisie.core.security.AnnuaireAuthoritiesPopulator.getGrantedAuthorities()");
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
