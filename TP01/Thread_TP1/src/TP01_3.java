import java.util.Scanner;
public class TP01_3 {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of thread you want to create: ");
        int numb_thread = scanner.nextInt();
        
        for(int i = 0 ; i < numb_thread; i++){
            Thread thread = new Thread(new Run(i));
            thread.start();
            thread.join();
            thread.setName(""+i);
        }

        scanner.close();
    }
    
    public static class Run implements Runnable {
        int index; 
        Run(int index){
            this.index =  index;
        }
        @Override
        public void run() {
            System.out.println("Thread-"+ this.index);
            
        }
    
        
    }

}
