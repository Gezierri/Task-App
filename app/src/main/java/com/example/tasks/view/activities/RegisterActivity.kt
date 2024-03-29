package com.example.tasks.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        // Inicializa eventos
        listeners()
        observe()
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {

            val name = edit_name.text.toString()
            val email = edit_email.text.toString()
            val password = edit_password.text.toString()

            mViewModel.create(name, email, password)
        }
    }

    private fun observe() {
        mViewModel.create.observe(this, Observer {
            if (it.getSuccess()){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                val msg = it.getFailure()
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun listeners() {
        button_save.setOnClickListener(this)
    }
}
