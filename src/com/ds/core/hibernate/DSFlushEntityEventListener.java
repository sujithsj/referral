package com.ds.core.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.StaleObjectStateException;
import org.hibernate.engine.EntityEntry;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.event.FlushEntityEvent;
import org.hibernate.event.def.DefaultFlushEntityEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

/**
 * @author adlakha.vaibhav
 */
@Component
public class DSFlushEntityEventListener extends DefaultFlushEntityEventListener {

    public void onFlushEntity(FlushEntityEvent event) throws HibernateException {

        Object entity = event.getEntity();
        EntityEntry entry = event.getEntityEntry();
        EntityPersister persister = entry.getPersister();
        SessionImplementor session = event.getSession();

        if (persister.isVersioned()) {
            Object version = persister.getVersion(entity, session.getEntityMode());
            // Make sure version has not changed
            if (!persister.getVersionType().isEqual(version,entry.getVersion())){
                throw new StaleObjectStateException(persister.getEntityName(),entry.getId());
            }
        }
        super.onFlushEntity(event);
    }
}
