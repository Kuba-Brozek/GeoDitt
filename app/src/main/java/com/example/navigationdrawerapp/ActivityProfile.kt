package com.example.navigationdrawerapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.github.barteksc.pdfviewer.PDFView
import com.google.android.material.navigation.NavigationView


class ActivityProfile: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        this.setTitle("")
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        findViewById<PDFView>(R.id.activityMainPdfView).fromAsset("dokument.pdf").load()
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
}