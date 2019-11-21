package com.crazy4web.websocket_example_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView output;
    public OkHttpClient okHttpClient;
    TextInputLayout textInputLayout;

    private final class echoWebsocketlistener extends WebSocketListener{
        private static final int Closure_status=1000;

        @Override
        public void onOpen(final WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);

            webSocket.send("Hello, my name is Girik");
            webSocket.send("How are you?");

            webSocket.send("good");


        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {

            output("Receiving " + text);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);

            output("Closing :"+code+""+reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);

            output("error is: "+t.getMessage());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.start);
        output = findViewById(R.id.output);
        okHttpClient = new OkHttpClient();
        textInputLayout = findViewById(R.id.message);

        textInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                start();
            }
        });

    }

    private void start() {

        Request request = new Request.Builder().url("ws://echo.websocket.org").build();
        echoWebsocketlistener listener = new echoWebsocketlistener();
        final WebSocket ws = okHttpClient.newWebSocket(request, listener);
        textInputLayout = findViewById(R.id.message);

         ws.send("hello");



//        okHttpClient.dispatcher().executorService().shutdown();
    }

    private void output(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("output",output.getText().toString() );
                Log.d("txt",txt);
                output.setText(output.getText().toString() + "\n\n" + txt);
            }
        });
    }
    }

