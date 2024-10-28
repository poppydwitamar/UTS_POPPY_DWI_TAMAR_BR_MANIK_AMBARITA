package uts.poppyambarita.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddAgendaActivity extends AppCompatActivity {

    private EditText agendaNameEditText;
    private EditText agendaDescriptionEditText;
    private Spinner statusSpinner;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agenda);

        agendaNameEditText = findViewById(R.id.agendaNameEditText);
        agendaDescriptionEditText = findViewById(R.id.agendaDescriptionEditText);
        statusSpinner = findViewById(R.id.statusSpinner);
        saveButton = findViewById(R.id.saveButton);

        // Inisialisasi spinner dengan status
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil data dari EditText dan Spinner
                String agendaName = agendaNameEditText.getText().toString().trim();
                String agendaDescription = agendaDescriptionEditText.getText().toString().trim();
                String status = statusSpinner.getSelectedItem().toString();

                // Validasi data input
                if (agendaName.isEmpty()) {
                    agendaNameEditText.setError("Nama agenda tidak boleh kosong");
                    return;
                }
                if (agendaDescription.isEmpty()) {
                    agendaDescriptionEditText.setError("Deskripsi agenda tidak boleh kosong");
                    return;
                }

                // Buat string agenda
                String agenda = agendaName + "\n" + agendaDescription + "\n" + status;

                // Kembalikan data ke AgendaListActivity
                Intent intent = new Intent();
                intent.putExtra("agenda", agenda);
                setResult(Activity.RESULT_OK, intent);
                finish(); // Tutup AddAgendaActivity
            }
        });
    }
}
