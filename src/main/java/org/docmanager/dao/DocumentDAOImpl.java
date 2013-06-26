package org.docmanager.dao;

import java.util.List;

import org.docmanager.domain.Document;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentDAOImpl implements DocumentDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Document> list() {
		Session session = sessionFactory.getCurrentSession();
		List<Document> documents = null;
		try {
			documents = (List<Document>) session.createQuery("from Document").list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return documents;
	}

	public Document get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		return (Document) session.get(Document.class, id);
	}

	public void save(Document document) {
		Session session = sessionFactory.getCurrentSession();
		session.save(document);
	}

	public void remove(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Document document = (Document) session.get(Document.class, id);
		
		if (document != null)
			session.delete(document);
	}
}
