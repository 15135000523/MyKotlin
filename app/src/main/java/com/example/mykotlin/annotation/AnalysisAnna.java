package com.example.mykotlin.annotation;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 解析注解
 */
public class AnalysisAnna {

    public static String TAG = "AnalysisAnna";

    /**
     * 解析injectId注解
     *
     * @param activity activity中的注解
     */
    public static void injectId(Activity activity) {
        Field[] fields = activity.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean isHasAnnotation = field.isAnnotationPresent(injectId.class);
            if (isHasAnnotation) {
                injectId annotation = field.getAnnotation(injectId.class);
                int viewId = annotation.value();
                Method method = null;
                try {
                    method = activity.getClass().getMethod("findViewById", int.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    field.setAccessible(true);
                    field.set(activity, method.invoke(activity, viewId));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    ;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        inJectClick(activity);
    }

    /**
     * @param fragment
     */
    public static void injectId(Fragment fragment) {
        Field[] fields = fragment.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean isHasAnnotation = field.isAnnotationPresent(injectId.class);
            if (isHasAnnotation) {
                injectId annotation = field.getAnnotation(injectId.class);
                int viewId = annotation.value();
                Method getView = null;
                try {
                    getView = fragment.getClass().getMethod("getView");
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                View view;
                Method findViewById;
                try {
                    view = (View) getView.invoke(fragment);
                    findViewById = view.getClass().getMethod("findViewById", int.class);
                    field.setAccessible(true);
                    field.set(fragment, findViewById.invoke(view, viewId));
                } catch (IllegalAccessException e) {
                    Log.e(TAG, e.toString());
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    Log.e(TAG, e.toString());
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    Log.e(TAG, e.toString());
                    e.printStackTrace();
                }
            }
        }
        inJectClick(fragment);
    }

    /**
     * 解析方法中的注解，为控件注册点击时间
     * @param activity
     */
    private static void inJectClick(Activity activity) {
        Method[] methods = activity.getClass().getDeclaredMethods();
        for (Method method : methods) {
            boolean isHas = method.isAnnotationPresent(InjectClick.class);
            if (isHas) {
                InjectClick click = method.getAnnotation(InjectClick.class);
                int[] ids = click.value();
                View[] views = new View[ids.length];
                Method findViewById;
                try {
                    findViewById = activity.getClass().getMethod("findViewById", int.class);
                    for (int i = 0; i < ids.length; i++) {
                        views[i] = (View) findViewById.invoke(activity, ids[i]);
                        views[i].setOnClickListener(v -> {
                            try {
                                method.invoke(activity, v);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 给fragment控件注册点击事件
     * @param fragment
     */
    private static void inJectClick(Fragment fragment) {
        Method[] methods = fragment.getClass().getDeclaredMethods();
        for (Method method : methods) {
            boolean isHas = method.isAnnotationPresent(InjectClick.class);
            if(isHas){
                InjectClick click = method.getAnnotation(InjectClick.class);
                int[] ids = click.value();
                View[] views = new View[ids.length];
                //fragment获取布局的方法
                Method getView = null;
                //fragment初始化控件的方法
                Method findViewById = null;
                //fragment布局
                View rootView = null;
                try {
                    getView = fragment.getClass().getMethod("getView");
                    rootView = (View) getView.invoke(fragment);
                    findViewById = rootView.getClass().getMethod("findViewById", int.class);
                    for (int i = 0; i < ids.length; i++) {
                        views[i] = (View) findViewById.invoke(rootView,ids[i]);
                        views[i].setOnClickListener(v -> {
                            try {
                                method.invoke(fragment,v);
                            } catch (IllegalAccessException e) {
                                Log.e(TAG,e.toString());
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                Log.e(TAG,e.toString());
                                e.printStackTrace();
                            }
                        });
                    }
                } catch (NoSuchMethodException e) {
                    Log.e(TAG,e.toString());
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    Log.e(TAG,e.toString());
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    Log.e(TAG,e.toString());
                    e.printStackTrace();
                }

            }
        }
    }
}
