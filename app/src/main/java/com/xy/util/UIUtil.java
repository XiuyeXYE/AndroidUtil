package com.xy.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


public class UIUtil {

    public static boolean DEBUG = true;

    private static Context currentActivty;

    public static synchronized Context getCurrentActivty() {
        return currentActivty;
    }

    public static synchronized void setCurrentActivty(Context activity) {
        if (activity == null) return;
        currentActivty = activity;
    }

    public static <T> void log(Context context, T... tList) {
        if (!DEBUG) return;
        setCurrentActivty(context);
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

        DialogInfo info = new DialogInfo();
        info.setContext(context);
        info.setTitle(title);
        info.setMsg(msg);
        info.setCommand(1);
        AsyncDialogThread thread = new AsyncDialogThread();
        thread.execute(info);

    }


    public static void logCurrentWindow() {
        log(getCurrentActivty(), getCurrentActivty());
    }

    public static void log(String... s) {
        log(getCurrentActivty(), s);
    }
}

class DialogInfo {
    private Context context;
    private String title;
    private String msg;
    private AlertDialog dialog;
    private int command;//-1：close ，1 open

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AlertDialog getDialog() {
        return dialog;
    }

    public void setDialog(AlertDialog dialog) {
        this.dialog = dialog;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "DialogInfo{" +
                "context=" + context +
                ", title='" + title + '\'' +
                ", msg='" + msg + '\'' +
                ", dialog=" + dialog +
                ", command=" + command +
                '}';
    }
}

class AsyncDialogThread extends AsyncTask<DialogInfo, Void, DialogInfo> {


    @Override
    protected DialogInfo doInBackground(DialogInfo... dialogInfos) {
        if (dialogInfos.length > 0) {
            DialogInfo info = dialogInfos[0];
            return info;
        }
        return null;
    }


    @Override
    protected void onPostExecute(DialogInfo info) {
        super.onPostExecute(info);
        if (info != null) {
            if (info.getCommand() == 1 && !((Activity) info.getContext()).isFinishing()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(info.getContext());
                builder.setMessage(info.getMsg()).setTitle(info.getTitle()).setPositiveButton("Over", (dlg, id) -> {
                    Toast.makeText(info.getContext(), "dialog:" + info.getMsg(), Toast.LENGTH_LONG).show();
                });
                info.setDialog(builder.create());
                info.getDialog().show();

            }

        }

    }
}
