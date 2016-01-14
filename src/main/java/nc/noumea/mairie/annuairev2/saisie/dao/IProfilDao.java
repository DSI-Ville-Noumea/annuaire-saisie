/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.dao;

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



import nc.noumea.mairie.annuairev2.saisie.core.dao.IGenericDao;
import nc.noumea.mairie.annuairev2.saisie.core.security.CodeProfil;
import nc.noumea.mairie.annuairev2.saisie.core.security.Profil;

/**
 * @author barmi83
 * @since 0.2.0
 */
public interface IProfilDao extends IGenericDao<Profil> {

    public static final String BEAN_ID = "profilDao";

    public Profil findByLogin(String login);

    public Profil findByProfilName(CodeProfil code);

}
