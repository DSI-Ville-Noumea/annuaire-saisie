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

import nc.noumea.mairie.annuairev2.saisie.core.utility.Format;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.BindUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import java.io.Serializable;

/**
 * ViewModel abstrait parent des ViewModel de l'application qui manipulent une
 * entité (création, modification, et même liste où on considère que l'entité
 * est celle sélectionnée dans la liste)
 * 
 */
public abstract class AbstractViewModel implements Serializable {

    private static final long serialVersionUID = 7676969464572907078L;

    private static Logger logger = LoggerFactory.getLogger(AbstractViewModel.class);

    /**
     * Méthode utilitaire, pour lister les valeurs d'une énumération (dans
     * l'ordre de leur déclaration).
     * 
     * @param enumClassName
     *            nom complet de la classe (avec le package, ex :
     *            "nc.noumea.mairie.pdc.enums.Civilite")
     * @return la liste des valeurs énumérées, dans l'ordre de leur déclaration.
     */
    public ListModelList<?> getListeEnum(String enumClassName) {
	return getListeEnum(enumClassName, false);
    }

    /**
     * Méthode utilitaire, pour lister les valeurs d'une énumération (dans
     * l'ordre de leur déclaration), avec la possibilité d'insérer en tête la
     * valeur null.
     * 
     * @param enumClassName
     *            nom complet de la classe (avec le package, ex :
     *            "nc.noumea.mairie.pdc.enums.Civilite")
     * @param insertNull
     *            indique s'il faut insérer en tête de la liste résultat la
     *            valeur null
     * @return la liste des valeurs énumérées, dans l'ordre de leur déclaration
     *         (avec null en tête optionnellement)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ListModelList<?> getListeEnum(String enumClassName, boolean insertNull) {
	try {
	    Class<?> classe = Class.forName(enumClassName);
	    ListModelList result = new ListModelList(classe.getEnumConstants());
	    if (insertNull) {
		result.add(0, null);
	    }
	    return result;
	} catch (ClassNotFoundException e) {
	    Messagebox.show(e.toString());
	}
	return null;
    }

    public String getFormatCfp() {
	return Format.FORMAT_CFP;
    }

    public String getFormatNumber() {
	return Format.FORMAT_NUMBER;
    }

    public static void notifyChange(String prop, Object bean) {
	BindUtils.postNotifyChange(null, null, bean, prop);
    }

    public void notifyChange(String prop) {
	notifyChange(prop, this);
    }

    public static void notifyChange(String[] listProperty, Object bean) {
	if (listProperty == null) {
	    return;
	}
	if (Executions.getCurrent() == null) {
	    return;
	}
	for (String prop : listProperty) {
	    if (!StringUtils.isBlank(prop)) {
		notifyChange(prop, bean);
	    }
	}
    }

    public void showNotificationStandard(String message) {
	Clients.showNotification(message, "info", null, "top_center", 0);
    }

}
