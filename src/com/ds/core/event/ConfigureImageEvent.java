package com.ds.core.event;

import com.ds.domain.core.FileAttachment;
import com.ds.domain.user.User;
import com.ds.impl.service.ServiceLocatorFactory;
import com.ds.pact.service.core.FileAttachmentService;
import com.ds.security.dao.SecurityDao;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adlakha.vaibhav
 */
public class ConfigureImageEvent implements AsyncEvent {
  private User user;
  private FileAttachment fileAttachment;

  // default constructor to make it serializable
  public ConfigureImageEvent() {

  }

  public ConfigureImageEvent(User user) {
    this.user = user;
  }

  public ConfigureImageEvent(User user, FileAttachment fileAttachment) {
    this.user = user;
    this.fileAttachment = fileAttachment;
  }

  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * @return the fileAttachment
   */
  public FileAttachment getFileAttachment() {
    return fileAttachment;
  }

  @Override
  public Map<String, String> getWireRepresentation() {
    Map<String, String> data = new HashMap<String, String>();
    data.put("USERID", user.getUsername());
    if (fileAttachment != null) {
      data.put("FileAttachment.Id", String.valueOf(fileAttachment.getId()));
    }
    return data;
  }

  @Override
  public void prepareFromWireRepresentation(Map<String, String> data) {
    SecurityDao securityDao = ServiceLocatorFactory.getService(SecurityDao.class);
    String userid = data.get("USERID");
    this.user = securityDao.findUserWithUsername(userid);

    FileAttachmentService fileAttachmentService = ServiceLocatorFactory.getService(FileAttachmentService.class);
    String fileAttachmentId = data.get("FileAttachment.Id");
    if (StringUtils.isNotBlank(fileAttachmentId)) {
      this.fileAttachment = fileAttachmentService.loadFileAttachment(Long.parseLong(fileAttachmentId));
    }
  }

}
