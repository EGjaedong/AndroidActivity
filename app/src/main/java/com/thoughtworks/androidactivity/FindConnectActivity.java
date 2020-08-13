package com.thoughtworks.androidactivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FindConnectActivity extends AppCompatActivity {

    private static final int REQUEST_SELECT_PHONE_CONNECT = 1;

    private View.OnClickListener jumpToConnect = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_PICK)
                    .setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivityForResult(intent, REQUEST_SELECT_PHONE_CONNECT);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_connect_activity);

        Button button = findViewById(R.id.btn_find_connect);
        button.setOnClickListener(jumpToConnect);
    }
}
