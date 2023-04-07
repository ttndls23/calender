package com.cookandroid.calendar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //날짜 계산,,, ㅇㅋ 자동 계산.
    //하드코딩은 기본,,, 자동 계산은 가산점
    CalendarView calendarView;
    int year = 0;
    int day = 0;
    int month = 0;
    int color_set = 0; // 이번달 아닌 달은 회색으로 글씨를 표현하기 위해서
    Calendar calendar = Calendar.getInstance();
    Integer buttonId[] = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12, R.id.button13,
            R.id.button14, R.id.button15, R.id.button16, R.id.button17, R.id.button18, R.id.button19, R.id.button20,
            R.id.button21, R.id.button22, R.id.button23, R.id.button24, R.id.button25, R.id.button26, R.id.button27,
            R.id.button28, R.id.button29, R.id.button30, R.id.button31, R.id.button32, R.id.button33, R.id.button34,
            R.id.button35, R.id.button36, R.id.button37, R.id.button38, R.id.button39, R.id.button40, R.id.button41, R.id.button42
    };

    Integer textViewId[] = {R.id.textView, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5, R.id.textView6,
            R.id.textView7, R.id.textView8, R.id.textView9, R.id.textView10, R.id.textView11, R.id.textView12, R.id.textView13,
            R.id.textView14, R.id.textView15, R.id.textView16, R.id.textView17, R.id.textView18, R.id.textView19, R.id.textView20,
            R.id.textView21, R.id.textView22, R.id.textView23, R.id.textView24, R.id.textView25, R.id.textView26, R.id.textView27,
            R.id.textView28, R.id.textView29, R.id.textView30, R.id.textView31, R.id.textView32, R.id.textView33, R.id.textView34,
            R.id.textView35, R.id.textView36, R.id.textView37, R.id.textView38, R.id.textView39, R.id.textView40, R.id.textView41, R.id.textView42
    };

    ActionBar actionBar;
    Button button[] = new Button[42];
    TextView textView[] = new TextView[42];
    EditText schedule_name_et, schedule_start_time_et, schedule_end_time_et, schedule_place_et, schedule_memo_et;
    View dialogView;
    String schedule_name = "", schedule_start_time = "", schedule_end_time = "", schedule_place = "", schedule_memo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();


        Display display = getWindowManager().getDefaultDisplay(); // in Activity
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Log.i("width", Integer.toString(width));
        Log.i("height", Integer.toString(height));

        for (int i = 0; i < textViewId.length; i++) { // 캘린더 기본 설정(크기)
            final int index;
            index = i;
            textView[index] = (TextView) findViewById(textViewId[index]);
            textView[index].setTextSize(17);
            button[index] = (Button) findViewById(buttonId[index]);
            button[index].setHeight((height) / 9);
            //button[index].setGravity("left|top");
        }

        for(int i = 0; i < buttonId.length; i++){

            final int index;
            index = i;

            button[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int current_text_color = textView[index].getCurrentTextColor();

                    if (current_text_color == Integer.parseInt("-1") || current_text_color == Integer.parseInt("-65536") || current_text_color == Integer.parseInt("-16711425")) {

                        String click_day = textView[index].getText().toString();
                        String click_month = Integer.toString(month);
                        String click_year = Integer.toString(year);
                        final String file_name = click_year + "_" + click_month + "_" + click_day + ".txt";


                        dialogView = (View) View.inflate(MainActivity.this, R.layout.input_schedule, null);

                        schedule_name_et = dialogView.findViewById(R.id.schedule_name);
                        schedule_start_time_et = dialogView.findViewById(R.id.schedule_start_time);
                        schedule_end_time_et = dialogView.findViewById(R.id.schedule_end_time);
                        schedule_place_et = dialogView.findViewById(R.id.schedule_place);
                        schedule_memo_et = dialogView.findViewById(R.id.schedule_memo);

                        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                        dlg.setTitle("월간 일정 등록");
                        dlg.setView(dialogView);
                        dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //if(schedule_name_et.getText().toString().equals("") || schedule_start_time_et.getText().toString().equals("") || schedule_end_time_et.getText().toString().equals("") || schedule_place_et.getText().toString().equals("") || schedule_memo_et.getText().toString().equals("")){
                                //    System.out.println("입력이 필요합니다. ");
                                //}else {
                                schedule_name = schedule_name_et.getText().toString();
                                schedule_start_time = schedule_start_time_et.getText().toString();
                                schedule_end_time = schedule_end_time_et.getText().toString();
                                schedule_place = schedule_place_et.getText().toString();
                                schedule_memo = schedule_memo_et.getText().toString();
                                //}

                                try {
                                    FileOutputStream fileOutputStream = openFileOutput(file_name, Context.MODE_PRIVATE);

                                    String name = "약속 제목 : " + schedule_name;
                                    String start_time = "시작 시간 : " + schedule_start_time;
                                    String end_time = "끝 시간 : " + schedule_end_time;
                                    String place = "장소 : " + schedule_place;
                                    String memo = "메모 : " + schedule_memo;
                                    String all = name + "\n" + start_time + "\n" + end_time + "\n" + place + "\n" + memo;

                                    fileOutputStream.write(all.getBytes());
                                    fileOutputStream.close();

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                button[index].setText(schedule_name);
                                button[index].setTextColor(Color.parseColor("#FFFFFF"));
                            }
                        });
                        dlg.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                /*File file = new File("test_two.txt");
                                if(file.exists()){
                                    file.delete();
                                }*/
                                    deleteFile(file_name);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                button[index].setText("");
                            }
                        });
                        dlg.show();



                    }else{
                        Toast.makeText(MainActivity.this, "잘못된 선택입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        for(int i = 0; i < buttonId.length; i++){

            final int index;
            index = i;

            button[index].setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int current_text_color = textView[index].getCurrentTextColor();

                    if (current_text_color == Integer.parseInt("-1") || current_text_color == Integer.parseInt("-65536") || current_text_color == Integer.parseInt("-16711425")) {

                        String click_day = textView[index].getText().toString();
                        String click_month = Integer.toString(month);
                        String click_year = Integer.toString(year);
                        final String file_name = click_year + "_" + click_month + "_" + click_day + ".txt";

                        try {
                            FileInputStream fileInputStream = openFileInput(file_name);
                            byte[] txt = new byte[2048];
                            fileInputStream.read(txt);
                            String str = new String(txt);

                            AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                            dlg.setTitle("상세 일정");
                            dlg.setMessage(str);
                            dlg.setPositiveButton("확인", null);
                            dlg.show();

                            //Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                            fileInputStream.close();
                        }catch (IOException e){
                            Toast.makeText(getApplicationContext(), "파일 없음", Toast.LENGTH_SHORT).show();
                        } catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                    return false;
                }
            });
        }


        makeCalendar();

    }

    public void show_schedule(int today, String exist_check_file){
        try {
            FileInputStream inFs = openFileInput(exist_check_file);
            byte[] txt = new byte[50];
            inFs.read(txt);
            String str = new String(txt);
            String[] schedule_name_start_time = str.split(":");
            String[] schedule_name = schedule_name_start_time[1].split("\n");
            schedule_name[0] = schedule_name[0].replace(" ", "");
            if(schedule_name[0].equals("")){
                schedule_name[0] = "일정 있음";
                //button[today].setText("일정 있음");
            }/*else {
                button[today].setText(schedule_name[0]);
            }*/
            button[today].setText(schedule_name[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public boolean file_exist_check_function(int today, String exist_check_file){
        File file = new File(exist_check_file);
        if(file.exists()){
            button[today].setText("파일이 존재합니다.");
            button[today].setTextColor(Color.parseColor("#FFFFFF"));
            return true;
        }else{
            button[today].setText("");
            return false;
        }
    }



    public void makeCalendar(){
        int day_of_week_two;
        int day_of_week = 0;
        int change_month = 0; // 달 구성 때 이번달 마지막 일하고 저번달 마지막 주 구분
        year = calendar.get(Calendar.YEAR);
        month = (calendar.get(Calendar.MONTH) + 1);
        day = calendar.get(Calendar.DAY_OF_MONTH); // 문제 있음 하루 적게 나옴. 아침에 확인해볼것
        day_of_week = calendar.get(Calendar.DAY_OF_WEEK); // 이것도 하루 전으로 나옴
        day_of_week_two = day_of_week;

        int month_Jan = 31, month_Apl = 30, month_Feb = 28, month_Feb_two = 29, date_calculation, j_makeCalendar, month_total = 0;

        for (j_makeCalendar = day; j_makeCalendar > 1; j_makeCalendar--) { // 1자리 잡기. (요일) 1일의 요일 구하기
            if (day_of_week_two == 1) {
                day_of_week_two = 7;
            } else {
                day_of_week_two--;
            }
        }

        for (date_calculation = 0; date_calculation < (day_of_week_two - j_makeCalendar); date_calculation++) { // 날짜 계산. 요일 - 1.
            if (month == 5 || month == 7 || month == 10 || month == 12) { // 마지막 날짜 가지고 판단. 저번달 기준
                month_total = month_Apl; // 30일부터 --해서 이번달 시작 전에 며칠이나 있나 결정 즉, 29 30 31 1 2 3 4 이런식으로 하기 위해 29를 구하는 코드
                month_Apl--;
            } else if(month == 3){
                if((((year%4==0) && (year%100!=0)) || (year%400==0))){
                    month_total = month_Feb_two;
                    month_Feb_two --;
                }else{
                    month_total = month_Feb;
                    month_Feb --;
                }
            } else {
                month_total = month_Jan;
                month_Jan--;
            }
        }

        actionBar.setTitle(Integer.toString(month)+"월");
        actionBar.setSubtitle(Integer.toString(year)+"년");

        for (int i = 0; i < textViewId.length; i++) {
            final int index;
            index = i;
            if (month == 5 || month == 7 || month == 10 || month == 12) { // 저번달 기준
                if (month_total >= 31 && change_month == 0) { // 이번달 출력
                    month_total = 1;
                    change_month = 1;
                    color_set = 1;
                } else if (month_total >= 30 && change_month == 1) { // 다음달 출력
                    month_total = 1;
                    change_month = 0;
                    color_set = 0;
                }
                textView[index].setText(Integer.toString(month_total));
                if (color_set == 0) {
                    if (index % 7 == 0) {
                        textView[index].setTextColor(Color.parseColor("#80FF0000"));
                    } else {
                        textView[index].setTextColor(Color.parseColor("#80FFFFFF"));
                    }
                } else if (color_set == 1) {
                    if (index % 7 == 0) {
                        textView[index].setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        textView[index].setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }
                month_total++;

            }else if(month == 3){
                if((((year%4==0) && (year%100!=0)) || (year%400==0))){ // 윤년. 29일까지 존재
                    if (month_total >= 30 && change_month == 0) { // 이번달 출력
                        month_total = 1;
                        change_month = 1;
                        color_set = 1;
                    } else if (month_total >= 29 && change_month == 1) { // 다음달 출력
                        month_total = 1;
                        change_month = 0;
                        color_set = 0;
                    }

                }else{ // 평년 28일까지 존재

                    if (month_total >= 29 && change_month == 0) { // 이번달 출력
                        month_total = 1;
                        change_month = 1;
                        color_set = 1;
                    } else if (month_total >= 28 && change_month == 1) { // 다음달 출력
                        month_total = 1;
                        change_month = 0;
                        color_set = 0;
                    }

                }

                textView[index].setText(Integer.toString(month_total));
                if (color_set == 0) {
                    if (index % 7 == 0) {
                        textView[index].setTextColor(Color.parseColor("#80FF0000"));
                    } else {
                        textView[index].setTextColor(Color.parseColor("#80FFFFFF"));
                    }
                } else if (color_set == 1) {
                    if (index % 7 == 0) {
                        textView[index].setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        textView[index].setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }
                month_total++;

            }else {
                if (month_total >= 32 && change_month == 0) {
                    month_total = 1;
                    change_month = 1;
                    color_set = 1;
                } else if (month_total >= 31 && change_month == 1) {
                    month_total = 1;
                    change_month = 0;
                    color_set = 0;
                }
                textView[index].setText(Integer.toString(month_total));
                if (color_set == 0) {
                    if (index % 7 == 0) {
                        textView[index].setTextColor(Color.parseColor("#80FF0000"));
                    } else {
                        textView[index].setTextColor(Color.parseColor("#80FFFFFF"));
                    }
                } else if (color_set == 1) {
                    if (index % 7 == 0) {
                        textView[index].setTextColor(Color.parseColor("#FF0000"));
                    } else {
                        textView[index].setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }
                month_total++;
            }
        }

        change_month = 0;
        for(int i = 0; i < 42; i++){ // 색 지정
            final int today;
            today = i;
           /* if((Integer.parseInt(textView[today].getText().toString()) == day) && change_month == 0){ // 31일 정도면 위험 이 부분 다시 생각 필요
                button[today].setBackgroundResource(R.drawable.border_button_current);
                textView[today].setBackgroundResource(R.drawable.border_text_current);
                textView[today].setTextColor(Color.parseColor("#0100FF")); // 파란색
                change_month++;
            }*/

            if((calendar.get(Calendar.YEAR) == year) && ((calendar.get(Calendar.MONTH) + 1) == month) && (Integer.parseInt(textView[today].getText().toString()) == day) && (textView[today].getCurrentTextColor() == Integer.parseInt("-65536") || textView[today].getCurrentTextColor() == Integer.parseInt("-1"))){ // 31일 정도면 위험 이 부분 다시 생각 필요
                button[today].setBackgroundResource(R.drawable.border_button_current);
                textView[today].setBackgroundResource(R.drawable.border_text_current);
                textView[today].setTextColor(Color.parseColor("#0100FF"));
                change_month++;
            }



            button[today].setText("");
            if(textView[today].getCurrentTextColor() == Integer.parseInt("-1") || textView[today].getCurrentTextColor() == Integer.parseInt("-65536") || textView[today].getCurrentTextColor() == Integer.parseInt("-16711425")){
                String exist_check_year = Integer.toString(year);
                String exist_check_month = Integer.toString(month);
                String exist_check_day = textView[today].getText().toString(); // 색깔도 필요

                String exist_check_file =  exist_check_year + "_" + exist_check_month + "_" + exist_check_day + ".txt";

                if(file_exist_check_function(today, "/data/data/com.cookandroid.calendar/files/"+exist_check_file)){
                   show_schedule(today, exist_check_file);
                }

            }
        }
    }


    public void makeCalendar_back(){
        month = month-1;
        if(month == 0){
            year--;
            month = 12;
        }
        actionBar.setTitle(Integer.toString(month)+"월");
        actionBar.setSubtitle(Integer.toString(year)+"년");
        int j = 28; // 뒤쪽 초기화 index
        int index_num = 0; // 28번째 인덱스 안에 든 값
        int color_set_back = 0;


        for(int i = 0; i < 14; i++){
            final int index; // 뒷 달 앞에서 두줄
            index = i;
            final int index_two;// 앞 달 뒤에서 두줄
            index_two = j; // 앞 달 뒤에서 두줄
            textView[index_two].setText(textView[index].getText());
            j++;
        }

        index_num = Integer.parseInt(textView[28].getText().toString())-1;
        Log.i("index_num", Integer.toString(index_num));

        if(Integer.parseInt(textView[28].getText().toString()) >= 30 || Integer.parseInt(textView[28].getText().toString()) == 1){
            j = 35;
            for(int i = 0; i < 7; i++){
                final int index; // 뒷 달 앞에서 한줄
                index = i;
                final int index_two;// 앞 달 뒤에서 한줄
                index_two = j; // 앞 달 뒤에서 한줄
                textView[index_two].setText(textView[index].getText());
                j++;
            }
            index_num = Integer.parseInt(textView[35].getText().toString())-1;
            if(index_num == 0){ // 현재 달 맨 끝 수 초기화
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10) { // 현재 달 기준
                    index_num = 31;
                }else if(month == 2){
                    index_num = 29;
                }else{
                    index_num = 30;
                }
            }

            for(int k = 34; k >= 0; k--){
                final int index;
                index = k;

                textView[index].setText(Integer.toString(index_num));
                index_num--;

                if(index_num == 0){ // 전 달 기준
                    if (month == 5 || month == 7 || month == 10 || month == 12) { // 전 달 기준
                        index_num = 30;
                    }else if(month == 3){
                        if((((year%4==0) && (year%100!=0)) || (year%400==0))){
                            index_num = 29;
                        }else{
                            index_num = 28;
                        }
                    }else{
                        index_num = 31;
                    }

                }


            }

        }else{
            for(int k = 27; k >= 0; k--){
                final int index;
                index = k;

                textView[index].setText(Integer.toString(index_num));
                index_num--;
                if(index_num == 0){
                    if (month == 5 || month == 7 || month == 10 || month == 12) { // 저번달 기준
                        index_num = 30;
                    }else if(month == 3){
                        if((((year%4==0) && (year%100!=0)) || (year%400==0))){
                            index_num = 29;
                        }else{
                            index_num = 28;
                        }
                    }else{
                        index_num = 31;
                    }
                }

            }

        }


        for(int i = 0; i < 42; i++){ // 색 지정
            final int index_color;
            index_color = i;

            if((Integer.parseInt(textView[index_color].getText().toString()) == 1) && color_set_back == 0){
                color_set_back = 1;
            }else if((Integer.parseInt(textView[index_color].getText().toString()) == 1) && color_set_back == 1){
                color_set_back = 0;
            }

            if (color_set_back == 0) {
                if (index_color % 7 == 0) {
                    textView[index_color].setTextColor(Color.parseColor("#80FF0000"));
                } else {
                    textView[index_color].setTextColor(Color.parseColor("#80FFFFFF"));
                }
            } else if (color_set_back == 1) {
                if (index_color % 7 == 0) {
                    textView[index_color].setTextColor(Color.parseColor("#FF0000"));
                } else {
                    textView[index_color].setTextColor(Color.parseColor("#FFFFFF"));
                }
            }




            if((month ==  (calendar.get(Calendar.MONTH) + 1)) && (Integer.parseInt(textView[index_color].getText().toString()) == day) && (calendar.get(Calendar.YEAR)) == year && (textView[index_color].getCurrentTextColor() == Integer.parseInt("-65536") || textView[index_color].getCurrentTextColor() == Integer.parseInt("-1"))){
                button[index_color].setBackgroundResource(R.drawable.border_button_current);
                textView[index_color].setBackgroundResource(R.drawable.border_text_current);
                textView[index_color].setTextColor(Color.parseColor("#0100FF"));
            }else{
                button[index_color].setBackgroundResource(R.drawable.border_button_else);
                textView[index_color].setBackgroundResource(R.drawable.border_text_else);
            }





            button[index_color].setText("");

            if(textView[index_color].getCurrentTextColor() == Integer.parseInt("-1") || textView[index_color].getCurrentTextColor() == Integer.parseInt("-65536") || textView[index_color].getCurrentTextColor() == Integer.parseInt("-16711425")){
                String exist_check_year = Integer.toString(year);
                String exist_check_month = Integer.toString(month);
                String exist_check_day = textView[index_color].getText().toString(); // 색깔도 필요

                String exist_check_file = exist_check_year + "_" + exist_check_month + "_" + exist_check_day + ".txt";

                if(file_exist_check_function(index_color, "/data/data/com.cookandroid.calendar/files/"+exist_check_file)){
                    show_schedule(index_color, exist_check_file);
                }

            }

        }

    }

    public void makeCalendar_next(){
        month = month+1;
        if(month == 13){
            year++;
            month = 1;
        }
        actionBar.setTitle(Integer.toString(month)+"월");
        actionBar.setSubtitle(Integer.toString(year)+"년");
        int j = 0; // 뒤쪽 초기화 index
        int index_num = 0; // 14번째 인덱스에 든 날짜
        int color_set_back = 0; // color 지정 위함
        int start_num = 14;

        for(int i = 28; i < 42; i++){
            final int index; // 앞 달 뒤에서 두 줄
            index = i;
            final int index_two;// 현재 달 앞에서 두줄
            index_two = j; // 현재 달 앞에서 두 줄
            textView[index_two].setText(textView[index].getText());
            j++;
        }

        index_num = Integer.parseInt(textView[13].getText().toString())+1;
        Log.i("index_num", Integer.toString(index_num));

        if(Integer.parseInt(textView[6].getText().toString()) >= 29){
            j = 0;
            for(int i = 35; i < 42; i++){
                final int index; // 앞 달 뒤에서 한 줄
                index = i;
                final int index_two;// 현재 달 앞에서 한 줄
                index_two = j; // 현재 달 앞에서 한 줄
                textView[index_two].setText(textView[index].getText());
                j++;
            }
            index_num = Integer.parseInt(textView[6].getText().toString())+1;
            start_num = 7;
        }

        for(int i = start_num; i < 42; i++){
            final int index;
            index = i;
            textView[index].setText(Integer.toString(index_num));
            index_num++;

            if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                if(index_num == 32){
                    index_num = 1;
                }
            }else if(month == 2){
                if((((year%4==0) && (year%100!=0)) || (year%400==0))){
                    if(index_num == 30){
                        index_num = 1;
                    }
                }else{
                    if(index_num == 29){
                        index_num = 1;
                    }
                }
            }else{
                if(index_num == 31){
                    index_num = 1;
                }
            }
        }


        for(int i = 0; i < 42; i++){ // 색 지정
            final int index_color;
            index_color = i;

            // 이 부분 변경
            if((Integer.parseInt(textView[index_color].getText().toString()) == 1) && color_set_back == 0){
                color_set_back = 1;
            }else if((Integer.parseInt(textView[index_color].getText().toString()) == 1) && color_set_back == 1){
                color_set_back = 0;
            }

            if (color_set_back == 0) {
                if (index_color % 7 == 0) {
                    textView[index_color].setTextColor(Color.parseColor("#80FF0000"));
                } else {
                    textView[index_color].setTextColor(Color.parseColor("#80FFFFFF"));
                }
            } else if (color_set_back == 1) {
                if (index_color % 7 == 0) {
                    textView[index_color].setTextColor(Color.parseColor("#FF0000"));
                } else {
                    textView[index_color].setTextColor(Color.parseColor("#FFFFFF"));
                }
            }

            if((month ==  (calendar.get(Calendar.MONTH) + 1)) && (Integer.parseInt(textView[index_color].getText().toString()) == day) && (calendar.get(Calendar.YEAR)) == year && (textView[index_color].getCurrentTextColor() == Integer.parseInt("-65536") || textView[index_color].getCurrentTextColor() == Integer.parseInt("-1"))){
                button[index_color].setBackgroundResource(R.drawable.border_button_current);
                textView[index_color].setBackgroundResource(R.drawable.border_text_current);
                textView[index_color].setTextColor(Color.parseColor("#0100FF"));
            }else{
                button[index_color].setBackgroundResource(R.drawable.border_button_else);
                textView[index_color].setBackgroundResource(R.drawable.border_text_else);
            }



            button[index_color].setText("");

            if(textView[index_color].getCurrentTextColor() == Integer.parseInt("-1") || textView[index_color].getCurrentTextColor() == Integer.parseInt("-65536") || textView[index_color].getCurrentTextColor() == Integer.parseInt("-16711425")){
                String exist_check_year = Integer.toString(year);
                String exist_check_month = Integer.toString(month);
                String exist_check_day = textView[index_color].getText().toString(); // 색깔도 필요

                String exist_check_file = exist_check_year + "_" + exist_check_month + "_" + exist_check_day + ".txt";

                if(file_exist_check_function(index_color, "/data/data/com.cookandroid.calendar/files/"+exist_check_file)){
                    show_schedule(index_color, exist_check_file);
                }

            }


        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu, menu);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 활성화
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.left);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        //item.setChecked(true);
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM/dd");
        String current_date = simpleDate.format(mDate);


        switch (item.getItemId()){
            case R.id.today:
                /*Log.i(this.getClass().getName() ,current_date);
                //calendarView.setDate (milliTime, true, true);
                try {
                    calendarView.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(current_date).getTime(), true, true);
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/
                makeCalendar();
                return true;

            case R.id.see:
                Intent intent = new Intent(MainActivity.this, WeekCalendar.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.next_month:
                makeCalendar_next();
                return true;

            case R.id.month:
                return true;

            case R.id.week:
                Intent intent_two = new Intent(MainActivity.this, WeekCalendar.class);
                startActivity(intent_two);
                finish();
                return true;

            case android.R.id.home:
                Log.i("click", "ok");
                makeCalendar_back();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }



    /*
    *         <TimePicker
            android:id="@+id/timePicker1"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />*/
}
