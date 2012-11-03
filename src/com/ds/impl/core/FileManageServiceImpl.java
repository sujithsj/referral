package com.ds.impl.core;

import com.ds.core.event.ConfigureImageEvent;
import com.ds.core.event.DefaultEventDispatcher;
import com.ds.domain.company.Company;
import com.ds.domain.core.FileAttachment;
import com.ds.domain.user.User;
import com.ds.pact.service.admin.AdminService;
import com.ds.pact.service.core.FileAttachmentService;
import com.ds.pact.service.core.FileManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adlakha.vaibhav
 */
@Service
public class FileManageServiceImpl implements FileManageService {

  @Autowired
  private FileAttachmentService fileAttachmentService;
  @Autowired
  private AdminService adminService;
  @Autowired
  private DefaultEventDispatcher eventDispatcher;

  @Override
  @Transactional
  public Company deleteCompanyLogo(String companyShortName) {
    Company company = getAdminService().getCompany(companyShortName);

    if (company.getLogo() != null) {
      getFileAttachmentService().deleteFile(company.getLogo().getId());
      company.setLogo(null);
      getAdminService().updateEntity(company);

    }
    return company;
  }


  @Override
  @Transactional
  public Company associateCompanyLogo(String companyShortName, Long fileAttachmentId) {
    Company company = getAdminService().getCompany(companyShortName);
    FileAttachment logo = getFileAttachmentService().loadFileAttachment(fileAttachmentId);
    company.setLogo(logo);
    getAdminService().updateEntity(company);
    return company;
  }


  @Override
  @Transactional
  public void associateUserImage(String companyShortName, Long fileAttachmentId, String userId) {

    User user = getAdminService().getUser(userId);
    FileAttachment logo = getFileAttachmentService().loadFileAttachment(fileAttachmentId);

    if (user != null) {
      getEventDispatcher().dispatchEvent(new ConfigureImageEvent(user, logo));
    }
  }


  @Override
  @Transactional
  public User deleteUserImage(String companyShortName, String userId) {

    User user = getAdminService().getUser(userId);

    if (user.getLogoId() != null && user.getThumbnailImageUrl() != null && user.getOriginalImageUrl() != null) {
      getFileAttachmentService().deleteFile(getImageIdFromUrl(user.getThumbnailImageUrl()));
      getFileAttachmentService().deleteFile(getImageIdFromUrl(user.getOriginalImageUrl()));
      getFileAttachmentService().deleteFile(user.getLogoId());

      user.setThumbnailImageUrl(null);
      user.setOriginalImageUrl(null);
      user.setLogoId(null);

      getAdminService().updateEntity(user);

    }

    return user;
  }

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
