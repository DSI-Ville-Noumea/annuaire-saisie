/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

import java.util.ArrayList;
import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.dao.ISectorisationDao;
import nc.noumea.mairie.annuairev2.saisie.entity.Sectorisation;
import nc.noumea.mairie.annuairev2.saisie.service.ISectorisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author barmi83
 */
@Service(ISectorisationService.BEAN_ID)
public class SectorisationService implements ISectorisationService{

    @Autowired
    private ISectorisationDao sectorisationDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Sectorisation> findAll() {
        List<Sectorisation> results = sectorisationDao.findAll();
        if(results == null)
            results = new ArrayList<>();
        
        return results;
    }

    @Override
    @Transactional(readOnly = true)
    public Sectorisation findById(Long id) {
        return sectorisationDao.findById(id);
    }

    @Override
    public Sectorisation findByLibelle(String libelle) {
    return sectorisationDao.findByLibelle(libelle);    
    }
    
}
