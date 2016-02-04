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


import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zul.SimpleListModel;

public class ContainsSimpleListModel<T> extends SimpleListModel<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContainsSimpleListModel.class);

    private static final long serialVersionUID = -3726852960052576655L;

    protected String property;
    protected Class<T> clazz;
    protected Field field;

    public ContainsSimpleListModel(List<T> data, Class<T> clazz, String property) {
	super(data);
	this.clazz = clazz;
	this.property = property;

	try {
	    this.field = clazz.getDeclaredField(property);
	} catch (NoSuchFieldException e) {
            LOGGER.error(e.toString(),e);
	    try {
		this.field = clazz.getSuperclass().getDeclaredField(property);
	    } catch (NoSuchFieldException e1) {
                LOGGER.error(e1.toString(),e1);
	    }
	}

	field.setAccessible(true);
    }

    @Override
    public boolean inSubModel(Object key, Object value) {
	String searchString = (String) key;
	if (StringUtils.isEmpty(searchString))
	    return true;
	try {
	    return StringUtils.containsIgnoreCase(field.get(value).toString(), searchString);
	} catch (IllegalAccessException e) {
	    LOGGER.error(e.toString(),e);
	    return false;
	}
    }
}
