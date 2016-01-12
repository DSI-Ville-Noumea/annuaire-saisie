package nc.noumea.mairie.annuairev2.saisie.viewmodel;

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
import nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity;
import nc.noumea.mairie.annuairev2.saisie.core.security.SecurityUtil;
import nc.noumea.mairie.annuairev2.saisie.entity.IContact;
import nc.noumea.mairie.annuairev2.saisie.entity.Sectorisation;
import nc.noumea.mairie.annuairev2.saisie.entity.Utilisateur;
import nc.noumea.mairie.annuairev2.saisie.service.ILocalityService;
import nc.noumea.mairie.annuairev2.saisie.service.ISectorisationService;
import nc.noumea.mairie.annuairev2.saisie.service.IUtilisateurService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;


/**
 * Created by barmi83 on 29/12/15.
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SearchViewModel extends AbstractViewModel {

    private Logger logger = LoggerFactory.getLogger(SearchViewModel.class);

    private String nom = null;
    private String service = null;
    private List<IContact> searchResults = null;
    private IContact selectedEntity = null;
    private boolean readOnly;
    private List<Sectorisation> services;
    private Utilisateur user;
    private List<String> serviceNameList;
    private StringSimpleListModel servicesListModel;
    private String searchMode;



    @WireVariable
    private IGuestService guestService;
    @WireVariable
    private ILocalityService localityService;
    @WireVariable
    private ISectorisationService sectorisationService;
     @WireVariable
    private IUtilisateurService utilisateurService;

    @Init
    @NotifyChange("*")
     public void initView(@ExecutionArgParam("args") Map<String, Object> args) {
        readOnly = true;
               
        resetSearch();
        
        services = sectorisationService.findAll();
        serviceNameList = new ArrayList<>();
        setUser(utilisateurService.findByLogin(SecurityUtil.getUser()));
        
        initSearchFields();
        
        if(! searchResults.isEmpty())
            selectedEntity = searchResults.get(0);
        
    }

    public List<IContact> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<IContact> searchResults) {
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

    public IContact getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(IContact selectedEntity) {
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

    public String getSearchMode() {
        return searchMode;
    }

    @NotifyChange("searchMode")
    public void setSearchMode(String searchMode) {
        this.searchMode = searchMode;
    }
       
    
    @Command
    public void editEntity(@BindingParam("idEntity") Long idEntity, @BindingParam("type") String type){
        System.out.println("idEntity="+idEntity+", type="+type);
        Map<String, Object> args = new HashMap<>();
        args.put("idEntity", idEntity);
        if(IContact.TYPE_GUEST.equals(type))
            BindUtils.postGlobalCommand(null, null, "openAdminGuestTab", args);
        else 
            BindUtils.postGlobalCommand(null, null, "openAdminLocalityTab", args);

    }
    
    @Command
    public void newGuest(){
        BindUtils.postGlobalCommand(null, null, "openAdminGuestTab", null);
    }
    
    @Command
    public void newLocality(){
        BindUtils.postGlobalCommand(null, null, "openAdminLocalityTab", null);
    }
    
    @Command
    @NotifyChange({"searchResults"})
    public void confirmDelete(){
        Messagebox.show("Vous allez supprimer "+ (IContact.TYPE_GUEST.equals(selectedEntity.getType()) ? "le guest" : "la locality") 
                    + " \"" + ((IContact)selectedEntity).getFullName()
                    + "\".\n Cliquez sur OK pour confirmer.",
                    (IContact.TYPE_GUEST.equals(selectedEntity.getType()) ? "Supprimer un guest" : "Supprimer une locality"), 
                    Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                    new EventListener() {
                        public void onEvent(Event e) {

                            if (Messagebox.ON_OK.equals(e.getName())) {
                                if(IContact.TYPE_GUEST.equals(selectedEntity.getType())){
                                    guestService.deleteById(((AbstractEntity)selectedEntity).getId());
                                    Map<String, Object> args = new HashMap<>();
                                    args.put("tabId", "adminGuestTab_"+((AbstractEntity)selectedEntity).getId());
                                    BindUtils.postGlobalCommand(null, null, "closeTabById", args);
                                    showBottomRightNotification("Guest supprimé avec succès.");
                                }
                                else{
                                    localityService.deleteById(((AbstractEntity)selectedEntity).getId());
                                    Map<String, Object> args = new HashMap<>();
                                    args.put("tabId", "adminLocalityTab_"+((AbstractEntity)selectedEntity).getId());
                                    BindUtils.postGlobalCommand(null, null, "closeTabById", args);
                                    showBottomRightNotification("Locality supprimée avec succès.");
                                }
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
        
        if(IContact.TYPE_GUEST.equals(searchMode)){        
            if(nom != null || service != null)
                searchResults = (List<IContact>) (Object) guestService.findGuestInfoByNomEtService(nom, service);
            else
                searchResults = (List<IContact>) (Object) guestService.findAllGuestInfo();
        }
        else if(IContact.TYPE_LOCALITY.equals(searchMode)){   
            if(nom != null || service != null)
                searchResults = (List<IContact>) (Object) localityService.findByNomEtService(nom, service);
            else
                searchResults = (List<IContact>) (Object) localityService.findAll();
        }
        else{
            if(nom != null || service != null){
                searchResults = (List<IContact>) (Object) guestService.findGuestInfoByNomEtService(nom, service);
                searchResults.addAll((List<IContact>) (Object) localityService.findByNomEtService(nom, service));
            }
            else{
                searchResults = (List<IContact>) (Object) guestService.findAllGuestInfo();
                searchResults.addAll((List<IContact>) (Object) localityService.findAll());
            }
        }
        
        Collections.sort(searchResults);
        
    }
    
    @Command
    @NotifyChange({"*"})
    public void resetSearch(){
        
        nom = null;
        service = null;
        searchResults = (List<IContact>) (Object)guestService.findAllGuestInfo();
        searchResults.addAll((List<IContact>) (Object)localityService.findAll());
        Collections.sort(searchResults);
                
    }
    
    @NotifyChange("*")
    private void initSearchFields() {
        nom = null;
        serviceNameList.clear();
        searchMode = "all";
        
        for (Sectorisation service : services) {
            serviceNameList.add(service.getLibelle());
        }
        Collections.sort(serviceNameList);
        servicesListModel = new StringSimpleListModel(serviceNameList);
    }
}
