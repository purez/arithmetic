package arithmetic;

import java.util.ArrayList;
import java.util.Random;

public class Expression {
	
	public static int n;   //算式中运算数大小限制
	private static int m; //算式中运算数的个数
	
	Random random = new Random();   //随机生成器
	ArrayList<String> expression = new ArrayList<String>();
	public Fraction result;
	
	public Fraction popNumber() {
		int j = random.nextInt(4);
		Fraction fraction;
		if(j == 0) {     //25%
			int b = random.nextInt(10)+1;     
			int a = random.nextInt(2*b);
			fraction = new Fraction(a, b);
		}
		else {
			int b = 1;
			int a = random.nextInt(n+1)+1;		
			fraction = new Fraction(a, b);
		}
		
		return fraction;
	}
	
	private String popOperator() {
		String[] operator = {"+","-","*","÷"};
		return (operator[random.nextInt(4)]);
	}
	
	public Expression() {
		
		new Test();
		n = Test.n;
		m = random.nextInt(3) + 2;//234
		
		Fraction a = popNumber();
		this.expression.add(a.toString());//开始写入表达式
		
		for(int i = 0; i < m-1; i++) {
			Fraction b = popNumber();
			String operator = popOperator();
			
			switch(operator) {
			case("+"): a = a.plus(b); 
				this.expression.add(operator);
				this.expression.add(b.toString());
				break;
			
			case("-"):
				Fraction temp = new Fraction(1, 1);///为使ａ不变
				if(a.minus(b).isNegative() == false)
					a = a.minus(b);
				else {
					while((a.minus(b)).isNegative()) {
						b = popNumber();
						temp = a.minus(b);
					}
					a = temp;
				}
				this.expression.add(operator);
				this.expression.add(b.toString());
				break;
			
			case("÷"): //运算出错，定有问题，盯住就对
//				while(b.a == 0) {
//					b = popNumber();
//					a = a.divide(b); 
//				}//单纯上面注释这三行的话，b.a不等于零的话，即不处理＝＝逻辑不够缜密，全面
				while(b.a == 0) {
					b = popNumber();
					//a = a.divide(b); 
				}
				a = a.divide(b); 
				this.expression.add(operator);
				this.expression.add(b.toString());
				break;
			
			case("*"):   a = a.multiply(b); 
				this.expression.add(operator);
				this.expression.add(b.toString());
				break;
			}
		}
		this.result = a;
		this.expression = addBrackets(this.expression);//添加必要的括号、等号
	}
	
	private ArrayList<String> addBrackets(ArrayList<String> exp) {
		String low = "+-";
		String high = "*÷";
		if(exp.size() == 5) {      //stack_E经过生成exp已空  ,不能用来判断长度
			if(low.contains(exp.get(1)) && high.contains(exp.get(3))) {
				exp.add(0,"(");
				exp.add(4,")");
			}
		}
		if(exp.size() == 7) { //++*  +*o
			if(low.contains(exp.get(1)) && high.contains(exp.get(3))) {
				exp.add(0,"(");
				exp.add(4,")");
			}
			if(high.contains(exp.get(5)) && low.contains(exp.get(3))){
				//if(low.contains(exp.get(1)) || ) {
				exp.add(0, "(");
				exp.add(6, ")");
				}
		}
		exp.add("=");
		return exp;
	}
		
	//试用
	public void print(){
		for(String string : this.expression) {
			System.out.print(string + " ");
		}
		System.out.println(this.result.toString());
	}

}