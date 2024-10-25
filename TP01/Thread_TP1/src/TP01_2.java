import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class TP01_2 {
    static boolean running = true;
    static String text = "";
    public static void main(String[] args) throws InterruptedException {
        try{
            GlobalScreen.registerNativeHook();
        }catch (NativeHookException e){
            e.printStackTrace();
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
                }else{
                    text = NativeKeyEvent.getKeyText(arg0.getKeyCode());
                }
            }
        });

        while(running){
            Thread.sleep(400);
            System.out.print(text);
        }
    }
}
