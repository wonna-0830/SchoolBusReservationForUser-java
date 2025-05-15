package com.example.userr_bus;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;


public class routechoose extends AppCompatActivity {

    private FloatingActionButton fab;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private FirebaseUser mFirebaseUser;
    private TextView scholl_num; // 이메일을 표시할 TextView

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routechoose);

        // Firebase 인증 객체 초기화
        mFirebaseAuth = FirebaseAuth.getInstance();

        // SharedPreferences에서 이메일 정보 가져오기
        SharedPreferences sharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("userEmail", "No Email Found");

        // 이메일을 TextView에 설정
        TextView School_num = findViewById(R.id.scholl_num);
        School_num.setText(userEmail);

        // FloatingActionButton 초기화 및 클릭 리스너 설정
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

        // 이동 버튼들 초기화 및 클릭 리스너 설정
        Button moveButton=findViewById(R.id.gyonea);
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), gyonea.class);
                startActivity(intent);
            }
        });

        Button moveButton2=findViewById(R.id.hayang);
        moveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(getApplicationContext(), hayang.class);
                startActivity(intent2);
            }
        });

        Button moveButton3=findViewById(R.id.ansim);
        moveButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(getApplicationContext(), ansimstation.class);
                startActivity(intent3);
            }
        });

        Button moveButton4=findViewById(R.id.sawel);
        moveButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4=new Intent(getApplicationContext(), sawel.class);
                startActivity(intent4);
            }
        });

        Button moveButton5=findViewById(R.id.sawel_ansim);
        moveButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5=new Intent(getApplicationContext(), ansim_sawel.class);
                startActivity(intent5);
            }
        });

        Button moveButton6=findViewById(R.id.btn_back);
        moveButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6=new Intent(getApplicationContext(), login.class);
                startActivity(intent6);
            }
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    finishAffinity(); // 앱 종료
                    return;
                }

                doubleBackToExitPressedOnce = true;
                Toast.makeText(routechoose.this, "뒤로가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
            }
        });
    }

    // 팝업 메뉴 표시하는 메소드
    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FirebaseAuth myAuth = FirebaseAuth.getInstance();
                int itemId = item.getItemId();
                if (itemId == R.id.menu_item_1) {
                    // 원하는 웹사이트로 이동하는 Intent 생성
                    String url = "https://www.cu.ac.kr/life/welfare/schoolbus"; // 여기에 이동하려는 웹사이트 주소 입력
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item_2) {
                    startActivity(new Intent(routechoose.this, selectbuslist.class));
                    finish();
                    return true;
                } else if (itemId == R.id.menu_item_3) {
                    // 로그인 화면으로 이동
                    if (myAuth != null){
                        myAuth.signOut(); //로그아웃 구현
                        startActivity(new Intent(routechoose.this, login.class));
                        finish();
                        return true;
                    }
                    else { //로그아웃 실패시 토스트 메세지 알림
                        Toast.makeText(getApplicationContext(), "로그아웃에 실패했습니다", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

        popupMenu.show();
    }
}
