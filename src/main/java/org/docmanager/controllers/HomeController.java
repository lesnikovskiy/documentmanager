package org.docmanager.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.docmanager.domain.Document;
import org.docmanager.service.DocumentService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {	
	@Autowired
	private DocumentService documentService;
	
	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Map<String, Object> map) {
		try {
			map.put("document", new Document());
			map.put("documentList", documentService.list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "home";
	}
	
	@RequestMapping("/download/{documentId}")
	public String download(@PathVariable("documentId") Integer documentId, HttpServletResponse response) {
		Document doc = documentService.get(documentId);
		
		try {
			response.setHeader("Content-Disposition", "inline;filename=\"" + doc.getFilename() + "\"");
			OutputStream outputStream = response.getOutputStream();
			response.setContentType(doc.getContentType());
			
			IOUtils.copy(doc.getContent().getBinaryStream(), outputStream);
			
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("document") Document document, @RequestParam("file") MultipartFile file) {
		try {
			Blob blob = Hibernate.createBlob(file.getInputStream());
			
			document.setName(document.getName());
			document.setFilename(file.getOriginalFilename());
			document.setContent(blob);
			document.setContentType(file.getContentType());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			documentService.save(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/index";
	}
	
	@RequestMapping("/remove/{documentId}")
	public String remove(@PathVariable("documentId") Integer documentId) {
		documentService.remove(documentId);
		
		return "redirect:/index";
	}
}
