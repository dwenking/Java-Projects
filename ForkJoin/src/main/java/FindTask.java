import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class FindTask extends RecursiveTask<Long> {
	private int start;
	private int end;
	private ArrayList<Long> a;
	
	public FindTask() {
	}
	
	public FindTask(int a,int b,ArrayList<Long> t) {
		this.start=a;
		this.end=b;
		this.a=t;
	}
	
	public static final int threadhold = 5;//阈值

	@Override
	protected Long compute() {
		Long res = Long.MIN_VALUE;
		boolean canCompute = (end - start) <= threadhold;
		if (canCompute) {
			for (int i = start; i < end; i++) {
				res=Math.max(res,a.get(i));		
			}
		} else {
			int middle = (start + end) / 2;
			FindTask subTask1 = new FindTask(start, middle,a);
			FindTask subTask2 = new FindTask(middle, end,a);

			invokeAll(subTask1, subTask2);

			Long res1 = subTask1.join();
			Long res2 = subTask2.join();
			
			res=Math.max(res1, res2);
		}
		return res;
	}
}
