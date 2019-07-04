package com.example.database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.guru_semiproject.model.MemberModel;
import com.example.guru_semiproject.model.MyMemo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {

    public final static String TBL_ITEM = "ITEM";

    static Database inst;
    private static SharedPreferences sf = null; //저장객체

    private static List<MyMemo> memos = null; //원본데이터

    private Database(){}

    public static Database getInstance(Context context){

        if(memos == null){
            memos = new ArrayList<>();
        }

        if (sf == null){
            sf = context.getSharedPreferences("MEMO", Activity.MODE_PRIVATE);
        }

        if (inst == null){
            inst = new Database();
        }

        return inst;
    }

    //item 선두에 추가
    public void addItem(MyMemo memo){
        memos.add(0, memo);
    }

    //item 획득
    public MyMemo getItem(int index){
        return memos.get(index);
    }

    //item 변경
    public void setItem(int index, MyMemo item){
        memos.set(index, item);
    }

    // item 삭제
    public void removeItem(int index){
        memos.remove(index);
    }

    // items를 SharedPreference에 저장
    public void saveItems(){
        //객체를 문자열(JSON)로 변경
        String itemString = new GsonBuilder().serializeNulls().create().toJson(memos);

        //저장
        SharedPreferences.Editor editor  = sf.edit();
        editor.putString(TBL_ITEM,itemString); //key, value 형식으로 저장
        editor.commit();
    }

    //items 획득
    public List<MyMemo> loadItems(){
        //SharedPreferences의 items정보를 문자열로 획득
        String itemsString = sf.getString(TBL_ITEM, "");
        if (!itemsString.isEmpty()){
            //문자열은 MyItem 배열 형태로 변환
            MyMemo[] itemArray = new Gson().fromJson(itemsString, MyMemo[].class);

            //배열을 ArrayList형태로 변환
            memos = new ArrayList<>(Arrays.asList(itemArray));
        }

        return memos;
    }

    //회원저장
    public void setMember(MemberModel member){
        //Member객체를 JSON형태의 문자열로 변환
        String memberString = new GsonBuilder(). serializeNulls().create().toJson(member);
        Log.d("Database", "memberString=" + memberString);

        //저장
        SharedPreferences.Editor editor  = sf.edit();
        editor.putString(member.getId(), memberString); //key, value 형식으로 저장
        editor.commit();
    }

    public MemberModel getMember(String id){
        MemberModel member = null;

        //id를 이용해 회원정보 획득
        String memberString = sf.getString(id, "");
        if(memberString != null && memberString.length() > 0)
        {
            member = new Gson().fromJson(memberString, MemberModel.class);
        } else{
            Log.e("Database", "member null");
        }

        return member;
    }

    //회원의 비밀번호 체크
    public boolean checkMember(String id, String pwd) {
        boolean isTrue = false;

        if (id.isEmpty() || pwd.isEmpty()){
            return isTrue; //실패
        }
        //회원획득
        MemberModel member = getMember(id);
        if(member == null){
            return isTrue; //실패
        }

        if(member.getPwd().equals(pwd)){
            isTrue = true; //성공
        }

        return isTrue;
    }

}
