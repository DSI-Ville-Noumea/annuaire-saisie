/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.dao.impl;

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


import nc.noumea.mairie.annuairev2.saisie.core.dao.AbstractHibernateDao;
import nc.noumea.mairie.annuairev2.saisie.dao.IUtilisateurDao;
import nc.noumea.mairie.annuairev2.saisie.entity.Utilisateur;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author barmi83
 * @since
 */
@Repository
public class UtilisateurDao extends AbstractHibernateDao<Utilisateur> implements IUtilisateurDao {

    public UtilisateurDao() {
	setClazz(Utilisateur.class);
    }

    @Override
    public Utilisateur findByLogin(String login) {
	Query query = getCurrentSession()
		.createQuery("from Utilisateur where upper(" + Utilisateur.PROPERTYNAME_IDENTIFIANT + ") = :login");
	query.setParameter("login", login.toUpperCase());
	return (Utilisateur) query.uniqueResult();
    }

}
