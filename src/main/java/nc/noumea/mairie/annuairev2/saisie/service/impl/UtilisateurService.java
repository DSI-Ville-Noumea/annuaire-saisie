/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.service.impl;

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
import nc.noumea.mairie.annuairev2.saisie.dao.IUtilisateurDao;
import nc.noumea.mairie.annuairev2.saisie.entity.Utilisateur;
import nc.noumea.mairie.annuairev2.saisie.service.IUtilisateurService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author barmi83
 * @since
 */
@Service(IUtilisateurService.BEAN_ID)
public class UtilisateurService implements IUtilisateurService {

    @Autowired
    IUtilisateurDao utilisateurDao;

    @Override
    @Transactional(readOnly = true)
    public Utilisateur findById(Long id) {
	return utilisateurDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Utilisateur findByLogin(String login) {
	return utilisateurDao.findByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Utilisateur> findAll() {
	List<Utilisateur> results = utilisateurDao.findAll();

	if (results == null)
	    results = new ArrayList<Utilisateur>();

	return results;
    }

    @Override
    @Transactional(readOnly = false)
    public Utilisateur createUtilisateur(Utilisateur newUtilisateur) throws BusinessException {
	newUtilisateur.setNom(newUtilisateur.getNom().toUpperCase());

	Long id = utilisateurDao.save(newUtilisateur);
	Utilisateur result = findById(id);
	if (result == null) {
	    LoggerFactory.getLogger(UtilisateurService.class).error(
		    "Impossible de créer l'utilisateur :" + newUtilisateur);
	    throw new BusinessException("La création de l'utilisateur a échoué pour une raison non identifiée. "
		    + "\nVeuillez réessayer ultérieurement. Si le problème persiste, merci de contacter le SED.");
	}

	return result;
    }

    @Override
    @Transactional(readOnly = false)
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) throws BusinessException {
	utilisateur.setNom(utilisateur.getNom().toUpperCase());

	return utilisateurDao.update(utilisateur);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteUtilisateur(Utilisateur utilisateur) {
	utilisateurDao.delete(utilisateur);
    }


}
