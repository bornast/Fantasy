import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RecordName } from '../models/recordName';

@Injectable({
  providedIn: 'root'
})
export class CardService {

    baseUrl = environment.apiUrl + "cards/";

	constructor(private http: HttpClient) { }


    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "record-names");
	}

    getCard(id) {
		return this.http.get<RecordName>(this.baseUrl + id);
	}

}
