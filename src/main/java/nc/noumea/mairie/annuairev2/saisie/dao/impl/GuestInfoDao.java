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

import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.core.dao.AbstractHibernateDao;
import nc.noumea.mairie.annuairev2.saisie.dao.IGuestInfoDao;
import nc.noumea.mairie.annuairev2.saisie.entity.GuestInfo;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by barmi83 on 30/12/15.
 */
@Repository
public class GuestInfoDao extends AbstractHibernateDao<GuestInfo> implements IGuestInfoDao {

    public GuestInfoDao() {
        setClazz(GuestInfo.class);
    }

    @Override
    public List<GuestInfo> findByNomEtService(String nom, String service) {
        
        String queryString;
        if(nom != null){
            if(service == null)
                queryString = "from GuestInfo as g where lower(g.nom) like :nom";
            else
                queryString = "from GuestInfo as g "
                    + " where lower(g.service.libelle) = :serviceName "
                    + " and (lower(g.nom) like :nom or lower(g.prenom) like :nom) ";
        }
        else{
            queryString = "from GuestInfo as g "
                    + " where lower(g.service.libelle) = :serviceName ";
        }
        Query query = getCurrentSession().createQuery(queryString);
        

	if(nom != null)
            query.setParameter("nom", "%"+nom.toLowerCase()+"%");
        if(service != null)
            query.setParameter("serviceName", service.toLowerCase());

	return (List<GuestInfo>) query.list();
    }
    
    @Override
    public GuestInfo findByIdentifiant(String identifiant) {
       Query query = getCurrentSession()
		.createQuery("from GuestInfo where ('G' || lpad(id,4,0)) = :identifiant");
	query.setParameter("identifiant", identifiant);
	return (GuestInfo) query.uniqueResult();
    }
}
