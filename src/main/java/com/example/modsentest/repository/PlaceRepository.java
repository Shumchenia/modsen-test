package com.example.modsentest.repository;

import com.example.modsentest.entity.Place;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PlaceRepository extends HibernateDaoSupport implements CrudRepository<Integer, Place> {

    @Autowired
    public void setSessionFactory(@Qualifier("sessionFactory") LocalSessionFactoryBean sessionFactory) {
        setSessionFactory(Objects.requireNonNull(sessionFactory.getObject()));
    }

    @Override
    public Place save(Place entity) {
        getHibernateTemplate().save(entity);
        return entity;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Place> place = findById(id);
        place.ifPresent(x -> getHibernateTemplate().delete(x));
        getHibernateTemplate().flush();
        return place.isPresent();
    }

    @Override
    public Place update(Place entity) {
        getHibernateTemplate().update(entity);
        return entity;
    }

    @Override
    public Optional<Place> findById(Integer id) {
        return Optional.ofNullable(getHibernateTemplate().get(Place.class, id));
    }

    @Override
    public List<Place> findAll() {
        DetachedCriteria dc = DetachedCriteria.forClass(Place.class);
        List<Place> list = (List<Place>) getHibernateTemplate().findByCriteria(dc);
        return list;
    }

}
