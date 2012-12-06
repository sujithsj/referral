package com.ds.security.aff;

/**
 * @author adlakha.vaibhav
 */
public abstract class AffAbstractAuthenticationToken implements AffAuthentication {

  private boolean authenticated = false;


  public boolean equals(Object obj) {
    if (!(obj instanceof AffAbstractAuthenticationToken)) {
      return false;
    }

    AffAbstractAuthenticationToken test = (AffAbstractAuthenticationToken) obj;


    if ((this.getCredentials() == null) && (test.getCredentials() != null)) {
      return false;
    }

    if ((this.getCredentials() != null) && !this.getCredentials().equals(test.getCredentials())) {
      return false;
    }

    if (this.getPrincipal() == null && test.getPrincipal() != null) {
      return false;
    }

    if (this.getPrincipal() != null && !this.getPrincipal().equals(test.getPrincipal())) {
      return false;
    }


    return this.isAuthenticated() == test.isAuthenticated();
  }


  public int hashCode() {
    int code = 31;


    if (this.getPrincipal() != null) {
      code ^= this.getPrincipal().hashCode();
    }

    if (this.getCredentials() != null) {
      code ^= this.getCredentials().hashCode();
    }

    if (this.isAuthenticated()) {
      code ^= -37;
    }

    return code;
  }

  public boolean isAuthenticated() {
    return authenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString()).append(": ");
    sb.append("Principal: ").append(this.getPrincipal()).append("; ");
    sb.append("Password: [PROTECTED]; ");
    sb.append("Authenticated: ").append(this.isAuthenticated()).append("; ");


    return sb.toString();
  }


}