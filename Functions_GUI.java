package myMath;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//import jdk.nashorn.internal.parser.JSONParser;



public class Functions_GUI implements functions {
	private ArrayList<function> collection = new ArrayList<function>();
	private function funvalue;
	public String type="";

	
	public Functions_GUI() {
		this.collection.removeAll(this.collection);
		this.type="null";
		this.funvalue=null;
	}

	public Functions_GUI(function f1){
		this.collection.add(f1);
		this.type=f1.toString();
		this.funvalue = f1;	
	}

	public Functions_GUI(String s){
		function f1 = new Polynom(s);
		this.collection.add(f1);
		this.type=f1.toString();
		this.funvalue = f1;	
	}

	@Override
	public int size() {
		return this.collection.size();
	}

	@Override
	public boolean isEmpty() {
		return this.collection.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return this.collection.contains(o);
	}

	@Override
	public Iterator<function> iterator() {	
		return  collection.iterator();
	}

	@Override
	public Object[] toArray(){
		return collection.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return (collection.toArray(a));
	}

	@Override
	public boolean add(function e) {
		if(!this.contains(e)) {
			this.collection.add(e);
			return true;
		}
		else return false;
	}

	@Override
	public boolean remove(Object o) {
		if(	collection.contains(o)) return false;
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.collection.containsAll(c);
	}

	public static void main(String[] a) {

		function f1 = new Polynom("3-x^3+x^2");
		function f3 = new Polynom("x^2-x^3");
		function m = new Monom("2x^2");
		function m1 = new Monom("3x^2");
		Functions_GUI one = new Functions_GUI(f1);
		Functions_GUI one1 = new Functions_GUI(f1);
		one1.add(m);
		one1.add(m1);
		one.add(m1);
		System.out.println(one);
		System.out.println(one1);
		System.out.println(one1.removeAll(one));
		System.out.println(one);
		System.out.println(one1);
	}
	
	@Override
	public boolean addAll(Collection<? extends function> c) {
		if(this.collection.containsAll(c)) return false;
		else{
			for (function x: c) {
			this.collection.add(x);
			}
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if(!this.collection.containsAll(c)) return false;
		else{
			this.collection.removeAll(c);
			
		}
		return true;

	}

	@Override
	public boolean retainAll(Collection<?> c) {
		this.collection.removeAll(this.collection);
		//		this.collection=c.addAll(c);	

		return true;
	}

	@Override
	public void clear() {
		this.removeAll(this.collection);
	}

	@Override
	public void initFromFile(String file){
		function p =new Polynom();
		try {
			FileInputStream fileIn = new FileInputStream("C:\\Users\\Obador\\eclipse-workspace\\ofir.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			p =  (function) in.readObject();
			in.close();
			fileIn.close();	
			System.out.println(p.toString());
		}
		catch(Throwable e){
			System.out.println(p.toString());
		}
	}

	@Override
	public void saveToFile(String file)  {
		function p = this.funvalue;
		try {
			FileOutputStream fileIn = new FileOutputStream("C:\\Users\\Obador\\eclipse-workspace\\ofir.txt");
			ObjectOutputStream in = new ObjectOutputStream(fileIn);
			in.writeObject(p); 
			System.out.println(p.toString());
			in.close();
			fileIn.close();	
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(ry.get_min(),ry.get_max());
		double l=0;

		while(l<15) {
			StdDraw.setPenColor(StdDraw.GRAY);
			StdDraw.line(l,-15,l,15);	//+y grid	
			StdDraw.line(-l,-15,-l,15);	//-y grid
			StdDraw.line(-15,l,15,l);  //+x grid
			StdDraw.line(-15,-l,15,-l);	//-x grid
			l++;
		}
		for(int i=-15;i<16;i++){
			int y =1;
			StdDraw.text(-y, i, ""+i);	
			StdDraw.text(i, 0, ""+i);
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(0,-15,0,15);
		StdDraw.line(-15,0,15,0);
		double i = 0;
		while(i < resolution) {
			StdDraw.filledCircle(i, this.funvalue.f(i), 0.1);
			StdDraw.filledCircle(-i,this.funvalue.f(-i), 0.1);
			i=i+0.008;
		}
	}
	public void drawFunctions() {

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
			StdDraw.text(-y, i, ""+i);	
			StdDraw.text(i, 0, ""+i);
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(0,-15,0,15);
		StdDraw.line(-15,0,15,0);
		double i = 0;
		while(i<15) {
			StdDraw.filledCircle(i, this.funvalue.f(i), 0.1);
			StdDraw.filledCircle(-i,this.funvalue.f(-i), 0.1);
			i=i+0.008;
		}
		System.out.println("finish");
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.filledRectangle(-15, -10, 30, 5);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.text(-12, -6, ""+this.type);

	}
	@Override
	public void drawFunctions(String json_file) {
//		// parsing file "fileName.json" 
//				Object obj = null;
//				try {
//					JSONParser jp = new JSONParser();
//					FileReader fr = new FileReader(fileName);
//					obj = jp.parse(fr);
//					//obj = new JSONParser().parse(new FileReader(fileName));
//				} catch (IOException | ParseException e) {
//					e.printStackTrace();
//				} 
//
//				// type casting obj to JSONObject 
//				JSONObject jo = (JSONObject) obj; 
//
//				// getting firstName and lastName 
//				String firstName = (String) jo.get("firstName"); 
//				String lastName = (String) jo.get("lastName"); 
//
//				System.out.println(firstName); 
//				System.out.println(lastName); 
	}

	public String toString(){	
		return this.collection.toString();
	}

}
