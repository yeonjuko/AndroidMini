package com.example.cafeapplication.maindoor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.cafeapplication.R
import com.example.cafeapplication.auth.IntroActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Oncreate가 실행되고 나서 3초 뒤에 IntroActivity로 넘어가게 만들기
        // Thread, Intent
        // 프로그램에는 MainThread 무조건 한개
        // 내가 만든 작업처리 SubThread
        // SubThread가 동작하고 있는 MainThread에 끼어들려면
        // Handler가 필요(메세지 객체, Thread를 실행시킬 수 있음)

        // Handler 가져오기!
        // postDelayed({실행할 코드}, 지연시킬 시간)
        Handler().postDelayed({
            // Intent 생성
            val intent = Intent(this@SplashActivity, IntroActivity::class.java)
            // Intent 실행
            startActivity(intent)
        }, 3000)
    }
}