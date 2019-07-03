package com.example.sampleesp;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showProgress(){
        if(progressDialog==null||!progressDialog.isShowing()){
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("لطفا چند لحظه صبر کنید");
            progressDialog.setTitle("درحال بارگذاری ...");
            progressDialog.show();
        }
    }

    public void hideProgress(){
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.cancel();
            progressDialog=null;
        }
    }
}
