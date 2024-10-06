package com.example.taskreminder2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TaskDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        // Menerima data dari Intent
        val title = intent.getStringExtra("title")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val repeat = intent.getStringExtra("repeat")

        // Menampilkan data ke tampilan
        findViewById<TextView>(R.id.textTaskTitle).text = title
        findViewById<TextView>(R.id.textTaskDate).text = date
        findViewById<TextView>(R.id.textTaskTime).text = time
        findViewById<TextView>(R.id.textTaskRepeat).text = repeat

        // Event handler untuk tombol "Add Task" agar kembali ke ReminderActivity
        val btnBackToReminder = findViewById<Button>(R.id.btnBackToReminder)
        btnBackToReminder.setOnClickListener {
            // Kembali ke ReminderActivity
            val intent = Intent(this, ReminderActivity::class.java)
            startActivity(intent)
        }
    }
}
