package arithmetic;

public class Fraction {
	/*
	 * a : numerator, b : denominator
	 */
	
	public int a;
	public int b;
	
	public Fraction(int a, int b) {
		if(b == 0) {
			throw new RuntimeException("分母不能为零");
		}
		else {
			this.a = a;
			this.b = b;
		}
	}
	
	public Fraction plus(Fraction fraction) {
        return new Fraction(this.a * fraction.b + fraction.a * this.b,
                this.b * fraction.b);
    }

    public Fraction minus(Fraction fraction) {
        return new Fraction(this.a * fraction.b - fraction.a * this.b,
                this.b * fraction.b);
    }
	
	public Fraction multiply(Fraction fraction) {// 0/3 * 1/2 
		return new Fraction(this.a * fraction.a, this.b * fraction.b);
	}
	
	public Fraction divide(Fraction fraction) {//  0/3 ÷ 1/2
		if(fraction.a == 0)
			return new Fraction(0, 1);
		else
			return new Fraction(this.a * fraction.b, this.b * fraction.a);
	}
	
	public boolean isNegative() {
		if(this.a < 0)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		
		if(this.a == 0) {// 0/n
			return String.format("0");
		}
		
		//this.simplify();//位置。分子为零不化简，因gcd会除０
		
		else if(this.a >= this.b) {//  n+1/n
			this.simplify();
			if((this.a % this.b) == 0) {//   2n/n
				return String.format("%d",this.a/this.b);
			}else {
			int mod = this.a / this.b;
			return String.format("%d'%d/%d",mod,(this.a % this.b),this.b);
		
			}
		}	
		else {//  n-1/n
			this.simplify();
			return String.format("%d/%d", this.a,this.b);
		}
	}
	
	public void simplify() {
		if(this.a != 0) { //  身为分数，b定不为０
			int gcd = gcd(this.a, this.b);  //a可能为０
			if(gcd != 0) {
				this.a = this.a / gcd;
				this.b = this.b / gcd;
			}
		}
	}
	
	private static int gcd(int m, int n) {
		if(m < n) {
			int temp = m;
			m = n;
			n = temp;
		}
		while(m % n != 0) {//   0/3?
			int temp = m % n;
			m = n;
			n = temp;
		}
		return n;
	}

//	private static int lcm(int m, int n) {
///		return m * n / gcd(m, n);
//	}

}
