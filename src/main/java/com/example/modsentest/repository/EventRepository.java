package com.example.modsentest.repository;

import com.example.modsentest.dto.EventFilter;
import com.example.modsentest.entity.Event;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.modsentest.entity.QEvent.event;

@Repository
public class EventRepository extends HibernateDaoSupport implements CrudRepository<Integer, Event> {

    @Autowired
    public void setSessionFactory(@Qualifier("sessionFactory") LocalSessionFactoryBean sessionFactory) {
        setSessionFactory(Objects.requireNonNull(sessionFactory.getObject()));
    }

    @Override
    public Event save(Event entity) {
        getHibernateTemplate().save(entity);
        return entity;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Event> event = findById(id);
        event.ifPresent(x -> getHibernateTemplate().delete(x));
        getHibernateTemplate().flush();
        return event.isPresent();
    }

    @Override
    public Event update(Event entity) {
        getHibernateTemplate().update(entity);
        return entity;
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return Optional.ofNullable(getHibernateTemplate().get(Event.class, id));
    }

    @Override
    public List<Event> findAll() {
        DetachedCriteria dc = DetachedCriteria.forClass(Event.class);
        List<Event> list = (List<Event>) getHibernateTemplate().findByCriteria(dc);
        return list;
    }

    public List<Event> findAllByFilter(EventFilter eventFilter){
        var predicate=QPredicate.builder()
                .add(eventFilter.getTitle(),event.title::eq)
                .add(eventFilter.getOrganizer(),event.organizer.name::eq)
                .add(eventFilter.getTime(),event.date::before)
                .buildAnd();

        return new JPAQuery<Event>(getSessionFactory().createEntityManager())
                .select(event)
                .from(event)
                .where(predicate)
                .fetch();
    }
}
