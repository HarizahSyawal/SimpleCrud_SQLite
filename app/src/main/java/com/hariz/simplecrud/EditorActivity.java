package com.hariz.simplecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hariz.simplecrud.helper.Database;

public class EditorActivity extends AppCompatActivity {

    private EditText editName,editEmail,editMatricnum;
    private Button btnSave;
    private Database db = new Database(this);
    private String id, name, email, matricnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editName = findViewById(R.id.etName);
        editEmail = findViewById(R.id.etEmail);
        editMatricnum = findViewById(R.id.etMatricnum);
        btnSave = findViewById(R.id.btnSave);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        matricnum = getIntent().getStringExtra("matric_num");

        if (id == null || id.equals("")){
            setTitle("Adding User");
        }else {
            setTitle("Edit User");
            editName.setText(name);
            editEmail.setText(email);
            editMatricnum.setText(matricnum);
        }
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if(id == null || id.equals("")){
                            save();
                        }else {
                            edit();
                        }
                    }catch (Exception e){
                        Log.e("Saving", e.getMessage());
                    }
                }
            });
    }
    private void save() {
        // Get the text from the EditText fields
        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String matricnum = editMatricnum.getText().toString().trim();

        // Check if any of the fields is empty
        if (name.isEmpty() || email.isEmpty() || matricnum.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please complete the form!", Toast.LENGTH_SHORT).show();
        } else {
            // All fields are filled, you can proceed to save the data
            db.insert(name, email, matricnum);
            finish();
        }
    }

    private void edit() {
        // Get the text from the EditText fields
        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String matricnum = editMatricnum.getText().toString().trim();

        // Check if any of the fields is empty
        if (name.isEmpty() || email.isEmpty() || matricnum.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please complete the form!", Toast.LENGTH_SHORT).show();
        } else {
            // All fields are filled, you can proceed to save the data
            db.update(Integer.parseInt(id),name, email, matricnum);
            finish();
        }
    }
}

