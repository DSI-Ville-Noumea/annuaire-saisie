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

import nc.noumea.mairie.annuairev2.saisie.dao.IGuestDao;
import nc.noumea.mairie.annuairev2.saisie.entity.Guest;
import nc.noumea.mairie.annuairev2.saisie.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.dao.IGuestInfoDao;
import nc.noumea.mairie.annuairev2.saisie.entity.GuestInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by barmi83 on 30/12/15.
 */
@Service(IGuestService.BEAN_ID)
public class GuestService implements IGuestService {

    @Autowired
    IGuestDao guestDao;
    @Autowired
    IGuestInfoDao guestInfoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Guest> findAll() {
        List<Guest> results = guestDao.findAll();

        if(results == null)
            results = new ArrayList<>();

        return results;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Guest findById(Long id) {
        return guestDao.findById(id);      
    }
    
    @Override
    @Transactional(readOnly = true)
    public Guest findByIdentifiant(String identifiant) {
        return guestDao.findByIdentifiant(identifiant);      
    }

    @Override
    @Transactional(readOnly = false)
    public Guest saveOrUpdate(Guest guest) {
        Guest newGuest = null;
        if(guest.getId() == null){
            Long newGuestId = guestDao.save(guest);
            newGuest = guestDao.findById(newGuestId);
        }
        else
            newGuest = guestDao.update(guest);
        
        return newGuest;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        guestDao.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Guest> findByNomEtService(String nom, String service) {
	return guestDao.findByNomEtService(nom, service);
    }

    @Override
    public List<GuestInfo> findAllGuestInfo() {
        List<GuestInfo> results = guestInfoDao.findAll();

        if(results == null)
            results = new ArrayList<>();

        return results;
    }

    @Override
    public List<GuestInfo> findGuestInfoByNomEtService(String nom, String service) {
        return guestInfoDao.findByNomEtService(nom, service);
    }
}
