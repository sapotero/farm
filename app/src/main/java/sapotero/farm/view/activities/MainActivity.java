package sapotero.farm.view.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import sapotero.farm.R;
import sapotero.farm.utils.PrefUtils;


//public class MainActivity extends AppCompatActivity implements MainMarketFragment.OnFragmentInteractionListener, MainStatFragment.OnFragmentInteractionListener, MainTechFragment.OnFragmentInteractionListener, MainVendorFragment.OnFragmentInteractionListener {
//  @BindView(R.id.activity_main_toolbar) Toolbar toolbar;
//  @BindView(R.id.activity_main_view_pager) ViewPager viewPager;
//  @BindView(R.id.activity_main_tabs) TabLayout tabLayout;
//
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//
//    FarmApplication.getAppComponent().inject(this);
//    setContentView(R.layout.activity_main);
//    ButterKnife.bind(this);
//
//
//    initDrawer();
//    initToolbar();
//
//    initTabs();
//  }
//
//  private void initDrawer() {
//
//    AccountHeader header = new AccountHeaderBuilder()
//      .withActivity(this)
//      .withHeaderBackground(R.color.md_blue_500)
//      .addProfiles(
//        new ProfileDrawerItem()
//          .withName("Иван Иванов")
//          .withEmail("i.ivanov@some-mail.com")
//          .withIcon(R.drawable.user)
//      )
//      .withOnAccountHeaderListener((view, profile, currentProfile) -> {
//        return false;
//      })
//      .build();
//
//    Drawer result = new DrawerBuilder()
//      .withActivity(this)
//      .withToolbar(toolbar)
//      .withAccountHeader(header)
//      .addDrawerItems(
//        new PrimaryDrawerItem().withIdentifier(1).withName("HOME"),
//        new DividerDrawerItem(),
//        new SecondaryDrawerItem().withIdentifier(2).withName("SETTINGS")
//      )
//      .withOnDrawerItemClickListener((view, position, drawerItem) -> {
//        return false;
//      })
//      .build();
//  }
//
//  private void initToolbar() {
//    toolbar.setTitle("Новости и аналитика");
//  }
//
//  private void initTabs() {
//    MainPageTabAdapter adapter = new MainPageTabAdapter( getSupportFragmentManager() );
//    viewPager.setAdapter(adapter);
//    viewPager.setOffscreenPageLimit(10);
//
//    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//      @Override
//      public void onTabSelected(TabLayout.Tab tab) {
//        viewPager.setCurrentItem( tab.getPosition() );
//      }
//
//      @Override
//      public void onTabUnselected(TabLayout.Tab tab) {
//      }
//
//      @Override
//      public void onTabReselected(TabLayout.Tab tab) {
//      }
//    });
//
//    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//    tabLayout.setupWithViewPager(viewPager);
//  }
//
//  @Override
//  public void onFragmentInteraction(Uri uri) {
//
//  }
//}
public class MainActivity extends AppCompatActivity{
  static Boolean mTwoPane;

  Toolbar mToolbar;
  NavigationView mNavigationView;
  DrawerLayout mDrawerLayout;

  private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
  private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

  private boolean mUserLearnedDrawer;
  private boolean mFromSavedInstanceState;
  private int mCurrentSelectedPosition = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setUpToolbar();

    mUserLearnedDrawer = Boolean.valueOf(PrefUtils.readSharedSetting(this, PREF_USER_LEARNED_DRAWER, "false"));

    if (savedInstanceState != null) {
      mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
      mFromSavedInstanceState = true;
    }

    if (findViewById(R.id.drawer) != null) {
//            Phone layout
      mTwoPane = false;

      mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
      setUpNavDrawer();

    } else {
//            Tablet layout
      mTwoPane = true;

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primary_dark));
      }

    }

    mNavigationView = (NavigationView) findViewById(R.id.nav_view);
    mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        switch (menuItem.getItemId()) {
          case R.id.navigation_item_home:
//                        Do something
            mCurrentSelectedPosition = 0;
            return true;
          case R.id.navigation_item_explore:
//                        Do something
            mCurrentSelectedPosition = 1;
            return true;
          case R.id.navigation_item_following:
//                        Do something
            mCurrentSelectedPosition = 2;
            return true;
          case R.id.navigation_item_favourites:
//                        Do something
            mCurrentSelectedPosition = 3;
            return true;
          case R.id.navigation_item_settings:
//                        Do something
            mCurrentSelectedPosition = 4;
            return true;
          case R.id.navigation_item_help:
//                        Do something
            mCurrentSelectedPosition = 5;
            return true;
          default:
            return true;
        }
      }
    });

  }

  private void setUpNavDrawer() {
    if (mToolbar != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//      mToolbar.setNavigationIcon(R.drawable.ic_drawer);
      mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mDrawerLayout.openDrawer(GravityCompat.START);
        }
      });
    }

    if (!mUserLearnedDrawer) {
      mDrawerLayout.openDrawer(GravityCompat.START);
      mUserLearnedDrawer = true;
      PrefUtils.saveSharedSetting(this, PREF_USER_LEARNED_DRAWER, "true");
    }
  }

  private void setUpToolbar() {
    mToolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    outState.putBoolean("TWO_PANE", mTwoPane);
  }


  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    mTwoPane = savedInstanceState.getBoolean("TWO_PANE");
    mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, 0);
    Menu menu = mNavigationView.getMenu();
    menu.getItem(mCurrentSelectedPosition).setChecked(true);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
