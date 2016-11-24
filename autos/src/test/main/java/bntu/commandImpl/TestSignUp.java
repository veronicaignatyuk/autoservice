package test.main.java.bntu.commandImpl;

import static org.junit.Assert.assertEquals;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import static org.mockito.Mockito.*;
import main.java.bntu.commandImpl.SignUp;
import main.java.bntu.entity.Users;

public class TestSignUp {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");
	@Test
	public void testValidationACH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("passwordF")).thenReturn("");
		when(request.getParameter("email1")).thenReturn("");
		SignUp signUp = new SignUp ();
		Users user = signUp.validate(request);
		assertEquals(user,null) ;
	}
	
	@Test
	public void testValidationABEH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("passwordF")).thenReturn("123456");
		when(request.getParameter("email1")).thenReturn("lenochka_sobol@mail.ru");
		SignUp signUp = new SignUp ();
		Users user = signUp.validate(request);
		assertEquals(null, user) ;
	}
	@Test
	public void testValidationABDGH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("passwordF")).thenReturn("123456789101112131415");
		when(request.getParameter("email1")).thenReturn("lena@mail.ru");
		SignUp signUp = new SignUp ();
		Users user = signUp.validate(request);
		assertEquals(null, user) ;
	}
	@Test
	public void testValidationABDjFH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("passwordF")).thenReturn("1234567");
		when(request.getParameter("passwordS")).thenReturn("1236278");
		when(request.getParameter("email1")).thenReturn("sobol@mail.ru");
		SignUp signIn = new SignUp ();
		Users user = signIn.validate(request);
		assertEquals(null, user) ;
	}
	@Test
	public void testValidationABDFH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("passwordF")).thenReturn("1234567");
		when(request.getParameter("passwordS")).thenReturn("1234567");
		when(request.getParameter("email1")).thenReturn("sobol@mail.ru");
		SignUp signIn = new SignUp ();
		Users user = signIn.validate(request);
		assertEquals(new Users("sobol@mail.ru","1234567"), user) ;
	}
}
