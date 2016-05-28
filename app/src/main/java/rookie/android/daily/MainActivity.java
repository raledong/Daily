package rookie.android.daily;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import rookie.android.daily.base.view.BaseActivity;
import rookie.android.daily.util.FragmentFactory;

public class MainActivity extends BaseActivity {

    private static final String TITLE = "TITLE";

    private static final String KEY_SHOW_ACTION = "SHOW_ACTION";
    private static final String ACTION_LABEL = "ACTION_LABEL";

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @BindView(R.id.drawerLayout)
    public DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.leftPane)
    ViewGroup leftPane;

    ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("oncreate", "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //Let's first set up toolbar
        mTitle = mDrawerTitle = getTitle();
        setupToolbar();

        //Setup Titles and Icons of Navigation Drawer
//        navTitles = getResources().getStringArray(R.array.navDrawerItems);
//        navIcons = getResources().obtainTypedArray(R.array.navDrawerIcons);


        /**
         *Here , pass the titles and icons array to the adapter .
         *Additionally , pass the context of 'this' activity .
         *So that , later we can use the fragmentManager of this activity to add/replace fragments.
         */
//
//        recyclerViewAdapter = new RecyclerViewAdapter(navTitles,navIcons,this);
//        recyclerView.setAdapter(recyclerViewAdapter);

        /**
         *It is must to set a Layout Manager For Recycler View
         *As per docs ,
         *RecyclerView allows client code to provide custom layout arrangements for child views.
         *These arrangements are controlled by the RecyclerView.LayoutManager.
         *A LayoutManager must be provided for RecyclerView to function.
         */

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        //Finally setup ActionBarDrawerToggle
        setupDrawerToggle();
//
//
//        //Add the Very First i.e Squad Fragment to the Container
//        Fragment squadFragment = new SquadFragment();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.containerView,squadFragment,null);
//        fragmentTransaction.commit();

    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }
    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.base_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        //通过intent判断进行什么跳转
        selectAction(intent);


    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    public static void menuActionStart(Context context, String label){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(KEY_SHOW_ACTION, ACTION_LABEL);

        intent.putExtra(TITLE, label);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    public void selectAction(Intent intent){
        String showAction = intent.getStringExtra(KEY_SHOW_ACTION);
        switch (showAction){
            case ACTION_LABEL:
                showMenuAction(intent.getStringExtra(TITLE));
        }
    }

    private void showMenuAction(String label){
        toolbar.setTitle(label);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.leftPane, FragmentFactory.getFragmentByLabel(label))
                .commit();
    }
}
