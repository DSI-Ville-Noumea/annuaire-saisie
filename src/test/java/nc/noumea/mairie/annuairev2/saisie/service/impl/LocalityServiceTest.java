/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package nc.noumea.mairie.annuairev2.saisie.service.impl;

/*
* #%L
 * * Gestion des Guest et Locality
 * *
 * %%
 * Copyright (C) 2015 - 2016 Mairie de Nouméa
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

import java.sql.SQLException;
import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.config.security.TestConfig;
import nc.noumea.mairie.annuairev2.saisie.dao.ISectorisationDao;
import nc.noumea.mairie.annuairev2.saisie.entity.Locality;
import nc.noumea.mairie.annuairev2.saisie.entity.Sectorisation;
import nc.noumea.mairie.annuairev2.saisie.service.ILocalityService;
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
public class LocalityServiceTest {
    
    @Autowired
            ISectorisationDao sectorisationDao;
    @Autowired
            ILocalityService localityService;
    
    private Locality locality;
    private Locality locality2;
    private Sectorisation secteurDSI;
    private Sectorisation secteurSED;
    
    @Before
    public void init(){
        
        secteurDSI = new Sectorisation();
        secteurDSI.setCode4("AAAA");
        secteurDSI.setLibelle("Direction des Services de l'Information");
        secteurDSI.setSigle("DSI");
        secteurDSI = sectorisationDao.findById(sectorisationDao.save(secteurDSI));
        
        locality = new Locality();
        locality.setNom("Salle Reunion DSI");
        locality.setLigneDirecte("+687 35.24.78");
        locality.setPoste("6547");
        locality.setService(secteurDSI);
        
        locality2 = new Locality();
        locality2.setNom("Accueil DSI");
        locality2.setLigneDirecte("+687 35.24.79");
        locality2.setPoste("6548");
        locality2.setService(secteurDSI);
        
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void createLocalityTest() throws SQLException{
        
        Assert.assertNull(locality.getId());
        
        locality = localityService.saveOrUpdate(locality);
        
        Assert.assertNotNull(locality);
        Assert.assertNotNull(locality.getId());
        Assert.assertEquals("Salle Reunion DSI", locality.getNom());
        Assert.assertEquals("+687 35.24.78", locality.getLigneDirecte());
        Assert.assertEquals("6547", locality.getPoste());
        Assert.assertTrue(locality.getIdentifiant().startsWith("L"));
        
        Assert.assertNotNull(locality.getService());
        Assert.assertEquals("AAAA", locality.getService().getCode4());
        
        
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void updateLocalityTest() throws SQLException{
        
        locality = localityService.saveOrUpdate(locality);
        
        Assert.assertNotNull(locality);
        Assert.assertNotNull(locality.getId());
        Assert.assertEquals("Salle Reunion DSI", locality.getNom());
        Assert.assertEquals("+687 35.24.78", locality.getLigneDirecte());
        Assert.assertEquals("6547", locality.getPoste());
        
        Assert.assertNotNull(locality.getService());
        Assert.assertEquals("AAAA", locality.getService().getCode4());
        
        String identifiant = locality.getIdentifiant();
        
        locality.setNom("Accueil entrée DSI");
        locality.setPoste("5000");
        locality = localityService.saveOrUpdate(locality);
        
        Assert.assertNotNull(locality);
        Assert.assertEquals("Accueil entrée DSI", locality.getNom());
        Assert.assertEquals("+687 35.24.78", locality.getLigneDirecte());
        Assert.assertEquals("5000", locality.getPoste());
        Assert.assertEquals(identifiant, locality.getIdentifiant());
        
        Assert.assertNotNull(locality.getService());
        Assert.assertEquals("AAAA", locality.getService().getCode4());
        
    }
    
    
    
    @Test
    @Transactional
    @Rollback(true)
    public void findAllTest() throws SQLException{
        
        List<Locality> localities = localityService.findAll();
        
        Assert.assertNotNull(localities);
        Assert.assertEquals(0, localities.size());
        
        localityService.saveOrUpdate(locality);
        
        localities = localityService.findAll();
        Assert.assertNotNull(localities);
        Assert.assertEquals(1, localities.size());
        
        localityService.saveOrUpdate(locality2);
        
        localities = localityService.findAll();
        Assert.assertNotNull(localities);
        Assert.assertEquals(2, localities.size());
        
    }
    
    
    @Test
    @Transactional
    @Rollback(true)
    public void deleteByIdTest() throws SQLException{
        
        locality = localityService.saveOrUpdate(locality);
        
        Assert.assertNotNull(locality);
        Assert.assertNotNull(locality.getId());
        Assert.assertEquals("Salle Reunion DSI", locality.getNom());
        Assert.assertEquals("+687 35.24.78", locality.getLigneDirecte());
        Assert.assertEquals("6547", locality.getPoste());
        
        localityService.deleteById(locality.getId());
        Locality newLocality = localityService.findById(locality.getId());
        Assert.assertNull(newLocality);
        
        
    }
    
    
    @Test
    @Transactional
    @Rollback(true)
    public void findByNomEtServiceTest() throws SQLException{
        locality = localityService.saveOrUpdate(locality);
        locality2 = localityService.saveOrUpdate(locality2);
        
        List<Locality> results = localityService.findByNomEtService("Salle Reunion DSI", secteurDSI.getLibelle());
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertTrue(contains(locality.getId(), results));
        
        results = localityService.findByNomEtService("DSI", null);
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        Assert.assertTrue(contains(locality.getId(), results));
        Assert.assertTrue(contains(locality2.getId(), results));
        
        results = localityService.findByNomEtService(null,secteurDSI.getLibelle());
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        Assert.assertTrue(contains(locality.getId(), results));
        Assert.assertTrue(contains(locality2.getId(), results));
        
    }
    
    private boolean contains(Long id, List<Locality> results){
        boolean found = false;
        
        for (Locality result : results) {
            if(result.getId().equals(id)){
                found = true;
                break;
            }
        }
        
        return found;
    }
    
}
