/**
 * 
 */
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

import nc.noumea.mairie.annuairev2.saisie.core.security.SecurityUtil;
import nc.noumea.mairie.annuairev2.saisie.entity.Utilisateur;
import nc.noumea.mairie.annuairev2.saisie.service.IUtilisateurService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Label;


/**
 * @author barmi83
 * @since
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class BannerViewModel extends SelectorComposer<Component> {

    private static final long serialVersionUID = 7060483150223149060L;

    @Wire
    Label user;

    @WireVariable
    IUtilisateurService utilisateurService;

    @Override
    public void doAfterCompose(Component comp) throws Exception {

	super.doAfterCompose(comp);
	if (SecurityUtil.getUser() != null) {
	    Utilisateur currentUser = utilisateurService.findByLogin(SecurityUtil.getUser());
	    if (currentUser != null) {
		user.setValue(currentUser.getFullName() + " - " + currentUser.getProfil().getNom().toString() + " (");
	    }
	}
    }

}
