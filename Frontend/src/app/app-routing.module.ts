import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CoachEditorComponent } from './components/admin/coach/coach-editor/coach-editor.component';
import { CoachListComponent } from './components/admin/coach/coach-list/coach-list.component';
import { FormationEditorComponent } from './components/admin/formation/formation-editor/formation-editor.component';
import { FormationListComponent } from './components/admin/formation/formation-list/formation-list.component';
import { LeagueEditorComponent } from './components/admin/league/league-editor/league-editor.component';
import { LeagueListComponent } from './components/admin/league/league-list/league-list.component';
import { MatchEditorComponent } from './components/admin/match/match-editor/match-editor.component';
import { MatchListComponent } from './components/admin/match/match-list/match-list.component';
import { MediaListComponent } from './components/admin/media/media-list/media-list.component';
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
import { TeamEditorComponent } from './components/admin/team/team-editor/team-editor.component';
import { TeamListComponent } from './components/admin/team/team-list/team-list.component';
import { TransferEditorComponent } from './components/admin/transfer/transfer-editor/transfer-editor.component';
import { TransferListComponent } from './components/admin/transfer/transfer-list/transfer-list.component';
import { UserEditorComponent } from './components/admin/user/user-editor/user-editor.component';
import { UserListComponent } from './components/admin/user/user-list/user-list.component';
import { DashboardMessagesComponent } from './components/pages/dashboard/dashboard-messages/dashboard-messages.component';
import { DashboardMyProfileComponent } from './components/pages/dashboard/dashboard-my-profile/dashboard-my-profile.component';
import { NotFoundComponent } from './components/pages/not-found/not-found.component';
import { FavouriteTeamListComponent } from './components/user-panel/favourite-team-list/favourite-team-list.component';
import { HomeComponent } from './components/user-panel/home/home.component';
import { MatchDetailComponent } from './components/user-panel/match-detail/match-detail.component';
import { TeamDetailComponent } from './components/user-panel/team-detail/team-detail.component';
import { TeamPickerComponent } from './components/user-panel/team-picker/team-picker.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
    {path: '', component: HomeComponent},
    // {path: 'dashboard-messages', component: DashboardMessagesComponent},
    // {path: 'dashboard-my-profile', component: DashboardMyProfileComponent},
    {path: 'dashboard', component: UserListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/media-list', component: MediaListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/user-list', component: UserListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/user-editor', component: UserEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/user-editor/:id', component: UserEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/role-list', component: RoleListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/role-editor', component: RoleEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/role-editor/:id', component: RoleEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/season-list', component: SeasonListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/season-editor', component: SeasonEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/season-editor/:id', component: SeasonEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/position-list', component: PositionListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/position-editor', component: PositionEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/position-editor/:id', component: PositionEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/coach-list', component: CoachListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/coach-editor', component: CoachEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/coach-editor/:id', component: CoachEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/president-list', component: PresidentListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/president-editor', component: PresidentEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/president-editor/:id', component: PresidentEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/formation-list', component: FormationListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/formation-editor', component: FormationEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/formation-editor/:id', component: FormationEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/referee-list', component: RefereeListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/referee-editor', component: RefereeEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/referee-editor/:id', component: RefereeEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/stadium-list', component: StadiumListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/stadium-editor', component: StadiumEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/stadium-editor/:id', component: StadiumEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/player-list', component: PlayerListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/player-editor', component: PlayerEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/player-editor/:id', component: PlayerEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/team-list', component: TeamListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/team-editor', component: TeamEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/team-editor/:id', component: TeamEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/league-list', component: LeagueListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/league-editor', component: LeagueEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/league-editor/:id', component: LeagueEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/transfer-list', component: TransferListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/transfer-editor', component: TransferEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/transfer-editor/:id', component: TransferEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/match-list', component: MatchListComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/match-editor', component: MatchEditorComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] }},
    {path: 'admin/match-editor/:id', component: MatchEditorComponent, canActivate: [AuthGuard]},    
    {path: 'team-picker', component: TeamPickerComponent, canActivate: [AuthGuard]},
    {path: 'favourite-team-list', component: FavouriteTeamListComponent, canActivate: [AuthGuard]},
    {path: 'home', component: HomeComponent},
    {path: 'team-detail/:id', component: TeamDetailComponent},
    {path: 'match-detail/:id', component: MatchDetailComponent},
    {path: '**', component: NotFoundComponent} // This line will remain down from the whole pages component list
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
    exports: [RouterModule]
})
export class AppRoutingModule { }