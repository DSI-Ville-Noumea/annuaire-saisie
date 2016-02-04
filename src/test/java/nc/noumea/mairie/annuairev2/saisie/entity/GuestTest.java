/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nc.noumea.mairie.annuairev2.saisie.entity;

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

import nc.noumea.mairie.annuairev2.saisie.config.security.TestConfig;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author barmi83
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class GuestTest {
    
    @Test
    public void equalsContractTest() {
        
        Sectorisation secteurDSI = new Sectorisation();
        secteurDSI.setId(1L);
        secteurDSI.setCode4("AAAA");
        secteurDSI.setLibelle("Direction des Services de l'Information");
        secteurDSI.setSigle("DSI");
        
        Sectorisation secteurSED = new Sectorisation();
        secteurSED.setId(2L);
        secteurSED.setCode4("ABAA");
        secteurSED.setLibelle("Service Etudes et Développpement");
        secteurSED.setSigle("SED");
        secteurSED.setParent(secteurDSI);
        
        
        EqualsVerifier.forClass(Guest.class)
                .withPrefabValues(Sectorisation.class, secteurDSI, secteurSED)
                .verify();
    }
    
    @Test
    public void fullNameTest(){
        Sectorisation secteurSED = new Sectorisation();
        secteurSED.setCode4("ABAA");
        secteurSED.setLibelle("Service Etudes et Développpement");
        secteurSED.setSigle("SED");
        
        Guest guest = new Guest();
        guest.setNom("Doe");
        guest.setPrenom("John");
        guest.setFonction("Developpeur");
        guest.setLigneDirecte("+687 35.24.78");
        guest.setPoste("6547");
        guest.setService(secteurSED);
        
        Assert.assertEquals("Doe John", guest.getFullName());
    }
    
    @Test
    public void guestInfoFullNameTest(){
        Sectorisation secteurSED = new Sectorisation();
        secteurSED.setCode4("ABAA");
        secteurSED.setLibelle("Service Etudes et Développpement");
        secteurSED.setSigle("SED");
        
        GuestInfo guest = new GuestInfo();
        guest.setNom("Doe");
        guest.setPrenom("John");
        guest.setFonction("Developpeur");
        guest.setLigneDirecte("+687 35.24.78");
        guest.setPoste("6547");
        guest.setService(secteurSED);
        
        Assert.assertEquals("Doe John", guest.getFullName());
    }
    
    @Test
    public void fullNameEmptyTest(){
        Guest guest = new Guest();
        Assert.assertEquals("", guest.getFullName());
    }
    
     @Test
    public void guestInfoFullNameEmptyTest(){
        GuestInfo guest = new GuestInfo();
        Assert.assertEquals("", guest.getFullName());
    }
    
    @Test
    public void compareTest(){
        Sectorisation secteurSED = new Sectorisation();
        secteurSED.setCode4("ABAA");
        secteurSED.setLibelle("Service Etudes et Développpement");
        secteurSED.setSigle("SED");
        
        Guest guest = new Guest();
        guest.setNom("Doe");
        guest.setPrenom("John");
        guest.setFonction("Developpeur");
        guest.setLigneDirecte("+687 35.24.78");
        guest.setPoste("6547");
        guest.setService(secteurSED);
        
        Guest guest2 = new Guest();
        guest2.setNom("Doe");
        guest2.setPrenom("Jane");
        guest2.setFonction("Chef de projet");
        guest2.setLigneDirecte("+687 35.24.79");
        guest2.setPoste("6548");
        guest2.setService(secteurSED);
        
        Assert.assertTrue(guest.compareTo(guest2) > 0);
        
    }
    
    @Test
    public void guestInfoCompareTest(){
        Sectorisation secteurSED = new Sectorisation();
        secteurSED.setCode4("ABAA");
        secteurSED.setLibelle("Service Etudes et Développpement");
        secteurSED.setSigle("SED");
        
        GuestInfo guest = new GuestInfo();
        guest.setNom("Doe");
        guest.setPrenom("John");
        guest.setFonction("Developpeur");
        guest.setLigneDirecte("+687 35.24.78");
        guest.setPoste("6547");
        guest.setService(secteurSED);
        
        GuestInfo guest2 = new GuestInfo();
        guest2.setNom("Doe");
        guest2.setPrenom("Jane");
        guest2.setFonction("Chef de projet");
        guest2.setLigneDirecte("+687 35.24.79");
        guest2.setPoste("6548");
        guest2.setService(secteurSED);
        
        Assert.assertTrue(guest.compareTo(guest2) > 0);
        
    }
    
    @Test
    public void typeTest(){
        Guest guest = new Guest();
        guest.setNom("Doe");
        guest.setPrenom("John");
        guest.setFonction("Developpeur");
        guest.setLigneDirecte("+687 35.24.78");
        guest.setPoste("6547");
        
        Assert.assertEquals(IContact.TYPE_GUEST, guest.getType());
    }
    
    @Test
    public void guestInfoTypeTest(){
        GuestInfo guest = new GuestInfo();
        guest.setNom("Doe");
        guest.setPrenom("John");
        guest.setFonction("Developpeur");
        guest.setLigneDirecte("+687 35.24.78");
        guest.setPoste("6547");
        
        Assert.assertEquals(IContact.TYPE_GUEST, guest.getType());
    }
    
    @Test
    public void identifantTest(){
        Guest guest = new Guest();
        guest.setId(1L);
        guest.setNom("Doe");
        guest.setPrenom("John");
        guest.setFonction("Developpeur");
        guest.setLigneDirecte("+687 35.24.78");
        guest.setPoste("6547");
        
        Assert.assertEquals("G0001", guest.getIdentifiant());
    }
    
    @Test
    public void guestInfoIdentifantTest(){
        GuestInfo guest = new GuestInfo();
        guest.setId(1L);
        guest.setNom("Doe");
        guest.setPrenom("John");
        guest.setFonction("Developpeur");
        guest.setLigneDirecte("+687 35.24.78");
        guest.setPoste("6547");
        
        Assert.assertEquals("G0001", guest.getIdentifiant());
    }
    
    
    
}
