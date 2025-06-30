package com.example.flightcardapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)

        // Updated: Each card now shows a bottom sheet with its own title and message
        val cardData = listOf(
            Triple(R.id.flight_card, "Flight Info", "Flight Number: BA123\nAirline: British Airways"),
            Triple(R.id.departure_card, "Departure Info", "Airport: JFK\nTime: 08:00 AM"),
            Triple(R.id.arrival_card, "Arrival Info", "Airport: LAX\nTime: 11:30 AM\nGate A3\nBaggage: Carousel 2")
        )

        for ((id, title, message) in cardData) {
            val card = findViewById<LinearLayout>(id)
            card.startAnimation(slideUp)
            card.setOnClickListener {
                showBottomSheet(title, message)
            }
        }
    }

    private fun showBottomSheet(title: String, message: String) {
        val view = layoutInflater.inflate(
            R.layout.bottom_sheet_info,
            findViewById(android.R.id.content),
            false
        )

        val titleText = view.findViewById<TextView>(R.id.sheetTitle)
        val infoText = view.findViewById<TextView>(R.id.infoText)

        titleText.text = title
        infoText.text = message

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }
}
