package myMath;
import java.util.ArrayList;
import java.util.Iterator;
public class ComplexFunction implements complex_function {
	private String newleft;
	private  function left;
	private function right;
		
	public  ComplexFunction(){
		this.newleft="0";
		Polynom p = new Polynom("0");
		this.left=p;
		}
	
	public  ComplexFunction(function p1){
	this.newleft=p1.toString();
	this.left=p1;
	}

	public String toString(){
		return this.newleft;
	}
	
	public ComplexFunction(Operation op, function f1,function f2){
		left=f1;
		right=f2;
		if(op==Operation.Plus){
			this.newleft="(" + f1 + ")+(" + f2 +")";
			System.out.println("the complex function after the operator: f(x)=" + this.newleft);
			String s1 = "" + f1;
			String s2 = "" + f2;
			Polynom p = new Polynom(s1);
			Polynom p1 = new Polynom(s2);
			p.add(p1);
			System.out.println("after the operation calculate the complex function is: f(x)=" + p);
			this.left=p;
			this.right=f2;
		}

		if(op==Operation.Times){
			this.newleft="(" + f1 + ")*(" + f2 +")";
			System.out.println("the complex function after the operator: f(x)=" + this.newleft);
			String s1 = "" + f1;
			String s2 = "" + f2;
			Polynom p = new Polynom(s1);
			Polynom p1 = new Polynom(s2);
			p.multiply(p1);
			System.out.println("after the operation calculate the complex function is: f(x)=" + p);
			this.left =p;
			this.right=f2;
		}

		if(op==Operation.Divid){
			function left1 = new Polynom();
			function right1 = new Polynom();
			double cul=0;
			this.newleft="(" + f1 + ")/(" + f2 +")";
			System.out.println("the complex function after the operator: f(x)=" + this.newleft);
			String s1 = "" + f1;
			String s2 = "" + f2;
			Polynom p = new Polynom(s1);
			Polynom p1 = new Polynom(s2);
			left1=p.initFromString(s1);
			right1=p1.initFromString(s2);
			cul=left1.f(1)/right1.f(1);
			System.out.println("after the operation calculate the complex function is: f(x)=" + cul);
			this.left = p;
			this.right=f2;
		}
	}
	/** Add to this complex_function the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	public void plus(function f1){
		ComplexFunction r = new ComplexFunction(Operation.Plus,this.left,f1);
		this.left = r.left;
		this.newleft="the abstract complex function is: f(x)=("+ newleft +")+(" + f1 +")";
	}
	/** Multiply this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1) {
		ComplexFunction r = new ComplexFunction(Operation.Times,this.left,f1);
		this.left = r.left;
		this.newleft="the abstract complex function is: f(x)=("+ newleft +")*(" + f1 +")";
	}
	/** Divides this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be divid this complex_function.
	 */
	public void div(function f1) {
		ComplexFunction r = new ComplexFunction(Operation.Divid,this.left,f1);
		this.left = r.left;
		this.newleft="the abstract complex function is: f(x)=("+ newleft +")/(" + f1 +")";
	}
	/** Computes the maximum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
	public void max(function f1){
		double a=this.left.f(1);
		double b=f1.f(1);
		if(a>b) {System.out.println("the max function is the left");}
		else System.out.println("the max function is the right");
	}
	/** Computes the minimum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
	public void min(function f1){
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
	public void comp(function f1){
		String s1=this.toString();
		String s2=f1.toString();
		s1=s1.replace("x", "("+s2+")");
//		System.out.println(s1);
//		Monom m = new Monom(this.left.toString());
//		Monom a =new Monom (""+m.get_coefficient());
//		int b =m.get_power();
//		Polynom p = new Polynom(f1.toString());
//		Polynom p1 = (Polynom) p.copy();
//		for(int i=0;i<m.get_power()-1;i++){p.multiply(p1);}
//		p.multiply(a);
//		System.out.println(p);
//		this.left=p;
		
	
	}
	
	public static void main(String[] args) {
		Polynom p1 = new Polynom("5x^2+4x^2+x+x^3");
//		function m = new Monom("3x^2");
		function m1 = new Monom("2");
		ComplexFunction p = new ComplexFunction(Operation.Times,p1,m1);
//		complexFunction p2 = new complexFunction(Operation.Times,p,m1);
//		p.comp(m);
//		System.out.println(p);
		System.out.println(p.f(-1));
	}
//
//	public static void main(String[] args) {
//		function m1 = new Monom("2x");
//		function p1 = new Polynom("3x");
//		function p2 = new Polynom("5.5x");
//		complexFunction	oper = new complexFunction(Operation.Times,m1,p2);
//		oper.plus(p1);
//		System.out.println(oper);
//		oper.mul(p1);
//		System.out.println(oper);
//		oper.div(p1);
//		System.out.println(oper);
//	}
	/** returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function left(){
		return this.left;
	}
	/** returns the right side of the complex function - this side might not exists (aka equals null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function right(){
		return this.right;
	}
	/**
	 * The complex_function oparation: plus, mul, div, max, min, comp
	 * @return
	 */
	public Operation getOp(){
		return null;
	}
	@Override
	public double f(double x){
		double f=0;
		f=f+left.f(x);
		return f;
	}
	@Override
	public function initFromString(String s){
		function f = new Polynom(s);
		return f;
	}
	@Override
	public function copy(){
		function p = new ComplexFunction(this.left);
		return p;
	}
}