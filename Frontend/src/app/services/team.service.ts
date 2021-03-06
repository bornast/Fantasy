import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { League } from '../models/league';
import { PaginatedResult } from '../models/pagination';
import { Player } from '../models/player';
import { RecordName } from '../models/recordName';
import { Team } from '../models/team';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

    baseUrl = environment.apiUrl + "teams/";

	constructor(private http: HttpClient) { }


    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "record-names");
	}

    getTeamPlayers(teamId): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + teamId + "/players");
	}

    getTeamTable(leagueId: number): Observable<[]> {
        let params = new HttpParams();
		if (leagueId != null)
			params = params.append('leagueId', leagueId);
        
        return this.http.get<[]>(this.baseUrl + "table", { params });
	}

    getTeamPlayersStatistics(teamId): Observable<Player[]> {
        return this.http.get<Player[]>(this.baseUrl + teamId + "/players");
	}

    getTeamsByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Team[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('name', name);
        
        return this.http.get<PaginatedResult<Team[]>>(this.baseUrl, { params });
	}

    getFavouriteTeamsByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Team[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('name', name);
        
        return this.http.get<PaginatedResult<Team[]>>(this.baseUrl + "favourites", { params });
	}

    getUnfavoredTeamsByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Team[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('name', name);
        
        return this.http.get<PaginatedResult<Team[]>>(this.baseUrl + "unfavored", { params });
	}

    getTeamResultsByFilter(teamId: any, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
        return this.http.get<PaginatedResult<[]>>(this.baseUrl + teamId + "/results", { params });
	}

    getTeamTransfersByFilter(teamId: any, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
        return this.http.get<PaginatedResult<[]>>(this.baseUrl + teamId + "/transfers", { params });
	}

    getTeamLeagues(id) {
		return this.http.get<League[]>(this.baseUrl + id + "/leagues");
	}

    getTeam(id) {
		return this.http.get<Team>(this.baseUrl + id);
	}

    createTeam(objecToCreate) {
		return this.http.post(this.baseUrl, objecToCreate);
	}

    updateTeam(id, objectToUpdate) {
		return this.http.put(this.baseUrl + id, objectToUpdate);
	}

	deleteTeam(id: any) {
		return this.http.delete(this.baseUrl + id);
	}

    markTeamAsFavourite(id) {
		return this.http.post(this.baseUrl + id + "/set-favourite", {});
	}

    markTeamAsUnfavored(id) {
		return this.http.post(this.baseUrl + id + "/set-unfavored", {});
	}

}
