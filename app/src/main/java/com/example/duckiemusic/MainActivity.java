package com.example.duckiemusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.duckiemusic.fragment.AccountFragment;
import com.example.duckiemusic.fragment.HomeFragment;
import com.example.duckiemusic.fragment.LibraryFragment;
import com.example.duckiemusic.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView mnBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mnBottom=findViewById(R.id.navMenu);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Main");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mnBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fmNew;

                if (item.getItemId() == R.id.mnHome) {
                    loadFragment(new HomeFragment());
                    getSupportActionBar().setTitle(item.getTitle());
                    fmNew = new HomeFragment();
                    loadFragment(fmNew);
                    return true;
                } else if (item.getItemId() == R.id.mnSearch) {
                    loadFragment(new SearchFragment());
                    getSupportActionBar().setTitle(item.getTitle());
                    fmNew = new SearchFragment();
                    return true;
                } else if (item.getItemId() == R.id.mnLibrary) {
                    loadFragment(new LibraryFragment());
                    getSupportActionBar().setTitle(item.getTitle());
                    fmNew = new LibraryFragment();
                    return true;
                }
                else if (item.getItemId()==R.id.mnAccount)
                {
                    loadFragment(new AccountFragment());
                    getSupportActionBar().setTitle(item.getTitle());
                    fmNew = new AccountFragment();
                    return true;
                }
                else {
                    return false;
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
            return true;
        }
        return true;
    }

    void loadFragment(Fragment fmNew)
    {
        FragmentTransaction fmTran=getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment,fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }
}