package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import static org.junit.Assert.*;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.junit.Test;

public class ProfesorTest {
	
	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String ERROR_NO_EXCEPCION = "No debería haber saltado la excepción.";
	private static final String nombre = "José Ramón";
	private static final String correo = "joseramon.jimenez@iesalandalus.org";
	private static final String telefono = "950112233";
	
	Profesor profesorConTelefono = new Profesor(nombre, correo, telefono);
	Profesor profesorSinTelefono = new Profesor(nombre, correo);

	@Test
	public void constructorDosParametrosValidoTest() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(nombre, correo);
			assertEquals(nombre, profesor.getNombre());
			assertEquals(correo, profesor.getCorreo());
			assertNull(profesor.getTelefono());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorTresParametrosValidoTest() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(nombre, correo, telefono);
			assertEquals(nombre, profesor.getNombre());
			assertEquals(correo, profesor.getCorreo());
			assertEquals(telefono, profesor.getTelefono());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
		try {
			profesor = new Profesor(nombre, correo, null);
			assertEquals(nombre, profesor.getNombre());
			assertEquals(correo, profesor.getCorreo());
			assertNull(profesor.getTelefono());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorDosParametrosNoValidoTest() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(null, correo);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: El nombre del profesor no puede ser nulo.", e.getMessage());
			assertNull(profesor);
		}
		try {
			profesor = new Profesor("", correo);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("ERROR: El nombre del profesor no puede estar vacío.", e.getMessage());
			assertNull(profesor);
		}
		try {
			profesor = new Profesor(nombre, null);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: El correo del profesor no puede ser nulo.", e.getMessage());
			assertNull(profesor);
		}
		try {
			profesor = new Profesor(nombre, "");
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("ERROR: El correo del profesor no es válido.", e.getMessage());
			assertNull(profesor);
		}
	}
	
	@Test
	public void constructorTresParametrosNoValidoTest() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(nombre, correo, "");
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("ERROR: El teléfono del profesor no es válido.", e.getMessage());
			assertNull(profesor);
		}
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		Profesor profesor;
		try {
			profesor = new Profesor(profesorConTelefono);
			assertEquals(nombre, profesor.getNombre());
			assertEquals(correo, profesor.getCorreo());
			assertEquals(telefono, profesor.getTelefono());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		Profesor profesor = null;
		try {
			profesor = new Profesor(null);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: No se puede copiar un profesor nulo.", e.getMessage());
			assertNull(profesor);
		}
	}
	
	@Test
	public void getTest() {
		assertEquals(nombre, profesorConTelefono.getNombre());
		assertEquals(correo, profesorConTelefono.getCorreo());
		assertEquals(telefono, profesorConTelefono.getTelefono());
		assertNull(profesorSinTelefono.getTelefono());
	}
	
	@Test
	public void equalTest() {
		Profesor profesor = new Profesor(nombre, correo, telefono);
		Profesor profesor1 = new Profesor("Andrés", correo, telefono);
		assertNotEquals(profesor, null);
		assertNotEquals(profesor, "");
		assertEquals(profesor, profesor);
		assertEquals(profesorConTelefono, profesor);
		assertNotEquals(profesor, profesor1);
	}
	
	@Test
	public void hashCodeTest() {
		Profesor profesor = new Profesor(nombre, correo, telefono);
		Profesor profesor1 = new Profesor("Andrés", correo, telefono);
		assertEquals(profesor.hashCode(), profesor.hashCode());
		assertEquals(profesorConTelefono.hashCode(), profesor.hashCode());
		assertNotEquals(profesor.hashCode(), profesor1.hashCode());
	}
	
	@Test
	public void toStringTest() {
		assertEquals("nombre=José Ramón, correo=joseramon.jimenez@iesalandalus.org, telefono=950112233", profesorConTelefono.toString());
		assertEquals("nombre=José Ramón, correo=joseramon.jimenez@iesalandalus.org", profesorSinTelefono.toString());
	}

}
