package com.cookandroid.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeekCalendar extends AppCompatActivity {

    //날짜
    Integer textViewId_Week_Day[] = { R.id.week_day, R.id.week_day2, R.id.week_day3, R.id.week_day4, R.id.week_day5, R.id.week_day6, R.id.week_day7};

    //일정 입력
    Integer buttonId_Week_schedule[] = {R.id.week_btn_Sun1, R.id.week_btn_Mon1, R.id.week_btn_Tues1, R.id.week_btn_Wed1, R.id.week_btn_Thur1, R.id.week_btn_Fri1, R.id.week_btn_Sat1,
            R.id.week_btn_Sun2, R.id.week_btn_Mon2, R.id.week_btn_Tues2, R.id.week_btn_Wed2, R.id.week_btn_Thur2, R.id.week_btn_Fri2, R.id.week_btn_Sat2,
            R.id.week_btn_Sun3, R.id.week_btn_Mon3, R.id.week_btn_Tues3, R.id.week_btn_Wed3, R.id.week_btn_Thur3, R.id.week_btn_Fri3, R.id.week_btn_Sat3,
            R.id.week_btn_Sun4, R.id.week_btn_Mon4, R.id.week_btn_Tues4, R.id.week_btn_Wed4, R.id.week_btn_Thur4, R.id.week_btn_Fri4, R.id.week_btn_Sat4,
            R.id.week_btn_Sun5, R.id.week_btn_Mon5, R.id.week_btn_Tues5, R.id.week_btn_Wed5, R.id.week_btn_Thur5, R.id.week_btn_Fri5, R.id.week_btn_Sat5,
            R.id.week_btn_Sun6, R.id.week_btn_Mon6, R.id.week_btn_Tues6, R.id.week_btn_Wed6, R.id.week_btn_Thur6, R.id.week_btn_Fri6, R.id.week_btn_Sat6,
            R.id.week_btn_Sun7, R.id.week_btn_Mon7, R.id.week_btn_Tues7, R.id.week_btn_Wed7, R.id.week_btn_Thur7, R.id.week_btn_Fri7, R.id.week_btn_Sat7,
            R.id.week_btn_Sun8, R.id.week_btn_Mon8, R.id.week_btn_Tues8, R.id.week_btn_Wed8, R.id.week_btn_Thur8, R.id.week_btn_Fri8, R.id.week_btn_Sat8,
            R.id.week_btn_Sun9, R.id.week_btn_Mon9, R.id.week_btn_Tues9, R.id.week_btn_Wed9, R.id.week_btn_Thur9, R.id.week_btn_Fri9, R.id.week_btn_Sat9,
            R.id.week_btn_Sun10, R.id.week_btn_Mon10, R.id.week_btn_Tues10, R.id.week_btn_Wed10, R.id.week_btn_Thur10, R.id.week_btn_Fri10, R.id.week_btn_Sat10,
            R.id.week_btn_Sun11, R.id.week_btn_Mon11, R.id.week_btn_Tues11, R.id.week_btn_Wed11, R.id.week_btn_Thur11, R.id.week_btn_Fri11, R.id.week_btn_Sat11,
            R.id.week_btn_Sun12, R.id.week_btn_Mon12, R.id.week_btn_Tues12, R.id.week_btn_Wed12, R.id.week_btn_Thur12, R.id.week_btn_Fri12, R.id.week_btn_Sat12,
            R.id.week_btn_Sun13, R.id.week_btn_Mon13, R.id.week_btn_Tues13, R.id.week_btn_Wed13, R.id.week_btn_Thur13, R.id.week_btn_Fri13, R.id.week_btn_Sat13,
            R.id.week_btn_Sun14, R.id.week_btn_Mon14, R.id.week_btn_Tues14, R.id.week_btn_Wed14, R.id.week_btn_Thur14, R.id.week_btn_Fri14, R.id.week_btn_Sat14,
            R.id.week_btn_Sun15, R.id.week_btn_Mon15, R.id.week_btn_Tues15, R.id.week_btn_Wed15, R.id.week_btn_Thur15, R.id.week_btn_Fri15, R.id.week_btn_Sat15,
            R.id.week_btn_Sun16, R.id.week_btn_Mon16, R.id.week_btn_Tues16, R.id.week_btn_Wed16, R.id.week_btn_Thur16, R.id.week_btn_Fri16, R.id.week_btn_Sat16,
            R.id.week_btn_Sun17, R.id.week_btn_Mon17, R.id.week_btn_Tues17, R.id.week_btn_Wed17, R.id.week_btn_Thur17, R.id.week_btn_Fri17, R.id.week_btn_Sat17,
            R.id.week_btn_Sun18, R.id.week_btn_Mon18, R.id.week_btn_Tues18, R.id.week_btn_Wed18, R.id.week_btn_Thur18, R.id.week_btn_Fri18, R.id.week_btn_Sat18,
            R.id.week_btn_Sun19, R.id.week_btn_Mon19, R.id.week_btn_Tues19, R.id.week_btn_Wed19, R.id.week_btn_Thur19, R.id.week_btn_Fri19, R.id.week_btn_Sat19,
            R.id.week_btn_Sun20, R.id.week_btn_Mon20, R.id.week_btn_Tues20, R.id.week_btn_Wed20, R.id.week_btn_Thur20, R.id.week_btn_Fri20, R.id.week_btn_Sat20,
            R.id.week_btn_Sun21, R.id.week_btn_Mon21, R.id.week_btn_Tues21, R.id.week_btn_Wed21, R.id.week_btn_Thur21, R.id.week_btn_Fri21, R.id.week_btn_Sat21,
            R.id.week_btn_Sun22, R.id.week_btn_Mon22, R.id.week_btn_Tues22, R.id.week_btn_Wed22, R.id.week_btn_Thur22, R.id.week_btn_Fri22, R.id.week_btn_Sat22,
            R.id.week_btn_Sun23, R.id.week_btn_Mon23, R.id.week_btn_Tues23, R.id.week_btn_Wed23, R.id.week_btn_Thur23, R.id.week_btn_Fri23, R.id.week_btn_Sat23,
            R.id.week_btn_Sun24, R.id.week_btn_Mon24, R.id.week_btn_Tues24, R.id.week_btn_Wed24, R.id.week_btn_Thur24, R.id.week_btn_Fri24, R.id.week_btn_Sat24,
    };

    //시간
    Integer textViewId_Week_time[] = {R.id.week_time, R.id.week_time2, R.id.week_time3, R.id.week_time4, R.id.week_time5, R.id.week_time6,
            R.id.week_time7, R.id.week_time8, R.id.week_time9, R.id.week_time10, R.id.week_time11, R.id.week_time12, R.id.week_time13,
            R.id.week_time14, R.id.week_time15, R.id.week_time16, R.id.week_time17, R.id.week_time18, R.id.week_time19, R.id.week_time20,
            R.id.week_time21, R.id.week_time22, R.id.week_time23, R.id.week_time24
    };


    CalendarView calendarView_week;
    int year_week = 0;
    int day_week = 0;
    int month_week = 0;
    int day_of_week_week = 0;
    Calendar calendar = Calendar.getInstance();
    ActionBar actionBar_week;
    Button button_Week_schedule[] = new Button[168];
    TextView textView_week_day[] = new TextView[7];
    int first_week_ok_next_week = 0; //첫째 주 가지고 한 번 왔는지
    int first_week_ok_pre_week = 0; //첫째 주 가지고 한 번 왔는지
    int change_next_month_week_calendar = 0; // 다음달로 바뀌었는지
    int change_pre_month_week_calendar = 0;
    EditText schedule_name_et_week, schedule_start_time_et_week, schedule_end_time_et_week, schedule_place_et_week, schedule_memo_et_week;
    View dialogView_week;
    String schedule_name_week = "", schedule_start_time_week = "", schedule_end_time_week = "", schedule_place_week = "", schedule_memo_week = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_calendar);
        actionBar_week = getSupportActionBar();

        for (int i = 0; i < textView_week_day.length; i++) { // 날짜 id 설정
            final int index;
            index = i;
            textView_week_day[index] = (TextView) findViewById(textViewId_Week_Day[index]);
        }

        for(int i = 0; i < buttonId_Week_schedule.length; i++){
            final int index;
            index = i;
            button_Week_schedule[index] = (Button) findViewById(buttonId_Week_schedule[index]);
        }


        for(int i = 0; i < buttonId_Week_schedule.length; i++){

            final int index;
            index = i;
            button_Week_schedule[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int current_text_color_week = textView_week_day[index % 7].getCurrentTextColor();

                    if (current_text_color_week == Integer.parseInt("-1") || current_text_color_week == Integer.parseInt("-16711425") || current_text_color_week == Integer.parseInt("-65536")) {

                        System.out.println(current_text_color_week);

                        dialogView_week = (View) View.inflate(WeekCalendar.this, R.layout.input_schedule, null);

                        String click_day_week = textView_week_day[index % 7].getText().toString();
                        String click_month_week = Integer.toString(month_week);
                        String click_year_week = Integer.toString(year_week);

                        final String file_name_week = click_year_week + "_" + click_month_week + "_" + click_day_week + ".txt";


                        schedule_name_et_week = dialogView_week.findViewById(R.id.schedule_name);
                        schedule_start_time_et_week = dialogView_week.findViewById(R.id.schedule_start_time);
                        schedule_end_time_et_week = dialogView_week.findViewById(R.id.schedule_end_time);
                        schedule_place_et_week = dialogView_week.findViewById(R.id.schedule_place);
                        schedule_memo_et_week = dialogView_week.findViewById(R.id.schedule_memo);

                        AlertDialog.Builder dlg = new AlertDialog.Builder(WeekCalendar.this);
                        dlg.setTitle("주간 일정 등록");
                        dlg.setView(dialogView_week);


                        dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //if(schedule_name_et.getText().toString().equals("") || schedule_start_time_et.getText().toString().equals("") || schedule_end_time_et.getText().toString().equals("") || schedule_place_et.getText().toString().equals("") || schedule_memo_et.getText().toString().equals("")){
                                //    System.out.println("입력이 필요합니다. ");
                                //}else {
                                schedule_name_week = schedule_name_et_week.getText().toString();
                                schedule_start_time_week = schedule_start_time_et_week.getText().toString();
                                schedule_end_time_week = schedule_end_time_et_week.getText().toString();
                                schedule_place_week = schedule_place_et_week.getText().toString();
                                schedule_memo_week = schedule_memo_et_week.getText().toString();
                                //}

                                try {
                                    FileOutputStream fileOutputStream = openFileOutput(file_name_week, Context.MODE_PRIVATE);

                                    String name = "약속 제목 : " + schedule_name_week;
                                    String start_time = "시작 시간 : " + schedule_start_time_week;
                                    String end_time = "끝 시간 : " + schedule_end_time_week;
                                    String place = "장소 : " + schedule_place_week;
                                    String memo = "메모 : " + schedule_memo_week;
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

                                // 배경 변경
                                //makeCalendar_week();
                                //if(Integer.parseInt(schedule_end_time_week) - Integer.parseInt(schedule_start_time_week) < 0){
                                //    Toast.makeText(WeekCalendar.this, "잘못된 시간선택입니다.", Toast.LENGTH_SHORT).show();
                                //}else{
                                show_schedule_week(index%7, file_name_week);
                                //}


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
                                    deleteFile(file_name_week);

                                    for(int ij = 0; ij < 24; ij++){
                                        final int index_button;
                                        index_button = ij;
                                        button_Week_schedule[((index_button+1) * 7) + (index%7) - 7].setBackgroundResource(R.drawable.border_button_week_calendar);
                                    }

                                    //button_Week_schedule[((start_time_week+index_two_week) * 7) + today - 7].setBackgroundResource(R.drawable.button_week_calendar);
                                    //show_schedule_week(index%7, file_name_week);

                                    //여기나
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                // 여기에 동작 넣기
                                //makeCalendar_week();

                            }
                        });
                        dlg.show();

                    }else{
                        Toast.makeText(WeekCalendar.this, "잘못된 선택입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        for(int i = 0; i < buttonId_Week_schedule.length; i++){

            final int index;
            index = i;

            button_Week_schedule[index].setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int current_text_color_week = textView_week_day[index % 7].getCurrentTextColor();

                    if (current_text_color_week == Integer.parseInt("-1") || current_text_color_week == Integer.parseInt("-65536") || current_text_color_week == Integer.parseInt("-16711425")) {

                        String click_day_week = textView_week_day[index % 7].getText().toString();
                        String click_month_week = Integer.toString(month_week);
                        String click_year_week = Integer.toString(year_week);

                        final String file_name_week = click_year_week + "_" + click_month_week + "_" + click_day_week + ".txt";

                        try {
                            FileInputStream fileInputStream = openFileInput(file_name_week);
                            byte[] txt = new byte[2048];
                            fileInputStream.read(txt);
                            String str = new String(txt);

                            AlertDialog.Builder dlg = new AlertDialog.Builder(WeekCalendar.this);
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




        makeCalendar_week();

    }

    public void show_schedule_week(int today, String exist_check_file){
        try {
            FileInputStream inFs = openFileInput(exist_check_file);
            byte[] txt = new byte[1024];
            inFs.read(txt);
            String str = new String(txt);
            String[] schedule_name_start_time = str.split(":");
            String[] schedule_name = schedule_name_start_time[1].split("\n");
            String[] schedule_start_time = schedule_name_start_time[2].split("\n"); // 이것도 첫번째
            String[] schedule_end_time = schedule_name_start_time[3].split("\n");

            int start_time_week = 0, end_time_week = 0;
            schedule_start_time[0] = schedule_start_time[0].replace(" ", "");
            schedule_end_time[0] = schedule_end_time[0].replace(" ","");
            if(schedule_start_time[0].equals("")){
                if(schedule_end_time[0].equals("")) {
                    start_time_week = 1;
                    end_time_week = 24;
                }else{
                    end_time_week = Integer.parseInt(schedule_end_time[0])+1;
                    start_time_week = end_time_week;
                }
            }else{
                start_time_week = Integer.parseInt(schedule_start_time[0])+1;
                if(schedule_end_time[0].equals("")){
                    end_time_week = start_time_week;
                }else{
                    end_time_week = Integer.parseInt(schedule_end_time[0])+1;
                }
            }

            for(int j = 0; j <= end_time_week - start_time_week; j++){
                final int index_two_week;
                index_two_week = j;
                //button_Week_schedule[((start_time_week+index_two_week) * 7) + today - 7].setBackgroundColor(Color.parseColor("#1DDB16"));
                button_Week_schedule[((start_time_week+index_two_week) * 7) + today - 7].setBackgroundResource(R.drawable.button_week_calendar);
            }

            /*for(int i = 0; i < buttonId_Week_schedule.length; i++){
                final int index_week;
                index_week = i;
                for(int j = 0; j <= end_time_week - start_time_week; j++){
                    final int index_two_week;
                    index_two_week = j;
                    button_Week_schedule[((start_time_week+index_two_week) * 7) + today - 7].setBackgroundColor(Color.parseColor("#1DDB16"));
                }
            }*/


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public boolean file_exist_check_function_week(String exist_check_file){

        File file = new File(exist_check_file);
        if(file.exists()){
            //button_Week_schedule[today].setText("파일이 존재합니다.");
            //button_Week_schedule[today].setTextColor(Color.parseColor("#FFFFFF"));
            return true;
        }else{
            //button_Week_schedule[today].setText("");
            return false;
        }
    }

    public void makeCalendar_week(){
        year_week = calendar.get(Calendar.YEAR);
        month_week = (calendar.get(Calendar.MONTH) + 1);
        day_week = calendar.get(Calendar.DAY_OF_MONTH);
        day_of_week_week = calendar.get(Calendar.DAY_OF_WEEK); // 1~7 반환
        int week_first_day = day_week;
        int pre_day = 0, next_day = 0;
        int color = 0;

        for(int i = 1; i < day_of_week_week; i++){
            week_first_day--;
            if(week_first_day < 1){
                if(month_week == 1 || month_week == 2 || month_week == 4 || month_week == 6 || month_week == 8 || month_week == 9 || month_week == 11){
                    week_first_day = 31;
                }else if(month_week == 3){
                    week_first_day = 29;
                }else{
                    week_first_day = 30;
                }
                color = 1;
                pre_day = 1;
            }
        }

        for(int j = 0; j < 7; j++){
            final int index;
            index = j;
            actionBar_week.setTitle(Integer.toString(month_week)+"월");
            actionBar_week.setSubtitle(Integer.toString(year_week)+"년");
            if(month_week == 1 || month_week == 3 || month_week == 5 || month_week == 7 || month_week == 8 || month_week == 10 || month_week == 12){
                if(week_first_day == 32){ // 이전에서 1로 넘어온 경우(pre_day = 1, color = 1), 현재 달에서 다음 달로 넘어가는 경우(color = 1, next_day = 1;)
                    week_first_day = 1;
                    if(pre_day == 1){
                        color = 0;
                    }else{
                        next_day = 1;
                        color = 1;
                    }
                }
            }else if(month_week == 2){
                if(week_first_day == 30){
                    week_first_day = 1;
                    if(pre_day == 1){
                        color = 0;
                    }else{
                        next_day = 1;
                        color = 1;
                    }
                }
            }else{
                if(week_first_day == 31){
                    week_first_day = 1;
                    if(pre_day == 1){
                        color = 0;
                    }else{
                        next_day = 1;
                        color = 1;
                    }
                }
            }
            textView_week_day[index].setText(Integer.toString(week_first_day));
            if(color == 1 && (pre_day == 1 || next_day == 1)){
                if(index == 0){
                    textView_week_day[index].setTextColor(Color.parseColor("#80FF0000"));
                }else{
                    textView_week_day[index].setTextColor(Color.parseColor("#80FFFFFF"));
                }

            }
            if(index == 0){
                textView_week_day[index].setTextColor(Color.parseColor("#FF0000"));
            }else{
                textView_week_day[index].setTextColor(Color.parseColor("#FFFFFF"));
            }
            week_first_day++;

            if((calendar.get(Calendar.YEAR) == year_week) && ((calendar.get(Calendar.MONTH) + 1) == month_week) && (Integer.parseInt(textView_week_day[index].getText().toString()) == day_week)){ // 31일 정도면 위험 이 부분 다시 생각 필요
                textView_week_day[index].setBackgroundResource(R.drawable.border_text_current);
                textView_week_day[index].setTextColor(Color.parseColor("#0100FF"));
            }



            if(textView_week_day[index].getCurrentTextColor() == Integer.parseInt("-1") || textView_week_day[index].getCurrentTextColor() == Integer.parseInt("-65536") || textView_week_day[index].getCurrentTextColor() == Integer.parseInt("-16711425")){
                String exist_check_year = Integer.toString(year_week);
                String exist_check_month = Integer.toString(month_week);
                String exist_check_day = textView_week_day[index].getText().toString(); // 색깔도 필요

                String exist_check_file =  exist_check_year + "_" + exist_check_month + "_" + exist_check_day + ".txt";

                if(file_exist_check_function_week("/data/data/com.cookandroid.calendar/files/"+exist_check_file)){
                    /*for(int i = 0; i < buttonId_Week_schedule.length; i++){
                        final int index_button;
                        index_button = i;
                        button_Week_schedule[index_button].setBackgroundResource(R.drawable.border_button_week_calendar);
                    }*/
                    show_schedule_week(index, exist_check_file);
                }
            }
        }




    }

    public void makeCalendar_next_week(){
        int next_week_first_day = 0;
        int text_color_next_week = 0;
        int plus_month = 0; // 달 바뀔건지

        next_week_first_day = (Integer.parseInt(textView_week_day[6].getText().toString())) + 1;
        if((next_week_first_day-1) < 7 && first_week_ok_next_week == 0){ // 만약 달이 변경되면,,, (1~6이 일요일이면) 한 번더 그대로 가지고 오고 달만 ++ 해주기
            for(int i = 0; i < 7; i++){
                final int index;
                index = i;
                if(index > (7 - next_week_first_day)){ // 글자색 지정 이번달만 진하게
                    if(index == 0){
                        textView_week_day[index].setTextColor(Color.parseColor("#FF0000"));
                    }else{
                        textView_week_day[index].setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }else{
                    if(index == 0){
                        textView_week_day[index].setTextColor(Color.parseColor("#80FF0000"));
                    }else{
                        textView_week_day[index].setTextColor(Color.parseColor("#80FFFFFF"));
                    }
                }
                textView_week_day[index].setText(textView_week_day[index].getText()); // 그대로 대입
                if((calendar.get(Calendar.YEAR) == year_week) && ((calendar.get(Calendar.MONTH) + 1) == month_week) && (Integer.parseInt(textView_week_day[index].getText().toString()) == day_week)){
                    textView_week_day[index].setBackgroundResource(R.drawable.border_text_current);
                    textView_week_day[index].setTextColor(Color.parseColor("#0100FF"));
                }else{
                    textView_week_day[index].setBackgroundResource(R.drawable.border_text_else);
                }

            }
            plus_month++; // 달 ++
            first_week_ok_pre_week = 0;
            first_week_ok_next_week = 1;
        }else{ // 달이 변경되지 않을 때
            for (int i = 0; i < 7; i++){
                final int index;
                index = i;
                if(month_week == 1 || month_week == 3 || month_week == 5 || month_week == 7 || month_week == 8 || month_week == 10 || month_week == 12){
                    if(next_week_first_day == 32){ //날짜 세팅
                        next_week_first_day = 1;
                        text_color_next_week = 1;
                        first_week_ok_next_week = 0;
                    }
                }else if(month_week == 2){ // 날짜 세팅
                    if(next_week_first_day == 30){
                        next_week_first_day = 1;
                        text_color_next_week = 1;
                        first_week_ok_next_week = 0;
                    }
                }else{
                    if(next_week_first_day == 31){ // 날짜 세팅
                        next_week_first_day = 1;
                        text_color_next_week = 1;
                        first_week_ok_next_week = 0;
                    }
                }
                if(index == 0 && text_color_next_week == 1){
                    text_color_next_week = 0;
                    plus_month = 1;
                }
                textView_week_day[index].setText(Integer.toString(next_week_first_day)); // 날짜 넣어주기
                if(text_color_next_week == 0){ // 색상 변경
                    if(index == 0){
                        textView_week_day[index].setTextColor(Color.parseColor("#FF0000"));
                    }else{
                        textView_week_day[index].setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }else{
                    if(index == 0){
                        textView_week_day[index].setTextColor(Color.parseColor("#80FF0000"));
                    }else{
                        textView_week_day[index].setTextColor(Color.parseColor("#80FFFFFF"));
                    }
                }
                if((calendar.get(Calendar.YEAR) == year_week) && ((calendar.get(Calendar.MONTH) + 1) == month_week) && (Integer.parseInt(textView_week_day[index].getText().toString()) == day_week)){
                    textView_week_day[index].setBackgroundResource(R.drawable.border_text_current);
                    textView_week_day[index].setTextColor(Color.parseColor("#0100FF"));
                }else{
                    textView_week_day[index].setBackgroundResource(R.drawable.border_text_else);
                }
                next_week_first_day++; // 날짜++
            }
            first_week_ok_pre_week = 1;
        }
        if(plus_month == 1){ // 만약 달이 증가되면
            month_week++; // 달 값 증가
        }
        if(month_week == 13){ // 13월이되면 달을 1로 변경하고 년도 1년 증가
            month_week = 1;
            year_week++;
            actionBar_week.setSubtitle(Integer.toString(year_week)+"년");
        }

        for(int i = 0; i < buttonId_Week_schedule.length; i++){
            final int index_button;
            index_button = i;
            button_Week_schedule[index_button].setBackgroundResource(R.drawable.border_button_week_calendar);
        }

        for(int k = 0; k < 7; k++){
            final int index_k;
            index_k = k;
            if(textView_week_day[index_k].getCurrentTextColor() == Integer.parseInt("-1") || textView_week_day[index_k].getCurrentTextColor() == Integer.parseInt("-65536") || textView_week_day[index_k].getCurrentTextColor() == Integer.parseInt("-16711425")){
                String exist_check_year = Integer.toString(year_week);
                String exist_check_month = Integer.toString(month_week);
                String exist_check_day = textView_week_day[index_k].getText().toString(); // 색깔도 필요

                String exist_check_file =  exist_check_year + "_" + exist_check_month + "_" + exist_check_day + ".txt";




                if(file_exist_check_function_week("/data/data/com.cookandroid.calendar/files/"+exist_check_file)){

                    show_schedule_week(index_k, exist_check_file);
                }
            }
        }

        actionBar_week.setTitle(Integer.toString(month_week)+"월");
    }

    public void makeCalendar_back_week(){

        int pre_week_first_day = 0;
        int text_color_pre_week = 0;
        int minus_month = 0; // 달 바뀔건지

        pre_week_first_day = (Integer.parseInt(textView_week_day[0].getText().toString())) - 1;

        if(pre_week_first_day+1 >= 25 && first_week_ok_pre_week == 0){ // 월요일이 25~31일이면 그대로 가지고 오기
            for (int i = 0; i < 7; i++){
                final int index;
                index = i;
                textView_week_day[index].setText(textView_week_day[index].getText().toString()); // 그대로 대입
                if(index < (7 - (Integer.parseInt(textView_week_day[6].getText().toString())))){ // 이번달이면 진하게, 다음달이었으면 연하게
                    if(index == 0){
                        textView_week_day[index].setTextColor(Color.parseColor("#FF0000"));
                    }else{
                        textView_week_day[index].setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }else{
                    if(index == 0){
                        textView_week_day[index].setTextColor(Color.parseColor("#80FF0000"));
                    }else{
                        textView_week_day[index].setTextColor(Color.parseColor("#80FFFFFF"));
                    }
                }
                if((calendar.get(Calendar.YEAR) == year_week) && ((calendar.get(Calendar.MONTH) + 1) == month_week) && (Integer.parseInt(textView_week_day[index].getText().toString()) == day_week)){
                    textView_week_day[index].setBackgroundResource(R.drawable.border_text_current);
                    textView_week_day[index].setTextColor(Color.parseColor("#0100FF"));
                }else{
                    textView_week_day[index].setBackgroundResource(R.drawable.border_text_else);
                }
            }
            first_week_ok_pre_week = 1;
            first_week_ok_next_week = 0;
            month_week--; // 달 줄이기
            //first_week_ok_pre_week = 1;
        }else{
            for (int i = 6; i >= 0; i--){
                final int index;
                index = i;
                if(pre_week_first_day == 0){ // 날짜가 0일일때 세팅
                    if(month_week == 1 || month_week == 2 || month_week == 4 || month_week == 6 || month_week == 8 || month_week == 9 || month_week == 11){
                        pre_week_first_day = 31;
                    }else if(month_week == 3){
                        pre_week_first_day = 29;
                    }else{
                        pre_week_first_day = 30;
                    }
                    first_week_ok_pre_week = 0; // 그대로 쓸 건지 아니면 달 바꿀건지
                    text_color_pre_week = 1; // 1이면 연함

                    if(index == 6){
                        text_color_pre_week = 0;
                        month_week--;
                    }
                }

                if(text_color_pre_week == 0){ // 글자색
                    if(index == 0){
                        textView_week_day[index].setTextColor(Color.parseColor("#FF0000"));
                    }else{
                        textView_week_day[index].setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }else{
                    if(index == 0){
                        textView_week_day[index].setTextColor(Color.parseColor("#80FF0000"));
                    }else{
                        textView_week_day[index].setTextColor(Color.parseColor("#80FFFFFF"));
                    }
                }
                textView_week_day[index].setText(Integer.toString(pre_week_first_day));// 날짜 입력
                if((calendar.get(Calendar.YEAR) == year_week) && ((calendar.get(Calendar.MONTH) + 1) == month_week) && (Integer.parseInt(textView_week_day[index].getText().toString()) == day_week)){
                    textView_week_day[index].setBackgroundResource(R.drawable.border_text_current);
                    textView_week_day[index].setTextColor(Color.parseColor("#0100FF"));
                }else{
                    textView_week_day[index].setBackgroundResource(R.drawable.border_text_else);
                }
                pre_week_first_day--; // 날짜 --
            }
            first_week_ok_next_week = 1;
        }
        if(month_week < 1){ // 0월이면 년도 -하고 달도 12월로 바꾸기
            year_week--;
            month_week = 12;
            actionBar_week.setSubtitle(Integer.toString(year_week)+"년");
        }
        actionBar_week.setTitle(Integer.toString(month_week)+"월");

        for(int i = 0; i < buttonId_Week_schedule.length; i++){
            final int index_button;
            index_button = i;
            button_Week_schedule[index_button].setBackgroundResource(R.drawable.border_button_week_calendar);
        }

        for(int k = 0; k < 7; k++){
            final int index_k;
            index_k = k;
            if(textView_week_day[index_k].getCurrentTextColor() == Integer.parseInt("-1") || textView_week_day[index_k].getCurrentTextColor() == Integer.parseInt("-65536") || textView_week_day[index_k].getCurrentTextColor() == Integer.parseInt("-16711425")){
                String exist_check_year = Integer.toString(year_week);
                String exist_check_month = Integer.toString(month_week);
                String exist_check_day = textView_week_day[index_k].getText().toString(); // 색깔도 필요

                String exist_check_file =  exist_check_year + "_" + exist_check_month + "_" + exist_check_day + ".txt";

                if(file_exist_check_function_week("/data/data/com.cookandroid.calendar/files/"+exist_check_file)){
                    show_schedule_week(index_k, exist_check_file);
                }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu, menu);

        actionBar_week.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 활성화
        actionBar_week.setHomeButtonEnabled(true);
        actionBar_week.setHomeAsUpIndicator(R.drawable.left);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.today:
                makeCalendar_week();
                return true;

            case R.id.see:
                Intent intent = new Intent(WeekCalendar.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.next_month:
                makeCalendar_next_week();
                return true;

            case android.R.id.home:
                Log.i("click", "ok");
                makeCalendar_back_week();
                return true;

            case R.id.month:
                Intent intent_two = new Intent(WeekCalendar.this, MainActivity.class);
                startActivity(intent_two);
                finish();
                return true;

            case R.id.week:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
