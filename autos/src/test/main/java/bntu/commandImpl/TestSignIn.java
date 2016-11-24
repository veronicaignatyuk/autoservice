package test.main.java.bntu.commandImpl;

import static org.junit.Assert.assertEquals;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import static org.mockito.Mockito.*;
import main.java.bntu.commandImpl.SignIn;
import main.java.bntu.entity.Role;
import main.java.bntu.entity.Users;

public class TestSignIn  extends SignIn {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");
	@Test
	public void testExecuteABI() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		HttpServletResponse response = mock(HttpServletResponse.class) ;
		
		when(request.getParameter("password")).thenReturn("");
		when(request.getParameter("email")).thenReturn("");
		SignIn signIn = new SignIn ();
		String result = signIn.execute(request, response);
		String page = properties.getString("LOGIN_PAGE_PATH");
		assertEquals(result,page) ;
	}
	@Test
	public void testExecuteACDI() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		HttpServletResponse response = mock(HttpServletResponse.class) ;
		when(request.getParameter("password")).thenReturn("1234");
		when(request.getParameter("email")).thenReturn("Master@mail.ru");
		SignIn signIn = new SignIn ();
		String result = signIn.execute(request, response);
		String page = properties.getString("LOGIN_PAGE_PATH");
		assertEquals(result,page) ;
	}
	@Test
	public void testExecuteACEFI() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		HttpServletResponse response = mock(HttpServletResponse.class) ;
		Role role = mock(Role.class);
		when(request.getParameter("password")).thenReturn("master");
		when(request.getParameter("email")).thenReturn("master@admin.ad");
		when(role.getId()).thenReturn(2);
		SignIn signIn = new SignIn ();
		String result = signIn.execute(request, response);
		String page = properties.getString("PAGE_COMMAND_MASTER");
		assertEquals(result,page) ;
	}
	@Test
	public void testExecuteACEGI() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		HttpServletResponse response = mock(HttpServletResponse.class) ;
		Role role = mock(Role.class);
		when(request.getParameter("password")).thenReturn("admin");
		when(request.getParameter("email")).thenReturn("admin@admin.ad");
		when(role.getId()).thenReturn(1);
		SignIn signIn = new SignIn ();
		String result = signIn.execute(request, response);
		String page = properties.getString("PAGE_COMMAND_ADMIN");
		assertEquals(result,page) ;
	}
	@Test
	public void testExecuteACEHI() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		HttpServletResponse response = mock(HttpServletResponse.class) ;
		Role role = mock(Role.class);
		when(request.getParameter("password")).thenReturn("lena");
		when(request.getParameter("email")).thenReturn("lena@mail.ru");
		when(role.getId()).thenReturn(3);
		SignIn signIn = new SignIn ();
		String result = signIn.execute(request, response);
		String page = properties.getString("PAGE_COMMAND_USERPAGE");
		assertEquals(page,result) ;
	}
	
	@Test
	public void testValidationACH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("password")).thenReturn("");
		when(request.getParameter("email")).thenReturn("");
		SignIn signIn = new SignIn ();
		Users user = signIn.validate(request);
		assertEquals(user,null) ;
	}
	@Test
	public void testValidationABEH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("password")).thenReturn("123456");
		when(request.getParameter("email")).thenReturn("lenochka_sobol@mail.ru");
		SignIn signIn = new SignIn ();
		Users user = signIn.validate(request);
		assertEquals(null, user) ;
	}
	@Test
	public void testValidationABDGH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("password")).thenReturn("123456789101112131415");
		when(request.getParameter("email")).thenReturn("lena@mail.ru");
		SignIn signIn = new SignIn ();
		Users user = signIn.validate(request);
		assertEquals(null, user) ;
	}
	@Test
	public void testValidationABDFH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("password")).thenReturn("lena");
		when(request.getParameter("email")).thenReturn("lena@mail.ru");
		SignIn signIn = new SignIn ();
		Users user = signIn.validate(request);
		assertEquals(new Users("lena@mail.ru","lena"), user) ;
	}
}
