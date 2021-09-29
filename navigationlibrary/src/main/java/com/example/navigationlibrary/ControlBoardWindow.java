package com.example.navigationlibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.navisdk.adapter.BaiduNaviManagerFactory;
import com.example.navigationlibrary.nav.ControlAdapter;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ControlBoardWindow {
    private final String TAG = "ControlBoardWindow";
    private View mPopupContentView = null;
    private WindowManager mWindowManager = null;
    WindowManager.LayoutParams params;
    private Context mContext = null;
    public Boolean isShown = false;
    static ControlBoardWindow mInstance = null;

    CopyOnWriteArrayList<Twotxt> controlTxts = new CopyOnWriteArrayList<>();
    ArrayList<String> tags = new ArrayList<>();

    boolean recycleIsshow = false;
    boolean isRecycleTouch = false;

    RecyclerView recyclerView;
    Button settingBtn;
    LinearLayout goneViews;

    ControlAdapter adapter;

    public static ControlBoardWindow getInstance() {

        if (mInstance == null) {
            synchronized (ControlBoardWindow.class) {
                mInstance = new ControlBoardWindow();
            }
        }

        return mInstance;
    }

    /**
     * Popup window显示弹出框
     *
     * @param context
     */
    public void showPopupWindow(final Context context) {
        controlTxts.add(new Twotxt("显示控制台弹窗"));
        if (isShown) {
            Log.i(TAG, "return cause already shown");
            return;
        }

        // Log.i(TAG, "showPopupWindow");
        mPopupContentView = initPopupView(context);

        mContext = context.getApplicationContext();
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
        // WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        int flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.flags = flags;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        // params.format = PixelFormat.TRANSPARENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        mWindowManager.addView(mPopupContentView, params);
        Log.e(TAG, "addview 结束");
        isShown = true;
    }

    /**
     * 隐藏弹出框
     */
    public void hidePopupWindow() {
        Log.i(TAG, "hide " + isShown + ", " + mPopupContentView);
        if (isShown && null != mPopupContentView) {
            Log.i(TAG, "hidePopupWindow");
            mWindowManager.removeView(mPopupContentView);
            isShown = false;
        }
    }

    public void showControlOnlyone(final String txt1, final String txtTAG) {
        if (!tags.contains(txtTAG) && recyclerView != null && isShown) {
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    tags.add(txtTAG);
                    controlTxts.add(new Twotxt(txt1 + " 这个应该是好烦的吧，只展示一次_gb"));
                    adapter.notifyItemInserted(adapter.getItemCount());
                    maxtxtRemove();
                }
            });
        }
    }

    public void showControl(final String txt1) {
        if (recyclerView != null && isShown) {
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    controlTxts.add(new Twotxt(txt1));
                    adapter.notifyItemInserted(adapter.getItemCount());
                    maxtxtRemove();
                }
            });
        }
    }

    public void showControl(final String txt1, final Showback back) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                controlTxts.add(new Twotxt(txt1));
                back.backIndex(controlTxts.size() - 1);
                adapter.notifyItemInserted(adapter.getItemCount());
                maxtxtRemove();
            }
        });
    }

    public void showControl(final String txt2, final int index) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                try {
                    controlTxts.get(index).setTxt2(txt2 + controlTxts.get(index).getTxt2());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter.notifyItemChanged(index);
                maxtxtRemove();
            }
        });
    }

    public void showControl(final String txt1, final String txt2) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                controlTxts.add(new Twotxt(txt1, txt2));
                adapter.notifyItemInserted(adapter.getItemCount());
                maxtxtRemove();
            }
        });
    }

    // 防止占用过大的内存
    public void maxtxtRemove() { // 会导致 show_control(String txt2,int index) 不正确，不过没问题
        // 这么久才回调一定是ANR了，200条之上也不会看了
        if (adapter.getItemCount() > 250) {
            controlTxts.remove(0);
            adapter.notifyItemRemoved(0);
            // Log.e(TAG,"清除过多的信息");
        }
        if (recycleIsshow && !isRecycleTouch) {
            // Log.e(TAG,"滚动到底部");
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        }
        // adapter.notifyItemInserted(control_txts.size());
    }

    @SuppressLint("ClickableViewAccessibility")
    private View initPopupView(final Context context) {
        Log.i(TAG, "setUp view");
        View view = LayoutInflater.from(context).inflate(R.layout.controlwindow, null);
        recyclerView = view.findViewById(R.id.recycle_view);
        settingBtn = view.findViewById(R.id.setting_btn);
        goneViews = view.findViewById(R.id.gone_views);
        Button uiSetting = view.findViewById(R.id.naviui_close);

        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setReverseLayout(true);
        adapter = new ControlAdapter(controlTxts);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isRecycleTouch = true;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    isRecycleTouch = false;
                }
                return false;
            }
        });
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e(TAG, "window click" + adapter.getItemCount());
                // show_control("点击window");
                if (recycleIsshow) {
                    recyclerView.setVisibility(View.GONE);
                    goneViews.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    goneViews.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(controlTxts.size() - 1);
                }
                recycleIsshow = !recycleIsshow;
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaiduNaviManagerFactory.getBaiduNaviManager().isInited()) {
//                    Intent it = new Intent(mContext, DemoNaviSettingActivity.class);
//                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    mContext.startActivity(it);

                    recyclerView.setVisibility(View.GONE);
                    goneViews.setVisibility(View.GONE);
                    recycleIsshow = false;
                }
            }
        });
        uiSetting.setVisibility(View.GONE);
//        uiSetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BaiduNaviManagerFactory.getRouteGuideManager().getUIController().hideAllView(true); // ui定制化 隐藏所有view
//                recyclerView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(context, "导航页ui已隐藏", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        return view;

    }

    public interface Showback {

        void backIndex(int index);

    }

    public static class Twotxt {
        private String txt1;
        private String txt2;

        public Twotxt(String txt1, String txt2) {
            this.txt1 = txt1;
            this.txt2 = txt2;
        }

        public Twotxt(String txt1) {
            this.txt1 = txt1;
        }

        public String getTxt1() {
            return txt1;
        }

        public void setTxt1(String txt1) {
            this.txt1 = txt1;
        }

        public String getTxt2() {
            return txt2;
        }

        public void setTxt2(String txt2) {
            this.txt2 = txt2;
        }

    }

}