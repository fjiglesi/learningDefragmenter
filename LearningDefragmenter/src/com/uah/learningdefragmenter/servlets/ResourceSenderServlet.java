package com.uah.learningdefragmenter.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


/**
 * Servlet implementation class ResourceSenderServlet
 */
@WebServlet("/ResourceSenderServlet")
public class ResourceSenderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_AGENT = "Learning-Defragmenter-Agent";
	private HttpClient client;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResourceSenderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String urlStr = request.getParameter("resource");
		System.out.println(urlStr);
		if (urlStr != null && !urlStr.equals("")) {
			URL url=new URL(urlStr); 
			HttpGet get = new HttpGet(url.toString());
			get.addHeader("User-Agent", USER_AGENT);
			get.addHeader("host", url.getHost());
			HttpResponse httpResp = client.execute(get);
			if (httpResp.getStatusLine().getStatusCode() == 200) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(httpResp.getEntity().getContent()));
				/*String line=null;
				while((line=reader.readLine())!=null)
					System.out.println(line);*/
				byte buffer[]=new byte[1024];
				InputStream in=httpResp.getEntity().getContent();
				int bytesLeidos;
				do{
					bytesLeidos=in.read(buffer);
					if(bytesLeidos!=-1){
						response.getOutputStream().write(buffer, 0, bytesLeidos);
						System.out.println(new String(buffer, 0, bytesLeidos));
					}
				}while(bytesLeidos!=-1);
			}
			get.releaseConnection();
			
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		client = HttpClientBuilder.create().build();
		
	}

}
