package pa.ufo.windows;





import static pa.ufo.windows.init.demo;

import android.accessibilityservice.AccessibilityService;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public class MyAccessibilityService extends AccessibilityService {
        public  static MyAccessibilityService Acc;
    private static boolean isServiceCreated = false;

    public MyAccessibilityService() {


    }

    @Override
    public void onCreate() {

        super.onCreate();
        //只在第一次创建时调用
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Acc=this;
        init i=new init();
        i.Context(this);
        demo();


    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        return super.onKeyEvent(event);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {
        //当服务要被中断时调用.会被调用多次
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceCreated = false;
    }

    public static boolean isServiceRunning(){
        return isServiceCreated;
    }

}
