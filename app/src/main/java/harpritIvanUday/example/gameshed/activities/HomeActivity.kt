package harpritIvanUday.example.gameshed.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import harpritIvanUday.example.gameshed.*
import harpritIvanUday.example.gameshed.databinding.ActivityHomeBinding
import harpritIvanUday.example.gameshed.fragments.*
import harpritIvanUday.example.gameshed.viewModel.HomeViewModel


class HomeActivity : AppCompatActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    fun moveToLogin() {
        val intent = Intent(this,LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.statusBarColor = this.resources.getColor(R.color.white)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        bottomNavigationView = binding.bottomNavigationView

        setContentView(binding.root)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        // Fragments for bottom tabs
        val homeFragment= HomeFragment()
        val favouriteFragment= FavouriteFragment()
        val aboutUsFragment= AboutUsFragment()
        val profileFragment= ProfileFragment()

        getFamousGameDataActivity()
        homeViewModel.getUpcomingGamesData().start()
        bottomNavigationView.setOnItemSelectedListener {
            item-> when(item.itemId){
            R.id.home -> {
                setCurrentFragment(homeFragment)
                binding.topAppBar.title = getString(R.string.home)
                true
            }
            R.id.favourite -> {
                setCurrentFragment(favouriteFragment)
                binding.topAppBar.title = getString(R.string.favorites)
                true
            }
            R.id.person -> {
                setCurrentFragment(profileFragment)
                binding.topAppBar.title = getString(R.string.profile)
                true
            }
            R.id.aboutUs -> {
                setCurrentFragment(aboutUsFragment)
                binding.topAppBar.title = getString(R.string.about)
                true
            }
            else -> false
        }
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.viewPager,fragment)
            commit()
        }

    private fun getFamousGameDataActivity(){
        homeViewModel.getFamousGamesData()
    }

}