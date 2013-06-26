package org.docmanager.dao;

import java.util.List;

import org.docmanager.domain.Document;

public interface DocumentDAO {
	public List<Document> list();
	public Document get(Integer id);
	public void save(Document document);
	public void remove(Integer id);
}
