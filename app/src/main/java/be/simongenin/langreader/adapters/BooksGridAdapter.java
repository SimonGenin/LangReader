package be.simongenin.langreader.adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.simongenin.langreader.R;
import nl.siegmann.epublib.domain.Book;

public class BooksGridAdapter extends ArrayAdapter<Book> {

    private Context context;
    private ArrayList<Book> books;

    public BooksGridAdapter(Context context, List<Book> books) {
        super(context, R.layout.item_books_gridview, books);
        this.context = context;
        this.books = (ArrayList<Book>) books;
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
        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book book = books.get(position);

        holder.title.setText(book.getTitle());
        String authorFullName = book.getMetadata().getAuthors().get(0).getFirstname() + " " + book.getMetadata().getAuthors().get(0).getLastname();
        holder.author.setText(authorFullName);
        try {
            Bitmap coverImage = BitmapFactory.decodeStream(book.getCoverImage().getInputStream());
            holder.cover.setImageBitmap(coverImage);
        } catch (IOException e) {
            // e.printStackTrace();
            holder.cover.setImageDrawable(getContext().getResources().getDrawable(R.drawable.book_128));
        }


        return convertView;
    }

    private static class ViewHolder {

        TextView title;
        TextView author;
        ImageView cover;

    }


}
