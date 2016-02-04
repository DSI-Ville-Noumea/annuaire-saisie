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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.dao.ILocalityDao;
import nc.noumea.mairie.annuairev2.saisie.entity.Locality;
import nc.noumea.mairie.annuairev2.saisie.service.ILocalityService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by barmi83 on 30/12/15.
 */
@Service(ILocalityService.BEAN_ID)
public class LocalityService implements ILocalityService {

    @Autowired
    ILocalityDao localityDao;

    @Override
    @Transactional(readOnly = true)
    public List<Locality> findAll() {
        return localityDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Locality findById(Long id) {
        return localityDao.findById(id);      
    }

    @Override
    @Transactional(readOnly = false)
    public Locality saveOrUpdate(Locality locality) {
        Locality newLocality;
        if(locality.getId() == null){
            Long newLocalityId = localityDao.save(locality);
            newLocality = localityDao.findById(newLocalityId);
        }
        else
            newLocality = localityDao.update(locality);
        
        return newLocality;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        localityDao.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Locality> findByNomEtService(String nom, String service) {
	return localityDao.findByNomEtService(nom, service);
    }

}
