package com.example.navigationdrawerapp

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class ActivityMessages: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        this.setTitle("")
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)


        val ButtonSend: Button =findViewById(R.id.ButtonSend)
        val nazwa_uzytkownika: EditText =findViewById(R.id.nazwa_uzytkownika)
        val nazwatemat: EditText =findViewById(R.id.nazwatemat)
        val nazwa_tresc: EditText =findViewById(R.id.nazwa_tresc)


        ButtonSend.setOnClickListener{
            val nazwa_uzytkownika = nazwa_uzytkownika.text.toString()
            val nazwatemat = nazwatemat.text.toString()
            val nazwa_tresc = nazwa_tresc.text.toString()
            sendEmail(nazwa_uzytkownika, nazwatemat, nazwa_tresc)

        }
        val imageviewfb: ImageButton =findViewById(R.id.imageViewfb)
        imageviewfb.setOnClickListener(View.OnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com"))
            startActivity(i)

        })
        val imageviewig: ImageButton =findViewById(R.id.imageViewig)
        imageviewig.setOnClickListener(View.OnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com"))
            startActivity(i)

        })
        val imageviewtw: ImageButton =findViewById(R.id.imageViewsnap)
        imageviewtw.setOnClickListener(View.OnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com"))
            startActivity(i)

        })
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_messages -> {
                val intent2 = Intent(this, ActivityMessages::class.java)
                startActivity(intent2)

            }
            R.id.nav_friends -> {
                val intent3 = Intent(this, ActivityFriends::class.java)
                startActivity(intent3)

            }
            R.id.nav_update -> {
                val intent4 = Intent(this, ActivityProfile::class.java)
                startActivity(intent4)

            }
            R.id.strona -> {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://uindowebsite.000webhostapp.com/index.php"))
                startActivity(i)

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val geo = LatLng(51.157617, 17.127664)
        mMap.addMarker(MarkerOptions().position(geo).title("W prawym dolnym rogu znajdziesz opcje."))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(geo))
        mMap.setMinZoomPreference(0f)
        mMap.setMaxZoomPreference(21f)
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18f), 2000, null)
    }


private fun sendEmail(nazwa_uzytkownika: String, nazwatemat: String, nazwa_tresc:String) {
    val mIntent = Intent(Intent.ACTION_SEND)
    mIntent.data= Uri.parse("mailto:")
    mIntent.type="text/plan"
    mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(nazwa_uzytkownika))
    mIntent.putExtra(Intent.EXTRA_SUBJECT,nazwatemat)
    mIntent.putExtra(Intent.EXTRA_TEXT,nazwa_tresc)
    try{
        startActivity(Intent.createChooser(mIntent,"wybierz email"))
    }
    catch (e: Exception){
        Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
    }
}
}


