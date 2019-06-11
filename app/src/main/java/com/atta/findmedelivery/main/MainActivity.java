package com.atta.findmedelivery.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.atta.findmedelivery.R;
import com.atta.findmedelivery.fragments.OrdersFragment;
import com.atta.findmedelivery.model.SessionManager;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_current:
                    mTextMessage.setText(R.string.title_current);
                    return true;
                case R.id.navigation_old:
                    mTextMessage.setText(R.string.title_old);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);


        Fragment fragment = new OrdersFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", "current");
        fragment.setArguments(bundle);
        loadFragment(fragment);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            SessionManager.getInstance(this).logoutUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    private boolean loadFragment(Fragment fragment) {
        //switching frag loadFragment(fragment);ment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();


            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = new OrdersFragment();
        Bundle bundle = new Bundle();
        switch (menuItem.getItemId()) {
            case R.id.navigation_current:
                bundle.putString("category", "current");
                break;

            case R.id.navigation_old:
                bundle.putString("category", "old");
                break;
            default:

                bundle.putString("category", "current");
                break;


        }

        fragment.setArguments(bundle);
        return loadFragment(fragment);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
