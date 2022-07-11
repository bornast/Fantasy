import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Match } from '../models/match';
import { PaginatedResult } from '../models/pagination';
import { RecordName } from '../models/recordName';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

    baseUrl = environment.apiUrl + "matches/";

	constructor(private http: HttpClient) { }


    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "record-names");
	}

    getMatchesByFilter(name?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Match[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (name != null)
			params = params.append('teamName', name);
        
        return this.http.get<PaginatedResult<Match[]>>(this.baseUrl, { params });
	}

    getMatch(id) {
		return this.http.get<Match>(this.baseUrl + id);
	}

    createMatch(objecToCreate) {
		return this.http.post(this.baseUrl, this.cloneObjectForRequest(objecToCreate));
	}

    updateMatch(id, objectToUpdate) {
		return this.http.put(this.baseUrl + id, this.cloneObjectForRequest(objectToUpdate));
	}

	deleteMatch(id: any) {
		return this.http.delete(this.baseUrl + id);
	}

    private cloneObjectForRequest(object) {
        var clonedObject = JSON.parse(JSON.stringify(object));
        clonedObject.matchDate = clonedObject.matchDate + ":00Z";
        if (clonedObject.goals.length == 1 && Object.keys(clonedObject.goals[0]).length == 0) {
            clonedObject.goals = [];
        }
        if (clonedObject.cards.length == 1 && Object.keys(clonedObject.cards[0]).length == 0) {
            clonedObject.cards = [];
        }
        if (clonedObject.homeTeam && clonedObject.homeTeam.substitutions.length == 1 && Object.keys(clonedObject.homeTeam.substitutions[0]).length == 0) {
            clonedObject.homeTeam.substitutions = [];
        }
        if (clonedObject.awayTeam && clonedObject.awayTeam.substitutions.length == 1 && Object.keys(clonedObject.awayTeam.substitutions[0]).length == 0) {
            clonedObject.awayTeam.substitutions = [];
        }
        console.log("not cloned object", object);
        console.log("clonedObject", clonedObject);
        return clonedObject;
    }

}
