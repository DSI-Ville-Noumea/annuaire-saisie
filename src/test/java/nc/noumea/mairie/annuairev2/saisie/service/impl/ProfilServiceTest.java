/**
 *
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

import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.config.security.TestConfig;
import nc.noumea.mairie.annuairev2.saisie.core.exception.BusinessException;
import nc.noumea.mairie.annuairev2.saisie.core.security.CodeProfil;
import nc.noumea.mairie.annuairev2.saisie.core.security.Permission;
import nc.noumea.mairie.annuairev2.saisie.core.security.Profil;
import nc.noumea.mairie.annuairev2.saisie.dao.IPermissionDao;
import nc.noumea.mairie.annuairev2.saisie.dao.IProfilDao;
import nc.noumea.mairie.annuairev2.saisie.entity.Utilisateur;
import nc.noumea.mairie.annuairev2.saisie.service.IProfilService;
import nc.noumea.mairie.annuairev2.saisie.service.IUtilisateurService;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author barmi83
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class ProfilServiceTest {
    
    private static Logger LOGGER = LoggerFactory.getLogger(ProfilServiceTest.class);
    
    @Autowired
    IUtilisateurService utilisateurService;
    @Autowired
    IProfilDao profilDao;
    @Autowired
    IPermissionDao permissionDao;
    @Autowired
    IProfilService profilService;
    
    private Profil profilGestionnaireGuest;
    private Profil profilGestionnaireLocality;
    private Profil profilGestionnaire;
    private Profil profilAdmin;
    private Profil profilConsultant;
    
    @Before
    public void init(){
      
        profilGestionnaire = new Profil();
        profilGestionnaire.setNom(CodeProfil.GESTIONNAIRE);
        profilGestionnaire = profilDao.findById(profilDao.save(profilGestionnaire));
        
    }
    
    
    @Transactional
    @Rollback(true)
    @Test
    public void findByLoginTest() throws BusinessException {
        
        Assert.assertNull(profilService.findByLogin("dupto96"));
        
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        user.setProfil(profilGestionnaire);
        
        utilisateurService.createUtilisateur(user);
        
        Profil result = profilService.findByLogin("dupto96");
        Assert.assertNotNull(result);
        Assert.assertEquals(CodeProfil.GESTIONNAIRE, result.getNom());
        
        
        
    }
    
    @Transactional
    @Rollback(true)
    @Test
    public void findAllTest() throws BusinessException {
        
        List<Profil> results = profilService.findAll();
        Assert.assertEquals(1, results.size());
        
        profilGestionnaireGuest = new Profil();
        profilGestionnaireGuest.setNom(CodeProfil.GESTIONNAIRE_GUEST);
        profilDao.save(profilGestionnaireGuest);
        
        profilGestionnaireLocality = new Profil();
        profilGestionnaireLocality.setNom(CodeProfil.GESTIONNAIRE_LOCALITY);
        profilDao.save(profilGestionnaireLocality);
        
        profilAdmin = new Profil();
        profilAdmin.setNom(CodeProfil.ADMIN);
        profilDao.save(profilAdmin);
        
        profilConsultant = new Profil();
        profilConsultant.setNom(CodeProfil.CONSULTANT);
        profilDao.save(profilConsultant);
        
        results = profilService.findAll();
        Assert.assertEquals(5, results.size());
                
        
    }
    
}
