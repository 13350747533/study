package cs;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import org.apache.commons.math3.special.Gamma;
public class cs {
	Random r=new Random();
	final double ��=0.01,��=1.5,p=0.25;
	//��ά���У���Ҫ�������½��ʱ������еĽ�ʹ��
	public double levy(double s,double x) {
		double a=Gamma.gamma(x);
		double b=Math.sin(Math.PI*x/2);
		double c=Math.pow(s,1+x);
		double answer=x*a*b/(Math.PI*c);
		System.out.println(s+"a");
		return answer;
	}
	//������ߣ��ڵ�������ʱ�����½�ʱʹ��
	public double randomWalk(double s,double x,double nest[] ) {
		double a=r.nextDouble();
		int x1=r.nextInt(nest.length),x2=r.nextInt(nest.length);
		double b=nest[x1],c=nest[x2];
		if(a==��) {                //ʵ��ԾǨ����
			return x;
		}else 
			return x+��*s*1*(b-c);
	}
	//��ȡ��ά���еĲ���������������֪���Ƿ�Ҫ�õ���
	public double get_s(double x) {
		double ��=Gamma.gamma(1+x)*Math.sin(Math.PI*x/2)/((x*Gamma.gamma(1+x)/2)*Math.pow(2, (x-1)/2));
		double ��2=Math.pow(��, 1/x);
		double U=Math.sqrt(��2)*r.nextGaussian()+0;
		double V=Math.abs(r.nextGaussian());
		double s=U/Math.pow(V,1/x);
		return s;
	}
	//��p�ĸ��ʷ��ֵ������³�
	public double[] updateEgg(double[] nest) {
		double[] newnest=new double[nest.length];
		for(int i=0;i<nest.length;i++) {
			double pa=r.nextDouble();
			if(pa<=p) {
				double temp=nest[i];
				newnest[i]=randomWalk(get_s(��),temp,nest);
			}else {
				newnest[i]=nest[i];
			}
		}
		return newnest;
		
	}
	
}
