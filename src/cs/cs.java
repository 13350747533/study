package cs;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import org.apache.commons.math3.special.Gamma;
public class cs {
	Random r=new Random();
	final double α=0.01,λ=1.5,p=0.25;
	//莱维飞行，需要在生成新解的时候对所有的解使用
	public double levy(double s,double x) {
		double a=Gamma.gamma(x);
		double b=Math.sin(Math.PI*x/2);
		double c=Math.pow(s,1+x);
		double answer=x*a*b/(Math.PI*c);
		System.out.println(s+"a");
		return answer;
	}
	//随机游走，在蛋被发现时生成新解时使用
	public double randomWalk(double s,double x,double nest[] ) {
		double a=r.nextDouble();
		int x1=r.nextInt(nest.length),x2=r.nextInt(nest.length);
		double b=nest[x1],c=nest[x2];
		if(a==α) {                //实现跃迁函数
			return x;
		}else 
			return x+α*s*1*(b-c);
	}
	//获取莱维飞行的步长？？？（并不知道是否要用到）
	public double get_s(double x) {
		double σ=Gamma.gamma(1+x)*Math.sin(Math.PI*x/2)/((x*Gamma.gamma(1+x)/2)*Math.pow(2, (x-1)/2));
		double σ2=Math.pow(σ, 1/x);
		double U=Math.sqrt(σ2)*r.nextGaussian()+0;
		double V=Math.abs(r.nextGaussian());
		double s=U/Math.pow(V,1/x);
		return s;
	}
	//以p的概率发现蛋并更新巢
	public double[] updateEgg(double[] nest) {
		double[] newnest=new double[nest.length];
		for(int i=0;i<nest.length;i++) {
			double pa=r.nextDouble();
			if(pa<=p) {
				double temp=nest[i];
				newnest[i]=randomWalk(get_s(λ),temp,nest);
			}else {
				newnest[i]=nest[i];
			}
		}
		return newnest;
		
	}
	
}
