package com.ict4everyone.testsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView _questionTextView;
    private TestOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        // FloatingActionButton
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SnackBar.make(view, "Hi!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }

        });
*/

        // DB作成
        helper = new TestOpenHelper(getApplicationContext());

        // TextViewにコロン区切りでまとめて表示するための変数設定
        // _questionTextView = findViewById(R.id.questionTextView);

        readData();
    }

    /**
     * DBから全件取得し画面表示
     */
    public void readData(){

        // 画面部品ListViewを取得
        ListView  lvQuiz = findViewById(R.id.lvQuiz);
        // SimpleAdapterで使用するListオブジェクトを用意
        List<Map<String, String>> quizList = new ArrayList<>();
        Map<String, String> quiz;


        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                "testquizdb",
                new String[] {"question", "cor_ans", "option1", "option2", "option3"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();
//        StringBuilder sbuilder = new StringBuilder() ;
        for (int i=0; i<cursor.getCount(); i++){
/*            sbuilder.append(cursor.getString(0));
            sbuilder.append(": ");
            sbuilder.append(cursor.getString(1));
            sbuilder.append(": ");
            sbuilder.append(cursor.getString(2));
            sbuilder.append(": ");
            sbuilder.append(cursor.getString(3));
            sbuilder.append(": ");
            sbuilder.append(cursor.getString(4));
            sbuilder.append("\n");
*/
            // question1のデータを格納するMapオブジェクトの用意とquizListへのデータ登録
            quiz = new HashMap<>();
            quiz.put("question", cursor.getString(0));
            quiz.put("cor_ans", cursor.getString(1));
            quiz.put("option1", cursor.getString(2));
            quiz.put("option2", cursor.getString(3));
            quiz.put("option3", cursor.getString(4));
            quizList.add(quiz);

            cursor.moveToNext();
        }

        cursor.close();

        //_questionTextView.setText(sbuilder.toString());

        // SimpleAdapter第４引数from用データの用意
        String[] from ={"question", "cor_ans"};
        // SimpleAdapter第５引数to用データの用意
        int[] to = {android.R.id.text1, android.R.id.text2};
        // SimpleAdapterを生成
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, quizList, android.R.layout.simple_expandable_list_item_2,
                from, to);
        // アダプタの登録
        lvQuiz.setAdapter(adapter);
    }
}



