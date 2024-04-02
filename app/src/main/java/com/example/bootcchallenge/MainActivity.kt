package com.example.bootcchallenge

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.bootcchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var list: MutableList<Pair<EditText, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpData()
        setUpListener()
        setUpObserve()
    }


    private fun setUpData() {
        list = mutableListOf(
            Pair(binding.etName, Constants.NAME),
            Pair(binding.etEmail, Constants.EMAIL),
            Pair(binding.etPw, Constants.PASSWORD),
            Pair(binding.etChPw, Constants.CHECKPASSWORD),
        )
    }

    private fun setUpListener() {
        list.forEach { pair ->
            pair.first.onTextChanged { s, start, before, after ->
                viewModel.validation(s.toString(), pair.second)
            }
        }
        list.forEach { pair ->
            pair.first.setOnFocusChangeListener { view, b ->
                if (b) {
                    viewModel.validation(pair.first.text.toString(), pair.second)
                }
            }
        }
    }

    private fun setUpObserve() {
        viewModel.name.observe(this) {

            if (it) {
                binding.tvCheckName.text = ""
            } else {
                binding.tvCheckName.apply {
                    this.text =
                        ContextCompat.getString(this@MainActivity, R.string.check_wrong_name)
                    this.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.red))
                }
            }
            viewModel.enableEvent()

        }

        viewModel.email.observe(this) {
            if (it) {
                binding.tvCheckEmail.text = ""
            } else {
                binding.tvCheckEmail.apply {
                    this.text =
                        ContextCompat.getString(this@MainActivity, R.string.check_wrong_email)
                    this.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.red))
                }
            }
            viewModel.enableEvent()

        }

        viewModel.pw.observe(this) {
            if (it) {
                binding.tvCheckPw.text = ""
            } else {
                binding.tvCheckPw.apply {
                    this.text = ContextCompat.getString(this@MainActivity, R.string.check_wrong_pw)
                    this.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.gray))
                }
            }
            viewModel.enableEvent()

        }

        viewModel.chPw.observe(this) {
            if (it) {
                binding.tvCheckChPw.text = ""
            } else {
                binding.tvCheckChPw.apply {
                    this.text =
                        ContextCompat.getString(this@MainActivity, R.string.check_wrong_chpw)
                    this.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.gray))
                }
            }
            viewModel.enableEvent()
        }

        viewModel.event.observe(this) {
            if (it) {
                binding.btn.isEnabled = true
            } else {
                binding.btn.isEnabled = false
            }
        }
    }
}