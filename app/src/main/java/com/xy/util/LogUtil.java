package com.xy.util;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class LogUtil {

    private static final String TAG = "LogUtil";

    public static boolean DEBUG = true;

    public static final Map<String, AlertDialog> dialogs = new ConcurrentHashMap<String, AlertDialog>();

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
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
//        builder.setMessage(msg).setTitle(title).setPositiveButton("Over", (dlg, id) -> {
//            Toast.makeText(context, "dialog:" + msg, Toast.LENGTH_LONG).show();
//        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();

        DialogInfo info = new DialogInfo();
        info.setContext(context);
        info.setTitle(title);
        info.setMsg(msg);
        info.setCommand(1);

        AsyncDialogThread thread = new AsyncDialogThread();
        thread.execute(info);

    }

    public static void closeActivityDialog(Context context) {
//        AlertDialog dialog = dialogs.get(context.toString());
//        if (dialog != null) {
//            dialog.dismiss();
//            LogUtil.dialogs.remove(context.toString());
//
//        }
        DialogInfo info = new DialogInfo();
        info.setContext(context);
        info.setCommand(-1);
        AsyncDialogThread thread = new AsyncDialogThread();
        thread.execute(info);
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

    private static final String TAG = "AsyncDialogThread";

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
            if (info.getCommand() == 1 && !((AppCompatActivity) info.getContext()).isFinishing()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(info.getContext());
                builder.setMessage(info.getMsg()).setTitle(info.getTitle()).setPositiveButton("Over", (dlg, id) -> {
                    Toast.makeText(info.getContext(), "dialog:" + info.getMsg(), Toast.LENGTH_LONG).show();
                });
                info.setDialog(builder.create());
//                if () {
                LogUtil.dialogs.put(info.getContext().toString(), info.getDialog());
                Log.d(TAG, "dialog : " + info.getDialog() + " before showing");
                info.getDialog().show();
                Log.d(TAG, "dialog : " + info.getDialog() + " after showing");
//                }

            } else if (info.getCommand() == -1) {
                AlertDialog dialog = LogUtil.dialogs.get(info.getContext().toString());
                if (dialog != null) {
                    dialog.dismiss();
                    LogUtil.dialogs.remove(info.getContext().toString());

                }

            }
        }

    }
}
