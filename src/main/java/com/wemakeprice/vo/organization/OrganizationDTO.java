package com.wemakeprice.vo.organization;


@SuppressWarnings("serial")
public class OrganizationDTO  {
	private long seq;
	private String organizationCode;
	private String organizationName;
	private String parentCode;
	private long parentSeq;
	private String status;
	
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public long getParentSeq() {
		return parentSeq;
	}
	public void setParentSeq(long parentSeq) {
		this.parentSeq = parentSeq;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\n");
			sb.append("seq : ").append(this.getSeq()).append(" , \n");
			sb.append("organizationCode : ").append(this.getOrganizationCode()).append(" , \n");
			sb.append("getOrganizationName : ").append(this.getOrganizationName()).append(" , \n");
			sb.append("parentCode : ").append(this.getParentCode()).append(" , \n");
			sb.append("parentSeq : ").append(this.getParentSeq()).append(" , \n");
			sb.append("status : ").append(this.getStatus()).append(" \n");
		sb.append("}");
			
		return sb.toString();
	}
	
	
}