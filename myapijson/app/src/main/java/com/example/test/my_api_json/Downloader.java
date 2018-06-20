package com.example.test.my_api_json;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Downloader {

	public static InputStream download(String url) throws IOException {
		InputStream is = null;
		HttpClient hc = null;
		
		try {
			hc = new DefaultHttpClient();
			HttpResponse hr = hc.execute(new HttpGet(url));
			HttpEntity he = hr.getEntity();
			BufferedHttpEntity bhe = new BufferedHttpEntity(he);
			
			is = bhe.getContent();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (hc != null) {
				hc.getConnectionManager().shutdown();
			}
			if (is != null) {
				is.close();
			}
		}
		return is;
	}
	
	public static String convert(InputStream is) throws IOException{
		BufferedReader br = null;
		
		StringBuffer sb = new StringBuffer();
		String line = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
