package com.example.cafeapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.cafeapplication.auth.IntroActivity
import com.example.cafeapplication.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 어떤 item을 클릭했는지에 따라서 FrameLayout에 Fragment를 갈아끼워주자

        auth = Firebase.auth

        val fl = findViewById<FrameLayout>(R.id.fl)
        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        val imgLogout = findViewById<ImageView>(R.id.imgLogout)

        // 로그아웃 기능
        imgLogout.setOnClickListener{
            auth.signOut()
            // 로그아웃 하고 나면 IntroActivity로 이동
            val intent = Intent(this, IntroActivity::class.java)
            // 이전에 쌓여있는 Activity를 모두 날려주기
            // finish()는 하나만 날림
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // auth에 담겨있는 기능
        // createUsersWithEmailandPassword : 회원가입 (email, pw)
        // SingInWithEmailandPassword : 로그인(email, pw)
        // SignInAnonymous : 익명로그인 ()
        // singOut() : 로그아웃 (페이지를 이동하는 기능 X)

        bnv.setOnItemSelectedListener { item ->
            // item : 내가 클릭한 item 정보
            when(item.itemId){
                R.id.tab1 ->{
                    // Fragment1 부분화면으로 fl에 갈아끼워줌
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment1()
                    ).commit()
                }
                R.id.tab2 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment2()
                    ).commit()
                }
                R.id.tab3 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment3()
                    ).commit()
                }
                R.id.tab4 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment4()
                    ).commit()
                }
                R.id.tab5 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment5()
//                        ChatFragment()
                    ).commit()
                }
            }
            true
        }
    }
}