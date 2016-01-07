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

import java.util.ArrayList;
import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.core.dao.AbstractHibernateDao;
import nc.noumea.mairie.annuairev2.saisie.dao.IGuestInfoDao;
import nc.noumea.mairie.annuairev2.saisie.entity.Guest;
import nc.noumea.mairie.annuairev2.saisie.entity.GuestInfo;
import nc.noumea.mairie.annuairev2.saisie.entity.Sectorisation;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
        SQLQuery query = getCurrentSession().createSQLQuery(
		"select g.* from " + GuestInfo.TABLENAME + " g " +
			" join " + Sectorisation.TABLENAME + " s on g." + GuestInfo.JOIN_COLUMNNAME_SERVICE + " = s."
			+ Sectorisation.COLUMNNAME_ID 
                        + " where " 
                        + (nom != null ? "(lower(g."+GuestInfo.COLUMNNAME_NOM+") like :nom or lower(g."+GuestInfo.COLUMNNAME_PRENOM+") like :nom)" : "")
                        + ((service != null && nom != null) ? " and " : "")
			+ (service != null ? " s." + Sectorisation.COLUMNNAME_LIBELLE + " = :serviceName ": ""));
			
        System.out.println("query:"+query);
	query.addEntity(GuestInfo.class);
	if(nom != null)
            query.setParameter("nom", nom.toLowerCase());
        if(service != null)
            query.setParameter("serviceName", service);

	List<GuestInfo> results = (List<GuestInfo>) query.list();
	if (results == null)
	    results = new ArrayList<>();

	return results;
    }
    
    @Override
    public GuestInfo findByIdentifiant(String identifiant) {
       Query query = getCurrentSession()
		.createQuery("from GuestInfo where ('G' || lpad("+GuestInfo.PROPERTYNAME_ID+",4,0)) = :identifiant");
	query.setParameter("identifiant", identifiant);
	return (GuestInfo) query.uniqueResult();
    }
}
