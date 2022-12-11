package com.example.cafeapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cafeapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    // FirebaseAuth 선언
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val btnJoinJoin = findViewById<Button>(R.id.btnJoinJoin)
        val etJoinEmail = findViewById<EditText>(R.id.etJoinEmail)
        val etJoinPw = findViewById<EditText>(R.id.etJoinPw)
        val etJoinCheck = findViewById<EditText>(R.id.etJoinCheck)

        // auth를 초기화
        auth = Firebase.auth
        // Firebase.auth : 로그인, 회원가입, 인증 시스템에 대한 모든 기능이 담겨 있음!

        // btnJoinJoin을 눌렀을 때
        btnJoinJoin.setOnClickListener {
            var isJoin = true // 조건을 만족했는지
            val email = etJoinEmail.text.toString()
            val pw = etJoinPw.text.toString()
            val checkPw = etJoinCheck.text.toString() // 비밀번호 재입력 내용
            // Toast.makeText(this@JoinActivity, "$email, $pw", Toast.LENGTH_SHORT).show()

            // create가 보내고 있는 전달인자 2개(email, pw)는 실제로 회원가입 정보 전달(Firebase로 전달)

            // EditText에 내용이 있는지
            if(email.isEmpty()){
                isJoin = false
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            if(pw.isEmpty()){
                isJoin = false
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            if(checkPw.isEmpty()){
                isJoin = false
                Toast.makeText(this, "비밀번호 재입력을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            // 비밀번호랑 재입력한 비밀번호가 똑같은지
            if(pw != checkPw){
                isJoin = false
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            }
            // 비밀번화 8자리 이상인지
            if(pw.length < 8){
                isJoin = false
                Toast.makeText(this, "비밀번호를 8자리 이상 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            // Firebase에 규칙은 정해져있지만 그 규칙을 사용자는 모르기 때문에 토스트를 통해 알려줘야함

            if(isJoin){
                // 회원가입을 진행
                auth.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this){ task ->
                        // task : 성공했는지 실패했는지 결과값이 담겨있음
                        // task가 보낸 후 결과(성공했는지 실패했는지)
                        if(task.isSuccessful){
                            // 성공했을 때 실행시킬 코드
                            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@JoinActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            // 실패했을 때 실행시킬 코드
                            Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        }
            }

        }
    }
    }
}