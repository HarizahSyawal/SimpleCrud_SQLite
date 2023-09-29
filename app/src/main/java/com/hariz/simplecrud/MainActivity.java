package com.hariz.simplecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.hariz.simplecrud.adapter.ListStudentAdapter;
import com.hariz.simplecrud.helper.Database;
import com.hariz.simplecrud.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Student> lists = new ArrayList<>();
    ListStudentAdapter adapter; // Change the type to ListStudentAdapter
    Database db = new Database(this);
    Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(getApplicationContext());
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.list_item);
        adapter = new ListStudentAdapter(MainActivity.this, lists); // Initialize the adapter here
        listView.setAdapter(adapter);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                final String id = lists.get(position).getId();
                final String name = lists.get(position).getName();
                final String email = lists.get(position).getEmail();
                final String matricnum = lists.get(position).getMatricnum();
                final CharSequence[] dialogItem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("name", name);
                                intent.putExtra("email", email);
                                intent.putExtra("matric_num", matricnum);
                                startActivity(intent);
                                break;
                            case 1:
                                db.delete(Integer.parseInt(id));
                                lists.clear();
                                getData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getData();
    }
    private void getData(){
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i < rows.size(); i++){
            String id = rows.get(i).get("id");
            String name = rows.get(i).get("name");
            String email = rows.get(i).get("email");
            String matricnum = rows.get(i).get("matric_num");

            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setEmail(email);
            student.setMatricnum(matricnum);
            lists.add(student);
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume(){
        super.onResume();
        lists.clear();
        getData();
    }
}