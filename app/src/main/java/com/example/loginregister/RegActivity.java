package com.example.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegActivity extends AppCompatActivity
{
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증 처리
    private DatabaseReference mDatebaseRef; //실시간 데이터베이스!
    private EditText mEtEmail, mEtHak; //회원가입 입력필드
    private Button mBtnRegister; //회원가입 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatebaseRef = FirebaseDatabase.getInstance().getReference("example");

        mEtEmail = findViewById(R.id.et_Email);
        mEtHak = findViewById(R.id.et_Hak);
        mBtnRegister = findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //회원가입 처리 시작
                String strEmail = mEtEmail.getText().toString(); //입력받은 문자열을 strName에 할당
                String strHak = mEtHak.getText().toString(); //입력받은 문자열을 strHak에 할당

                //FirebaseAuth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strHak).addOnCompleteListener(RegActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmail(firebaseUser.getEmail());
                            account.setHackbun(strHak);

                            // setValue: database에 insert(삽입) 행위
                            mDatebaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Intent intent = new Intent(RegActivity.this, LogInActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(RegActivity.this, "회원가입에 성공했어요!", Toast.LENGTH_SHORT).show();

                        } else{
                            Toast.makeText(RegActivity.this, "회원가입에 실패했어요!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }
}
