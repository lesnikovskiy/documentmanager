package org.docmanager.service;

import java.util.List;

import org.docmanager.dao.DocumentDAO;
import org.docmanager.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentServiceImpl implements DocumentService {
	@Autowired
	private DocumentDAO documentDAO;
	
	@Transactional
	public List<Document> list() {
		return documentDAO.list();
	}

	@Transactional
	public Document get(Integer id) {
		return documentDAO.get(id);
	}

	@Transactional
	public void save(Document document) {
		documentDAO.save(document);		
	}

	@Transactional
	public void remove(Integer id) {
		documentDAO.remove(id);
	}
}
