package be.simongenin.langreader.adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.simongenin.langreader.R;
import nl.siegmann.epublib.domain.Book;

public class BooksGridAdapter extends ArrayAdapter<Book> {

    private Context context;
    private ArrayList<Book> books;
    private HashMap<Integer, ImageView> covers;

    public BooksGridAdapter(Context context, List<Book> books) {
        super(context, R.layout.item_books_gridview, books);
        this.context = context;
        this.books = (ArrayList<Book>) books;
        this.covers = new HashMap<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_books_gridview, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.book_title_tv);
            holder.author = (TextView) convertView.findViewById(R.id.author_name_tv);
            holder.cover = (ImageView) convertView.findViewById(R.id.book_cover_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book book = books.get(position);

        holder.title.setText(book.getTitle());
        String authorFullName = book.getMetadata().getAuthors().get(0).getFirstname() + " " + book.getMetadata().getAuthors().get(0).getLastname();
        holder.author.setText(authorFullName);

        Bundle bundle = new Bundle();
        bundle.putSerializable("book", book);
        bundle.putInt("pos", position);

        covers.put(position, holder.cover);

        new LoadCoverBitmap().execute(bundle);

        return convertView;
    }

    private static class ViewHolder {

        TextView title;
        TextView author;
        ImageView cover;

    }

    public class LoadCoverBitmap extends AsyncTask<Bundle, Void, Bitmap> {

        private int position;
        private ImageView iv;

        @Override
        protected Bitmap doInBackground(Bundle... params) {

            Bitmap coverImage = null;
            Book book = (Book) params[0].getSerializable("book");
            position = params[0].getInt("pos");
            iv = covers.get(position);

            try {
                coverImage = BitmapFactory.decodeStream(book != null ? book.getCoverImage().getInputStream() : null);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return coverImage;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            iv.setImageBitmap(bitmap);

        }
    }


}
