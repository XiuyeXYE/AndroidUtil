package com.xy.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.util.cls.XType;

public class MixtureActivity extends AppCompatActivity {

    // Creates a Uri based on a base Uri and a record ID based on the contact's last name
    // Declares the base URI string
    private static final String CONTACTS = "content://com.example.contacts";

    // Declares a path string for URIs that you use to copy data
    private static final String COPY_PATH = "/copy";

    // Declares the Uri to paste to the clipboard
    Uri copyUri = Uri.parse(CONTACTS + COPY_PATH + "/" + "Nothing");

    // Declares a MIME type constant to match against the MIME types offered by the provider
    public static final String MIME_TYPE_CONTACT = "vnd.android.cursor.item/vnd.example.contact";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixture);

        ClipboardManager clipboard = XType.cast(getSystemService(Context.CLIPBOARD_SERVICE));

        ClipData clip = ClipData.newPlainText("simple text", "Hello World");


        ClipData clip3 = ClipData.newUri(getContentResolver(), "URI", copyUri);

        // Creates the Intent
        Intent appIntent = new Intent(this, SplashActivity.class);


        // Creates a clip object with the Intent in it. Its label is "Intent" and its data is
        // the Intent object created previously
        ClipData clip2 = ClipData.newIntent("Intent", appIntent);

        // Examines the item on the clipboard. If getText() does not return null, the clip item contains the
        // text. Assumes that this application can only handle one item at a time.
        ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);

        // Gets the clipboard as text.
        String pasteData = item.getText().toString();

        // If the string contains data, then the paste operation is done
        if (pasteData != null) {

            // The clipboard does not contain text. If it contains a URI, attempts to get data from it
        } else {
            Uri pasteUri = item.getUri();

            // If the URI contains something, try to get text from it
            if (pasteUri != null) {

                // calls a routine to resolve the URI and get data from it. This routine is not
                // presented here.
//                pasteData = resolveUri(Uri);
            } else {

                // Something is wrong. The MIME type was plain text, but the clipboard does not contain either
                // text or a Uri. Report an error.
                Log.e("Nothing", "Clipboard contains an invalid data type");
            }
        }

        // Gets a content resolver instance
        ContentResolver cr = getContentResolver();

        // Gets the clipboard data from the clipboard
        ClipData clip5 = clipboard.getPrimaryClip();

        if (clip != null) {

            // Gets the first item from the clipboard data
            ClipData.Item item2 = clip.getItemAt(0);

            // Tries to get the item's contents as a URI
            Uri pasteUri = item.getUri();
            // If the clipboard contains a URI reference
            if (pasteUri != null) {

                // Is this a content URI?
                String uriMimeType = cr.getType(pasteUri);
                // If the return value is not null, the Uri is a content Uri
                if (uriMimeType != null) {

                    // Does the content provider offer a MIME type that the current application can use?
                    if (uriMimeType.equals(MIME_TYPE_CONTACT)) {

                        // Get the data from the content provider.
                        Cursor pasteCursor = cr.query(pasteUri, null, null, null, null);

                        // If the Cursor contains data, move to the first record
                        if (pasteCursor != null) {
                            if (pasteCursor.moveToFirst()) {

                                // get the data from the Cursor here. The code will vary according to the
                                // format of the data model.
                            }
                        }

                        // close the Cursor
                        pasteCursor.close();
                    }
                }
            }
        }

        // Checks to see if the clip item contains an Intent, by testing to see if getIntent() returns null
        Intent pasteIntent = clipboard.getPrimaryClip().getItemAt(0).getIntent();

        if (pasteIntent != null) {

            // handle the Intent

        } else {

            // ignore the clipboard, or issue an error if your application was expecting an Intent to be
            // on the clipboard
        }

    }
}
