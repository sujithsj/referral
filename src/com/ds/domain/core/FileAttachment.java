package com.ds.domain.core;

import com.ds.domain.BaseDataObject;

/**
 * @author adlakha.vaibhav
 */
public class FileAttachment extends BaseDataObject {

  private long id;
  private String mimeType;
  private String fileName;
  private long fileSize;
  private byte[] fileContent;
  private int height;
  private int width;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public long getFileSize() {
    return fileSize;
  }

  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  public byte[] getFileContent() {
    return fileContent;
  }

  public void setFileContent(byte[] fileContent) {
    this.fileContent = fileContent;
  }

  /**
   * @return the height
   */
  public int getHeight() {
    return height;
  }

  /**
   * @param height the height to set
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * @return the width
   */
  public int getWidth() {
    return width;
  }

  /**
   * @param width the width to set
   */
  public void setWidth(int width) {
    this.width = width;
  }

}
