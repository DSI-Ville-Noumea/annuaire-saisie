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

import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.config.security.TestConfig;
import nc.noumea.mairie.annuairev2.saisie.dao.ISectorisationDao;
import nc.noumea.mairie.annuairev2.saisie.entity.Guest;
import nc.noumea.mairie.annuairev2.saisie.entity.Sectorisation;
import nc.noumea.mairie.annuairev2.saisie.service.ISectorisationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author barmi83
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class SectorisationServiceTest {
    
    @Autowired
    ISectorisationService sectorisationService;
    @Autowired
    ISectorisationDao sectorisationDao;
    
    private Sectorisation secteurDSI;
    private Sectorisation secteurSED;
    
    @Before
    public void init(){
        
        secteurDSI = new Sectorisation();
        secteurDSI.setCode4("AAAA");
        secteurDSI.setLibelle("Direction des Services de l'Information");
        secteurDSI.setSigle("DSI");
        
        secteurSED = new Sectorisation();
        secteurSED.setCode4("ABAA");
        secteurSED.setLibelle("Service Etudes et Développpement");
        secteurSED.setSigle("SED");
        secteurSED.setParent(secteurDSI);
        
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void findByIdTest(){
        
        Long secteurDsiId = sectorisationDao.save(secteurDSI);
        Long secteurSedId = sectorisationDao.save(secteurSED);
        
        Sectorisation result = sectorisationService.findById(secteurSedId);
              
        Assert.assertNotNull(result);
        Assert.assertEquals(secteurSedId, result.getId());
        Assert.assertEquals("ABAA", result.getCode4());
        Assert.assertEquals("Service Etudes et Développpement", result.getLibelle());
        Assert.assertEquals("SED", result.getSigle());
        Assert.assertNotNull(result.getParent());
        Assert.assertEquals(secteurDsiId, result.getParent().getId());
        
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void findByLibelleTest(){
        
        Long secteurDsiId = sectorisationDao.save(secteurDSI);
        Long secteurSedId = sectorisationDao.save(secteurSED);
        
        Sectorisation result = sectorisationService.findByLibelle(secteurSED.getLibelle());
              
        Assert.assertNotNull(result);
        Assert.assertEquals(secteurSedId, result.getId());
        Assert.assertEquals("ABAA", result.getCode4());
        Assert.assertEquals("Service Etudes et Développpement", result.getLibelle());
        Assert.assertEquals("SED", result.getSigle());
        Assert.assertNotNull(result.getParent());
        Assert.assertEquals(secteurDsiId, result.getParent().getId());
        
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void findAllTest(){
        
        List<Sectorisation> services = sectorisationService.findAll();
        
        Assert.assertNotNull(services);
        Assert.assertEquals(0, services.size());
        
        sectorisationDao.save(secteurDSI);
        
        services = sectorisationService.findAll();
        Assert.assertNotNull(services);
        Assert.assertEquals(1, services.size());
        Assert.assertTrue(contains(secteurDSI.getId(), services));
        
        sectorisationDao.save(secteurSED);
        
        services = sectorisationService.findAll();
        Assert.assertNotNull(services);
        Assert.assertEquals(2, services.size());
        Assert.assertTrue(contains(secteurDSI.getId(), services));
        Assert.assertTrue(contains(secteurSED.getId(), services));
        
    }
    
    private boolean contains(Long id, List<Sectorisation> results){
        boolean found = false;
        
        for (Sectorisation result : (List<Sectorisation>)results) {
            if(result.getId().equals(id)){
                found = true;
                break;
            }
        }
        
        return found;
    }
    
}
