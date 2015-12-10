/**
 * 
 */
package nc.noumea.mairie.annuairev2.saisie.core.dao;


import nc.noumea.mairie.annuairev2.saisie.core.entity.AbstractEntity;

import java.util.List;

/**
 * @author barmi83
 * @since
 */
public interface IGenericDao<T extends AbstractEntity> {

    public T findById(final long id);

    public List<T> findAll();

    public Long save(final T entity);

    public Long save(final T entity, String userLogin);

    public void persist(T entity);

    public T update(final T entity);

    public void delete(final T entity);

    public void deleteById(final long entityId);

    public void evict(final T entity);

    public void refresh(T entity);

    public void flush();

}
