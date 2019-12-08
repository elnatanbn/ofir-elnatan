package myMath;
import java.io.IOException;
import java.io.*;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions {
	private String print;
	private  function value;
//	private function right;
	public Functions_GUI() {
		this.print="0";
	}
	
	public Functions_GUI(function f1) {
		this.print=""+f1.toString();
		this.value=f1;
	}
	
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<function> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(function e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		StdDraw.clear();	
	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
	
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setXscale(-15,15);
		StdDraw.setYscale(-15,15);
		double l=0;
		while(l<15) {
			StdDraw.setPenColor(StdDraw.GRAY);
			double b =l*1.5;
			StdDraw.line(b,-15,b,15);	//+y grid	
			StdDraw.line(-b,-15,-b,15);	//-y grid
			StdDraw.line(-15,l,15,l);  //+x grid
			StdDraw.line(-15,-l,15,-l);	//-x grid
			l++;
		}
		for(int i=-15;i<16;i++){
			int y =1;
			double x=i*1.5;
			StdDraw.text(-y, i, ""+i);	
			StdDraw.text(i, 0, ""+i);
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(0,-15,0,15);
		StdDraw.line(-15,0,15,0);
		
		double i = 0;
		while(i<15) {
			StdDraw.filledCircle(i, this.value.f(i), 0.1);
			StdDraw.filledCircle(-i, this.value.f(i), 0.1);
			i=i+0.008;
		}
		System.out.println("finish");
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.filledRectangle(-15, -10, 30, 5);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.text(-12, -6, ""+this.print);
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] a) {
		Polynom p = new Polynom("x^2");
		ComplexFunction f = new ComplexFunction(p);
		Functions_GUI data = new Functions_GUI(f);
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		data.drawFunctions(w,h,rx,ry,res);
		
	}
	
	public String toString(){
		return this.print;
	}
	
}
