package com.example.userr_bus;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class selectbuslist extends AppCompatActivity {

    private FloatingActionButton fab;
    private static final String TAG = "selectbuslist";

    private FirebaseFirestore mFirestore;
    private RecyclerView.LayoutManager layoutManager;
    private CustomAdapter adapter;
    private ArrayList<Reservation> arrayList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectbuslist);

        mFirestore = FirebaseFirestore.getInstance();

        // 예약 리스트 표시
        displayReservationList();

        // 홈으로 이동하는 버튼
        Button moveButton = findViewById(R.id.home_img);
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), routechoose.class);
                startActivity(intent);
                // 현재 액티비티 종료
                finish();
            }
        });

        // 팝업 메뉴 표시하는 버튼
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

        // 홈으로 이동하는 두 번째 버튼
        Button moveButton2 = findViewById(R.id.btn_home);
        moveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), routechoose.class);
                startActivity(intent2);
                // 현재 액티비티 종료
                finish();
            }
        });
        //뒤로가기 버튼 클릭 시 routechose로 바로 이동
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // `routechoose` 액티비티로 이동하는 인텐트 생성
                Intent intent = new Intent(selectbuslist.this, routechoose.class);
                startActivity(intent);
                // 현재 액티비티 종료
                finish();
            }
        });
    }

    // 예약 리스트 표시하는 메서드(CostomAdapter.java, Reservation.java, item_list.xml과 연결)
    private void displayReservationList() {
        // Firestore에서 데이터 가져오기
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        CollectionReference reservationsRef = db.collection("Reservation");
        // RecyclerView와 CustomAdapter 연결
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //layoutManager 연결
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //ArrayList 초기화 후 진행
        ArrayList<Reservation> arrayList = new ArrayList<>();
        CustomAdapter adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);

        //리스트간 여백 설정하기 (DecorationItem.java 연결)
        DecorationItem itemDecoration = new DecorationItem(9);
        recyclerView.addItemDecoration(itemDecoration);

        //ProgressBar 보여주기
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        //userId 개별 사용자간의 데이터 예약 내역 보여주기
        if(currentUser != null) {
            String userId = currentUser.getUid();
            reservationsRef.whereEqualTo("userId", userId) //사용자 개인별로 예약 내역 보여주기위함
                    .orderBy("reservationDate", Query.Direction.DESCENDING) //쿼리를 이용해서 최근 순(내림차순으로) 정렬
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        arrayList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Reservation reservation = document.toObject(Reservation.class);
                            reservation.setDocumentId(document.getId());
                            arrayList.add(reservation);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //데이터베이스 업로드, 바뀐 걸 확인
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }
                        });

                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }

            });
        }

    }



    // 팝업 메뉴 표시 메서드
    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                FirebaseAuth myAuth = FirebaseAuth.getInstance();
                if (itemId == R.id.menu_item_1) {
                    // 웹사이트 열기
                    String url = "https://www.cu.ac.kr/life/welfare/schoolbus";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.menu_item_2) {
                    // 예약 리스트 다시 불러오기
                    displayReservationList();
                    return true;
                } else if (itemId == R.id.menu_item_3) {
                    // 로그인 화면으로 이동
                    if (myAuth != null){
                        myAuth.signOut(); //로그아웃 구현
                        startActivity(new Intent(selectbuslist.this, login.class));
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
