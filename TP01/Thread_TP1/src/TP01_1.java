import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class TP01_1 {
    static boolean running = true;
    public static void main(String[] args) throws Exception {
        try{
            GlobalScreen.registerNativeHook();
        }catch (NativeHookException msg){
            msg.printStackTrace();
        }

        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            @Override 
            public void nativeKeyPressed(NativeKeyEvent arg0) {
                if (arg0.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
                    running = false;
                    
                    try {
                        GlobalScreen.unregisterNativeHook();
                    } catch (NativeHookException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        while (running) {
            Thread.sleep(100);
            System.out.print("Hit me!");
        }
        System.out.println("\nThank you!");
    }
}

