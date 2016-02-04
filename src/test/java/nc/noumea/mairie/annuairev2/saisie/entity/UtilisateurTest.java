package nc.noumea.mairie.annuairev2.saisie.entity;

/*
* #%L
 * Gestion des Guest et Locality
 * * *
 * *
 * %%
 * Copyright (C) 2015 - 2016 Mairie de Nouméa
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

import nc.noumea.mairie.annuairev2.saisie.config.security.TestConfig;
import nc.noumea.mairie.annuairev2.saisie.core.security.CodePermission;
import nc.noumea.mairie.annuairev2.saisie.core.security.CodeProfil;
import nc.noumea.mairie.annuairev2.saisie.core.security.Permission;
import nc.noumea.mairie.annuairev2.saisie.core.security.Profil;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Before;
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
public class UtilisateurTest {
    
    
    private Permission permGuestAdd;
    private Permission permGuestDel;
    private Permission permGuestConsult;
    
    private Permission permLocalityAdd;
    private Permission permLocalityDel;
    private Permission permLocalityConsult;
    
    private Profil profilGestionnaireGuest;
    private Profil profilGestionnaireLocality;
    private Profil profilGestionnaire;
    private Profil profilAdmin;
    private Profil profilConsultant;
    
    @Before
    public void init(){
        permGuestAdd = new Permission();
        permGuestAdd.setCode(CodePermission.GUEST_ADD);
        
        permGuestDel = new Permission();
        permGuestDel.setCode(CodePermission.GUEST_DEL);
        
        permGuestConsult = new Permission();
        permGuestConsult.setCode(CodePermission.GUEST_CONSULT);
        
        permLocalityAdd = new Permission();
        permLocalityAdd.setCode(CodePermission.LOCALITY_ADD);
        
        permLocalityDel = new Permission();
        permLocalityDel.setCode(CodePermission.LOCALITY_DEL);
        
        permLocalityConsult = new Permission();
        permLocalityConsult.setCode(CodePermission.LOCALITY_CONSULT);
        
        profilGestionnaireGuest = new Profil();
        profilGestionnaireGuest.setNom(CodeProfil.GESTIONNAIRE_GUEST);
        
        profilGestionnaireLocality = new Profil();
        profilGestionnaireLocality.setNom(CodeProfil.GESTIONNAIRE_LOCALITY);
        
        profilGestionnaire = new Profil();
        profilGestionnaire.setNom(CodeProfil.GESTIONNAIRE);
        
        profilAdmin = new Profil();
        profilAdmin.setNom(CodeProfil.ADMIN);
        
        profilConsultant = new Profil();
        profilConsultant.setNom(CodeProfil.CONSULTANT);
        
        
    }
    
    @Test
    public void equalsContractTest() {
        
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        user.setProfil(profilGestionnaire);
        
        
        EqualsVerifier.forClass(Utilisateur.class)
                .verify();
    }
    
    
    @Test
    public void isConsultantTest(){
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        
        user.setProfil(profilGestionnaire);
        Assert.assertFalse(user.isConsultant());
        
        user.setProfil(profilGestionnaireGuest);
        Assert.assertFalse(user.isConsultant());
        
        user.setProfil(profilGestionnaireLocality);
        Assert.assertFalse(user.isConsultant());
        
        user.setProfil(profilAdmin);
        Assert.assertFalse(user.isConsultant());
        
        user.setProfil(profilConsultant);
        Assert.assertTrue(user.isConsultant());
    }
    
    @Test
    public void isAdministrateurTest(){
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        
        user.setProfil(profilGestionnaire);
        Assert.assertFalse(user.isAdministrateur());
        
        user.setProfil(profilGestionnaireGuest);
        Assert.assertFalse(user.isAdministrateur());
        
        user.setProfil(profilGestionnaireLocality);
        Assert.assertFalse(user.isAdministrateur());
        
        user.setProfil(profilAdmin);
        Assert.assertTrue(user.isAdministrateur());
        
        user.setProfil(profilConsultant);
        Assert.assertFalse(user.isAdministrateur());
    }
    
    @Test
    public void isGestionnaireTest(){
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        
        user.setProfil(profilGestionnaire);
        Assert.assertTrue(user.isGestionnaire());
        
        user.setProfil(profilGestionnaireGuest);
        Assert.assertFalse(user.isGestionnaire());
        
        user.setProfil(profilGestionnaireLocality);
        Assert.assertFalse(user.isGestionnaire());
        
        user.setProfil(profilAdmin);
        Assert.assertTrue(user.isGestionnaire());
        
        user.setProfil(profilConsultant);
        Assert.assertFalse(user.isGestionnaire());
    }
    
    @Test
    public void isGestionnaireLocalityTest(){
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        
        user.setProfil(profilGestionnaire);
        Assert.assertTrue(user.isGestionnaireLocality());
        
        user.setProfil(profilGestionnaireGuest);
        Assert.assertFalse(user.isGestionnaireLocality());
        
        user.setProfil(profilGestionnaireLocality);
        Assert.assertTrue(user.isGestionnaireLocality());
        
        user.setProfil(profilAdmin);
        Assert.assertTrue(user.isGestionnaireLocality());
        
        user.setProfil(profilConsultant);
        Assert.assertFalse(user.isGestionnaireLocality());
    }
    
    @Test
    public void isGestionnaireGuestTest(){
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        
        user.setProfil(profilGestionnaire);
        Assert.assertTrue(user.isGestionnaireGuest());
        
        user.setProfil(profilGestionnaireGuest);
        Assert.assertTrue(user.isGestionnaireGuest());
        
        user.setProfil(profilGestionnaireLocality);
        Assert.assertFalse(user.isGestionnaireGuest());
        
        user.setProfil(profilAdmin);
        Assert.assertTrue(user.isGestionnaireGuest());
        
        user.setProfil(profilConsultant);
        Assert.assertFalse(user.isGestionnaireGuest());
    }
    
    @Test
    public void fullNameTest(){
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        
        Assert.assertEquals("dupond toto", user.getFullName());
    }
    
    @Test
    public void compareTest(){
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        
        Utilisateur user2 = new Utilisateur();
        user.setNom("dupont");
        user.setPrenom("titi");
        user.setActif(true);
        user.setIdentifiant("dupti96");
        
        Assert.assertTrue(user.compareTo(user2) < 0);
    }
}
