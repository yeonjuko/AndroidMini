package com.example.cafeapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.cafeapplication.MainActivity
import com.example.cafeapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class IntroActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        auth = Firebase.auth

        val btnIntroLogin = findViewById<Button>(R.id.btnIntroLogin)
        val btnIntroNo = findViewById<Button>(R.id.btnIntroNo)
        val btnIntroJoin = findViewById<Button>(R.id.btnIntroJoin)

        // login -> LoginActivity로 이동
            btnIntroLogin.setOnClickListener {
                val intent = Intent(this@IntroActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        // join -> JoinActivity로 이동
            btnIntroJoin.setOnClickListener {
                val intent = Intent(this@IntroActivity, JoinActivity::class.java)
                startActivity(intent)
            }
        // no -> Firebase에서 익명으로 접속할 수 있는 권한 받아와서 성공시 MainActivity로 이동
            btnIntroNo.setOnClickListener {
                auth.signInAnonymously().addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        // 익명 로그인 성공
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                        // 성공 후 MainActivity로 이동
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        // 익명 로그인 실패
                        // 서버 문제
                        Toast.makeText(this, "서버 문제로 로그인에 실패했습니다", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}