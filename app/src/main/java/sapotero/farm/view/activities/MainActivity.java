package sapotero.farm.view.activities;

import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
  @BindView(R.id.toolbar) Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    FarmApplication.getAppComponent().inject(this);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);


    initDrawer();
    initToolbar();
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
}
