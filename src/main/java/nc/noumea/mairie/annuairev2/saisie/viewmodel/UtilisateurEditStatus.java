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

import nc.noumea.mairie.annuairev2.saisie.entity.Utilisateur;

/**
 * @author barmi83
 * @since
 */
public class UtilisateurEditStatus {

    private Utilisateur utilisateur;
    private boolean editingStatus;

    /**
     * 
     * @param utilisateur
     * @param editingStatus
     */
    public UtilisateurEditStatus(Utilisateur utilisateur, boolean editingStatus) {
	this.utilisateur = utilisateur;
	this.editingStatus = editingStatus;
    }

    /**
     * @return the utilisateur
     */
    public Utilisateur getUtilisateur() {
	return utilisateur;
    }

    /**
     * @param utilisateur
     *            the utilisateur to set
     */
    public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
    }

    /**
     * @return the editingStatus
     */
    public boolean isEditingStatus() {
	return editingStatus;
    }

    /**
     * @param editingStatus
     *            the editingStatus to set
     */
    public void setEditingStatus(boolean editingStatus) {
	this.editingStatus = editingStatus;
    }

}
