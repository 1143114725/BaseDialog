package com.example.basedialog.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * 像素分辨率转换工具类
 * 
 */
public class DensityUtil {
	/**
	 * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * convert px to its equivalent sp
	 *
	 * 将px转换为sp
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}


	/**
	 * convert sp to its equivalent px
	 *
	 * 将sp转换为px
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}


	/**
	 * 获取屏幕宽度,单位px
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		return getDisplayMetrics(context).widthPixels;
	}
	
	/**
	 * 获取屏幕高度,单位px
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		return getDisplayMetrics(context).heightPixels;
	}
	
	/**
	 * 获取设备密度
	 * 
	 * @param context
	 * @return
	 */
	public static float getDensity(Context context) {
		return getDisplayMetrics(context).density;
	}

	private static DisplayMetrics getDisplayMetrics(Context context) {
		DisplayMetrics dm = null;
		if (context != null) {
			dm = context.getApplicationContext().getResources().getDisplayMetrics();
			return dm;
		}
		return new DisplayMetrics();
	}

	
	/**
	 * 获取状态栏高度
	 * 
	 */
	public static int getStatusHeight(Activity activity) {
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusHeight = frame.top;
		if (0 == statusHeight) {
			Class<?> localClass;
			try {
				localClass = Class.forName("com.android.internal.R$dimen");
				Object localObject = localClass.newInstance();
				int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
				statusHeight = activity.getResources().getDimensionPixelSize(i5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return statusHeight;
	}

	public static void removeKeyboardVisibilityListener(Activity activity, ViewTreeObserver.OnGlobalLayoutListener listener){
		View parentView = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
		parentView.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
	}
	/**
	 * 用于页面监听输入法弹出
	 */
	public static ViewTreeObserver.OnGlobalLayoutListener setKeyboardVisibilityListener(Activity activity, OnKeyboardViListener listener) {//viewTreeObserver
		View parentView = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
		ViewTreeObserver.OnGlobalLayoutListener globalListener = new ViewTreeObserver.OnGlobalLayoutListener() {

			boolean alreadyOpen = false;
			int defaultKeyboardHeightDP = 100;
			Float EstimatedKeyboardDP = (float) (defaultKeyboardHeightDP + ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) ? 48 : 0));
			Rect rect = new Rect();

			@Override
			public void onGlobalLayout() {
				int estimatedKeyboardHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP, parentView.getResources().getDisplayMetrics());
				parentView.getWindowVisibleDisplayFrame(rect);
				int heightDiff = parentView.getRootView().getHeight() - (rect.bottom - rect.top);
				boolean isShown = heightDiff >= estimatedKeyboardHeight;
				if (isShown == alreadyOpen) {
					return;
				}
				alreadyOpen = isShown;
				if (listener != null){
					listener.onViChange(isShown, estimatedKeyboardHeight);
				}
			}
		};
		parentView.getViewTreeObserver().addOnGlobalLayoutListener(globalListener);
		return globalListener;
	}

	public interface OnKeyboardViListener{
		/**
		 * 输入法可见状态改变
		 */
		void onViChange(boolean isShow, int estimatedKeyboardHeight);
	}


}
