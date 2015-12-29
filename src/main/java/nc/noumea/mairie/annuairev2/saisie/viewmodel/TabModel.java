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

import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant un onglet
 * 
 * @author barmi83
 * @since
 */
public class TabModel {

    private String id;
    private String label;
    private boolean closable;
    private String sclass;
    private String zulTemplate;
    private Map<String, Object> args;

    public TabModel(String id, String label, String zulTemplate, boolean closable, String sclass) {
	this.setId(id);
	this.setLabel(label);
	this.setClosable(closable);
	this.setSclass(sclass);
	this.setZulTemplate(zulTemplate);
	this.setArgs(new HashMap<String, Object>());
    }

    public TabModel(String id, String label, String zulTemplate, boolean closable, String sclass,
                    Map<String, Object> args) {
	this.setId(id);
	this.setLabel(label);
	this.setClosable(closable);
	this.setSclass(sclass);
	this.setZulTemplate(zulTemplate);
	this.setArgs(args);
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getLabel() {
	return label;
    }

    public void setLabel(String label) {
	this.label = label;
    }

    public boolean isClosable() {
	return closable;
    }

    public void setClosable(boolean closable) {
	this.closable = closable;
    }

    public String getSclass() {
	return sclass;
    }

    public void setSclass(String sclass) {
	this.sclass = sclass;
    }

    public String getZulTemplate() {
	return zulTemplate;
    }

    public void setZulTemplate(String zulTemplate) {
	this.zulTemplate = zulTemplate;
    }

    /**
     * @return the args
     */
    public Map<String, Object> getArgs() {
	return args;
    }

    /**
     * @param args
     *            the args to set
     */
    public void setArgs(Map<String, Object> args) {
	this.args = args;
    }
}
