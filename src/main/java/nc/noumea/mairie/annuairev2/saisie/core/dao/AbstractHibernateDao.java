/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.core.dao;

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

import nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author barmi83
 */
public abstract class AbstractHibernateDao<T extends AbstractEntity> implements IGenericDao<T> {

    private Class<T> clazz;

    @Autowired
    SessionFactory sessionFactory;

    public final void setClazz(Class<T> clazzToSet) {
	this.clazz = clazzToSet;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public T findById(long id) {
	    return (T) getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<T> findAll() {
	    return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false)
    public Long save(T entity) {
	    return save(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void persist(T entity) {
	    getCurrentSession().persist(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public T update(T entity) {
	    return (T) getCurrentSession().merge(entity);
    }


    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false)
    public void trueUpdate(T entity) {
	    getCurrentSession().update(entity);
    }

    @Transactional(readOnly = false)
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Transactional(readOnly = false)
    public void deleteById(long entityId) {
	    T entity = findById(entityId);
	    delete(entity);
    }

    @Transactional(readOnly = false)
    public void evict(T entity) {
	    getCurrentSession().evict(entity);
    }

    @Transactional (readOnly = false)
    public void flush() {
	    getCurrentSession().flush();
    }

    protected final Session getCurrentSession() {
	    return sessionFactory.getCurrentSession();
    }

    @Transactional(readOnly = false)
    public void refresh(T entity) {
	getCurrentSession().refresh(entity);
    }

}
