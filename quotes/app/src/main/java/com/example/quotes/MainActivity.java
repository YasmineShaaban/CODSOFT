package com.example.quotes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView quote;
    Button nxt, fav, share, view, viewfavs;
    String txt= "";
    SQLiteDatabase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i= getIntent();

        quote= findViewById(R.id.quote);
        nxt= findViewById(R.id.btn);
        fav= findViewById(R.id.fav);
        share= findViewById(R.id.share);
        view= findViewById(R.id.view);
        viewfavs= findViewById(R.id.viewfavs);


        //create a new database named thelist
        myDb = openOrCreateDatabase("thelist", Context.MODE_PRIVATE, null);
        //Create the table favQ in the database thelist
        myDb.execSQL("CREATE TABLE IF NOT EXISTS favq(quote VARCHAR)");

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = myDb.rawQuery("SELECT * FROM favq WHERE quote='" + quote.getText() + "'", null);
                if (c.moveToFirst()) {
                    Toast.makeText(MainActivity.this, "It's already added to your favorites!", Toast.LENGTH_SHORT).show();
                    return;
                }
                myDb.execSQL("INSERT INTO favq VALUES('" + quote.getText() + "');");
                Toast.makeText(MainActivity.this, "The quote is added to your favorites!", Toast.LENGTH_SHORT).show();
            }});

        viewfavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=myDb.rawQuery("SELECT * FROM favq",null);
                StringBuffer buffer = new StringBuffer();
                if(c.getCount()>0) {
                    while (c.moveToNext()) {
                        buffer.append("\n\n  " + c.getString(0));
                        buffer.append("\n---------------------------------------------------------------------\n");
                    }
                }
                showMessage("Favorite Quotes:",buffer.toString());

                Intent i=new Intent(MainActivity.this,favorites.class);
                i.putExtra("FAVQ",buffer.toString());
                startActivity(i);
            }
        });


        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random= new Random();
                int r= random.nextInt(23);

                switch (r) {
                    case 1:
                        txt = getString(R.string.a);
                        break;
                    case 2:
                        txt = getString(R.string.b);
                        break;
                    case 3:
                        txt = getString(R.string.c);
                        break;
                    case 4:
                        txt = getString(R.string.d);
                        break;
                    case 5:
                        txt = getString(R.string.e);
                        break;
                    case 6:
                        txt = getString(R.string.f);
                        break;
                    case 7:
                        txt = getString(R.string.g);
                        break;
                    case 8:
                        txt = getString(R.string.h);
                        break;
                    case 9:
                        txt = getString(R.string.i);
                        break;
                    case 10:
                        txt = getString(R.string.j);
                        break;
                    case 11:
                        txt = getString(R.string.k);
                        break;
                    case 12:
                        txt = getString(R.string.l);
                        break;
                    case 13:
                        txt = getString(R.string.m);
                        break;
                    case 14:
                        txt = getString(R.string.n);
                        break;
                    case 15:
                        txt = getString(R.string.o);
                        break;
                    case 16:
                        txt = getString(R.string.p);
                        break;
                    case 17:
                        txt = getString(R.string.q);
                        break;
                    case 18:
                        txt = getString(R.string.r);
                        break;
                    case 19:
                        txt = getString(R.string.s);
                        break;
                    case 20:
                        txt = getString(R.string.t);
                        break;
                    case 21:
                        txt = getString(R.string.u);
                        break;
                    case 22:
                        txt = getString(R.string.v);
                        break;
                    default:
                        txt = getString(R.string.w);
                }
                quote.setText(txt);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, txt);
                startActivity(Intent.createChooser(i, "Select Application"));
            }
        });

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i= new Intent(MainActivity.this, viewquotes.class);
                startActivity(i);
            }
        });
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder b=new AlertDialog.Builder((this));
        b.setCancelable(true);
        b.setTitle(title);
        b.setIcon(R.drawable.quote);
        b.setMessage(message);
        b.show();
    }
}