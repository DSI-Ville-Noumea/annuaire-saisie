package nc.noumea.mairie.annuairev2.saisie.dao.impl;

/*
* #%L
 * * Gestion des Guest et Locality
 * *
 * %%
 * Copyright (C) 2015 Mairie de Nouméa
 * *
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
import nc.noumea.mairie.annuairev2.saisie.dao.IGuestDao;
import nc.noumea.mairie.annuairev2.saisie.entity.Guest;
import nc.noumea.mairie.annuairev2.saisie.entity.Sectorisation;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 * Created by barmi83 on 30/12/15.
 */
@Repository
public class GuestDao extends AbstractHibernateDao<Guest> implements IGuestDao {
    
    public GuestDao() {
        setClazz(Guest.class);
    }
    
    @Override
    public List<Guest> findByNomEtService(String nom, String service) {
        
        String queryString;
        
        if(nom != null){
            if(service == null)
                queryString = "select g.* from " + Guest.TABLENAME + " g where lower(g.nom) like :nom";
            else 
            queryString = "select g.* from " + Guest.TABLENAME + " g"
                    + " left join "+Sectorisation.TABLENAME+" s on g."+Guest.JOIN_COLUMNNAME_SERVICE+" = s."+Sectorisation.COLUMNNAME_ID
                    + " where lower(s.libelle) like :serviceName "
                    + " and lower(g.nom) like :nom or lower(g.prenom) like :nom ";
        }
        else{
            queryString = "select g.* from " + Guest.TABLENAME + " g"
                    + " left join "+Sectorisation.TABLENAME+" s on g."+Guest.JOIN_COLUMNNAME_SERVICE+" = s."+Sectorisation.COLUMNNAME_ID
                    + " where lower(s.libelle) like :serviceName ";
        }
        
        SQLQuery query = getCurrentSession().createSQLQuery(queryString);
        query.addEntity(Guest.class);
        if(nom != null)
            query.setParameter("nom", "%"+nom.toLowerCase()+"%");
        if(service != null)
            query.setParameter("serviceName", "%"+service.toLowerCase()+"%");
        
        return (List<Guest>)query.list();
    }
    
    @Override
    public Guest findByIdentifiant(String identifiant) {
        
        SQLQuery query = getCurrentSession()
                .createSQLQuery("select * from " + Guest.TABLENAME + " where ('G' || lpad(cast(id as varchar(4)), 4, '0')) = :identifiant");
        query.setParameter("identifiant", identifiant);
        query.addEntity(Guest.class);
        return (Guest) query.uniqueResult();
    }
}
