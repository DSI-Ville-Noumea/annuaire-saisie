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

import java.util.ArrayList;
import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.config.security.TestConfig;
import nc.noumea.mairie.annuairev2.saisie.core.exception.BusinessException;
import nc.noumea.mairie.annuairev2.saisie.core.security.CodePermission;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author barmi83
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class UtilisateurServiceTest {
    
    private static Logger LOGGER = LoggerFactory.getLogger(UtilisateurServiceTest.class);
    
    @Autowired
            IUtilisateurService utilisateurService;
    @Autowired
            IProfilDao profilDao;
    @Autowired
            IPermissionDao permissionDao;
    @Autowired
            IProfilService profilService;
    
    @Before
    public void init(){
        
        Permission permission1 = new Permission();
        permission1.setCode(CodePermission.GUEST_ADD);
        permission1 = permissionDao.findById(permissionDao.save(permission1));
        
        Permission permission2 = new Permission();
        permission2.setCode(CodePermission.GUEST_DEL);
        permission2 = permissionDao.findById(permissionDao.save(permission2));
        
        Permission permission3 = new Permission();
        permission3.setCode(CodePermission.GUEST_CONSULT);
        permission3 = permissionDao.findById(permissionDao.save(permission3));
        
        Profil profilGestionnaire = new Profil();
        profilGestionnaire.setNom(CodeProfil.GESTIONNAIRE_GUEST);
        profilGestionnaire.setPermissions(new ArrayList<>());
        
        profilGestionnaire.getPermissions().add(permission1);
        profilGestionnaire.getPermissions().add(permission2);
        profilGestionnaire.getPermissions().add(permission3);
        
        profilDao.save(profilGestionnaire);
    }
    
    
    @Test(expected = BusinessException.class)
    @Transactional
    @Rollback(true)
    public void createUtilisateurTest_failNom() throws BusinessException {
        
        Utilisateur user = new Utilisateur();
        user.setNom("dupond98");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        user.setProfil(profilService.findByProfilName(CodeProfil.GESTIONNAIRE_GUEST));
        
        utilisateurService.createUtilisateur(user);
        
    }
    
    @Transactional
    @Rollback(true)
    @Test(expected = BusinessException.class)
    public void createUtilisateurTest_failPrenom() throws BusinessException {
        
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("tot9o");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        user.setProfil(profilService.findByProfilName(CodeProfil.GESTIONNAIRE_GUEST));
        
        utilisateurService.createUtilisateur(user);
        
    }
    
    @Transactional
    @Rollback(true)
    @Test(expected = BusinessException.class)
    public void createUtilisateurTest_failIdentifiant() throws BusinessException {
        
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto969");
        user.setProfil(profilService.findByProfilName(CodeProfil.GESTIONNAIRE_GUEST));
        
        utilisateurService.createUtilisateur(user);
        
    }
    
    @Transactional
    @Rollback(true)
    @Test(expected = BusinessException.class)
    public void createUtilisateurTest_failLoginAlreadyExists() throws BusinessException {
        
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("simon");
        user.setActif(true);
        user.setIdentifiant("dupsi99");
        user.setProfil(profilService.findByProfilName(CodeProfil.GESTIONNAIRE_GUEST));
        
        utilisateurService.createUtilisateur(user);
        
        Utilisateur user2 = new Utilisateur();
        user2.setNom("dupont");
        user2.setPrenom("simone");
        user2.setActif(true);
        user2.setIdentifiant("dupsi99");
        user2.setProfil(profilService.findByProfilName(CodeProfil.GESTIONNAIRE_GUEST));
        
        utilisateurService.createUtilisateur(user2);
        
    }
    
    @Transactional
    @Rollback(true)
    @Test
    public void createUtilisateurTest() throws BusinessException {
        
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        user.setProfil(profilService.findByProfilName(CodeProfil.GESTIONNAIRE_GUEST));
        
        Utilisateur result = utilisateurService.createUtilisateur(user);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        Assert.assertEquals("DUPOND", result.getNom());
        Assert.assertEquals("toto", result.getPrenom());
        Assert.assertEquals(true, result.isActif());
        Assert.assertEquals("dupto96", result.getIdentifiant());
        Assert.assertEquals(CodeProfil.GESTIONNAIRE_GUEST, result.getProfil().getNom());
        
    }
    
    @Transactional
    @Rollback(true)
    @Test
    public void findByIdTest() throws BusinessException {
        
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        user.setProfil(profilService.findByProfilName(CodeProfil.GESTIONNAIRE_GUEST));
        
        Utilisateur result = utilisateurService.createUtilisateur(user);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        
        Utilisateur result2 = utilisateurService.findById(result.getId());
        Assert.assertNotNull(result2);
        Assert.assertNotNull(result2.getId());
        
        Assert.assertEquals("DUPOND", result2.getNom());
        Assert.assertEquals("toto", result2.getPrenom());
        Assert.assertEquals(true, result2.isActif());
        Assert.assertEquals("dupto96", result2.getIdentifiant());
        Assert.assertEquals(CodeProfil.GESTIONNAIRE_GUEST, result2.getProfil().getNom());
        
    }
    
    @Transactional
    @Rollback(true)
    @Test
    public void findAllTest() throws BusinessException {
        
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        user.setProfil(profilService.findByProfilName(CodeProfil.GESTIONNAIRE_GUEST));
        
        user = utilisateurService.createUtilisateur(user);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        
        Utilisateur user2 = new Utilisateur();
        user2.setNom("barre");
        user2.setPrenom("michele");
        user2.setActif(true);
        user2.setIdentifiant("barmi83");
        user2.setProfil(profilService.findByProfilName(CodeProfil.GESTIONNAIRE_GUEST));
        
        user2 = utilisateurService.createUtilisateur(user2);
        Assert.assertNotNull(user2);
        Assert.assertNotNull(user2.getId());
        
        List<Utilisateur> results = utilisateurService.findAll();
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        
    }
    
    @Transactional
    @Rollback(true)
    @Test
    public void updateTest() throws BusinessException {
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        user.setProfil(profilService.findByProfilName(CodeProfil.GESTIONNAIRE_GUEST));
        
        user = utilisateurService.createUtilisateur(user);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals("DUPOND", user.getNom());
        Assert.assertEquals("toto", user.getPrenom());
        Assert.assertEquals(true, user.isActif());
        Assert.assertEquals("dupto96", user.getIdentifiant());
        
        user.setNom("dupont");
        user.setPrenom("titi");
        user.setIdentifiant("dupti96");
        
        user = utilisateurService.updateUtilisateur(user);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals("DUPONT", user.getNom());
        Assert.assertEquals("titi", user.getPrenom());
        Assert.assertEquals(true, user.isActif());
        Assert.assertEquals("dupti96", user.getIdentifiant());
        
    }
    
    
    @Transactional
    @Rollback(true)
    @Test
    public void deleteTest() throws BusinessException {
        
        Utilisateur user = new Utilisateur();
        user.setNom("dupond");
        user.setPrenom("toto");
        user.setActif(true);
        user.setIdentifiant("dupto96");
        user.setProfil(profilService.findByProfilName(CodeProfil.GESTIONNAIRE_GUEST));
        
        user = utilisateurService.createUtilisateur(user);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        
        Utilisateur user2 = new Utilisateur();
        user2.setNom("barre");
        user2.setPrenom("michele");
        user2.setActif(true);
        user2.setIdentifiant("barmi83");
        user2.setProfil(profilService.findByProfilName(CodeProfil.GESTIONNAIRE_GUEST));
        
        user2 = utilisateurService.createUtilisateur(user2);
        Assert.assertNotNull(user2);
        Assert.assertNotNull(user2.getId());
        
        List<Utilisateur> results = utilisateurService.findAll();
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
        
        utilisateurService.deleteUtilisateur(user2);
        results = utilisateurService.findAll();
        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("DUPOND", results.get(0).getNom());
        
    }
    
}
