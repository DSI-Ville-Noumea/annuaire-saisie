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
import nc.noumea.mairie.annuairev2.saisie.dao.ILocalityDao;
import nc.noumea.mairie.annuairev2.saisie.entity.Locality;
import nc.noumea.mairie.annuairev2.saisie.entity.Sectorisation;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 * Created by barmi83 on 30/12/15.
 */
@Repository
public class LocalityDao extends AbstractHibernateDao<Locality> implements ILocalityDao {

    public LocalityDao() {
        setClazz(Locality.class);
    }

    @Override
    public List<Locality> findByNomEtService(String nom, String service) {
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("select l.* from " + Locality.TABLENAME + " l ");
        sqlQuery.append(" left join " + Sectorisation.TABLENAME + " s on l." + Locality.JOIN_COLUMNNAME_SERVICE + " = s."+Sectorisation.COLUMNNAME_ID );
        sqlQuery.append(" where ");
        sqlQuery.append(nom != null ? "lower(l."+Locality.COLUMNNAME_NOM+") like :nom " :"");
        sqlQuery.append((service != null && nom != null) ? " and " : "");
	sqlQuery.append(service != null ? " s." + Sectorisation.COLUMNNAME_LIBELLE + " = :serviceName ": "");
        

        SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery.toString());
        query.addEntity(Locality.class);
	if(nom != null)
            query.setParameter("nom", "%"+nom.toLowerCase()+"%");
        if(service != null)
            query.setParameter("serviceName", service);

	List<Locality> results = (List<Locality>) query.list();
	if (results == null)
	    results = new ArrayList<>();

	return results;
    }
}
