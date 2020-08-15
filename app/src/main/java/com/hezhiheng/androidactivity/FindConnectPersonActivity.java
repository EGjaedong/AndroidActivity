package com.hezhiheng.androidactivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FindConnectPersonActivity extends AppCompatActivity {

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

        Button buttonToConnect = findViewById(R.id.btn_find_connect);
        buttonToConnect.setOnClickListener(jumpToConnect);

        Button buttonToLifeCycle = findViewById(R.id.btn_to_life_cycle_activity);
        buttonToLifeCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindConnectPersonActivity.this, LifeCycleActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_PHONE_CONNECT && resultCode == RESULT_OK) {
            if (data == null)
                return;
            Uri connectUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
            try (Cursor cursor = getContentResolver().query(connectUri, projection, null, null, null);){
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String name = cursor.getString(nameIndex);
                    String number = cursor.getString(numberIndex);
                    displayConnect(name, number);
                }
            }
            catch (Exception e){
                Log.d(this.getClass().getSimpleName(), "查询结果为空！");
            }
        }
    }

    private void displayConnect(String name, String number) {
        TextView displayTextView = findViewById(R.id.display_connect);
        displayTextView.setText(getString(R.string.connect_name_and_phone_number, name, number));
    }
}
