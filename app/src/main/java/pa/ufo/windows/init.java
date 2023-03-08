package pa.ufo.windows;






import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;



import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

public class init {

    private  static AppCompatTextView tvContent;
    private static int mTouchStartX;
    private static int mTouchStartY;
    private static int mStartX;
    private static int mStartY;
    static WindowManager wm;
    static View view;
    static AppCompatTextView textView;

    public  static   WindowManager.LayoutParams layoutParams;
    private static  int mTouchCurrentX;
    private static int mTouchCurrentY;
    static  Context Acc;
    public void Context(Context Acc){
        this.Acc=Acc;
    }
    public static View setView(int flags,int width, int height,int layout){
        wm = (WindowManager) Acc.getSystemService(AccessibilityService.WINDOW_SERVICE);
        layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        } else {
            layoutParams.type= WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.format = PixelFormat.TRANSLUCENT;
        layoutParams.flags = flags;
        layoutParams.width =width;
        layoutParams.height =height;
        layoutParams.gravity = Gravity.CENTER;
        view = LayoutInflater.from(Acc).inflate(layout, null);
        return view;
    }
    public static TextView setTextView(int id){
        textView = view.findViewById(id);
        return textView;
    }
    //    public  static  void setOnClickListener(int id){
//
//        view.findViewById(id).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
//
//
//    }
//    public  static  void setOnClickListener(){
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
//
//
//    }
//    public  static  void setOnTouchListener(int  id){
//        view.setOnTouchListener((View.OnTouchListener)(new View.OnTouchListener() {
//            @SuppressLint({"ClickableViewAccessibility"})
//            public boolean onTouch(@org.jetbrains.annotations.Nullable View p0, @org.jetbrains.annotations.Nullable MotionEvent p1) {
//                System.out.println("123");
//                switch (p1.getAction()){
//                    case  MotionEvent.ACTION_DOWN :
//                        mTouchStartX = (int) p1.getRawX();
//                        mTouchStartY = (int) p1.getRawY();
//                        mStartX = (int) p1.getX();
//                        mStartY = (int) p1.getY();
//                    case  MotionEvent.ACTION_MOVE:
//                        mTouchCurrentX = (int) p1.getRawX();
//                        mTouchCurrentY =(int) p1.getRawY();
//                        layoutParams.x += mTouchCurrentX - mTouchStartX;
//                        layoutParams.y += mTouchCurrentY - mTouchStartY;
//                        wm.updateViewLayout(view, layoutParams);
//                        mTouchStartX = mTouchCurrentX;
//                        mTouchStartY = mTouchCurrentY;
//
//                }
//                return true;
//            }
//        }));
//
//    }
    public static void demo(){
        int flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |FLAG_NOT_FOCUSABLE;
        view=setView(flags,300,400,R.layout.layout_floating_window);
        textView=view.findViewById(R.id.tv_content);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        float v = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 4.0F, Acc.getResources().getDisplayMetrics());
        textView.setTextSize(v * 1.5f);
        textView.setText("欢迎使用");
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.argb(0x55, 0X00, 0x00, 0x00));
        view.setOnTouchListener((View.OnTouchListener)(new View.OnTouchListener() {
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(@org.jetbrains.annotations.Nullable View p0, @org.jetbrains.annotations.Nullable MotionEvent p1) {
                switch (p1.getAction()){
                    case  MotionEvent.ACTION_DOWN :
                        mTouchStartX = (int) p1.getRawX();
                        mTouchStartY = (int) p1.getRawY();
                        mStartX = (int) p1.getX();
                        mStartY = (int) p1.getY();
                    case  MotionEvent.ACTION_MOVE:
                        mTouchCurrentX = (int) p1.getRawX();
                        mTouchCurrentY =(int) p1.getRawY();
                        layoutParams.x += mTouchCurrentX - mTouchStartX;
                        layoutParams.y += mTouchCurrentY - mTouchStartY;
                        wm.updateViewLayout(view, layoutParams);
                        mTouchStartX = mTouchCurrentX;
                        mTouchStartY = mTouchCurrentY;

                }
                return true;
            }
        }));
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("6666666666");

            }


        });
        wm.addView(view,layoutParams);

    }



}
