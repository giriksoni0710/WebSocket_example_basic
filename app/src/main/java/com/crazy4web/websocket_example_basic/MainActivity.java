package com.crazy4web.websocket_example_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MainActivity extends AppCompatActivity {

    private Button button, send;
    private TextView output;
    public OkHttpClient okHttpClient;
    public EditText editText;

    private final class echoWebsocketlistener extends WebSocketListener{
        private static final int Closure_status=1000;

        @Override
        public void onOpen(final WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);


            webSocket.send("hey there");

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    if (editText.getText().toString()!=null) {
                        webSocket.send(editText.getText().toString());
                    }

                }
            });

        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {

            output("Receiving :" + text);
            editText.setText("");
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
        editText = findViewById(R.id.edittext);
        send = findViewById(R.id.send);


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

