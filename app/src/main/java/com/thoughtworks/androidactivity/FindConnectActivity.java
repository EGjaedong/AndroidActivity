package com.thoughtworks.androidactivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_PHONE_CONNECT && resultCode == RESULT_OK) {
            assert data != null;
            Uri connectUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME ,ContactsContract.CommonDataKinds.Phone.NUMBER};
            assert connectUri != null;
            Cursor cursor = getContentResolver().query(connectUri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String name = cursor.getString(nameIndex);
                String number = cursor.getString(numberIndex);
            }
        }
    }
}
