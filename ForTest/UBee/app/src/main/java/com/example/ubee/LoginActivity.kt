package com.example.ubee

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import com.example.ubee.MyApplication.Companion.auth
import com.example.ubee.databinding.ActivityAnnounceBinding
import com.example.ubee.databinding.ActivityLoginBinding
import com.example.ubee.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpBtn.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.findPwBtn.setOnClickListener {
            val intent = Intent(this, PasswordSearchActivity::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.authEmailEditView.text.toString()
            val password = binding.authPasswordEditView.text.toString()
            MyApplication.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("auth", 1)
                        //로그인때 입력한 아이디를 mainActivity에 넘겨줘서, 그 아이디값과 파이어베이스 대조 후, menu에 사용자이름 변경
                        intent.putExtra("uid", binding.authEmailEditView.text.toString())
                        startActivity(intent)
                    }else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                    }
                }
        }


    }
}

