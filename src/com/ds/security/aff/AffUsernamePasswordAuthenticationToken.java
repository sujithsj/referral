package com.ds.security.aff;

/**
 * @author adlakha.vaibhav
 */
public class AffUsernamePasswordAuthenticationToken extends AffAbstractAuthenticationToken {


    private final Object credentials;
    private final Object principal;
    

    public AffUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }


  public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }



    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                "Cannot set this token to trusted - use constructor which takes a GrantedOperation list instead");
        }

        super.setAuthenticated(false);
    }

}