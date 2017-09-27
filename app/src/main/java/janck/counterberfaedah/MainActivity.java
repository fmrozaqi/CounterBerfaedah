package janck.counterberfaedah;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button tombol1, tombol2, tombol3;
    TextView kata1, kata3;
    EditText kata2;
    Integer isi,batas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tombol1 = (Button) findViewById(R.id.tombolatur);
        tombol2 = (Button) findViewById(R.id.tomboltambah);
        tombol3 = (Button) findViewById(R.id.ulang);
        kata1 = (TextView) findViewById(R.id.atur);
        kata2 = (EditText) findViewById(R.id.isi);
        kata3 = (TextView) findViewById(R.id.angka);

        tombol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (kata2.getText().toString().trim().length() <= 0) {
                    Toast.makeText(MainActivity.this, "It's empty", Toast.LENGTH_SHORT).show();
                } else{
                    batas = Integer.parseInt(kata2.getText().toString());
                    if (batas==0){
                        Toast.makeText(MainActivity.this, "Isi selain 0", Toast.LENGTH_SHORT).show();
                    } else {
                        kata1.setText("Anda mengatur batas menjadi " + Integer.toString(batas));
                        kata3.setText("0");
                    }

                }
            }
        });

        tombol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(50);
                isi = Integer.parseInt(kata3.getText().toString()) + 1;
                if(isi==batas ){
                    v.vibrate(1000);
                    DialogFragment newFragment = new Habis();
                    newFragment.show(getSupportFragmentManager(),"");
                    kata3.setText("0");
                    kata1.setText("Batas Belum Diatur");
                    isi = 0;
                    batas=-1;
                }
                kata3.setText(Integer.toString(isi));
            }
        });

        tombol3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kata3.setText("0");
                kata1.setText("Batas Belum Diatur");
                isi = 0;
            }
        });

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.tentang, (ViewGroup) findViewById(R.id.about));

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new Fitur();
                newFragment.show(getSupportFragmentManager(),"");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
