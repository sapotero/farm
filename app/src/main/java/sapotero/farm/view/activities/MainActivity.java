package sapotero.farm.view.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import sapotero.farm.R;
import sapotero.farm.application.FarmApplication;
import sapotero.farm.view.adapter.MainPageTabAdapter;
import sapotero.farm.view.fragments.MainMarketFragment;
import sapotero.farm.view.fragments.MainStatFragment;
import sapotero.farm.view.fragments.MainTechFragment;
import sapotero.farm.view.fragments.MainVendorFragment;

public class MainActivity extends AppCompatActivity implements MainMarketFragment.OnFragmentInteractionListener, MainStatFragment.OnFragmentInteractionListener, MainTechFragment.OnFragmentInteractionListener, MainVendorFragment.OnFragmentInteractionListener {
  @BindView(R.id.activity_main_toolbar) Toolbar toolbar;
  @BindView(R.id.activity_main_view_pager) ViewPager viewPager;
  @BindView(R.id.activity_main_tabs) TabLayout tabLayout;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    FarmApplication.getAppComponent().inject(this);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);


    initDrawer();
    initToolbar();

    initTabs();
  }

  private void initDrawer() {

    AccountHeader header = new AccountHeaderBuilder()
      .withActivity(this)
      .withHeaderBackground(R.color.md_blue_500)
      .addProfiles(
        new ProfileDrawerItem()
          .withName("Иван Иванов")
          .withEmail("i.ivanov@some-mail.com")
          .withIcon(R.drawable.user)
      )
      .withOnAccountHeaderListener((view, profile, currentProfile) -> {
        return false;
      })
      .build();

    Drawer result = new DrawerBuilder()
      .withActivity(this)
      .withToolbar(toolbar)
      .withAccountHeader(header)
      .addDrawerItems(
        new PrimaryDrawerItem().withIdentifier(1).withName("HOME"),
        new DividerDrawerItem(),
        new SecondaryDrawerItem().withIdentifier(2).withName("SETTINGS")
      )
      .withOnDrawerItemClickListener((view, position, drawerItem) -> {
        return false;
      })
      .build();
  }

  private void initToolbar() {
    toolbar.setTitle("Новости и аналитика");
  }

  private void initTabs() {
    MainPageTabAdapter adapter = new MainPageTabAdapter( getSupportFragmentManager() );
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(10);

    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem( tab.getPosition() );
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {
      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {
      }
    });

    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    tabLayout.setupWithViewPager(viewPager);
  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }
}
