package myMath;
import java.util.ArrayList;
public class ComplexFunction implements complex_function {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String newleft;
	private  function left;
	private function right;
	public int sizecomp;
	
	
	public  ComplexFunction(){
		this.newleft="0";
		Polynom p = new Polynom("0");
		this.left=p;
		System.out.println("("+p+")");	
	}

	public  ComplexFunction(function p1){
		this.newleft=p1.toString();
		this.left=p1;
		System.out.println("("+p1+")");	
	
	}

	public String toString(){
		return this.newleft;
	}
	
	public ComplexFunction(String op, function f1,function f2){
		left=f1;
		right=f2;
		if(op == "plus"){
			this.newleft="(" + f1 + ")+(" + f2 +")";
			System.out.println(" operator - plus: f(x)=" + this.newleft);
			Polynom p = new Polynom(f1.toString());
			Polynom p1 = new Polynom(f2.toString());
			p.add(p1);
			System.out.println("Opening brackets: f(x)=" + p);
			this.left=p;
			this.right=f2;
		}

		if(op == "mul"){
			this.newleft="(" + f1 + ")*(" + f2 +")";
			System.out.println(" operator - times: f(x)=" + this.newleft);
			Polynom p = new Polynom(f1.toString());
			Polynom p1 = new Polynom(f2.toString());
			p.multiply(p1);
			System.out.println("Opening brackets: f(x)=" + p);
			this.left =p;
			this.right=f2;
		}

		if(op == "div"){
			function left1 = new Polynom();
			function right1 = new Polynom();
			double cul=0;
			this.newleft="(" + f1 + ")/(" + f2 +")";
			System.out.println("operator - times: f(x)=" + this.newleft);
			Polynom p = new Polynom(f1.toString());
			Polynom p1 = new Polynom(f2.toString());
			left1=p.initFromString(f1.toString());
			right1=p1.initFromString(f2.toString());
			cul=left1.f(1)/right1.f(1);
			System.out.println("Opening brackets: f(x)=" + cul);
			this.left = p;
			this.right=f2;
		}
		
	}
	/** Add to this complex_function the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	public void plus(function f1){
		ComplexFunction r = new ComplexFunction("Plus",this.left,f1);
		this.left = r.left;
		this.newleft="the abstract complex function is: f(x)=("+ newleft +")+(" + f1 +")";
	}
	/** Multiply this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1) {
		ComplexFunction r = new ComplexFunction("mul",this.left,f1);
		this.left = r.left;
		this.newleft="the abstract complex function is: f(x)=("+ newleft +")*(" + f1 +")";
	}
	/** Divides this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be divid this complex_function.
	 */
	public void div(function f1) {
		ComplexFunction r = new ComplexFunction("div",this.left,f1);
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
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s1);
		Polynom ptot = new Polynom("0");
		s1=s1.replace("x", "("+s2+")");
		System.out.println(s1);
		for(int i=0;i<p1.pol.size();i++){
			Monom m = new Monom(p1.pol.get(i));
			if(m.get_power() > 0) {
				Monom mcof = new Monom(""+m.get_coefficient());
				p2 = new Polynom(s2);
				for(int j=0;j<m.get_power()-1;j++) {
					p2.multiply(p2);	
				}
				p2.multiply(mcof);
				ptot.add(p2);
			}
			else if(m.get_power() == 0) {
				Monom mcof = new Monom(""+m.get_coefficient());
				ptot.add(mcof);
			}
		}
		System.out.println(ptot);
	}

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
	
	public int size(){
	
		return this.sizecomp;
	}
}
