package com.n2cj.common;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractGenericDao<E, ID extends Serializable> {
    @SuppressWarnings("unchecked")
    protected final Class<E> mType = (Class<E>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];
    protected final Logger mLog = LoggerFactory.getLogger(mType);

    @Autowired protected SessionFactory mFactory;

    protected Session getSession() {
        return mFactory.getCurrentSession();
    }

    protected Criteria createCriteria() {
        return getSession().createCriteria(mType);
    }

    @SuppressWarnings("unchecked")
    public E getById(final ID id) {
        return (E) getSession().get(mType, id);
    }

    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        return getSession().createCriteria(mType).list();
    }

    @SuppressWarnings("unchecked")
    public List<E> getNFrom(final int n, final int start) {
        return getSession()
                .createCriteria(mType)
                .setMaxResults(n)
                .setFirstResult(start)
                .list();
    }

    public int getCount() {
        return ((Number) getSession()
                .createCriteria(mType)
                .setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    @SuppressWarnings("unchecked")
    public List<E> getByExample(final E instance, final String[] excludedProperties) {
        final Example example = Example.create(instance);

        for (final String property : excludedProperties) {
            example.excludeProperty(property);
        }

        return getSession().createCriteria(mType).add(example).list();
    }

    public void create(final E o) {
        getSession().save(o);
    }

    public void update(final E o) {
        getSession().update(o);
    }

    public void delete(final E o) {
        getSession().delete(o);
    }
}