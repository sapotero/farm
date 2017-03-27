package sapotero.farm.application;

import android.app.Application;

import sapotero.farm.application.components.AppComponent;
import sapotero.farm.application.components.DaggerAppComponent;
import sapotero.farm.application.modules.FarmAppModule;
import timber.log.Timber;

public class FarmApplication extends Application {

  private static AppComponent appComponent;


  @Override public void onCreate() {
    super.onCreate();

    Timber.plant(new Timber.DebugTree());

    appComponent = DaggerAppComponent.builder()
      .farmAppModule(new FarmAppModule(this))
      .build();
  }

  public static AppComponent getAppComponent() {
    return appComponent;
  }
}
