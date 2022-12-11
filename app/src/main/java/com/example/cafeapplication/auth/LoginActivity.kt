package com.example.cafeapplication.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cafeapplication.MainActivity
import com.example.cafeapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    // FirebaseAuth 선언
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 자동 로그인 기능
        // Fragment (분할) : 부분화면
        // Fragment 간에 데이터 전송/이동 : SharedPreference, intent → FragmentManager
        // SharedPreference : Fragment 간에 데이터 전송/유지(어플리케이션 첫 실행 감지, 자동 로그인)
        val sharedPreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE)
        val loginId = sharedPreferences.getString("loginId", "")
        val loginPw = sharedPreferences.getString("loginPw", "")

        val sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE)
        val loginName = sp.getString("loginId", "null")

        // FirebaseAuth 초기화
        auth = Firebase.auth

        val btnLoginLogin = findViewById<Button>(R.id.btnLoginLogin)
        val etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etLoginPw = findViewById<EditText>(R.id.etLoginPw)
        etLoginEmail.setText(loginId)
        etLoginPw.setText(loginPw)

        // Login 버튼을 눌렀을 때
        btnLoginLogin.setOnClickListener {
            val email = etLoginEmail.text.toString()
            val pw = etLoginPw.text.toString()

            auth.signInWithEmailAndPassword(email, pw)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        // 로그인에 성공
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                        // 데이터 삽입, 수정, 삭제
                        val editor = sharedPreferences.edit()
                        editor.putString("loginId", email)
                        editor.putString("loginPw", pw)
                        editor.commit()

                        val editorSp = sp.edit()
                        editorSp.putString("loginId", email)
                        editorSp.commit()

                        // 로그인을 성공했으면 MainActivity로 이동
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        // 로그인에 실패
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }

            Toast.makeText(this@LoginActivity, "$email, $pw", Toast.LENGTH_SHORT).show()
        }
    }
}