/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.viewmodel;

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

import java.util.Collections;
import java.util.List;
import nc.noumea.mairie.annuairev2.saisie.core.exception.BusinessException;
import nc.noumea.mairie.annuairev2.saisie.core.security.Profil;
import nc.noumea.mairie.annuairev2.saisie.core.security.SecurityUtil;
import nc.noumea.mairie.annuairev2.saisie.entity.Utilisateur;
import nc.noumea.mairie.annuairev2.saisie.service.IProfilService;
import nc.noumea.mairie.annuairev2.saisie.service.IUtilisateurService;


import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

/**
 * @author barmi83
 * @since
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UtilisateurViewModel extends AbstractViewModel {

    private static final long serialVersionUID = 6400341038855494658L;

    @WireVariable
    private IUtilisateurService utilisateurService;
    @WireVariable
    private IProfilService profilService;
    
    private ListModelList<UtilisateurEditStatus> utilisateurList;
    private ListModelList<Profil> profilList;

    private Utilisateur user;

    @Init
    @NotifyChange("*")
    public void initView() {

	utilisateurList = generateUtilisateurStatusList(utilisateurService.findAll());
	profilList = new ListModelList<>(profilService.findAll());
        Collections.sort(profilList);
	user = utilisateurService.findByLogin(SecurityUtil.getUser());

    }

    @Command
    public void createUtilisateur() {
	UtilisateurEditStatus userStatus = new UtilisateurEditStatus(new Utilisateur(), true);
	((ListModelList<UtilisateurEditStatus>) utilisateurList).add(0, userStatus);
    }

    @Command
    @NotifyChange("utilisateurList")
    public void saveOrUpdateUser(@BindingParam("item") UtilisateurEditStatus utilEditStatus) {
	try {
	    if (utilEditStatus.getUtilisateur().getId() == null) {
		utilisateurService.createUtilisateur(utilEditStatus.getUtilisateur());
                showBottomRightNotification("Utilisateur crée avec succès.");
	    }
	    else {
		utilisateurService.updateUtilisateur(utilEditStatus.getUtilisateur());
                showBottomRightNotification("Utilisateur modifié avec succès.");
	    }

	} catch (BusinessException e) {
	    throw new RuntimeException(e.getMessage());
	} 

	refreshSelectedUtilisateur(utilEditStatus);
    }

    @Command
    @NotifyChange("utilisateurList")
    public void cancel(@BindingParam("item") UtilisateurEditStatus utilEditStatus) {
	if (utilEditStatus.getUtilisateur().getId() == null) {
	    ((ListModelList<UtilisateurEditStatus>) utilisateurList).remove(utilEditStatus);
	}
	else {
	    refreshSelectedUtilisateur(utilEditStatus);
	}
    }

    @SuppressWarnings("unchecked")
    @Command
    @NotifyChange("utilisateurList")
    public void confirmDelete(final @BindingParam("item") UtilisateurEditStatus utilEditStatus) {

	Messagebox.show("Vous allez supprimer l'utilisateur \"" + utilEditStatus.getUtilisateur().getFullName()
		+ "\".\n Cliquez sur OK pour confirmer.",
		"Supprimer un utilisateur", Messagebox.OK |
			Messagebox.CANCEL, Messagebox.QUESTION, (Event e) -> {
                            if (Messagebox.ON_OK.equals(e.getName())) {
                                deleteUtilisateur(utilEditStatus);
                                showBottomRightNotification("Utilisateur supprimé avec succès.");
                            }
        });

    }

    @NotifyChange("utilisateurList")
    public void deleteUtilisateur(@BindingParam("item") UtilisateurEditStatus utilEditStatus) {
	if (utilEditStatus.getUtilisateur().getId() == null) {
	    ((ListModelList<UtilisateurEditStatus>) utilisateurList).remove(utilEditStatus);
	}
	else {
	    utilisateurService.deleteUtilisateur(utilEditStatus.getUtilisateur());
	    ((ListModelList<UtilisateurEditStatus>) utilisateurList).remove(utilEditStatus);
	}
    }

    /**
     * @return the utilisateurList
     */
    public ListModelList<UtilisateurEditStatus> getUtilisateurList() {
	return utilisateurList;
    }

    /**
     * @return the profilList
     */
    public ListModelList<Profil> getProfilList() {
	return profilList;
    }

    @Command
    public void changeEditableStatus(@BindingParam("item") UtilisateurEditStatus utilEditStatus) {
	utilEditStatus.setEditingStatus(!utilEditStatus.isEditingStatus());
	refreshRowTemplate(utilEditStatus);
    }

    public void refreshRowTemplate(UtilisateurEditStatus utilEditStatus) {
	/*
	 * This code is special and notifies ZK that the bean's value has
	 * changed as it is used in the template mechanism. This stops the
	 * entire Grid's data from being refreshed
	 */
	BindUtils.postNotifyChange(null, null, utilEditStatus, "editingStatus");
    }

    private ListModelList<UtilisateurEditStatus> generateUtilisateurStatusList(List<Utilisateur> utilisateurs) {
	ListModelList<UtilisateurEditStatus> utilList = new ListModelList<>();
	Collections.sort(utilisateurs);
        utilisateurs.stream().forEach((aUser) -> {
            utilList.add(new UtilisateurEditStatus(aUser, false));
        });
	return utilList;
    }

    private void refreshSelectedUtilisateur(UtilisateurEditStatus utilEditStatus) {
	Utilisateur aUser = utilisateurService.findByLogin(utilEditStatus.getUtilisateur().getIdentifiant());
	for (UtilisateurEditStatus tmpEditStatus : utilisateurList) {
	    if (tmpEditStatus.getUtilisateur().getIdentifiant().equals(aUser.getIdentifiant())) {
		utilEditStatus.setUtilisateur(aUser);
		utilEditStatus.setEditingStatus(false);
		break;
	    }
	}
    }

    /**
     * @return the user
     */
    public Utilisateur getUser() {
	return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(Utilisateur user) {
	this.user = user;
    }
}
