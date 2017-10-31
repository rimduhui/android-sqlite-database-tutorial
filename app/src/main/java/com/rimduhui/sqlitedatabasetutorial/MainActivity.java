package com.rimduhui.sqlitedatabasetutorial;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TutorialDbHelper mTutorialDbHelper;

    private EditText mTitle;
    private EditText mSubtitle;
    private EditText mTargetTitle;
    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTutorialDbHelper = new TutorialDbHelper(this);

        mTitle = (EditText) findViewById(R.id.title);
        mSubtitle = (EditText) findViewById(R.id.subtitle);
        mTargetTitle = (EditText) findViewById(R.id.target_title);
        mResult = (TextView) findViewById(R.id.result);
    }

    public void onCreateItem(View v) {
        Log.i(getClass().getSimpleName(), "onCreateItem");

        SQLiteDatabase db = mTutorialDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TutorialContract.TutorialEntry.COLUMN_NAME_TITLE, mTitle.getText().toString());
        values.put(TutorialContract.TutorialEntry.COLUMN_NAME_SUBTITLE, mSubtitle.getText().toString());

        long newRowId = db.insert(TutorialContract.TutorialEntry.TABLE_NAME, null, values);
        if (newRowId > 0) {
            mResult.setText("new Row Id : " + newRowId);
        }
    }

    public void onReadItem(View v) {
        Log.i(getClass().getSimpleName(), "onReadItem");

        SQLiteDatabase db = mTutorialDbHelper.getReadableDatabase();

        Cursor c = db.query(
                TutorialContract.TutorialEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        c.moveToFirst();

        if (c.getCount() != 0) {
            mResult.setText("title : " + c.getString(c.getColumnIndex(TutorialContract.TutorialEntry.COLUMN_NAME_TITLE)) +
                    "\nsubtitle : " + c.getString(c.getColumnIndex(TutorialContract.TutorialEntry.COLUMN_NAME_SUBTITLE)));
        }

        while (c.moveToNext()) {
            mResult.append("\ntitle : " + c.getString(c.getColumnIndex(TutorialContract.TutorialEntry.COLUMN_NAME_TITLE)) +
                    "\nsubtitle : " + c.getString(c.getColumnIndex(TutorialContract.TutorialEntry.COLUMN_NAME_SUBTITLE)));
        }
    }

    public void onUpdateItem(View v) {
        Log.i(getClass().getSimpleName(), "onUpdateItem");

        SQLiteDatabase db = mTutorialDbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(TutorialContract.TutorialEntry.COLUMN_NAME_TITLE, mTitle.getText().toString());
        values.put(TutorialContract.TutorialEntry.COLUMN_NAME_SUBTITLE, mSubtitle.getText().toString());

        String selection = TutorialContract.TutorialEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { mTargetTitle.getText().toString() };

        int count = db.update(
                TutorialContract.TutorialEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count > 0) {
            mResult.setText("Count : " + count);
        }
    }

    public void onDeleteItem(View v) {
        Log.i(getClass().getSimpleName(), "onDeleteItem");

        SQLiteDatabase db = mTutorialDbHelper.getReadableDatabase();

        String selection = TutorialContract.TutorialEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { mTargetTitle.getText().toString() };
        db.delete(TutorialContract.TutorialEntry.TABLE_NAME, selection, selectionArgs);
    }
}
