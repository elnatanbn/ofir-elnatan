package myMath;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Iterator;
import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	ArrayList<Monom> pol = new ArrayList<Monom>();
	/**
	 * Zero (empty polynom)
	 */
	public Polynom(){
		this.pol=new ArrayList<Monom>();
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)(-1.2x-7.1)", "(3-3.4x+1)((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom (String s){
		s=s.replaceAll(" ", "");
		pol=new ArrayList<Monom>();
		String temp = "";
		int cnt=0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(0)== '+' || s.charAt(0)== '-' ){	
				if(s.charAt(0)=='-'){temp= "-";}
				s=s.substring(i+1);
			}
			if(s.charAt(i) == '+' || s.charAt(i) == '-'){
				Monom m = new Monom();
				temp = temp+s.substring(cnt,i);
				if(temp.equals("x") || temp.equals("+x") || temp.equals("-x")) {m = new Monom("x");}
				else if(temp.equals("-x") ) {m = new Monom("-x");}
				else m = new Monom(temp);
				pol.add(m);	
				temp="";
				cnt=i;
			}
		}
		if(cnt<s.length()){	
			Monom m = new Monom();
			temp = s.substring(cnt);
			if(temp.equals("x") || temp.equals("+x")) {m = new Monom("x");}
			else if(temp.equals("-x") ) {m = new Monom("-x");}
			else  m = new Monom(temp);
			pol.add(m);		
		}
	}

	@Override
	public double f(double x){
		double f=0;
		Iterator<Monom> fun = pol.iterator();
		while(fun.hasNext()){
			Monom m= fun.next();
			f= f+m.f(x);
		}
		return f;
	}

	@Override
	public void add(Polynom_able p1){
		Iterator<Monom> addI = p1.iteretor();
		while(addI.hasNext()){
			Monom m = addI.next();
			this.add(m);;
		}
	}

	@Override
	public void add(Monom m1){	
		ArrayList<Monom> poly=new ArrayList<Monom>();
		if(this.isZero()) {poly.add(m1);}
		else{
			Iterator<Monom> iter = this.iteretor();
			boolean flag = false;
			while(iter.hasNext() ){
				Monom m = iter.next();
				if(m.get_power() == m1.get_power() && flag == false ){
					m.add(m1);	
					poly.add(m);
					flag = true;
				}
				else {poly.add(m);}
			}	
			if(flag == false) {poly.add(m1);}
		}
		this.pol=poly;
		Monom_Comperator sort1 = new Monom_Comperator();
		this.pol.sort(sort1);
	}

	public void sub(Monom m1){
		this.sortpol();
		boolean found_power = false;
		Iterator<Monom> iter = this.iteretor();
		while(iter.hasNext()){
			Monom m = iter.next();
			if(m.get_power()==m1.get_power()) { // same power
				m.sub(m1);
				found_power = true;
				if(m.isZero()) { //if(m.get_coefficient()==0)
					iter.remove();
				}
			}
		}
		if(!found_power){
			this.pol.add(m1);
		}
	}
	/**
	 *  This method subtract Polynom p1 from this Polynom. 
	 *  @param p1 type Polynom_able.
	 *  */
	public void substract(Polynom_able p1) {
		this.sortpol();
		((Polynom) p1).sortpol();
		Iterator<Monom> It=p1.iteretor();
		while(It.hasNext()){
			this.sub(It.next());
		}
	}

	@Override
	public void multiply(Monom m1){
		Monom m = new Monom("0");
		for(int i=0;i<pol.size();i++){
			m=pol.get(i);
			m.multipy(m1);	
			pol.set(i, m);	
		}
		Monom_Comperator sort1 = new Monom_Comperator();
		this.pol.sort(sort1);
	}

	@Override
	public void multiply(Polynom_able p1){
		ArrayList<String> monoms=new ArrayList<String>();
		ArrayList<String> polynoms=new ArrayList<String>();
		Polynom ptot = new Polynom();
		for(int i=0; i<this.pol.size();i++){
			String s="";
			Monom m = new Monom();
			m=this.pol.get(i);
			s=p1.toString();
			polynoms.add(s);
			monoms.add(m.toString());		
		}
		for(int i=0; i<monoms.size();i++){
			Monom m = new Monom(monoms.get(i).toString());
			Polynom p = new Polynom(polynoms.get(i).toString());
			p.multiply(m);
			ptot.add(p);
		}
		Polynom psum = new Polynom(ptot.toString());
		this.pol=psum.pol;
	}

	@Override
	public boolean equals(Object p1){
		boolean ans=true;
		if(this.isZero() || p1 == null) ans = false;
		else {
			Polynom p = new Polynom(p1.toString());
			Polynom p2 = new Polynom(p.toString());
			p2.sortpol();
			this.sortpol();
			if(this.pol.size() != p2.pol.size()) {return false;}
			else {
				Iterator<Monom> pol1 = pol.iterator();
				Iterator<Monom> pol2 = p2.iteretor();
				while(ans && pol1.hasNext()){
					Monom m1 = pol1.next();
					Monom m2 = pol2.next();
					if(m1.equals(m2)) {ans=true;}
					else {return false;}
				}
			}
		}
		return ans;

	}

	@Override
	public boolean isZero(){
		if(pol.isEmpty() || pol.get(0) == Monom.ZERO) {return true;}
		else return false;
	}

	@Override
	public function copy(){	
		Polynom p = new Polynom(this.toString());
		function f = p;
		return f;
	}

	@Override
	public Polynom derivative(){
		Polynom poldev = new Polynom();
		Iterator<Monom> dev = this.iteretor();
		while(dev.hasNext()) {
			Monom m = dev.next();
			poldev.add(m.derivative());
		}
		return poldev;
	}

	@Override
	public double area(double x0, double x1, double eps){		
		double sum=0,i=x0;
		while(i<x1){
			if(0<=f(i)){
				sum=sum+f(i)*eps;
			}
			i=i+eps; 
		}
		return sum;
	}

	@Override
	public double root(double x0, double x1, double eps){
		double l = x0;
		double r = x1;
		if (l >r || this.f(l)*this.f(r)>0){throw new RuntimeException("parameters problem");}
		while(r-l > eps){
			double mid=(r+l)/2;
			if(this.f(l)*this.f(mid) <= 0) r=mid;
			else l=mid;
		}
		return (l+r)/2;
	}

	@Override
	public String toString(){
		if(pol.size() == 0 )  return "Polynomial not initialized";
		Iterator<Monom> i = pol.iterator();
		Monom f = i.next();
		String polString = f.toString();
		while(i.hasNext()){
			f = i.next();
			if(f.get_coefficient()>0)polString=polString + "+" + f.toString();
			else if(f.get_coefficient()<0)polString=polString + f.toString();
		}
		return polString ;
	}

	@Override
	public function initFromString(String s){
		function f = new Polynom(s);
		return f;
	}

	@Override
	public Iterator<Monom> iteretor() {
		return pol.iterator();
	}

	public static void main(String[] args) {
		Polynom p = new Polynom("1+x");
		Polynom p1 = new Polynom("1+2x^2+3x^4+5x^3+1+6+5+4");
		System.out.println(p);
	}
	//* Private Methods and Data **

	private void sortpol(){
		Polynom p = new Polynom(this.toString());
		Polynom ptot = new Polynom();
		for(int i=0; i<this.pol.size();i++){
			Monom m = new Monom(p.pol.get(i));
			ptot.add(m);
		}
		this.pol=ptot.pol;
		Monom_Comperator sort = new Monom_Comperator();
		this.pol.sort(sort);
	}
	public int sizepol() {
		Monom m = new Monom();
		Iterator<Monom> pol = this.iteretor();
		int cnt=0;
		while(pol.hasNext()){
			cnt++;
			m=pol.next();
		}
		if(m.equals(Monom.ZERO))cnt=0;
		this.sizepol = cnt;
		return this.sizepol;
	}
	public int sizepol;
}