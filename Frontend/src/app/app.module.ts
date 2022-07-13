import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SelectDropDownModule } from 'ngx-select-dropdown';
import { NgxTypedJsModule } from 'ngx-typed-js';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeDemoOneComponent } from './components/pages/home-demo-one/home-demo-one.component';
import { HomeoneListingsComponent } from './components/pages/home-demo-one/homeone-listings/homeone-listings.component';
import { NavbarStyleOneComponent } from './components/common/navbar-style-one/navbar-style-one.component';
import { NavbarStyleTwoComponent } from './components/common/navbar-style-two/navbar-style-two.component';
import { NotFoundComponent } from './components/pages/not-found/not-found.component';
import { VerticalListingsLeftSidebarComponent } from './components/pages/vertical-listings-left-sidebar/vertical-listings-left-sidebar.component';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { DashboardSidemenuComponent } from './components/common/dashboard-sidemenu/dashboard-sidemenu.component';
import { DashboardMessagesComponent } from './components/pages/dashboard/dashboard-messages/dashboard-messages.component';
import { DashboardMyProfileComponent } from './components/pages/dashboard/dashboard-my-profile/dashboard-my-profile.component';
import { HttpClientModule } from '@angular/common/http';
import { JwtModule } from '@auth0/angular-jwt';
import { ToastrModule } from 'ngx-toastr';
import { UserListComponent } from './components/admin/user/user-list/user-list.component';
import { UserEditorComponent } from './components/admin/user/user-editor/user-editor.component';
import { ErrorInterceptorProvider } from './interceptors/error.interceptor';
import { RoleListComponent } from './components/admin/role/role-list/role-list.component';
import { RoleEditorComponent } from './components/admin/role/role-editor/role-editor.component';
import { ListSearchComponent } from './components/common/list-search/list-search.component';
import { FileUploadModule } from 'ng2-file-upload';
import { MediaEditorComponent } from './components/common/media-editor/media-editor.component';
import { SeasonListComponent } from './components/admin/season/season-list/season-list.component';
import { SeasonEditorComponent } from './components/admin/season/season-editor/season-editor.component';
import { PositionListComponent } from './components/admin/position/position-list/position-list.component';
import { PositionEditorComponent } from './components/admin/position/position-editor/position-editor.component';
import { CoachListComponent } from './components/admin/coach/coach-list/coach-list.component';
import { CoachEditorComponent } from './components/admin/coach/coach-editor/coach-editor.component';
import { PresidentListComponent } from './components/admin/president/president-list/president-list.component';
import { PresidentEditorComponent } from './components/admin/president/president-editor/president-editor.component';
import { FormationListComponent } from './components/admin/formation/formation-list/formation-list.component';
import { FormationEditorComponent } from './components/admin/formation/formation-editor/formation-editor.component';
import { RefereeListComponent } from './components/admin/referee/referee-list/referee-list.component';
import { RefereeEditorComponent } from './components/admin/referee/referee-editor/referee-editor.component';
import { StadiumListComponent } from './components/admin/stadium/stadium-list/stadium-list.component';
import { StadiumEditorComponent } from './components/admin/stadium/stadium-editor/stadium-editor.component';
import { PlayerListComponent } from './components/admin/player/player-list/player-list.component';
import { PlayerEditorComponent } from './components/admin/player/player-editor/player-editor.component';
import { TeamListComponent } from './components/admin/team/team-list/team-list.component';
import { TeamEditorComponent } from './components/admin/team/team-editor/team-editor.component';
import { LeagueListComponent } from './components/admin/league/league-list/league-list.component';
import { LeagueEditorComponent } from './components/admin/league/league-editor/league-editor.component';
import { TransferListComponent } from './components/admin/transfer/transfer-list/transfer-list.component';
import { TransferEditorComponent } from './components/admin/transfer/transfer-editor/transfer-editor.component';
import { MatchListComponent } from './components/admin/match/match-list/match-list.component';
import { MatchEditorComponent } from './components/admin/match/match-editor/match-editor.component';
import { TeamPickerComponent } from './components/user-panel/team-picker/team-picker.component';
import { FavouriteTeamListComponent } from './components/user-panel/favourite-team-list/favourite-team-list.component';
import { HomeComponent } from './components/user-panel/home/home.component';
import { TeamDetailComponent } from './components/user-panel/team-detail/team-detail.component';
import { MatchDetailComponent } from './components/user-panel/match-detail/match-detail.component';
import { MediaListComponent } from './components/admin/media/media-list/media-list.component';
import { DashboardNavbarComponent } from './components/common/dashboard-navbar/dashboard-navbar.component';

export function tokenGetter() {
	return localStorage.getItem('fantasy-token');
}

@NgModule({
  declarations: [
    AppComponent,
    HomeDemoOneComponent,
    HomeoneListingsComponent,
    NavbarStyleOneComponent,
    NavbarStyleTwoComponent,
    NotFoundComponent, 
    VerticalListingsLeftSidebarComponent,
    DashboardComponent,
    DashboardNavbarComponent,
    DashboardSidemenuComponent,
    DashboardMessagesComponent,
    DashboardMyProfileComponent,
    UserListComponent,
    UserEditorComponent,
    RoleListComponent,
    RoleEditorComponent,
    ListSearchComponent,
    MediaEditorComponent,
    SeasonListComponent,
    SeasonEditorComponent,
    PositionListComponent,
    PositionEditorComponent,
    CoachListComponent,
    CoachEditorComponent,
    PresidentListComponent,
    PresidentEditorComponent,
    FormationListComponent,
    FormationEditorComponent,
    RefereeListComponent,
    RefereeEditorComponent,
    StadiumListComponent,
    StadiumEditorComponent,
    PlayerListComponent,
    PlayerEditorComponent,
    TeamListComponent,
    TeamEditorComponent,
    LeagueListComponent,
    LeagueEditorComponent,
    TransferListComponent,
    TransferEditorComponent,
    MatchListComponent,
    MatchEditorComponent,
    TeamPickerComponent,
    FavouriteTeamListComponent,
    HomeComponent,
    TeamDetailComponent,
    MatchDetailComponent,
    MediaListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CarouselModule,
    SelectDropDownModule,
    NgxTypedJsModule,
    FormsModule,
    FileUploadModule,
    NgxPaginationModule,
    HttpClientModule,
    ToastrModule.forRoot({
			timeOut: 8000,
			positionClass: 'toast-top-right'
		}),
    JwtModule.forRoot({
			config: {
				tokenGetter: tokenGetter,
				allowedDomains: ['localhost:8080'], // to what endpoits do we send authorization headers
				disallowedRoutes: ['localhost:8080/auth'] // to what endpoints do we not send authorization headers
			}
		})
  ],
  providers: [
    ErrorInterceptorProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
