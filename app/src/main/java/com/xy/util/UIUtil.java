package com.xy.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

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
        AsyncDialogThread.clearPrevActivityRelatedDialog();//second step
    }

    public static <T> void log(Context context, T... tList) {
        if (!DEBUG) return;
        String output = "";
        for (T t : tList) {
            output += t + " ";
        }
        int idx = 0;
        output = output.substring(0, (idx = output.length() - 1) > 0 ? idx : 0);
        alert(context, "LOG:", output);
    }

    public static void alert(Context context, String title, String msg) {
        if (context == null) return;
        AsyncDialogThread thread = new AsyncDialogThread();
        thread.execute(context, title, msg);
    }


    public static void logCurrentWindow() {
        log(getCurrentActivty(), getCurrentActivty());
    }

    public static void log(String... s) {
        log(getCurrentActivty(), s);
    }
}


class AsyncDialogThread extends AsyncTask<Object, Void, Object> {

    public static List<AlertDialog> dialogs = TypeUtil.list();

    @Override
    protected Object doInBackground(Object... dialogInfos) {
        if (dialogInfos.length > 0) {
            return dialogInfos;
        }
        return null;
    }

    public static void clearPrevActivityRelatedDialog() {
//        Iterator<AlertDialog> it = dialogs.iterator();
//        while (it.hasNext()) {
//            AlertDialog ad = it.next();
//            if (ad.isShowing()) {
//                ad.dismiss();
//            }
//        }
//        dialogs.clear();
        Iterator<AlertDialog> it = dialogs.iterator();
        while (it.hasNext()) {
            AlertDialog ad = it.next();
            if (ad.getOwnerActivity() == null || !ad.getOwnerActivity().equals(UIUtil.getCurrentActivty())
                    || UIUtil.getCurrentActivty() == null
            ) {
                ad.dismiss();
                it.remove();
            }
        }
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
                dialog.setOnShowListener(v -> {
                    clearPrevActivityRelatedDialog();
                });


            }

        }

    }
}
