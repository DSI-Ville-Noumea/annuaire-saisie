
package nc.noumea.mairie.annuairev2.saisie.service.impl;

/*
* #%L
 * * Gestion des Guest et Locality
 * *
 * * *
 * *
 * %%
 * Copyright (C) 2015 - 2016 Mairie de Nouméa
 * *
 * * *
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
import nc.noumea.mairie.annuairev2.saisie.entity.Guest;
import nc.noumea.mairie.annuairev2.saisie.entity.GuestInfo;
import nc.noumea.mairie.annuairev2.saisie.entity.Sectorisation;
import nc.noumea.mairie.annuairev2.saisie.service.IGuestService;
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
public class GuestServiceTest {
    
    @Autowired
    ISectorisationDao sectorisationDao;
    @Autowired
    IGuestService guestService;
    
    private Guest guest;
    private Guest guest2;
    private Sectorisation secteurDSI;
    private Sectorisation secteurSED;
    
    @Before
    public void init(){
        
        secteurDSI = new Sectorisation();
        secteurDSI.setCode4("AAAA");
        secteurDSI.setLibelle("Direction des Services de l'Information");
        secteurDSI.setSigle("DSI");
        secteurDSI = sectorisationDao.findById(sectorisationDao.save(secteurDSI));
        
        secteurSED = new Sectorisation();
        secteurSED.setCode4("ABAA");
        secteurSED.setLibelle("Service Etudes et Développpement");
        secteurSED.setSigle("SED");
        secteurSED.setParent(secteurDSI);
        secteurSED = sectorisationDao.findById(sectorisationDao.save(secteurSED));
        
        guest = new Guest();
        guest.setNom("Doe");
        guest.setPrenom("John");
        guest.setFonction("Developpeur");
        guest.setLigneDirecte("+687 35.24.78");
        guest.setPoste("6547");
        guest.setService(secteurSED);
        
        guest2 = new Guest();
        guest2.setNom("Doe");
        guest2.setPrenom("Jane");
        guest2.setFonction("Chef de projet");
        guest2.setLigneDirecte("+687 35.24.79");
        guest2.setPoste("6548");
        guest2.setService(secteurDSI);
        
    }
    
    @Test
    @Transactional
    @Rollback(true)
    //@WithMockUser(username = "guest", authorities = {"GUEST_ADD", "USER"})
    public void createGuestTest() throws SQLException{
        
        //        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        //        System.err.println("authentication="+SecurityContextHolder.getContext().getAuthentication());
        //        System.err.println("currentUser:" + currentUser.getName());
        //        System.err.println("currentUser:" + currentUser.getAuthorities());

        Assert.assertNull(guest.getId());

        guest = guestService.saveOrUpdate(guest);

        Assert.assertNotNull(guest);
        Assert.assertNotNull(guest.getId());
        Assert.assertEquals("Doe", guest.getNom());
        Assert.assertEquals("John", guest.getPrenom());
        Assert.assertEquals("Developpeur", guest.getFonction());
        Assert.assertEquals("+687 35.24.78", guest.getLigneDirecte());
        Assert.assertEquals("6547", guest.getPoste());
        Assert.assertTrue(guest.getIdentifiant().startsWith("G"));

        Assert.assertNotNull(guest.getService());
        Assert.assertEquals("ABAA", guest.getService().getCode4());


    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void updateGuestTest() throws SQLException{
        
        guest = guestService.saveOrUpdate(guest);
        
        Assert.assertNotNull(guest);
        Assert.assertNotNull(guest.getId());
        Assert.assertEquals("Doe", guest.getNom());
        Assert.assertEquals("John", guest.getPrenom());
        Assert.assertEquals("Developpeur", guest.getFonction());
        Assert.assertEquals("+687 35.24.78", guest.getLigneDirecte());
        Assert.assertEquals("6547", guest.getPoste());
        
        Assert.assertNotNull(guest.getService());
        Assert.assertEquals("ABAA", guest.getService().getCode4());
        
        String identifiant = guest.getIdentifiant();
        
        guest.setNom("DoeDoe");
        guest.setPrenom("Johnny");
        guest.setFonction("Chef de projet");
        guest.setLigneDirecte("+687 35.24.70");
        guest.setPoste("5000");
        guest.setService(secteurDSI);
        
        guest = guestService.saveOrUpdate(guest);
        
        Assert.assertNotNull(guest);
        Assert.assertEquals("DoeDoe", guest.getNom());
        Assert.assertEquals("Johnny", guest.getPrenom());
        Assert.assertEquals("Chef de projet", guest.getFonction());
        Assert.assertEquals("+687 35.24.70", guest.getLigneDirecte());
        Assert.assertEquals("5000", guest.getPoste());
        Assert.assertEquals(identifiant, guest.getIdentifiant());
        
        Assert.assertNotNull(guest.getService());
        Assert.assertEquals("AAAA", guest.getService().getCode4());
        
    }
    
    
    
    @Test
    @Transactional
    @Rollback(true)
    public void findAllTest() throws SQLException{
        
        List<Guest> guests = guestService.findAll();
        
        Assert.assertNotNull(guests);
        Assert.assertEquals(0, guests.size());
        
        guestService.saveOrUpdate(guest);
        
        guests = guestService.findAll();
        Assert.assertNotNull(guests);
        Assert.assertEquals(1, guests.size());
        
        guestService.saveOrUpdate(guest2);
        
        guests = guestService.findAll();
        Assert.assertNotNull(guests);
        Assert.assertEquals(2, guests.size());
        
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void findAllGuestInfoTest() throws SQLException{
        
        List<GuestInfo> guestInfos = guestService.findAllGuestInfo();
        
        Assert.assertNotNull(guestInfos);
        Assert.assertEquals(0, guestInfos.size());
        
        guestService.saveOrUpdate(guest);
        
        guestInfos = guestService.findAllGuestInfo();
        Assert.assertNotNull(guestInfos);
        Assert.assertEquals(1, guestInfos.size());
        
        guestService.saveOrUpdate(guest2);
        
        guestInfos = guestService.findAllGuestInfo();
        Assert.assertNotNull(guestInfos);
        Assert.assertEquals(2, guestInfos.size());
        
    }
    
    
    @Test
    @Transactional
    @Rollback(true)
    public void deleteByIdTest() throws SQLException{
        
        guest = guestService.saveOrUpdate(guest);
        
        Assert.assertNotNull(guest);
        Assert.assertNotNull(guest.getId());
        Assert.assertEquals("Doe", guest.getNom());
        Assert.assertEquals("John", guest.getPrenom());
        Assert.assertEquals("Developpeur", guest.getFonction());
        Assert.assertEquals("+687 35.24.78", guest.getLigneDirecte());
        Assert.assertEquals("6547", guest.getPoste());
        
        guestService.deleteById(guest.getId());
        Guest newGuest = guestService.findById(guest.getId());
        Assert.assertNull(newGuest);
        
        
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void findByIdentifiantTest(){
        guest = guestService.saveOrUpdate(guest);
        guest2 = guestService.saveOrUpdate(guest2);
        
        System.out.println("id="+guest.getIdentifiant());
        List<Guest> results = guestService.findAll();
        Guest result = guestService.findByIdentifiant(guest.getIdentifiant());
        Assert.assertNotNull(result);
        Assert.assertEquals(guest.getId(), result.getId());
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void findByNomEtServiceTest() throws SQLException{
        guest = guestService.saveOrUpdate(guest);
        guest2 = guestService.saveOrUpdate(guest2);
        
        List<Guest> results = guestService.findByNomEtService("Doe", secteurSED.getLibelle());
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertTrue(guestListContains(guest.getId(), results));
        
        results = guestService.findByNomEtService("Doe", null);
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        Assert.assertTrue(guestListContains(guest.getId(), results));
        Assert.assertTrue(guestListContains(guest2.getId(), results));
        
        results = guestService.findByNomEtService(null,secteurDSI.getLibelle());
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertTrue(guestListContains(guest2.getId(), results));
        
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void findGuestInfoByNomEtServiceTest() throws SQLException{
        guest = guestService.saveOrUpdate(guest);
        guest2 = guestService.saveOrUpdate(guest2);
        
        List<GuestInfo> results = guestService.findGuestInfoByNomEtService("Doe", secteurSED.getLibelle());
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertTrue(guestInfoListContains(guest.getId(), results));
        
        results = guestService.findGuestInfoByNomEtService("Doe", null);
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        Assert.assertTrue(guestInfoListContains(guest.getId(), results));
        Assert.assertTrue(guestInfoListContains(guest2.getId(), results));
        
        results = guestService.findGuestInfoByNomEtService(null,secteurDSI.getLibelle());
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertTrue(guestInfoListContains(guest2.getId(), results));
        
    }
    
    
    private boolean guestListContains(Long id, List<Guest> results){
        boolean found = false;
        
        for (Guest result : (List<Guest>)results) {
            if(result.getId().equals(id)){
                found = true;
                break;
            }
        }
        
        return found;
    }
    
    private boolean guestInfoListContains(Long id, List<GuestInfo> results){
        boolean found = false;
        
        for (GuestInfo result : results) {
            if(result.getId().equals(id)){
                found = true;
                break;
            }
        }
        
        return found;
    }
    
    
    
}
