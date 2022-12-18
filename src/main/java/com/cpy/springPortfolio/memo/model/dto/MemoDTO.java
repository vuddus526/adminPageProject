package com.cpy.springPortfolio.memo.model.dto;

public class MemoDTO {
	private int no;
	private String writer;
	private String content;
	private String regiDate;
	
	private int preNo;
	private String preWriter;
	private int nxtNo;
	private String nxtWriter;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegiDate() {
		return regiDate;
	}
	public void setRegiDate(String regiDate) {
		this.regiDate = regiDate;
	}
	public int getPreNo() {
		return preNo;
	}
	public void setPreNo(int preNo) {
		this.preNo = preNo;
	}
	public String getPreWriter() {
		return preWriter;
	}
	public void setPreWriter(String preWriter) {
		this.preWriter = preWriter;
	}
	public int getNxtNo() {
		return nxtNo;
	}
	public void setNxtNo(int nxtNo) {
		this.nxtNo = nxtNo;
	}
	public String getNxtWriter() {
		return nxtWriter;
	}
	public void setNxtWriter(String nxtWriter) {
		this.nxtWriter = nxtWriter;
	}
	
	
}
