import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class FindMax {
	 public static void main(String[] args) throws ExecutionException, InterruptedException {
		 ArrayList<Long> a=createRandom();
		//创建执行线程池
	    ForkJoinPool pool = new ForkJoinPool(4);
	    
	    //创建任务
	    FindTask task = new FindTask(0,a.size(),a);
	    
	    //提交任务
        ForkJoinTask<Long> result = pool.submit(task);
        
      //等待结果
        do {
			//System.out.printf("Main: Thread Count: %d\n",pool.getActiveThreadCount());
			//System.out.printf("Main: Paralelism: %d\n",pool.getParallelism());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
        
        //输出结果
        System.out.println(result.get().toString());
        System.out.println(Collections.max(a));
	 }
	 
	 public static ArrayList<Long> createRandom(){
		ArrayList<Long> a=new ArrayList<Long>();
		Random r = new Random();
		int size=r.nextInt(10000000);
		for(int i=0;i<size;i++) {
			a.add(r.nextLong());
		}
		return a;
	 }
}
