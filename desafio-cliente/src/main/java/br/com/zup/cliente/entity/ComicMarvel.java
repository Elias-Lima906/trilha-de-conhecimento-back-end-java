package br.com.zup.cliente.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ComicMarvel {

	@Id
	private Long id;
	
	@Column(length = 1000)
	private String title;
	
	@Column(length = 10000)
	private String description;
	
	private String modified;
	
	private String diamondCode;
	
	private String ean;
	
	private String format;
	
	 private Integer pageCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getDiamondCode() {
		return diamondCode;
	}

	public void setDiamondCode(String diamondCode) {
		this.diamondCode = diamondCode;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "Comic Id" + id + "\nTITLE: " + title + "\nDESCRIPTION: " + description + "\nMODIFIED: " + modified
				+ "\nDIAMOND CODE: " + diamondCode + "\nEAN BARCODE: " + ean + "\nFORMAT: " + format + "\nPAGE COUNT: "
				+ pageCount;
	}

}
