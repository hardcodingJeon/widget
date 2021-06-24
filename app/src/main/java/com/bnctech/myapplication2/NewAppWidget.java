package com.bnctech.myapplication2;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Random;

public class NewAppWidget extends AppWidgetProvider {

    private static final String ACTION_BUTTON1 = "com.js.widgetexample.BUTTON1";
    private static final String ACTION_BUTTON3 = "com.js.widgetexample.BUTTON3";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        //시작되면서 동적으로 타이틀 넣고 스타일 설정하기
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        views.setTextColor(R.id.appwidget_text, Color.WHITE);
        views.setViewPadding(R.id.appwidget_text, 8, 8, 8, 8);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        //랜덤 값을 만들어 화면에 출력해 보기
//        int number = (new Random().nextInt(100));
//        views.setViewPadding(R.id.message_text, 0, 8,0,8);
//        views.setTextColor(R.id.message_text, Color.YELLOW);
//        views.setTextViewText(R.id.message_text, String.valueOf(number));

        //시작되면서 지정 이미지로 교체
//        views.setImageViewResource(R.id.imageView, R.drawable.note5);



//        //버튼1 클릭 : 클릭 성공 메세지 출력!
        Intent intent1 = new Intent(ACTION_BUTTON1);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, 0, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
        views.setOnClickPendingIntent(R.id.bikeBorrow, pendingIntent1);
//
//        //버튼2 클릭 : 클릭하면 웹브라우저를 열어서 지정된 사이트를 보내 준다.
        Intent intent2 = new Intent(ACTION_BUTTON3);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, intent2, 0);
        views.setOnClickPendingIntent(R.id.bikeReturn, pendingIntent2);
//
//        //버튼3 클릭 : 이미지뷰에 비트맵 이미지를 교체해준다.
//        Intent intent3 = new Intent(ACTION_BUTTON3);
//        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(context, 0, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
//        views.setOnClickPendingIntent(R.id.button3, pendingIntent3);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent){
        super.onReceive(context, intent);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisAppWidget = new ComponentName(context.getPackageName(), NewAppWidget.class.getName());
        int[] appWidgets = appWidgetManager.getAppWidgetIds(thisAppWidget);

        final String action = intent.getAction();
        if(action.equals(ACTION_BUTTON1)){
            //your code here
            Toast.makeText(context, "버튼을 클릭했어요.", Toast.LENGTH_LONG).show();
            onUpdate(context, appWidgetManager, appWidgets);
        } else if (action.equals(ACTION_BUTTON3)){
            Toast.makeText(context, "이미지를 교체 할께요.", Toast.LENGTH_SHORT).show();
            //AsyncTask를 이용해서 이미지를 가져와서 교체해 보자.
            String imgUrl = "http:///교체할이미지주소.png";
//            new DownloadBitmap(views, appWidgets[0], appWidgetManager).execute(imgUrl); //AsyncTask 실행
//            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, NewAppWidget.class), views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

//    public class DownloadBitmap extends AsyncTask<String, Void, Bitmap> {
//
//        private RemoteViews views;
//        private int widgetID;
//        private AppWidgetManager appWidgetManager;
//
//        public DownloadBitmap(RemoteViews views, int appWidgetID, AppWidgetManager appWidgetManager) {
//            this.views = views;
//            this.widgetID = appWidgetID;
//            this.appWidgetManager = appWidgetManager;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            //다운로드 받을 이미지 주소
//            String url = params[0];
//
//            try {
//                InputStream in = new java.net.URL(url).openStream();
//                Bitmap bitmap = BitmapFactory.decodeStream(in);
//                Log.e("ImageDownload", "Download succeeded! " + params[0]);
//                return bitmap;
//            } catch (Exception e) {
//                Log.e("ImageDownload", "Download failed: " + e.getMessage());
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            //그 결과를 가지고 화면에 출력
//            if(isCancelled()) {
//                bitmap = null;
//            }
//            views.setImageViewBitmap(R.id.imageView, bitmap);
//            appWidgetManager.updateAppWidget(widgetID, views);
//
//        }
//    }
}