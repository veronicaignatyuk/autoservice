package test.main.java.bntu.commandImpl;

import static org.junit.Assert.assertEquals;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import static org.mockito.Mockito.*;
import main.java.bntu.commandImpl.AddCarcar;

public class TestAddCar {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");
	@Test
	public void testValidationACH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("model")).thenReturn("");
		when(request.getParameter("brand")).thenReturn("");
		AddCarcar car = new AddCarcar ();
		int id = car.validate(request);
		assertEquals(id, 0) ;
	}
	
	@Test
	public void testValidationABEH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("model")).thenReturn("fghg");
		when(request.getParameter("brand")).thenReturn("f");
		AddCarcar car = new AddCarcar ();
		int id = car.validate(request);
		assertEquals(id, 0) ;
	}
	@Test
	public void testValidationABDGH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("model")).thenReturn("g");
		when(request.getParameter("brand")).thenReturn("fhg");
		AddCarcar car = new AddCarcar ();
		int id = car.validate(request);
		assertEquals(id, 0) ;
	}
	@Test
	public void testValidationABDFH() {
		HttpServletRequest request = mock(HttpServletRequest.class) ;
		when(request.getParameter("model")).thenReturn("citroen");
		when(request.getParameter("brand")).thenReturn("c5");
		AddCarcar car = new AddCarcar ();
		int id = car.validate(request);
		assertEquals(id, 1) ;
	}
}
