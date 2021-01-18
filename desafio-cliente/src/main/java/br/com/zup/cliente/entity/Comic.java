package br.com.zup.cliente.entity;

import java.sql.Date;

public class Comic {

	private Integer id;
	private String title;
	private String description;
	private Date modified;
	private String diamondCode;
	private String ean;
	private String format;
	private Integer pageCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
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
