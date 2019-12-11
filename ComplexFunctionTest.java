package myMath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import myMath.ComplexFunction;
import myMath.Monom;
import myMath.Polynom;

public class ComplexFunctionTest {
	@Test
	public void Comptest() {
		Monom m = new Monom("x^2");
		Polynom p = new Polynom("2x^2");
		ComplexFunction comp = new ComplexFunction("comp",m,p);
		assertEquals(comp.f(1), 4);
	}
	
	@Test
	public void MonomP() {
		Monom m = new Monom("2x^2");
		ComplexFunction formon = new ComplexFunction("plus", m, m);
		assertEquals(formon.f(2), 16);
	}

	@Test
	public void Plustest() {
		Monom m = new Monom("2x^2");
		Polynom p = new Polynom("2x^2+1");
		ComplexFunction plus = new ComplexFunction("plus", p, m);
		assertEquals(plus.f(1), 5);
	}

	@Test
	public void Multest() {
		Monom m = new Monom("3x^2");
		Polynom p = new Polynom("2x^2+1");
		ComplexFunction mul = new ComplexFunction("mul", p, m);
		assertEquals(mul.f(1), 9);
	}

	@Test
	public void Mintest() {
		Monom m = new Monom("7x^3");
		Polynom p = new Polynom("2x^2+1");
		ComplexFunction min = new ComplexFunction("min",m,p);
		assertEquals(min.f(2), 9);
	}
	
	@Test
	public void Maxtest() {
		Monom m = new Monom("7x^3");
		Polynom p = new Polynom("2x^2+1");
		ComplexFunction max = new ComplexFunction("max",m,p);
		assertEquals(max.f(2), 56);
	}
	
	@Test
	public void Dividtest() {
		Polynom p1 = new Polynom("7x^2+1");
		Polynom p2 = new Polynom("3x^2+5");
		ComplexFunction div = new ComplexFunction("div",p1,p2);
		assertEquals(div.f(1), 1);
	}
}