package nc.noumea.mairie.annuairev2.saisie.viewmodel;

/*
 * #%L
 * Gestion des Locality et Locality
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nc.noumea.mairie.annuairev2.saisie.core.security.CodeProfil;
import nc.noumea.mairie.annuairev2.saisie.core.security.SecurityUtil;
import nc.noumea.mairie.annuairev2.saisie.entity.Locality;
import nc.noumea.mairie.annuairev2.saisie.entity.Sectorisation;
import nc.noumea.mairie.annuairev2.saisie.entity.Utilisateur;
import nc.noumea.mairie.annuairev2.saisie.service.ILocalityService;
import nc.noumea.mairie.annuairev2.saisie.service.ISectorisationService;
import nc.noumea.mairie.annuairev2.saisie.service.IUtilisateurService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

/**
 *
 * @author barmi83
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AdminLocalityViewModel extends AbstractViewModel {
    
    @WireVariable
    private ILocalityService localityService;
    @WireVariable
    private ISectorisationService sectorisationService;
    @WireVariable
    private IUtilisateurService utilisateurService;
     
    private Locality selectedEntity;
    private boolean readOnly;
    private List<Sectorisation> services;
    private Utilisateur user;
    private boolean createMode;
       
    
    @Init
    @NotifyChange("*")
    public void initView(@ExecutionArgParam("args") Map<String, Object> args) {
        setUser(utilisateurService.findByLogin(SecurityUtil.getUser()));
        this.readOnly = !(user.getProfil().getNom() == CodeProfil.ADMIN || 
                user.getProfil().getNom() == CodeProfil.GESTIONNAIRE || 
                user.getProfil().getNom()== CodeProfil.GESTIONNAIRE_LOCALITY);
        this.services = sectorisationService.findAll();
        Collections.sort(services);
        
        if(args.get("idLocality") != null){
            this.selectedEntity = localityService.findById((Long)args.get("idLocality"));
            this.createMode = false;
        }
        else{
            this.selectedEntity = new Locality();
            this.createMode = true;
        }
    }

    public Locality getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(Locality locality) {
        this.selectedEntity = locality;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
    
    public List<Sectorisation> getServices() {
        return services;
    }

    public void setServices(List<Sectorisation> services) {
        this.services = services;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public boolean isCreateMode() {
        return createMode;
    }

    public void setCreateMode(boolean createMode) {
        this.createMode = createMode;
    }
    
    
    
    
    @Command
    @NotifyChange({"selectedEntity"})
    public void  saveOrUpdateLocality(){
        selectedEntity = localityService.saveOrUpdate(selectedEntity);
        Map<String, Object> args = new HashMap<>();
        args.put("tmpTabId", "tmpLocalityTab");
        args.put("idLocality", selectedEntity.getId());
        BindUtils.postGlobalCommand(null, null, "refreshNewLocalityTabId", args);
        this.showBottomRightNotification("Locality modifié avec succés.");
    }
    
    @Command
    @NotifyChange({"selectedEntity"})
    public void  refresh(){
        if(selectedEntity.getId() != null)
            selectedEntity = localityService.findById(selectedEntity.getId());
        else
            selectedEntity = new Locality();
        
        this.showBottomRightNotification("Modifications annulées.");
    }
    
    @Command
    public void  deleteLocality(){
        
        if(selectedEntity.getId() == null)
            BindUtils.postGlobalCommand(null, null, "closeSelectedTab", null);
        else{
            Messagebox.show("Vous allez supprimer la locality \"" + selectedEntity.getFullName()
                    + "\".\n Cliquez sur OK pour confirmer.",
                    "Supprimer une locality", Messagebox.OK |
                            Messagebox.CANCEL, Messagebox.QUESTION, (Event e) -> {
                                if (Messagebox.ON_OK.equals(e.getName())) {
                                    localityService.deleteById(selectedEntity.getId());
                                    BindUtils.postGlobalCommand(null, null, "closeSelectedTab", null);
                                    showBottomRightNotification("Locality supprimé avec succès.");
                                }
            });
        }        
    }
    
    
}
