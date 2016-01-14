/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.service.impl;

/*
 * #%L
 * Gestion des Guest et Locality
 * %%
 * Copyright (C) 2015 - 2016 Mairie de Nouméa
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



import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.core.security.CodeProfil;
import nc.noumea.mairie.annuairev2.saisie.core.security.Profil;
import nc.noumea.mairie.annuairev2.saisie.dao.IProfilDao;
import nc.noumea.mairie.annuairev2.saisie.service.IProfilService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author barmi83
 * @since 0.2.0
 */
@Service(IProfilService.BEAN_ID)
public class ProfilService implements IProfilService {

    private Logger logger = LoggerFactory.getLogger(ProfilService.class);

    @Autowired
    IProfilDao profilDao;

    @Override
    @Transactional(readOnly = true)
    public Profil findByLogin(String login) {
	return profilDao.findByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Profil findByProfilName(CodeProfil code) {
	return profilDao.findByProfilName(code);
    }

    @Override
    public List<Profil> findAll() {
	return profilDao.findAll();
    }

}
