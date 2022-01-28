package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class AulaTest {
	
	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String ERROR_NO_EXCEPCION = "No debería haber saltado la excepción.";
	private static final String NOMBRE = "Salón de actos";

	@Test
	public void constructorUnParametroValidoTest() {
		Aula aula = null;
		try {
			aula = new Aula(NOMBRE);
			assertEquals(NOMBRE, aula.getNombre());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorUnParametroNoValidoTest() {
		Aula aula = null;
		try {
			String nombre = null;
			aula = new Aula(nombre);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: El nombre del aula no puede ser nulo.", e.getMessage());
			assertNull(aula);
		}
		try {
			aula = new Aula("");
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("ERROR: El nombre del aula no puede estar vacío.", e.getMessage());
			assertNull(aula);
		}
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		Aula aula = new Aula(NOMBRE);
		Aula aula1;
		try {
			aula1 = new Aula(aula);
			assertEquals(NOMBRE, aula1.getNombre());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		Aula aula = null;
		try {
			Aula aula1 = null;
			aula = new Aula(aula1);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: No se puede copiar un aula nula.", e.getMessage());
			assertNull(aula);
		}
	}
	
	@Test
	public void getTest() {
		Aula aula = new Aula(NOMBRE);
		assertEquals(NOMBRE, aula.getNombre());
	}
	
	@Test
	public void hasCodeTest() {
		Aula aula = new Aula(NOMBRE);
		Aula aula1 = new Aula(NOMBRE);
		Aula aula2 = new Aula("Andrés");
		assertEquals(aula.hashCode(), aula.hashCode());
		assertEquals(aula.hashCode(), aula1.hashCode());
		assertNotEquals(aula.hashCode(), aula2.hashCode());
	}
	
	@Test
	public void equalTest() {
		Aula aula = new Aula(NOMBRE);
		Aula aula1 = new Aula(NOMBRE);
		Aula aula2 = new Aula("Andrés");
		assertNotEquals(aula, null);
		assertNotEquals(aula, "");
		assertEquals(aula, aula);
		assertEquals(aula, aula1);
		assertNotEquals(aula, aula2);
	}
	
	@Test
	public void toStringTest() {
		Aula aula = new Aula(NOMBRE);
		assertEquals("nombre Aula=Salón de actos", aula.toString());
	}

}
