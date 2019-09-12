package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import polynomial.Monomial;
import polynomial.Polynomial;

class TestUnit {
	

	@Test
	void testAdd() {
		Polynomial p1=new Polynomial();
		Polynomial p2=new Polynomial();
		Polynomial p3=new Polynomial();
		Polynomial correctResult=new Polynomial();
		p1.addMonomial(new Monomial(2,2));
		p1.addMonomial(new Monomial(3,1));
		p1.addMonomial(new Monomial(3,0));
		p2.addMonomial(new Monomial(1,2));
		p2.addMonomial(new Monomial(1,1));
		correctResult.addMonomial(new Monomial(3,2));
		correctResult.addMonomial(new Monomial(4,1));
		correctResult.addMonomial(new Monomial(3,0));
		p3=p3.add(p1, p2);
		assertTrue(p3.equals(correctResult));
	}
	
	@Test
	void testSub() {
		Polynomial p1=new Polynomial();
		Polynomial p2=new Polynomial();
		Polynomial p3=new Polynomial();
		Polynomial correctResult=new Polynomial();
		p1.addMonomial(new Monomial(2,2));
		p1.addMonomial(new Monomial(3,1));
		p1.addMonomial(new Monomial(3,0));
		p2.addMonomial(new Monomial(1,2));
		p2.addMonomial(new Monomial(1,1));
		correctResult.addMonomial(new Monomial(1,2));
		correctResult.addMonomial(new Monomial(2,1));
		correctResult.addMonomial(new Monomial(3,0));
		p3=p3.sub(p1, p2);
		assertTrue(p3.equals(correctResult));
	}

	@Test
	void testMul()
	{
		Polynomial p1=new Polynomial();
		Polynomial p2=new Polynomial();
		Polynomial p3=new Polynomial();
		Polynomial correctResult=new Polynomial();
		p1.addMonomial(new Monomial(2,2));
		p1.addMonomial(new Monomial(3,1));
		p2.addMonomial(new Monomial(1,2));
		p2.addMonomial(new Monomial(1,1));
		correctResult.addMonomial(new Monomial(2,4));
		correctResult.addMonomial(new Monomial(5,3));
		correctResult.addMonomial(new Monomial(3,2));
		p3=p3.mul(p1, p2);
		assertTrue(p3.equals(correctResult));
	}
	
	@Test
	void testDerive()
	{
		Polynomial p1=new Polynomial();
		Polynomial p3=new Polynomial();
		Polynomial correctResult=new Polynomial();
		p1.addMonomial(new Monomial(2,2));
		p1.addMonomial(new Monomial(3,0));
		correctResult.addMonomial(new Monomial(4,1));
		p3 = p3.derive(p1);
		assertTrue(p3.equals(correctResult));
		
		
	}
	
	@Test
	void testIntegrate()
	{
		Polynomial p1=new Polynomial();
		Polynomial p3=new Polynomial();
		Polynomial correctResult=new Polynomial();
		p1.addMonomial(new Monomial(3,2));
		p1.addMonomial(new Monomial(3,0));
		correctResult.addMonomial(new Monomial(1,3));
		correctResult.addMonomial(new Monomial(3,1));
		p3 = p3.integrate(p1);
		assertTrue(p3.equals(correctResult));
		
		
	}
	
	@Test
	void testDivide() {
		Polynomial p1=new Polynomial();
		Polynomial p2=new Polynomial();
		ArrayList<Polynomial> p3=new ArrayList<Polynomial>();
		Polynomial correctResultQ=new Polynomial();
		Polynomial correctResultR=new Polynomial();
		p1.addMonomial(new Monomial(1,1));
		p1.addMonomial(new Monomial(1,0));
		p2.addMonomial(new Monomial(1,1));
		correctResultQ.addMonomial(new Monomial(1,0));
		correctResultR.addMonomial(new Monomial(1,0));
		p3=p1.divide(p1, p2);
		//System.out.println(p3.get(0));
		assertTrue(p3.get(0).equals(correctResultQ));
		assertTrue(p3.get(1).equals(correctResultR));
	}
}
