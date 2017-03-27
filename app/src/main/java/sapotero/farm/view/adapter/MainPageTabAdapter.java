package sapotero.farm.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sapotero.farm.view.fragments.MainMarketFragment;
import sapotero.farm.view.fragments.MainStatFragment;
import sapotero.farm.view.fragments.MainTechFragment;
import sapotero.farm.view.fragments.MainVendorFragment;

public class MainPageTabAdapter extends FragmentPagerAdapter {


  public MainPageTabAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new MainStatFragment();
      case 1:
        return new MainMarketFragment();
      case 2:
        return new MainVendorFragment();
      case 3:
        return new MainTechFragment();
    }
    return null;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch(position) {
      case 0:
        return "Общая статистика";
      case 1:
        return "Объем рынка";
      case 2:
        return "Производители";
      case 3:
        return "Классы техники";
    }
    return null;
  }

  @Override
  public int getCount() {
    return 4;
  }
}