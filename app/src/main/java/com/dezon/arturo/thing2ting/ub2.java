package com.dezon.arturo.thing2ting;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;



public class ub2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ub2);

        Button b1 = (Button) findViewById(R.id.bb1);
        Button b2 = (Button) findViewById(R.id.bb2);
        Button b3 = (Button) findViewById(R.id.bb3);
        Button b4 = (Button) findViewById(R.id.bb4);
        EditText e1 = (EditText) findViewById(R.id.tt1);
        String s = getIntent().getStringExtra("link");
        e1.setText(s) ;

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ub2.this, ub.class));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence colors[] = new CharSequence[] {"copy input", "copy def", "copy both"};

                AlertDialog.Builder builder = new AlertDialog.Builder(ub2.this);
                builder.setTitle("Pick what to copy");
                builder.setNeutralButton("copy input", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText e1 = (EditText) findViewById(R.id.tt1);
                        TextView t1 = (TextView)  findViewById(R.id.tt2);
                        String input = e1.getText().toString();
                        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            clipboard.setText(input);
                        } else {
                            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", input);
                            clipboard.setPrimaryClip(clip);
                        }
                    }
                }) ;


                builder.setPositiveButton("copy definition", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText e1 = (EditText) findViewById(R.id.tt1);
                        TextView t1 = (TextView)  findViewById(R.id.tt2);
                        String def = t1.getText().toString();
                        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            clipboard.setText(def);
                        } else {
                            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", def);
                            clipboard.setPrimaryClip(clip);
                        }
                    }
                }) ;


                builder.setNegativeButton("copy both", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText e1 = (EditText) findViewById(R.id.tt1);
                        TextView t1 = (TextView)  findViewById(R.id.tt2);
                        String input = e1.getText().toString();
                        String def = t1.getText().toString();
                        String both = "Word: "+input+" Definition: "+def;
                        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            clipboard.setText(both);
                        } else {
                            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", both);
                            clipboard.setPrimaryClip(clip);
                        }
                    }
                }) ;
                builder.show();
            }
        });

        b4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence colors[] = new CharSequence[] {"copy input", "copy def", "copy both"};

                AlertDialog.Builder builder = new AlertDialog.Builder(ub2.this);
                builder.setTitle("Pick what to share");
                builder.setNeutralButton("share input", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText e1 = (EditText) findViewById(R.id.tt1);
                        TextView t1 = (TextView)  findViewById(R.id.tt2);
                        String input = e1.getText().toString();
                        String def = t1.getText().toString();
                        String both = "Word: "+input+" Definition: "+def;
                        Intent share = new Intent(android.content.Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                        // Add data to the intent, the receiving app will decide
                        // what to do with it.
                        share.putExtra(Intent.EXTRA_SUBJECT, "");
                        share.putExtra(Intent.EXTRA_TEXT,input);

                        startActivity(Intent.createChooser(share, "Share Input!"));
                    }
                }) ;


                builder.setPositiveButton("share definition", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText e1 = (EditText) findViewById(R.id.tt1);
                        TextView t1 = (TextView)  findViewById(R.id.tt2);
                        String input = e1.getText().toString();
                        String def = t1.getText().toString();
                        String both = "Word:"+input+"Definition: "+def;
                        Intent share = new Intent(android.content.Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                        // Add data to the intent, the receiving app will decide
                        // what to do with it.
                        share.putExtra(Intent.EXTRA_SUBJECT, "");
                        share.putExtra(Intent.EXTRA_TEXT, def);

                        startActivity(Intent.createChooser(share, "Share Definition!"));
                    }
                }) ;


                builder.setNegativeButton("share both", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText e1 = (EditText) findViewById(R.id.tt1);
                        TextView t1 = (TextView)  findViewById(R.id.tt2);
                        String input = e1.getText().toString();
                        String def = t1.getText().toString();
                        String both = "Word: "+input+" Definition: "+def;
                        Intent share = new Intent(android.content.Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                        // Add data to the intent, the receiving app will decide
                        // what to do with it.
                        share.putExtra(Intent.EXTRA_SUBJECT, "");
                        share.putExtra(Intent.EXTRA_TEXT, both);

                        startActivity(Intent.createChooser(share, "Share Both!"));
                    }
                }) ;
                builder.show();
            }
        }) ;





        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                new doit().execute();

            }
        });

        };

    public class doit extends AsyncTask<Void,Void,Void>{
String words;
        String mean;
        String exam;

        @Override
        protected Void doInBackground(Void... params) {

            try {
                EditText e1 = (EditText) findViewById(R.id.tt1);
                String medit = e1.getText().toString() ;
                String mmedit = "https://www.urbandictionary.com/define.php?term=" + medit;
                String passedting = getIntent().getExtras().getString("yeet") ;
                Document doc = Jsoup.connect(mmedit).get();
                Elements mea= doc.getElementsByClass("meaning");
                Elements exa= doc.getElementsByClass("example");

                mean = mea.text();
                exam = exa.text();
                words = mean + "   " +exam;
            }catch(Exception e){e.printStackTrace() ;

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            EditText e1 = (EditText) findViewById(R.id.tt1);
            TextView t1 = (TextView)  findViewById(R.id.tt2);

            t1.setText(words);


        }
    }

    }





// b1.setOnClickListener(new OnClickListener() {
//  @Override
// public void onClick(View v) {
//    new Title.execute();
//  }