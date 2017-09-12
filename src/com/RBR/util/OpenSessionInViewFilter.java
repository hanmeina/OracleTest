package com.RBR.util;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

public class OpenSessionInViewFilter extends org.springframework.orm.hibernate3.support.OpenSessionInViewFilter {
	 /**
     * we do a different flushmode than in the codebase
     * here
     */
    protected Session getSession(org.hibernate.SessionFactory sessionFactory) throws org.springframework.dao.DataAccessResourceFailureException {
            Session session = SessionFactoryUtils.getSession(sessionFactory, true);
            session.setFlushMode(FlushMode.COMMIT);
            return session;
    }
    /**
     * we do an explicit flush here just in case
     * we do not have an automated flush
     */
    protected void closeSession(Session session, SessionFactory factory) {
            session.flush();
            super.closeSession(session, factory);
    }
	
}
