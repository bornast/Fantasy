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
import { HomeDemoTwoComponent } from './components/pages/home-demo-two/home-demo-two.component';
import { AppDownloadComponent } from './components/common/app-download/app-download.component';
import { HowItWorksComponent } from './components/common/how-it-works/how-it-works.component';
import { HomeoneBlogComponent } from './components/pages/home-demo-one/homeone-blog/homeone-blog.component';
import { FeedbackStyleOneComponent } from './components/common/feedback-style-one/feedback-style-one.component';
import { HomeoneDestinationsComponent } from './components/pages/home-demo-one/homeone-destinations/homeone-destinations.component';
import { CategoryComponent } from './components/common/category/category.component';
import { HomeoneListingsComponent } from './components/pages/home-demo-one/homeone-listings/homeone-listings.component';
import { FeaturesComponent } from './components/common/features/features.component';
import { HomeoneBannerComponent } from './components/pages/home-demo-one/homeone-banner/homeone-banner.component';
import { FooterStyleOneComponent } from './components/common/footer-style-one/footer-style-one.component';
import { NavbarStyleOneComponent } from './components/common/navbar-style-one/navbar-style-one.component';
import { NavbarStyleTwoComponent } from './components/common/navbar-style-two/navbar-style-two.component';
import { HometwoBannerComponent } from './components/pages/home-demo-two/hometwo-banner/hometwo-banner.component';
import { HometwoListingsComponent } from './components/pages/home-demo-two/hometwo-listings/hometwo-listings.component';
import { HometwoDestinationsComponent } from './components/pages/home-demo-two/hometwo-destinations/hometwo-destinations.component';
import { HometwoEventsComponent } from './components/pages/home-demo-two/hometwo-events/hometwo-events.component';
import { HometwoBlogComponent } from './components/pages/home-demo-two/hometwo-blog/hometwo-blog.component';
import { ComingSoonComponent } from './components/pages/coming-soon/coming-soon.component';
import { NotFoundComponent } from './components/pages/not-found/not-found.component';
import { AboutUsComponent } from './components/pages/about-us/about-us.component';
import { PartnerComponent } from './components/common/partner/partner.component';
import { TeamComponent } from './components/common/team/team.component';
import { FunfactsComponent } from './components/common/funfacts/funfacts.component';
import { HowItWorksPageComponent } from './components/pages/how-it-works-page/how-it-works-page.component';
import { PricingComponent } from './components/pages/pricing/pricing.component';
import { GalleryComponent } from './components/pages/gallery/gallery.component';
import { FaqComponent } from './components/pages/faq/faq.component';
import { ContactComponent } from './components/pages/contact/contact.component';
import { FooterStyleTwoComponent } from './components/common/footer-style-two/footer-style-two.component';
import { BlogGridComponent } from './components/pages/blog-grid/blog-grid.component';
import { BlogRightSidebarComponent } from './components/pages/blog-right-sidebar/blog-right-sidebar.component';
import { BlogDetailsComponent } from './components/pages/blog-details/blog-details.component';
import { ProductsListComponent } from './components/pages/products-list/products-list.component';
import { CartComponent } from './components/pages/cart/cart.component';
import { CheckoutComponent } from './components/pages/checkout/checkout.component';
import { ProductsDetailsComponent } from './components/pages/products-details/products-details.component';
import { RelatedProductsComponent } from './components/pages/products-details/related-products/related-products.component';
import { AuthorProfileComponent } from './components/pages/author-profile/author-profile.component';
import { CategoriesComponent } from './components/pages/categories/categories.component';
import { TopPlaceComponent } from './components/pages/top-place/top-place.component';
import { ListingsDetailsComponent } from './components/pages/listings-details/listings-details.component';
import { EventsDetailsComponent } from './components/pages/events-details/events-details.component';
import { EventsComponent } from './components/pages/events/events.component';
import { VerticalListingsLeftSidebarComponent } from './components/pages/vertical-listings-left-sidebar/vertical-listings-left-sidebar.component';
import { VerticalListingsRightSidebarComponent } from './components/pages/vertical-listings-right-sidebar/vertical-listings-right-sidebar.component';
import { VerticalListingsFullWidthComponent } from './components/pages/vertical-listings-full-width/vertical-listings-full-width.component';
import { GridListingsLeftSidebarComponent } from './components/pages/grid-listings-left-sidebar/grid-listings-left-sidebar.component';
import { GridListingsRightSidebarComponent } from './components/pages/grid-listings-right-sidebar/grid-listings-right-sidebar.component';
import { GridListingsFullWidthComponent } from './components/pages/grid-listings-full-width/grid-listings-full-width.component';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { DashboardNavbarComponent } from './components/common/dashboard-navbar/dashboard-navbar.component';
import { DashboardSidemenuComponent } from './components/common/dashboard-sidemenu/dashboard-sidemenu.component';
import { CopyrightsComponent } from './components/pages/dashboard/copyrights/copyrights.component';
import { StatsComponent } from './components/pages/dashboard/stats/stats.component';
import { RecentActivitiesComponent } from './components/pages/dashboard/recent-activities/recent-activities.component';
import { DashboardMessagesComponent } from './components/pages/dashboard/dashboard-messages/dashboard-messages.component';
import { DashboardBookingsComponent } from './components/pages/dashboard/dashboard-bookings/dashboard-bookings.component';
import { DashboardWalletComponent } from './components/pages/dashboard/dashboard-wallet/dashboard-wallet.component';
import { DashboardReviewsComponent } from './components/pages/dashboard/dashboard-reviews/dashboard-reviews.component';
import { DashboardInvoiceComponent } from './components/pages/dashboard/dashboard-invoice/dashboard-invoice.component';
import { DashboardMyProfileComponent } from './components/pages/dashboard/dashboard-my-profile/dashboard-my-profile.component';
import { DashboardAddListingsComponent } from './components/pages/dashboard/dashboard-add-listings/dashboard-add-listings.component';
import { DashboardBookmarksComponent } from './components/pages/dashboard/dashboard-bookmarks/dashboard-bookmarks.component';
import { DashboardMyListingsComponent } from './components/pages/dashboard/dashboard-my-listings/dashboard-my-listings.component';
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

export function tokenGetter() {
	return localStorage.getItem('fantasy-token');
}

@NgModule({
  declarations: [
    AppComponent,
    HomeDemoOneComponent,
    HomeDemoTwoComponent,
    AppDownloadComponent,
    HowItWorksComponent,
    HomeoneBlogComponent,
    FeedbackStyleOneComponent,
    HomeoneDestinationsComponent,
    CategoryComponent,
    HomeoneListingsComponent,
    FeaturesComponent,
    HomeoneBannerComponent,
    FooterStyleOneComponent,
    NavbarStyleOneComponent,
    NavbarStyleTwoComponent,
    HometwoBannerComponent,
    HometwoListingsComponent,
    HometwoDestinationsComponent,
    HometwoEventsComponent,
    HometwoBlogComponent,
    ComingSoonComponent,
    NotFoundComponent,
    AboutUsComponent,
    PartnerComponent,
    TeamComponent,
    FunfactsComponent,
    HowItWorksPageComponent,
    PricingComponent,
    GalleryComponent,
    FaqComponent,
    ContactComponent,
    FooterStyleTwoComponent,
    BlogGridComponent,
    BlogRightSidebarComponent,
    BlogDetailsComponent,
    ProductsListComponent,
    CartComponent,
    CheckoutComponent,
    ProductsDetailsComponent,
    RelatedProductsComponent,
    AuthorProfileComponent,
    CategoriesComponent,
    TopPlaceComponent,
    ListingsDetailsComponent,
    EventsDetailsComponent,
    EventsComponent,
    VerticalListingsLeftSidebarComponent,
    VerticalListingsRightSidebarComponent,
    VerticalListingsFullWidthComponent,
    GridListingsLeftSidebarComponent,
    GridListingsRightSidebarComponent,
    GridListingsFullWidthComponent,
    DashboardComponent,
    DashboardNavbarComponent,
    DashboardSidemenuComponent,
    CopyrightsComponent,
    StatsComponent,
    RecentActivitiesComponent,
    DashboardMessagesComponent,
    DashboardBookingsComponent,
    DashboardWalletComponent,
    DashboardReviewsComponent,
    DashboardInvoiceComponent,
    DashboardMyProfileComponent,
    DashboardAddListingsComponent,
    DashboardBookmarksComponent,
    DashboardMyListingsComponent,
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
    HomeComponent
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
