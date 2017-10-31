package com.rimduhui.sqlitedatabasetutorial;

import android.provider.BaseColumns;

public class TutorialContract {

    private TutorialContract() {}

    public static class TutorialEntry implements BaseColumns {
        public static final String TABLE_NAME = "tutorial";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}
