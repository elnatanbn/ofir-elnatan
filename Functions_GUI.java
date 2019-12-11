package myMath;
import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Functions_GUI extends ArrayList<function> implements functions {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<function> collection = new ArrayList<function>();
	public Functions_GUI() {
		this.removeAll(this.collection);
	}

	public Functions_GUI(function f1){
		this.add(f1);
	}

	public Functions_GUI(String s){
		function f1 = new Polynom(s);
		this.collection.add(f1);
	}

	@Override
	public void initFromFile(String file){
		for (int i = 0; i < this.size(); i++) {
			function f = this.get(i);
			try {
				FileInputStream fileIn = new FileInputStream("C:\\Users\\Obador\\eclipse-workspace\\ofir.txt");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				f =  (function) in.readObject();
				in.close();
				fileIn.close();		
			}
			catch(Throwable e){
				System.out.println("problem with file");
			}
		}
	}

	@Override
	public void saveToFile(String file)  {
		for (int i = 0; i < this.size(); i++) {
			function f = this.get(i);
			try {
				FileOutputStream fileIn = new FileOutputStream("C:\\Users\\Obador\\eclipse-workspace\\ofir.txt");
				ObjectOutputStream in = new ObjectOutputStream(fileIn);
				in.writeObject(f); 
				in.close();
				fileIn.close();	
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setCanvasSize(width,height);
		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(ry.get_min(),ry.get_max());
		StdDraw.setPenColor(StdDraw.GRAY);
		for(double i=rx.get_min();i<rx.get_max();i++){
			StdDraw.line(i,ry.get_min(),i,ry.get_max());
		}
		for(double i=ry.get_min();i<ry.get_max();i++){
			StdDraw.line(rx.get_min(),i,rx.get_max(),i);
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(rx.get_min(),0,rx.get_max(),0);  
		StdDraw.line(0,ry.get_min(),0,ry.get_max());
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setFont(new Font("ariel",Font.PLAIN, 15));
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			int d =(int) i;
			StdDraw.text(i ,-0.5, Integer.toString(d));
		}
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			int d =(int) i;
			StdDraw.text(-0.3 ,i, Integer.toString(d));
		}
		for(int j=0;j<this.size();j++) {
			function p = this.get(j);
			Color c = Colors[j];
			StdDraw.setPenColor(c);
			for(int i=0;i<1000;i++){
				double b= (double) i ;
				StdDraw.filledCircle(b/resolution*2, p.f(b/resolution*2), 0.07);
				StdDraw.filledCircle(-b/resolution*2,p.f(-b/resolution*2), 0.07);
			}
			System.out.println("finish");
		}
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.filledRectangle(0, ry.get_min(),rx.get_max(),this.size()/1.5);
		StdDraw.setPenColor(StdDraw.BLACK);
		double l=ry.get_min()+0.3;
		for (int i = 0; i < this.size(); i++) {
			function p = this.get(i);	
			StdDraw.textLeft(rx.get_min(), l, "f(x)="+p.toString());	
			l=l+0.5;
		}	
	}

	public void drawFunctions() {
		this.drawFunctions(_W, _H, _RX, _RY, RES);
	}

	@Override
	public void drawFunctions(String json_file) {
		try {
			readJsonFile(json_file);
			drawFunctions(_W, _H, _RX, _RY, RES);
		} catch (Exception e) {
			this.drawFunctions();
		}
	}
	
	public void readJsonFile(String json_file) throws IOException, ParseException {
		try {
			Reader Read = new FileReader(json_file);
			JSONParser sr = new JSONParser();
			JSONObject Jf = (JSONObject) sr.parse(Read);

			int w = ((Long) Jf.get("Width")).intValue();
			int h = ((Long) Jf.get("Height")).intValue();
			int res = ((Long) Jf.get("Resolution")).intValue();
			JSONArray X = (JSONArray) Jf.get("Range_X");
			JSONArray Y = (JSONArray) Jf.get("Range_Y");

			Iterator<Long> y = Y.iterator();
			Range RY = new Range(y.next(), y.next());
			
			Iterator<Long> x = X.iterator();
			Range RX = new Range(x.next(), x.next());

			this.RES=res;
			this._RY=RY;
			this._W=w;
			this._H=h;
			this._RX=RX;
		}
		catch (Exception e) {System.out.println("problem with json_file");}
	}
	public String toString(){
		String ans="";
		for(int i=0;i<this.size();i++) {
			ans = ans+","+this.get(i).toString();
		}
		return ans;
	}

	public static Color[] Colors = { Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN,
			Color.PINK };
	
	private int _W = 1000;
	private int _H = 600;
	private Range _RX = new Range(-10, 10);
	private Range _RY = new Range(-5, 15);
	private int RES = 200;
}
