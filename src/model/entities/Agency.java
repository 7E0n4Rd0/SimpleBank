package model.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Agency {
	private String agencyCode;
	private String agencyAddress;
	private Set<Account> accounts = new HashSet<Account>();
	
	public Agency(String agencyCode, String agencyAddress) {
		this.agencyCode = agencyCode;
		this.agencyAddress = agencyAddress;
	}

	public Agency() {}

	public String getAgencyAddress() {
		return agencyAddress;
	}

	public void setAgencyAddress(String agencyAddress) {
		this.agencyAddress = agencyAddress;
	}
	
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	public String getAgencyCode() {
		return agencyCode;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public void removeAccount(Account account) {
		accounts.remove(account);
	}
	
	public Set<Account> getAccountsList(){
		return accounts;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(agencyAddress, agencyCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agency other = (Agency) obj;
		return Objects.equals(agencyAddress, other.agencyAddress) && Objects.equals(agencyCode, other.agencyCode);
	}

	@Override
	public String toString() {
		return " Agency code: " + getAgencyCode() + 
				" Agency address: " + getAgencyAddress();
	}
	
}