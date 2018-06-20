package com.example.test.my_api_json;

import android.content.Context;
import android.view.LayoutInflater;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookCell extends LinearLayout {

    private WebView wvBookCover;
    private TextView tvTitle;
    private TextView tvPrice;

    public BookCell(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.book_cell, this, true);

        wvBookCover = (WebView) findViewById(R.id.wvBookCover);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvPrice = (TextView) findViewById(R.id.tvPrice);

        wvBookCover.setWebChromeClient(new WebChromeClient());
    }

    public void setContent(Book book) {
        wvBookCover.loadUrl(book.getImg());
        tvTitle.setText(book.getTitle() + "\r\n" + book.getAuthor());
        tvPrice.setText(book.getPrice());
    }

}
