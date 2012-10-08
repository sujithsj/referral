package com.ds.pact.service;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author adlakha.vaibhav
 */
public interface HttpService {

  public String getFile(String url) throws IOException;


  /**
   * This method sends a HTTP GET request to the server with request parameters and returns response sent by the
   * server.
   *
   * @param serverUrl
   * @param reqParameters
   * @return Response returned from the server.
   */
  public String sendGetRequest(String serverUrl, String reqParameters) throws MalformedURLException, IOException;
}
