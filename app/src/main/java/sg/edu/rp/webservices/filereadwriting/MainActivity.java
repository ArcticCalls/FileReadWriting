package sg.edu.rp.webservices.filereadwriting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {
    Button btnRead, btnWrite;
    TextView tv;
    String folderLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);
        tv = findViewById(R.id.tv);
        //Folder creation
        folderLocation = getFilesDir().getAbsolutePath() + "/Folder";
        File targetFile_I= new File(folderLocation, "data.txt");

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File folder = new File(folderLocation);
                if (folder.exists() == false){
                    boolean result = folder.mkdir();
                    if (result == true){
                        Log.d("File Read/Write", "Folder created");
                    }
                }
                try {
                    FileWriter writer_I = new FileWriter(targetFile_I, true);
                    writer_I.write("test data" + "\n");
                    writer_I.flush();
                    writer_I.close();
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "failed to write", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for file reading
                if(targetFile_I.exists() == true){
                    String data = "";
                    try {
                        FileReader reader = new FileReader(targetFile_I);
                        BufferedReader br = new BufferedReader(reader);

                        String line = br.readLine();
                        while (line != null){
                            data += line + "\n";
                            line = br.readLine();
                            tv.setText(data);
                        }

                        br.close();
                        reader.close();
                    } catch (Exception e){
                        Toast.makeText(MainActivity.this, "Failed to read!", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    Log.d("Content", data);
                }
            }
        });
    }

}