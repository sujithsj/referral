package com.ds.web.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author adlakha.vaibhav
 */
public class ServletMultiPartRequestUtil  {

  private HttpServletRequest request;
    @SuppressWarnings("unchecked")
    private List requestItems;

    public ServletMultiPartRequestUtil(HttpServletRequest request) throws FileUploadException {
        super();
        this.request = request;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        requestItems = upload.parseRequest(this.request);
    }

    /**
     * get Value of a form field from post request using name of the field in form.
     *
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getFormFieldValue(String fieldName) {

        if (requestItems != null) {
            String fieldValue = null;
            Iterator iter = requestItems.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    String name = item.getFieldName();

                    if(fieldName.equalsIgnoreCase(name)){
                        fieldValue = item.getString();
                    }
                }
            }
            return fieldValue;
        }
        else{
            return null;
        }
    }

  public List<FileItem> getFileItems(){
    List<FileItem> fileItems = new ArrayList<FileItem>();
      if (requestItems != null) {
            String fieldValue = null;
            Iterator iter = requestItems.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    fileItems.add(item);
                }
            }
            return fileItems;
        }
        else{
            return null;
        }
  }
}
