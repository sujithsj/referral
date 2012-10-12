package com.ds.domain.user;

/**
 * @author adlakha.vaibhav
 */
public class ThirdPartyAuth {

private long   id;

    // google, facebook, twitte
    private String providerName;
    // compulsory
    private String verifiedEmail;

    private String name;

    private String photoUrl;

    private String identifier;

    private String preferredUserName;

    private User   user;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * ~
     *
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the preferredUserName
     */
    public String getPreferredUserName() {
        return preferredUserName;
    }

    /**
     * @param preferredUserName the preferredUserName to set
     */
    public void setPreferredUserName(String preferredUserName) {
        this.preferredUserName = preferredUserName;
    }

    /**
     * @return the providerName
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * @param providerName the providerName to set
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    /**
     * @return the verifiedEmail
     */
    public String getVerifiedEmail() {
        return verifiedEmail;
    }

    /**
     * @param verifiedEmail the verifiedEmail to set
     */
    public void setVerifiedEmail(String verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the photoUrl
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * @param photoUrl the photoUrl to set
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
  
}
