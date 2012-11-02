package com.ds.rest.resource;

import com.ds.core.event.ConfigureImageEvent;
import com.ds.core.event.DefaultEventDispatcher;
import com.ds.domain.company.Company;
import com.ds.domain.core.FileAttachment;
import com.ds.domain.user.User;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.core.FileAttachmentService;
import com.ds.utils.JSONResponseBuilder;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @author adlakha.vaibhav
 */
@Path("/company")
@NoCache
@Component
public class FileAttachmentResource {

  @Autowired
  private FileAttachmentService fileAttachmentService;
  @Autowired
  private AdminService adminService;
  @Autowired
  private DefaultEventDispatcher eventDispatcher;

  @POST
  @Path("/{companyId}/logo/delete")
  @Produces("application/json")
  public String deleteFile(@PathParam("companyId")
  String companyShortName) {
    Company company = getAdminService().getCompany(companyShortName);

    if (company.getLogo() != null) {
      getFileAttachmentService().deleteFile(company.getLogo().getId());
      company.setLogo(null);
      getAdminService().updateEntity(company);
      return new JSONResponseBuilder().addField("message", "File Deleted Successfully").build();
    }
    return new JSONResponseBuilder().addField("message", "No file found to be deleted").build();
  }

  @POST
  @Path("/{companyId}/logo/{fileAttachmentId}")
  @Produces("application/json")
  public String associateLogo(@PathParam("companyId")
  String companyShortName, @PathParam("fileAttachmentId")
  Long fileAttachmentId) {
    Company company = getAdminService().getCompany(companyShortName);
    FileAttachment logo = getFileAttachmentService().loadFileAttachment(fileAttachmentId);
    company.setLogo(logo);
    getAdminService().updateEntity(company);
    return new JSONResponseBuilder().addField("message", "Logo Associated Successfully").build();
  }


  /*@POST
  @Path("/{companyShortName}/product/{productId}/logo/{fileAttachmentId}")
  @Produces("application/json")
  public String associateProductImage(@PathParam("companyShortName")
        String companyShortName, @PathParam("fileAttachmentId")
        Long fileAttachmentId, @PathParam("productId")
        Long productId) {

    Product product = getProductService().getProduct(productId);
    FileAttachment logo = getFileAttachmentService().loadFileAttachment(fileAttachmentId);
    product.setLogo(logo);
    getProductService().updateProduct(product);

    return new JSONResponse().addField("message", "Product Logo Associated Successfully").build();
  }

  @POST
  @Path("/{companyShortName}/product/{productId}/logo/delete")
  @Produces("application/json")
  public String deleteProductImage(@PathParam("companyShortName")
        String companyShortName,@PathParam("productId")Long productId) {

    Product product = getProductService().getProduct(productId);

    if(product.getLogo()!=null ){
      getFileAttachmentService().deleteFile(product.getLogo().getId());
      product.setLogo(null);

      getProductService().updateProduct(product);
      return new JSONResponse().addField("message", "File Deleted Successfully").build();
    }
    else{
      return new JSONResponse().addField("message", "No File found to be deleted.").build();
    }
  }*/

  @POST
  @Path("/{companyShortName}/employee/{userId}/logo/{fileAttachmentId}")
  @Produces("application/json")
  public String associateUserImage(@PathParam("companyShortName")
  String companyShortName, @PathParam("fileAttachmentId")
  Long fileAttachmentId, @PathParam("userId")
  String userId) {

    User user = getAdminService().getUser(userId);
    FileAttachment logo = getFileAttachmentService().loadFileAttachment(fileAttachmentId);

    if (user != null) {

      getEventDispatcher().dispatchEvent(new ConfigureImageEvent(user, logo));
      return new JSONResponseBuilder().addField("message", "Logo Associated Successfully").build();
    }

    return new JSONResponseBuilder().addField("message", "No user logged in").addField("exception",true).build();
  }

  @POST
  @Path("/{companyId}/employee/{userId}/logo/delete")
  @Produces("application/json")
  public String deleteUserImage(@PathParam("companyId")
  String companyShortName, @PathParam("userId")
  String userId) {

    User user = getAdminService().getUser(userId);

    if (user.getLogoId() != null && user.getThumbnailImageUrl() != null && user.getOriginalImageUrl() != null) {
      getFileAttachmentService().deleteFile(getImageIdFromUrl(user.getThumbnailImageUrl()));
      getFileAttachmentService().deleteFile(getImageIdFromUrl(user.getOriginalImageUrl()));
      getFileAttachmentService().deleteFile(user.getLogoId());

      user.setThumbnailImageUrl(null);
      user.setOriginalImageUrl(null);
      user.setLogoId(null);

      getAdminService().updateEntity(user);
      return new JSONResponseBuilder().addField("message", "File Deleted Successfully").build();
    } else {
      return new JSONResponseBuilder().addField("message", "No File found to be deleted.").build();
    }
  }


  /*@POST
      @Path("/{companyId}/reward/{rewardId}/logo/delete")
      @Produces("application/json")
      public String deleteRewardImage(@PathParam("companyId")
      String companyShortName, @PathParam("rewardId")
      long rewardId) {

        Reward reward = getAppStoreService().getReward(rewardId);

        if(reward.getLogoId()!=null ){
          getFileAttachmentService().deleteFile(reward.getLogoId());
          reward.setLogoId(null);

          getAppStoreService().addOrUpdateReward(reward);
          return new JSONResponse().addField("message", "File Deleted Successfully").build();
        }
        else{
          return new JSONResponse().addField("message", "No File found to be deleted.").build();
        }
      }
  */

  /**
   * returns the id of image from the image url.
   *
   * @return
   */
  private long getImageIdFromUrl(String imageUrl) {
    long imageId = -1;

    if (imageUrl.contains("imageId")) {
      String idStr = imageUrl.substring(imageUrl.indexOf("imageId=") + "imageId=".length());
      imageId = Long.parseLong(idStr);
    }

    return imageId;
  }


  /**
   * @return the fileAttachmentService
   */
  public FileAttachmentService getFileAttachmentService() {
    return fileAttachmentService;
  }

  /**
   * @param fileAttachmentService the fileAttachmentService to set
   */
  public void setFileAttachmentService(FileAttachmentService fileAttachmentService) {
    this.fileAttachmentService = fileAttachmentService;
  }

  /**
   * @return the adminService
   */
  public AdminService getAdminService() {
    return adminService;
  }

  /**
   * @param adminService the adminService to set
   */
  public void setAdminService(AdminService adminService) {
    this.adminService = adminService;
  }

  /**
   * @return the eventDispatcher
   */
  public DefaultEventDispatcher getEventDispatcher() {
    return eventDispatcher;
  }

  /**
   * @param eventDispatcher the eventDispatcher to set
   */
  public void setEventDispatcher(DefaultEventDispatcher eventDispatcher) {
    this.eventDispatcher = eventDispatcher;
  }


}

