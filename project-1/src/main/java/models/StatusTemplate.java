package models;

public class StatusTemplate {
	
	int reimbId;
	int statusId;
	String resolver;
	
	public StatusTemplate() {
		
	}

	public StatusTemplate(int reimbId, int statusId, String resolver) {
		super();
		this.reimbId = reimbId;
		this.statusId = statusId;
		this.resolver = resolver;
	}

	@Override
	public String toString() {
		return "StatusTemplate [reimbId=" + reimbId + ", statusId=" + statusId + ", resolver=" + resolver + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbId;
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + statusId;
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
		StatusTemplate other = (StatusTemplate) obj;
		if (reimbId != other.reimbId)
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (statusId != other.statusId)
			return false;
		return true;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	
		
	
}
