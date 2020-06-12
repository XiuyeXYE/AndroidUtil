package com.xy.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UIUtil {

    public static boolean DEBUG = true;

    private static Context currentActivty;

    public static synchronized Context getCurrentActivty() {
        return currentActivty;
    }

    public static synchronized void setCurrentActivty(Context activity) {
        currentActivty = activity;//first step
//        AsyncDialogThread.clearPrevActivityRelatedDialog();//second step
    }

    public static <T> void log(Context context, T... tList) {
        if (!DEBUG) return;
        String output = "";
        for (T t : tList) {
            output += t + " ";
        }
        int idx = 0;
        output = output.substring(0, (idx = output.length() - 1) > 0 ? idx : 0);
        alert(context, context.getClass().toString(), output);
    }

    public static void alert(Context context, String title, String msg) {
        if (context == null) return;
        AsyncDialogThread thread = new AsyncDialogThread();
        thread.execute(context, title, msg);
    }


    public static void logCurrentWindow() {
        log(getCurrentActivty(), getCurrentActivty());
    }

    public static <T> void log(T... s) {
        log(getCurrentActivty(), s);
    }


    public interface Consumer<T> {
        void accept(T t);
    }


    public static void checkAndRequestPermission(Activity that, String permission, int requestCode, Runnable runnable) {
        if (ContextCompat.checkSelfPermission(that, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(that, new String[]{permission}, requestCode);
        } else {
            runnable.run();
        }
    }

    public static void handleRequestPermissionResult(Activity activity, int requestCode, String[] permissions, int[] grantResults, int selfRequestCode, Runnable runnable) {
        if (requestCode == selfRequestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                runnable.run();
            } else {
                UIUtil.log(activity, "拒绝权限无法使用程序!");
                activity.finish();
            }
        }
    }


}


class AsyncDialogThread extends AsyncTask<Object, Void, Object> {

    public static List<AlertDialog> dialogs = new ArrayList<>();

    @Override
    protected Object doInBackground(Object... dialogInfos) {
        if (dialogInfos.length > 0) {
            return dialogInfos;
        }
        return null;
    }

    public static void clearPrevActivityRelatedDialog() {
//        XLog.lg("before remove all dialogs",dialogs.size(),dialogs);
        Iterator<AlertDialog> it = dialogs.iterator();
        while (it.hasNext()) {
            AlertDialog ad = it.next();
            ad.dismiss();
            it.remove();
        }
//        XLog.lg("after remove all dialogs",dialogs.size(),dialogs);
    }

    @Override
    protected void onPostExecute(Object info) {
        super.onPostExecute(info);
        if (info != null) {
            Object[] infos = (Object[]) info;
            Activity currentActivity = (Activity) infos[0];
            if (!currentActivity.isFinishing()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
                builder.setMessage(infos[2].toString()).setTitle(infos[1].toString());
                AlertDialog dialog = builder.create();
                dialog.show();
                dialogs.add(dialog);
//                XLog.lg("dialogs:",dialogs);
                dialog.setOnDismissListener(v -> {
                    dialogs.remove(dialog);
//                    XLog.lg("remove dialog by itself");
                });


            }

        }

    }
}
