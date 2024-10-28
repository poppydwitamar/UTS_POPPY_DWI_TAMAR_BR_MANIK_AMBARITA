package uts.poppyambarita.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AgendaListActivity extends AppCompatActivity {

    private ListView agendaListView;
    private FloatingActionButton addAgendaButton;
    private ArrayList<String> agendas;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_list);

        agendaListView = findViewById(R.id.agendaListView);
        addAgendaButton = findViewById(R.id.addAgendaButton);
        agendas = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, agendas);
        agendaListView.setAdapter(adapter);

        addAgendaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgendaListActivity.this, AddAgendaActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                String agenda = data.getStringExtra("agenda");
                if (agenda != null) {
                    agendas.add(agenda);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "Agenda tidak berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

