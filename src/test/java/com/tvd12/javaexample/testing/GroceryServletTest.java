package com.tvd12.javaexample.testing;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

class GroceryServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handle(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handle(req, resp);
	}
	
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		String line = null;
		StringBuffer stringBuffer = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			stringBuffer.append(line);
		}
		String jsonString = stringBuffer.toString();
		JSONObject jsonObj = new JSONObject(jsonString);
		PrintWriter writer = resp.getWriter();
		writer.append(stringBuffer);
		writer.flush(); 
	}
}

/**
 * Unit tests for {@link HelloAppEngine}.
 */
@RunWith(JUnit4.class)
public class GroceryServletTest {
	private static final String MOCK_URL = "/grocery";
	// Set up a helper so that the ApiProxy returns a valid environment for local
	// testing.
	// private final LocalServiceTestHelper helper = new LocalServiceTestHelper();

	@Mock
	private HttpServletRequest mockRequest;

	@Mock
	private HttpServletResponse mockResponse;

	private StringWriter responseWriter;
	private GroceryServlet servletUnderTest;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		// helper.setUp();

		// Set up some fake HTTP requests
		when(mockRequest.getRequestURI()).thenReturn(MOCK_URL);
		
		String test = "{'request' : 'Hello Kitty'}";
		Reader inputString = new StringReader(test);
		BufferedReader reader = new BufferedReader(inputString);
		when(mockRequest.getReader()).thenReturn(reader);

		// Set up a fake HTTP response.
		responseWriter = new StringWriter();
		when(mockResponse.getWriter()).thenReturn(new PrintWriter(responseWriter));

		servletUnderTest = new GroceryServlet();
	}

	@After
	public void tearDown() {
		// helper.tearDown();
	}

	@Test
	public void doGetWritesResponse() throws Exception {
		servletUnderTest.doGet(mockRequest, mockResponse);

		// We expect our hello world response.
		String writerString = responseWriter.toString();
		assertThat(writerString).contains("Hello Kitty");
	}

	 @Test
	 public void doPostWritesResponse() throws Exception {
		 JSONObject reqObj = new JSONObject();
		 reqObj.put("name", "Beer");
		 reqObj.put("welcome", "Hello Kitty");
		 StringReader reader = new StringReader(reqObj.toString());
		
		 when(mockRequest.getReader()).thenReturn(new BufferedReader(new
		 StringReader(reqObj.toString())));
		
		 servletUnderTest.doPost(mockRequest, mockResponse);
		
		 // We expect our hello world response.
		 assertThat(responseWriter.toString()).contains("Hello Kitty");
	 }
}