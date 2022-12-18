package com.cpy.springPortfolio.product.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {
	private int productNo;
	private String productName;
	private String vendor;
	private int productPrice;
	private String productDescription;
	private String attachInfo;
	private String regiDate;
	
	private int preProductNo;
	private String preProductName;
	private int nxtProductNo;
	private String nxtProductName;
	private List<MultipartFile> file;
	
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getAttachInfo() {
		return attachInfo;
	}
	public void setAttachInfo(String attachInfo) {
		this.attachInfo = attachInfo;
	}
	public String getRegiDate() {
		return regiDate;
	}
	public void setRegiDate(String regiDate) {
		this.regiDate = regiDate;
	}
	public int getPreProductNo() {
		return preProductNo;
	}
	public void setPreProductNo(int preProductNo) {
		this.preProductNo = preProductNo;
	}
	public String getPreProductName() {
		return preProductName;
	}
	public void setPreProductName(String preProductName) {
		this.preProductName = preProductName;
	}
	public int getNxtProductNo() {
		return nxtProductNo;
	}
	public void setNxtProductNo(int nxtProductNo) {
		this.nxtProductNo = nxtProductNo;
	}
	public String getNxtProductName() {
		return nxtProductName;
	}
	public void setNxtProductName(String nxtProductName) {
		this.nxtProductName = nxtProductName;
	}
	public List<MultipartFile> getFile() {
		return file;
	}
	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}
}
