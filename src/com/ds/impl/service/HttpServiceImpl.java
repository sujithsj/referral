package com.ds.impl.service;

import com.ds.pact.service.HttpService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author adlakha.vaibhav
 */
@Service
public class HttpServiceImpl implements HttpService{

  @Override
	public String getFile(String url) throws IOException {
		URL fileURL = new URL(url);
		URLConnection urlConnection = fileURL.openConnection();
		InputStream is = urlConnection.getInputStream();
		try {
			return IOUtils.toString(is);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	public String sendGetRequest(String serverUrl, String reqParameters) throws MalformedURLException, IOException {

		String result = null;

		if (reqParameters != null && reqParameters.length () > 0){
			serverUrl += "?" + reqParameters;
		}

		URL feedURL = new URL(serverUrl);
		URLConnection urlConnection = feedURL.openConnection();
		urlConnection.connect();

		BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null){
			sb.append(line);
		}
		rd.close();
		result = sb.toString();

		return result;
	}
}
