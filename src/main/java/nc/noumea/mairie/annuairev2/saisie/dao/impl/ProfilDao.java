/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.dao.impl;

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

import nc.noumea.mairie.annuairev2.saisie.core.dao.AbstractHibernateDao;
import nc.noumea.mairie.annuairev2.saisie.core.security.CodeProfil;
import nc.noumea.mairie.annuairev2.saisie.core.security.Profil;
import nc.noumea.mairie.annuairev2.saisie.dao.IProfilDao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author barmi83
 * @since
 */
@Repository(IProfilDao.BEAN_ID)
public class ProfilDao extends AbstractHibernateDao<Profil> implements IProfilDao {

    public ProfilDao() {
	setClazz(Profil.class);
    }

    @Override
    public Profil findByLogin(String login) {
	Query query = getCurrentSession().createQuery("select profil from Utilisateur where identifiant = :username");
	query.setParameter("username", login);
	return (Profil) query.uniqueResult();
    }

    @Override
    public Profil findByProfilName(CodeProfil code) {
	Query query = getCurrentSession().createQuery("from Profil where nom = :nom");
	query.setParameter("nom", code);
	return (Profil) query.uniqueResult();
    }

}
