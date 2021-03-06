import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EquipmentListComponent } from './equipment-list/equipment-list.component';
import { EquipmentDetailsComponent } from './equipment-details/equipment-details.component';
import { OrderListComponent } from './order-list/order-list.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AuthGuard } from './services/auth-guard.servise';
import { MatMenuModule, MatToolbarModule, MatCardModule } from '@angular/material';
import {MatButtonModule} from '@angular/material/button';
import { BackendService } from "./services/backend.service";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import {MatInputModule} from '@angular/material/input'
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    EquipmentListComponent,
    EquipmentDetailsComponent,
    OrderListComponent,
    AdminPageComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    MatMenuModule,
    MatButtonModule,
    MatToolbarModule,
    MatInputModule,
    MatCardModule,
    HttpClientModule
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
