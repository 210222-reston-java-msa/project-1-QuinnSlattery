package models;

import java.sql.Timestamp;

public class Reimbursement {

	int reimbId;
	double amount;
	String submitted;
	String resolved;
	String description;
	String author;
	String resolver;
	int statusId;
	int typeId;
	
	public Reimbursement() {
		
	}

	public Reimbursement(int reimbId, double amount, String submitted, String resolved, String description,
			String author, String resolver, int statusId, int typeId) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
	}
	
	public Reimbursement(double amount, String submitted, String resolved, String description,
			String author, String resolver, int statusId, int typeId) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
	}
	
	public Reimbursement(double amount, String submitted, String description,
			String author, int statusId, int typeId) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.author = author;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public Reimbursement(int reimbId, double amount, String submitted, String description, String author,
			int statusId, int typeId) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.author = author;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + ", author=" + author + ", resolver=" + resolver
				+ ", statusId=" + statusId + ", typeId=" + typeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + statusId;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + typeId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (statusId != other.statusId)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}



}
