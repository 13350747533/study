package cs;
import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;
public class Test {
	public static void main(String[] args) {
		double temp1;
		Random r=new Random();
		int count=0;
		cs c=new cs();
		double gobal_y=Double.NEGATIVE_INFINITY;//�õ�ǰ���Ž�Ϊ����С
		double gobal_x=0;
		final int max=1,min=0;//������ȡֵ��Χ
		double nest[]=new double[5];
		double answer[]=null;
		for(int i=0;i<5;i++) {
			nest[i]=r.nextDouble();
		}
		answer=get_answer(nest);
		double temp=get_best(answer);
		if(temp>gobal_y) {
			gobal_y=temp;
		}
		while(count<5000) {
			for(int i=0;i<nest.length;i++) {
				temp1=nest[i]+c.��*c.levy(c.get_s(c.��),c.��);
				while(true) {
					if(temp1>max||temp1<min) {
						temp1=nest[i]+c.��*c.levy(c.get_s(c.��),c.��);
					}
					else {
						nest[i]=temp1;
						break;
					}
				}
			}
			for(int i=0;i<nest.length;i++) {
				nest=c.updateEgg(nest);
			}
			answer=get_answer(nest);
			temp=get_best(answer);
			if(temp>gobal_y) {
				gobal_y=temp;
			}
			count++;
		}
		System.out.println(gobal_y);//��ӡ���Ž�
	}
	//Ҫ�Ż�������
	public static double demo(double x) {
		double answer=0;
		answer=Math.pow(x, 2)*0.5+1;
		return answer;
	}
	//������Ӧ��
	public static double[] get_answer(double[] a) {
		double[] answer = new double[a.length];
		for(int i=0;i<a.length;i++) {
			answer[i]=demo(a[i]);
		}
		return answer;
	}
	//����������Ӧ��
	public static double get_best(double a[]) {
		double answer=a[0];
		for(int i=1;i<a.length;i++) {
			if(answer<a[i]) {
				answer=a[i];
			}
		}
		return answer;
	}

}
