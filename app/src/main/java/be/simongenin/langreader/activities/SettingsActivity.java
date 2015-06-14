package be.simongenin.langreader.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import be.simongenin.langreader.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class SettingsActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.show_definition_cb)
    CheckBox definitionCB;

    @InjectView(R.id.show_translation_cb)
    CheckBox translationCB;

    @InjectView(R.id.settings_translation_bloc)
    RelativeLayout translationBloc;

    @InjectView(R.id.settings_definition_bloc)
    RelativeLayout definitionBloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        translationBloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the checkbox as true
                translationCB.setChecked(!translationCB.isChecked());
            }
        });

        definitionBloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the checkbox as true
                definitionCB.setChecked(!definitionCB.isChecked());
            }
        });

    }

}
