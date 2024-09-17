package com.gohool.firstlook.honeydolist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private Button saveButton;
    private EditText enterMessage;
    private TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        saveButton = (Button) findViewById(R.id.button);
        enterMessage = (EditText) findViewById(R.id.editText);
        showText = (TextView) findViewById(R.id.showText);

//        try {
//            File file = new File(getFilesDir(), "todolist.txt"); // Tạo file trong internal storage
//            FileOutputStream outputStream = new FileOutputStream(file);
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterMessage.getText().toString().equals(""))
                {
                    String message = enterMessage.getText().toString();
                    writeToFile(message);
                }

            }
        });

        try {
            if (readFromFile() != null)
            {
                showText.setText(readFromFile()); // There may be an error because when executing the code,
                                                  // it may not create the "todolist.txt" file immediately,
                                                  // so you need to find a way to fix this and create that "txt" file immediately.
            }
        } catch (IOException e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    private void writeToFile(String message)
    {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput
                                                    ("todolist.txt", Context.MODE_PRIVATE)
                                                                          );
            outputStreamWriter.write(message);
            outputStreamWriter.close(); // always close the stream

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private String readFromFile() throws IOException
    {
        String result = "";
        InputStream inputStream = openFileInput("todolist.txt"); // mở tệp "todolist.txt" trong chế độ chỉ đọc.

        if (inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader); /* Khi bạn đọc dữ liệu từ InputStreamReader, BufferedReader sẽ đọc một lượng lớn dữ liệu
                                                                                      từ file và lưu trữ vào bộ nhớ đệm. Các lần đọc tiếp theo sẽ được lấy trực tiếp
                                                                                      từ bộ nhớ đệm cho đến khi bộ đệm trống. Lúc này, BufferedReader sẽ lại đọc một lượng lớn dữ liệu
                                                                                      từ file để nạp vào bộ đệm.
                                                                                   */

            String tempString = "";
            StringBuilder stringBuilder = new StringBuilder(); /* StringBuilder là một lớp trong Java cho phép bạn tạo và thao tác với các chuỗi
                                                                có thể thay đổi (mutable). Khác với chuỗi thông thường (String)
                                                                là bất biến (immutable), StringBuilder cho phép bạn
                                                                sửa đổi nội dung của chuỗi mà không cần tạo ra đối tượng mới.
                                                                */


            while ((tempString = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(tempString);
            }

            inputStream.close();
            result = stringBuilder.toString();
        }

        return result;
    }

}

