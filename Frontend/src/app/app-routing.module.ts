import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CoachEditorComponent } from './components/admin/coach/coach-editor/coach-editor.component';
import { CoachListComponent } from './components/admin/coach/coach-list/coach-list.component';
import { FormationEditorComponent } from './components/admin/formation/formation-editor/formation-editor.component';
import { FormationListComponent } from './components/admin/formation/formation-list/formation-list.component';
import { PlayerEditorComponent } from './components/admin/player/player-editor/player-editor.component';
import { PlayerListComponent } from './components/admin/player/player-list/player-list.component';
import { PositionEditorComponent } from './components/admin/position/position-editor/position-editor.component';
import { PositionListComponent } from './components/admin/position/position-list/position-list.component';
import { PresidentEditorComponent } from './components/admin/president/president-editor/president-editor.component';
import { PresidentListComponent } from './components/admin/president/president-list/president-list.component';
import { RefereeEditorComponent } from './components/admin/referee/referee-editor/referee-editor.component';
import { RefereeListComponent } from './components/admin/referee/referee-list/referee-list.component';
import { RoleEditorComponent } from './components/admin/role/role-editor/role-editor.component';
import { RoleListComponent } from './components/admin/role/role-list/role-list.component';
import { SeasonEditorComponent } from './components/admin/season/season-editor/season-editor.component';
import { SeasonListComponent } from './components/admin/season/season-list/season-list.component';
import { StadiumEditorComponent } from './components/admin/stadium/stadium-editor/stadium-editor.component';
import { StadiumListComponent } from './components/admin/stadium/stadium-list/stadium-list.component';
import { UserEditorComponent } from './components/admin/user/user-editor/user-editor.component';
import { UserListComponent } from './components/admin/user/user-list/user-list.component';
import { AboutUsComponent } from './components/pages/about-us/about-us.component';
import { AuthorProfileComponent } from './components/pages/author-profile/author-profile.component';
import { BlogDetailsComponent } from './components/pages/blog-details/blog-details.component';
import { BlogGridComponent } from './components/pages/blog-grid/blog-grid.component';
import { BlogRightSidebarComponent } from './components/pages/blog-right-sidebar/blog-right-sidebar.component';
import { CartComponent } from './components/pages/cart/cart.component';
import { CategoriesComponent } from './components/pages/categories/categories.component';
import { CheckoutComponent } from './components/pages/checkout/checkout.component';
import { ComingSoonComponent } from './components/pages/coming-soon/coming-soon.component';
import { ContactComponent } from './components/pages/contact/contact.component';
import { DashboardAddListingsComponent } from './components/pages/dashboard/dashboard-add-listings/dashboard-add-listings.component';
import { DashboardBookingsComponent } from './components/pages/dashboard/dashboard-bookings/dashboard-bookings.component';
import { DashboardBookmarksComponent } from './components/pages/dashboard/dashboard-bookmarks/dashboard-bookmarks.component';
import { DashboardInvoiceComponent } from './components/pages/dashboard/dashboard-invoice/dashboard-invoice.component';
import { DashboardMessagesComponent } from './components/pages/dashboard/dashboard-messages/dashboard-messages.component';
import { DashboardMyListingsComponent } from './components/pages/dashboard/dashboard-my-listings/dashboard-my-listings.component';
import { DashboardMyProfileComponent } from './components/pages/dashboard/dashboard-my-profile/dashboard-my-profile.component';
import { DashboardReviewsComponent } from './components/pages/dashboard/dashboard-reviews/dashboard-reviews.component';
import { DashboardWalletComponent } from './components/pages/dashboard/dashboard-wallet/dashboard-wallet.component';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { EventsDetailsComponent } from './components/pages/events-details/events-details.component';
import { EventsComponent } from './components/pages/events/events.component';
import { FaqComponent } from './components/pages/faq/faq.component';
import { GalleryComponent } from './components/pages/gallery/gallery.component';
import { GridListingsFullWidthComponent } from './components/pages/grid-listings-full-width/grid-listings-full-width.component';
import { GridListingsLeftSidebarComponent } from './components/pages/grid-listings-left-sidebar/grid-listings-left-sidebar.component';
import { GridListingsRightSidebarComponent } from './components/pages/grid-listings-right-sidebar/grid-listings-right-sidebar.component';
import { HomeDemoOneComponent } from './components/pages/home-demo-one/home-demo-one.component';
import { HomeDemoTwoComponent } from './components/pages/home-demo-two/home-demo-two.component';
import { HowItWorksPageComponent } from './components/pages/how-it-works-page/how-it-works-page.component';
import { ListingsDetailsComponent } from './components/pages/listings-details/listings-details.component';
import { NotFoundComponent } from './components/pages/not-found/not-found.component';
import { PricingComponent } from './components/pages/pricing/pricing.component';
import { ProductsDetailsComponent } from './components/pages/products-details/products-details.component';
import { ProductsListComponent } from './components/pages/products-list/products-list.component';
import { TopPlaceComponent } from './components/pages/top-place/top-place.component';
import { VerticalListingsFullWidthComponent } from './components/pages/vertical-listings-full-width/vertical-listings-full-width.component';
import { VerticalListingsLeftSidebarComponent } from './components/pages/vertical-listings-left-sidebar/vertical-listings-left-sidebar.component';
import { VerticalListingsRightSidebarComponent } from './components/pages/vertical-listings-right-sidebar/vertical-listings-right-sidebar.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
    {path: '', component: HomeDemoOneComponent},
    {path: 'index-2', component: HomeDemoTwoComponent},
    {path: 'about', component: AboutUsComponent},
    {path: 'how-it-works', component: HowItWorksPageComponent},
    {path: 'pricing', component: PricingComponent},
    {path: 'gallery', component: GalleryComponent},
    {path: 'faq', component: FaqComponent},
    {path: 'coming-soon', component: ComingSoonComponent},
    {path: 'contact', component: ContactComponent},
    {path: 'blog-grid', component: BlogGridComponent},
    {path: 'blog-right-sidebar', component: BlogRightSidebarComponent},
    {path: 'blog-details', component: BlogDetailsComponent},
    {path: 'products-list', component: ProductsListComponent},
    {path: 'cart', component: CartComponent},
    {path: 'checkout', component: CheckoutComponent},
    {path: 'single-products', component: ProductsDetailsComponent},
    {path: 'user-profile', component: AuthorProfileComponent},
    {path: 'categories', component: CategoriesComponent},
    {path: 'destinations', component: TopPlaceComponent},
    {path: 'vertical-listings-left-sidebar', component: VerticalListingsLeftSidebarComponent},
    {path: 'vertical-listings-right-sidebar', component: VerticalListingsRightSidebarComponent},
    {path: 'vertical-listings-full-width', component: VerticalListingsFullWidthComponent},
    {path: 'grid-listings-left-sidebar', component: GridListingsLeftSidebarComponent},
    {path: 'grid-listings-right-sidebar', component: GridListingsRightSidebarComponent},
    {path: 'grid-listings-full-width', component: GridListingsFullWidthComponent},
    {path: 'single-listings', component: ListingsDetailsComponent},
    {path: 'events', component: EventsComponent},
    {path: 'single-events', component: EventsDetailsComponent},
    {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
    {path: 'dashboard-messages', component: DashboardMessagesComponent},
    {path: 'dashboard-bookings', component: DashboardBookingsComponent},
    {path: 'dashboard-wallet', component: DashboardWalletComponent},
    {path: 'dashboard-reviews', component: DashboardReviewsComponent},
    {path: 'dashboard-invoice', component: DashboardInvoiceComponent},
    {path: 'dashboard-my-profile', component: DashboardMyProfileComponent},
    {path: 'dashboard-add-listings', component: DashboardAddListingsComponent},
    {path: 'dashboard-bookmarks', component: DashboardBookmarksComponent},
    {path: 'dashboard-my-listings', component: DashboardMyListingsComponent},
    {path: 'admin/user-list', component: UserListComponent, canActivate: [AuthGuard]},
    {path: 'admin/user-editor', component: UserEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/user-editor/:id', component: UserEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/role-list', component: RoleListComponent, canActivate: [AuthGuard]},
    {path: 'admin/role-editor', component: RoleEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/role-editor/:id', component: RoleEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/season-list', component: SeasonListComponent, canActivate: [AuthGuard]},
    {path: 'admin/season-editor', component: SeasonEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/season-editor/:id', component: SeasonEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/position-list', component: PositionListComponent, canActivate: [AuthGuard]},
    {path: 'admin/position-editor', component: PositionEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/position-editor/:id', component: PositionEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/coach-list', component: CoachListComponent, canActivate: [AuthGuard]},
    {path: 'admin/coach-editor', component: CoachEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/coach-editor/:id', component: CoachEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/president-list', component: PresidentListComponent, canActivate: [AuthGuard]},
    {path: 'admin/president-editor', component: PresidentEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/president-editor/:id', component: PresidentEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/formation-list', component: FormationListComponent, canActivate: [AuthGuard]},
    {path: 'admin/formation-editor', component: FormationEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/formation-editor/:id', component: FormationEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/referee-list', component: RefereeListComponent, canActivate: [AuthGuard]},
    {path: 'admin/referee-editor', component: RefereeEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/referee-editor/:id', component: RefereeEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/stadium-list', component: StadiumListComponent, canActivate: [AuthGuard]},
    {path: 'admin/stadium-editor', component: StadiumEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/stadium-editor/:id', component: StadiumEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/player-list', component: PlayerListComponent, canActivate: [AuthGuard]},
    {path: 'admin/player-editor', component: PlayerEditorComponent, canActivate: [AuthGuard]},
    {path: 'admin/player-editor/:id', component: PlayerEditorComponent, canActivate: [AuthGuard]},
    // Here add new pages component

    {path: '**', component: NotFoundComponent} // This line will remain down from the whole pages component list
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
    exports: [RouterModule]
})
export class AppRoutingModule { }