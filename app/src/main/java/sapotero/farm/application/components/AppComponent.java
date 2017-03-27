package sapotero.farm.application.components;

import javax.inject.Singleton;

import dagger.Component;
import sapotero.farm.application.modules.FarmAppModule;
import sapotero.farm.view.activities.MainActivity;

@Singleton
@Component(modules = FarmAppModule.class)
public interface AppComponent {
  void inject(MainActivity context);
}