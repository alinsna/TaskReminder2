package com.example.taskreminder2

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import android.app.AlertDialog
import android.content.Intent

class ReminderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        // Tambahan Spinner untuk pengulangan
        val spinner = findViewById<Spinner>(R.id.spinnerRepeat)
        val repeatOptions = arrayOf("Once", "Daily", "Mon to Fri")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, repeatOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(0)

        // Date picker untuk memilih tanggal
        val btnSelectDate = findViewById<EditText>(R.id.btnSelectDate)
        btnSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val dateText = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    btnSelectDate.setText(dateText)
                },
                year, month, day
            )
            datePickerDialog.show()
        }

        // Spinner untuk jam, menit, dan detik
        val spinnerHour = findViewById<Spinner>(R.id.spinnerHour)
        val spinnerMinute = findViewById<Spinner>(R.id.spinnerMinute)
        val spinnerSecond = findViewById<Spinner>(R.id.spinnerSecond)

        val hours = (0..23).map { it.toString().padStart(2, '0') }.toTypedArray()
        val hourAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hours)
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerHour.adapter = hourAdapter
        spinnerHour.setSelection(0)

        val minutes = (0..59).map { it.toString().padStart(2, '0') }.toTypedArray()
        val minuteAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, minutes)
        minuteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMinute.adapter = minuteAdapter
        spinnerMinute.setSelection(0)

        val seconds = (0..59).map { it.toString().padStart(2, '0') }.toTypedArray()
        val secondAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, seconds)
        secondAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSecond.adapter = secondAdapter
        spinnerSecond.setSelection(0)

        // Button untuk menambah tugas baru
        val btnAddTaskNew = findViewById<Button>(R.id.btnAddTaskNew)
        btnAddTaskNew.setOnClickListener {
            val title = findViewById<EditText>(R.id.editTitle).text.toString()
            val repeat = spinner.selectedItem.toString()
            val date = btnSelectDate.text.toString()
            val hour = spinnerHour.selectedItem.toString()
            val minute = spinnerMinute.selectedItem.toString()
            val second = spinnerSecond.selectedItem.toString()
            val time = "$hour:$minute:$second"

            // Tambahkan dialog konfirmasi sebelum pindah activity
            AlertDialog.Builder(this).apply {
                setTitle("SimpliRemind")
                setMessage("Do you want to add this as a new task?")
                setPositiveButton("Yes") { _, _ ->
                    // Jika pengguna memilih Yes, pindah ke TaskDetailsActivity
                    val intent = Intent(this@ReminderActivity, TaskDetailsActivity::class.java).apply {
                        putExtra("title", title)
                        putExtra("date", date)
                        putExtra("time", time)
                        putExtra("repeat", repeat)
                    }
                    startActivity(intent)
                }
                setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss() // Tutup dialog jika pengguna memilih No
                }
                show()
            }

            // Menampilkan toast untuk konfirmasi penambahan task
            Toast.makeText(this, "Task added: $title, $repeat, $date, $time", Toast.LENGTH_SHORT).show()
        }
    }
}
