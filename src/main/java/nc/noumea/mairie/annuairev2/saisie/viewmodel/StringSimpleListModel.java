/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.viewmodel;

/*
 * #%L
 * nppb-v2
 * $Id:$
 * $HeadURL:$
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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.zul.SimpleListModel;

/**
 * @author barmi83
 * @since
 */
public class StringSimpleListModel extends SimpleListModel<String> {

    private static final long serialVersionUID = 6426284780867139802L;

    public StringSimpleListModel(List<String> data) {
	super(data);
    }

    @Override
    public boolean inSubModel(Object key, Object value) {
	String searchString = (String) key;
	if (StringUtils.isEmpty(searchString))
	    return true;

	return StringUtils.startsWithIgnoreCase(((String) value), searchString);
    }

}
