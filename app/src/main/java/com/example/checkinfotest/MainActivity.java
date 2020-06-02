package com.example.checkinfotest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    JSONObject object;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==1001){
                String userid = null;
                try {
                    userid = object.getString("userid");
                    String name = object.getString("xm");
                    String msg1 = object.getString("msg");
                    Toast.makeText(MainActivity.this,"学号："+userid+",姓名:"+name+",状态"+msg1,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        object = new JSONObject();
    }
    public void aaa(View view){
        final OkHttpClient client = new OkHttpClient();
        final RequestBody body = new FormBody.Builder()
                .add("param","3qon253ji68w35twsk2cnevm2d93s13r9yt13n2gae2g8q0k3t2ez62g8c4h3owlhm3pgi9v2lkld72mr1xc2gu2io3qobhc3noias3hr1v93oauba3n315r3iy40r3hr45j3mipo43k4nom3oarzu2tw9673owm9d2tw9ye2qxg9x2mr50i2qxfi62g8tvl3hr7962mr1x92riv3f3t2adn3gjb022kyrvj2nckns3kpu1f2ui5rf2mr6jt2ny7xk2qbobn2ojp2w2m5enc3qnobi3n3lzv3oavup2g8ias3lxhqy2mqu913oauba3n315h2ui0cz2mr2p32p56zz00004m")
                .add("param2", "d2487cd5457d37cdcaad9eebf3a158a8")
                .add("appinfo", "android2.5.107")
                .add("token", "000000")
                .build();
        final Request request = new Request.Builder()
                .url("http://www.xiqueer.com:8080/manager//wap/wapController.jsp")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("1212","123123"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    object = new JSONObject(response.body().string());
                    handler.sendEmptyMessage(1001);

//                    String uuid = object.getString("uuid");
//                    String token = object.getString("token");

                } catch (Exception e) {
                    Log.e("1212","22222"+e.getMessage());
                }
            }
        });
    }

}
