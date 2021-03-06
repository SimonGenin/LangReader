package be.simongenin.langreader;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import be.simongenin.langreader.activities.SettingsActivity;
import be.simongenin.langreader.adapters.BooksGridAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.books_gridView)
    GridView gv;

    @InjectView(R.id.fab)
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        // Set the toolbar
        setSupportActionBar(toolbar);

        // Set the empty view
        gv.setEmptyView(findViewById(R.id.empty_view));

        // If landscape, three columns
        if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {
            gv.setNumColumns(3);
        }

        // Dummy books
        AssetManager assetManager = getAssets();
        List<Book> books = new ArrayList<>();
        Book book = null;
        try {

            InputStream epubInputStream = assetManager.open("books/hp7.epub");
            book = (new EpubReader()).readEpub(epubInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);

        BooksGridAdapter adapter = new BooksGridAdapter(this, books);
        gv.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdfHandling();
            }
        });

    }

    private void pdfHandling() {

        AssetManager assetManager = getAssets();
        try {

            InputStream epubInputStream = assetManager.open("books/hp7.epub");
            Book book = (new EpubReader()).readEpub(epubInputStream);
            Toast.makeText(this, book.getTitle(), Toast.LENGTH_SHORT).show();


        } catch (IOException e) {
            e.printStackTrace();
        }

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
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_sort) {
            sortSelectDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortSelectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a sorting option");
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setItems(R.array.sort_options, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                switch (item) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
