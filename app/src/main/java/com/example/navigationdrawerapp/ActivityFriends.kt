package com.example.navigationdrawerapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class ActivityFriends: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    var modallist = ArrayList<Modal>()
    var names = arrayOf("Wrocław Brochów","Wrocław Brochów 2", "Wrocław Krzyki", "Wrocław Krzyki 2",
        "Wrocław Krzyki 3", "Wrocław Psie Pole", "Wrocław Psie Pole 2", "Realizacja Mirków","Realizacja Długołęka","Realizacja Byków")
    var describes = arrayOf("To zdjęcie przedstawia realizację we Wrocławiu na Brochowie","To zdjęcie przedstawia drugą realizację we Wrocławiu na Brochowie",
        "To zdjęcie przedstawia realizację we Wrocławiu na Krzykach", "To zdjęcie przedstawia drugą realizację we Wrocławiu na Krzykach ",
        "To zdjęcie przedstawia trzecią realizację we Wrocławiu na Krzykach ", "To zdjęcie przedstawia realizację we Wrocławiu na Psim Polu ",
        "To zdjęcie przedstawia drugą realizację we Wrocławiu na Psim Polu", "To zdjęcie przedstawia realizację w Mirkowie","To zdjęcie przedstawia realizację w Długołęce",
        "To zdjęcie przedstawia realizację w Bykowie")
    var images = intArrayOf(R.drawable.image1, R.drawable.image2, R.drawable.`image3`, R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8,
        R.drawable.image9, R.drawable.image10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        this.setTitle("")
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        for(i in names.indices){
            modallist.add(Modal(names[i], images[i],describes[i]))
        }
        var customAdapter = CustomAdapter(modallist, this);
        val gridView:GridView = findViewById(R.id.gridView)
        gridView.adapter = customAdapter;

        gridView.setOnItemClickListener { AdapterView, view ,i , l ->
            var intent = Intent(this,ViewActivity::class.java)
            intent.putExtra("data", modallist[i])
            startActivity(intent);


        }
        val imageviewfb:ImageButton=findViewById(R.id.imageViewfb)
        imageviewfb.setOnClickListener(View.OnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com"))
            startActivity(i)

        })
        val imageviewig:ImageButton=findViewById(R.id.imageViewig)
        imageviewig.setOnClickListener(View.OnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com"))
            startActivity(i)

        })
        val imageviewtw:ImageButton=findViewById(R.id.imageViewsnap)
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
                Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_friends -> {
                val intent3 = Intent(this, ActivityFriends::class.java)
                startActivity(intent3)
                Toast.makeText(this, "Friends clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                val intent4 = Intent(this, ActivityProfile::class.java)
                startActivity(intent4)
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.strona -> {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://uindowebsite.000webhostapp.com/index.php"))
                startActivity(i)

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    class CustomAdapter(
        var itemModel: ArrayList<Modal>,
        var context: Context
    ) : BaseAdapter(){

        var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?):View {
            var view = view;
            if(view == null){
                view = layoutInflater.inflate(R.layout.portfolia, viewGroup, false);
            }

            var tvImageName = view?.findViewById<TextView>(R.id.imageName)
            var imageView = view?.findViewById<ImageView>(R.id.imageName123);

            tvImageName?.text = itemModel[position].name;
            imageView?.setImageResource(itemModel[position].image!!)

            return view!!;

        }
        override fun getItem(p0: Int): Any {
            return itemModel[p0]
        }
        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }
        override fun getCount(): Int {
            return itemModel.size
        }
    }

}