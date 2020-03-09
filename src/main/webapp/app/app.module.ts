import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { MixwebSharedModule } from 'app/shared/shared.module';
import { MixwebCoreModule } from 'app/core/core.module';
import { MixwebAppRoutingModule } from './app-routing.module';
import { MixwebHomeModule } from './home/home.module';
import { MixwebEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    MixwebSharedModule,
    MixwebCoreModule,
    MixwebHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    MixwebEntityModule,
    MixwebAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class MixwebAppModule {}
