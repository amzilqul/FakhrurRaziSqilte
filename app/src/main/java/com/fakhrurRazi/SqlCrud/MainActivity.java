package com.fakhrurRazi.SqlCrud;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] daftar;
    ListView ListView01;
    Button btn;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn =findViewById(R.id.button2);
        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0;cc<cursor.getCount();cc++){
            cursor.moveToPosition(cc);
            daftar[cc]=cursor.getString(1).toString();
            ListView01 = findViewById(R.id.listView1);
            ListView01.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,daftar));
            ListView01.setSelected(true);
            ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final String selection = daftar[position];
                    final CharSequence[] dialogitem={"Lihat Biodata","Update Biodata","Hapus Biodata"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pilihan");
                    builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case 0 :
                                    Intent i = new Intent(MainActivity.this,LihatBiodata.class);
                                    i.putExtra("nama",selection);
                                    startActivity(i);
                                    break;
                                case 1:
                                    Intent a = new Intent(MainActivity.this,UpdateBiodata.class);
                                    a.putExtra("nama",selection);
                                    startActivity(a);
                                    break;
                                case 2 :
                                    SQLiteDatabase db= dbcenter.getWritableDatabase();
                                    db.execSQL("delete from biodata where nama = '"+selection+"'");
                                    RefreshList();
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }
            });
            ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();
        }

    }

    public void tambahData(View view) {
        Intent inten = new Intent(MainActivity.this,BuatBiodata.class);
        startActivity(inten);
    }
}
