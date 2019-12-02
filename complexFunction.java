package myMath;
import java.util.ArrayList;
import java.util.Iterator;
public class complexFunction implements complex_function {

	public String com1;
	public String newleft;
	private  function left;
	private function right;
	public complexFunction(){
		//		complex=new ArrayList<function>();
	}
	 Operation op =  Operation.Plus;
	public static void main(String[] args) {
		function m1 = new Monom("2x");
		function m2 = new Monom("6x");
		function p2 = new Polynom("4+x");
		complexFunction ofir = new complexFunction("mul",m1,m2);
//		System.out.println(ofir);
		System.out.println(p2);
		ofir.min(p2);
//		ofir.max(m1);
	}

	public String toString()
	{
		return com1;
	}
	public complexFunction(String op, function f1,function f2)
	{
		left=f1;
		right=f2;
		if(op=="plus"){
			com1="(" + f1 + ")+(" + f2 +")";
			newleft=com1;
			String s = f1+"+"+f2;
			function p = new Polynom(s);
			left=p;
			System.out.println(left);
			right = null;
		}

		if(op=="mul"){
			com1="(" + f1 + ")*(" + f2 +")";
			newleft=com1;
			String s1 = "" + f1;
			String s2 = "" + f2;
			Polynom p = new Polynom(s1);
			Monom p2 = new Monom(s2);
			p.multiply(p2);
			left =p;
			right = null;
		}

		if(op=="div"){
			com1="("+f1+")/("+f2+")";
			newleft=com1;
		}
	}
	/** Add to this complex_function the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	public void plus(function f1){
		complexFunction r = new complexFunction("plus",this.left,f1);
		left = r;
		com1="("+ newleft +")+(" + f1 +")";
		newleft=com1;
	}
	/** Multiply this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1) {
		complexFunction r = new complexFunction("mul",this.left,f1);
		left = r;
		com1="("+ newleft + ")*(" + f1 +")";
		newleft=com1;

	}
	/** Divides this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be divid this complex_function.
	 */
	public void div(function f1) {
		complexFunction r = new complexFunction("div",this.left,f1);
		left = r;
		com1="("+ newleft + ")/(" + f1 +")";
		newleft=com1;

	}
	/** Computes the maximum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
	public void max(function f1) {
		double a=this.left.f(1);
		double b=f1.f(1);
		if(a>b) {System.out.println("the max function is the left");}
		else System.out.println("the max function is the right");
	}
	/** Computes the minimum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
	public void min(function f1) {
		double a=left.f(1);
		System.out.println(a);
		System.out.println(this.left);
		double b=f1.f(1);
		if(a>b) {System.out.println("the min function is the right");}
		else System.out.println("the min function is the left");
	}
	/**
	 * This method wrap the f1 complex_function with this function: this.f(f1(x))
	 * @param f1 complex function
	 */
	public void comp(function f1) {
	}
	/** returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function left() {
		return left;
	}
	/** returns the right side of the complex function - this side might not exists (aka equals null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function right() {
		return right;
	}
	/**
	 * The complex_function oparation: plus, mul, div, max, min, comp
	 * @return
	 */
	public Operation getOp() {
		return null ;
	}
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}
}
