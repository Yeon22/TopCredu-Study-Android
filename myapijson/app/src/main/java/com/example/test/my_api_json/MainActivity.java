package com.example.test.my_api_json;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String DAUM_KEY = "28ea9874c487f5e0032ad9b9aed9e58d86fd1527";

    private EditText etSearch;
    private ListView lvResult;

    private InputMethodManager imm;
    private String url = "http://apis.daum.net/search/book?apikey="+DAUM_KEY+"&output=json&q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        etSearch = (EditText) findViewById(R.id.etSearch);
        lvResult = (ListView) findViewById(R.id.lvResult);

        //에디트텍스트 키 리스너 장착 : 키보드 키를 누를 때마다 콜백함수가 2번씩 호출된다.
        //엔터키를 치면 스레드를 호출해서 데이터를 가져옵니다.
        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        String query = etSearch.getText().toString();
                        url = url + query;
                        //Log.d("xxx",""+url);

                        doThread(url);
                        imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);

                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void doThread(String url){
        /*
            AsyncTask
            스레드를 래핑하여 보다 쉽게 스레드 처리를 하라고 구글 개발자가 제공하는 클래스.

            new AsyncTask<받는 파라미터, 보고파라미터, 최종결과파라미터>() {
                run() {
                    1.onPreExecute
                    2.doInBackground
                        publishProgress >> 3
                        return >> 4
                    3.onProgressUpdate
                    4.onPostExecute
                }
            }.execute(url); // url이 받는 파라미터이다.

         */
        new AsyncTask<String, Void, ArrayList<Book>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected ArrayList<Book> doInBackground(String... params) { // 받는 파라미터
                ArrayList<Book> list = new ArrayList<Book>();
                InputStream is = null;

                //Log.d("xxx",""+params[0]);

                try {
                    is = Downloader.download(params[0]);
                    String json = Downloader.convert(is);
                    //Log.d("xxx",""+json);

//                  publishProgress();

                    parseJson(list, json);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                return list; // 최종결과파라미터
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(final ArrayList<Book> result) {
                super.onPostExecute(result);

                lvResult.setAdapter(new BaseAdapter() {

                    @Override
                    public BookCell getView(int position, View convertView, ViewGroup parent) {

                        BookCell bookCell = null;

                        if (convertView == null) {
                            bookCell = new BookCell(getApplicationContext());
                        } else {
                            bookCell = (BookCell) convertView;
                        }
//						bookCell.setContent(result.get(position));
                        bookCell.setContent(getItem(position));

                        return bookCell;
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public Book getItem(int position) {
                        return result.get(position);
                    }

                    @Override
                    public int getCount() {
                        return result.size();
                    }
                });
            }

            private void parseJson(ArrayList<Book> list, String json) {
                JSONObject root;

                try {
                    root = new JSONObject(json);
                    JSONObject channel = root.getJSONObject("channel");
                    JSONArray items = channel.getJSONArray("item");

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject book = items.getJSONObject(i);

                        String title = book.getString("title");
                        title = title.replace("&lt;b&gt;", "[");
                        title = title.replace("&lt;/b&gt;", "]");

                        String price = book.getString("list_price");
                        String author = book.getString("author");
                        String img = book.getString("cover_s_url");

                        list.add(new Book(img, title, price, author));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(url);
    }
}
