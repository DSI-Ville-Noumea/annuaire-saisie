/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.service;

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
