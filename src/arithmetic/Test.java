package arithmetic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Test {
	
	public static int n;//大小限制
	private static int m;//题数
	
	public static void main(String[] args) throws IOException {
		if(args[0].equals("-r") && args[2].equals("-n")) {
			m = Integer.parseInt(args[1]);
			n = Integer.parseInt(args[3]);
			
			PrintWriter quePrint = new PrintWriter(new File("Exercises.txt"));
			PrintWriter ansPrint = new PrintWriter(new File("Answer.txt"));
			
			//String currentPath = Test.class.getResource("").getPath();//调试用
  			//String currentPath = System.getProperty("user.dir");//打包用
			
			for(int i = 1; i <= m; i++) {
				Expression exp = new Expression();
				//expression.print();
				String s = String.format("%d. ", i);
				quePrint.print(s);
				for(int t = 0; t < exp.expression.size(); t++) {//t变i、没从0开始，小细节耗了半个钟
					quePrint.print(exp.expression.get(t));	
				}
				quePrint.print("\n");
				ansPrint.println(s + exp.result.toString());	
			}
			quePrint.close();
			ansPrint.close();
		}
		
		else if(args[0].equals("-e") && args[2].equals("-a")) {
			
			if(args[3].equals("Answer.txt") == false ) {//答案是系统给出那份？

				BufferedReader answer = new BufferedReader(new FileReader(new File("Answer.txt")));
				BufferedReader myAnswer = new BufferedReader(new FileReader(new File(args[3])));
				
				ArrayList<Integer> correct = new ArrayList<Integer>();
				ArrayList<Integer> wrong = new ArrayList<Integer>();
				String s1 = answer.readLine();
				String s2 = myAnswer.readLine();
				
				while(s1 != null && s2 != null) {
					if(s1.equals(s2))
						//correct.add(Integer.parseInt(s2.substring(0, 1)));第10题怎么说？
						correct.add(Integer.parseInt(s1.substring(0, s1.indexOf("."))));
					else 
						wrong.add(Integer.parseInt(s2.substring(0, s2.indexOf("."))));
					s1 = answer.readLine();
					s2 = myAnswer.readLine();	
				}//各种细节？答案数量不一样多、乱序？
				
				StringBuilder sc = new StringBuilder(String.format("%d", correct.get(0)));//0呀0
				StringBuilder sw = new StringBuilder(String.format("%d", wrong.get(0)));
				for(int i = 1 ; i < correct.size(); i++)
					sc.append(String.format(", %d", correct.get(i)));
				for(int i = 1 ; i < wrong.size(); i++)
					sw.append(String.format(", %d", wrong.get(i)));
				PrintWriter printWriter = new PrintWriter(new File("Grade.txt"));
				printWriter.print("Correct: " + correct.size() + "(" + sc + ")" + "\n");
				printWriter.print("Wrong: " + wrong.size() + "(" + sw +")");
				
				answer.close();
				myAnswer.close();
				printWriter.close();
			}
			else
				throw new RuntimeException("文件不存在");
		}
		
		else
			throw new RuntimeException("输入错误，请按要求输入");
	}
	
}