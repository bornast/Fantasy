import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Rate } from '../models/rate';
import { RecordName } from '../models/recordName';

@Injectable({
  providedIn: 'root'
})
export class RateService {

    baseUrl = environment.apiUrl + "rates";

	constructor(private http: HttpClient) { }

    getRate(matchId: number, playerId: number): Observable<any> {
        let params = new HttpParams();
		if (matchId != null)
			params = params.append('matchId', matchId);
        if (playerId != null)
			params = params.append('playerId', playerId);
        
        return this.http.get<any>(this.baseUrl, { params });
	}

    createRate(objecToCreate) {
		return this.http.post(this.baseUrl, objecToCreate);
	}

    updateRate(id, objectToUpdate) {
		return this.http.put(this.baseUrl + "/" + id, objectToUpdate);
	}

	deleteRate(id: any) {
		return this.http.delete(this.baseUrl + "/" + id);
	}

}
