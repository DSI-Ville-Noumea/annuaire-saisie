package nc.noumea.mairie.annuairev2.saisie.viewmodel;

/*
 * #%L
 * Gestion des Locality et Locality
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

import nc.noumea.mairie.annuairev2.saisie.service.IGuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nc.noumea.mairie.annuairev2.saisie.core.security.SecurityUtil;
import nc.noumea.mairie.annuairev2.saisie.entity.GuestInfo;
import nc.noumea.mairie.annuairev2.saisie.entity.Locality;
import nc.noumea.mairie.annuairev2.saisie.entity.Sectorisation;
import nc.noumea.mairie.annuairev2.saisie.entity.Utilisateur;
import nc.noumea.mairie.annuairev2.saisie.service.ILocalityService;
import nc.noumea.mairie.annuairev2.saisie.service.ISectorisationService;
import nc.noumea.mairie.annuairev2.saisie.service.IUtilisateurService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;


/**
 * Created by barmi83 on 29/12/15.
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class LocalitySearchViewModel extends AbstractViewModel {

    private Logger logger = LoggerFactory.getLogger(LocalitySearchViewModel.class);

    private String nom = null;
    private String service = null;
    private List<Locality> searchResults = null;
    private Locality selectedEntity = null;
    private boolean readOnly;
    private List<Sectorisation> services;
    private Utilisateur user;
    private List<String> serviceNameList;
    private StringSimpleListModel servicesListModel;



    @WireVariable
    private ILocalityService localityService;
    @WireVariable
    private ISectorisationService sectorisationService;
     @WireVariable
    private IUtilisateurService utilisateurService;

    @Init
    @NotifyChange("*")
    public void initView() {
        readOnly = true;
        searchResults = localityService.findAll();
        services = sectorisationService.findAll();
        serviceNameList = new ArrayList<>();
        setUser(utilisateurService.findByLogin(SecurityUtil.getUser()));
        
        initSearchFields();
        
        if(! searchResults.isEmpty())
            selectedEntity = searchResults.get(0);
    }

    public List<Locality> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Locality> searchResults) {
        this.searchResults = searchResults;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Locality getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(Locality selectedEntity) {
        this.selectedEntity = selectedEntity;
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

    public List<String> getServiceNameList() {
        return serviceNameList;
    }

    public void setServiceNameList(List<String> serviceNameList) {
        this.serviceNameList = serviceNameList;
    }

    public StringSimpleListModel getServicesListModel() {
        return servicesListModel;
    }

    public void setServicesListModel(StringSimpleListModel servicesListModel) {
        this.servicesListModel = servicesListModel;
    }
    
    
    
    
    
    @Command
    public void editLocality(@BindingParam("idLocality") Long idLocality){
        
        Map<String, Object> args = new HashMap<>();
        args.put("idLocality", idLocality);
        BindUtils.postGlobalCommand(null, null, "openAdminLocalityTab", args);

    }
    
    @Command
    public void newLocality(){
        
        BindUtils.postGlobalCommand(null, null, "openAdminLocalityTab", null);

    }
    
    @Command
    @NotifyChange({"searchResults"})
    public void confirmDelete(){
        Messagebox.show("Vous allez supprimer la locality \"" + selectedEntity.getFullName()
                    + "\".\n Cliquez sur OK pour confirmer.",
                    "Supprimer une locality", Messagebox.OK |
                            Messagebox.CANCEL, Messagebox.QUESTION,
                    new EventListener() {
                        public void onEvent(Event e) {

                            if (Messagebox.ON_OK.equals(e.getName())) {
                                localityService.deleteById(selectedEntity.getId());
                                Map<String, Object> args = new HashMap<>();
                                args.put("tabId", "adminLocalityTab_"+selectedEntity.getId());
                                BindUtils.postGlobalCommand(null, null, "closeTabById", args);
                                showBottomRightNotification("Locality supprimée avec succès.");
                            }

                        }
                    });
        }        
    
    @Command
    @NotifyChange({"searchResults"})
    public void search(){
        if(nom.isEmpty())
            nom = null;
        if(service != null && service.isEmpty())
            service = null;
        
        if(nom != null || service != null)
            searchResults = localityService.findByNomEtService(nom, service);
        else
            searchResults = localityService.findAll();
        
    }
    
    @Command
    @NotifyChange({"*"})
    public void resetSearch(){
        
        nom = null;
        service = null;
        searchResults = localityService.findAll();
                
    }
    
     @NotifyChange("*")
    private void initSearchFields() {
        nom = null;
        serviceNameList.clear();
                
        for (Sectorisation service : services) {
	    serviceNameList.add(service.getLibelle());
	}
	Collections.sort(serviceNameList);
	servicesListModel = new StringSimpleListModel(serviceNameList);
    }
}
