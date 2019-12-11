package myMath;
import java.util.ArrayList;
import java.util.Iterator;
public class ComplexFunction implements complex_function {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String newleft;
	private  function left;
	private function right;
	private Operation op;

	public ComplexFunction(){
		this.left=null;
		this.right=null;		
	}

	public ComplexFunction(function p1){
		this.newleft="("+p1+")";
		this.left=p1;
		this.right=null;
	}
	
	public ComplexFunction(String s){
		s=s.replaceAll(" ", "");
		String temp="";
		try {
		temp = s.substring(0, s.indexOf('('));
		this.op=strToop(temp);
		}catch (Exception e) {System.out.println("problem with op");}

		try {
			temp=s.substring(s.indexOf('(')+1,s.indexOf(','));
			function f = new Polynom(temp);
			this.left=f;
		}catch (Exception e) {System.out.println("problem with left function");}
		try {
			temp=s.substring(s.indexOf(',')+1,s.indexOf(')'));
			function f = new Polynom(temp);
			this.right=f;
		}catch (Exception e) {System.out.println("problem with right function");}
		
	}
	
	public String toString(){
		if(this.left() ==null && this.right() == null) return null;
		else if (this.left() !=null && this.right() == null) return ""+this.left;
		else return this.getOp()+"("+this.left()+","+this.right()+")";     
	}

	public ComplexFunction(String op, function f1,function f2){
		this.left=f1;
		this.right=f2;
		this.newleft=op+"("+f1+","+f2+")";
		this.op = strToop(op);
	}
	/** Add to this complex_function the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	public void plus(function f1){
		if(this.left() == null)this.left = f1;
		else {
			this.left=this.copy();
			this.right=f1;
		}
		this.newleft=op+"("+this.toString()+","+this.right()+")";
		this.op = Operation.Plus;

	}
	/** Multiply this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1) {
		if(this.left() == null)this.left = f1;
		else{
			this.left=this.copy();
			this.right=f1;
		}	
		this.newleft=op+"("+this.toString()+","+this.right()+")";	
		this.op = Operation.Times;
	}
	/** Divides this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be divid this complex_function.
	 */
	public void div(function f1) {
		if(this.left() == null)this.left = f1;
		else{
			this.left=this.copy();
			this.right=f1;
		}
		this.newleft=op+"("+this.toString()+","+this.right()+")";
		this.op = Operation.Divid;	
	}
	public static void main(String[] args) {
		Polynom p1 = new Polynom("3x^3");
		Polynom p2 = new Polynom("2x^2");
		ComplexFunction c = new ComplexFunction("plus(2x^2,2x^2)");
		System.out.println(c);
		System.out.println(c.left()+"  "+c.right()+"  "+c.op);
	}
	/** Computes the maximum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
	public void max(function f1){
		if(this.left() == null)this.left = f1;
		else {
			this.left=this.copy();
		    this.right=f1;
		}
		this.newleft=op+"("+this.toString()+","+this.right()+")";
		this.op = Operation.Max;	
	}
	/** Computes the minimum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
	public void min(function f1){
		if(this.left() == null)this.left = f1;
		else{
			this.left=this.copy();
			this.right=f1;
		}	
		this.newleft=op+"("+this.toString()+","+this.right()+")";
		this.op = Operation.Min;
	}
	/**
	 * This method wrap the f1 complex_function with this function: this.f(f1(x))
	 * @param f1 complex function
	 */
	public void comp(function f1){
		if(this.left() == null)this.left = f1;
		else{
			this.left=this.copy();
			this.right=f1;	
		}
		this.newleft=op+"("+this.toString()+","+this.right()+")";
		this.op = Operation.Comp;
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
		return this.op;
	}
	@Override
	public double f(double x){
		switch (op) {
		case Plus:
			if(right().equals(null))return left().f(x);
			else return this.left().f(x) + this.right().f(x);
		case Times:
			if(right().equals(null))return left().f(x);
			else return left().f(x)*this.right().f(x);
		case Divid:
			if(right().equals(null))return left().f(x);
			else return left().f(x)/this.right().f(x);
		case Comp:
			if(right().equals(null))return left().f(x);
			else return left().f(this.right().f(x));
		case Max:
			if(right().equals(null))return left().f(x);
			else return Math.max(left().f(x), this.right().f(x));
		case Min:
			if(right().equals(null))return left().f(x);
			else return Math.min(left().f(x), this.right().f(x));
		default:
			throw new RuntimeException("err");
		}
	}

	@Override
	public function initFromString(String s){
		try {
			
			return new Polynom(s);
		}
		catch (Exception e) {System.out.println("isn't polynom");}
			return new ComplexFunction(s);	
	}
	@Override
	public function copy(){
		ComplexFunction p = new ComplexFunction();
		p.left=this.left().copy();
		p.right=this.right();
		p.op = this.getOp();
		return p;
	}

	public Operation strToop(String op){
		try {
		if(op.equals("plus"))
			return Operation.Plus;
		else if(op.equals("mul"))
			return Operation.Times;
		else if(op.equals("div"))
			return Operation.Divid;
		else if(op.equals("comp"))
			return Operation.Comp;
		else if(op.equals("max"))
			return Operation.Max;
		else if(op.equals("min"))
			return Operation.Min;
		}
		catch (Exception e) {System.out.println("isn't polynom");}
		return null;
	}
}
