import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaginatedResult } from '../models/pagination';
import { RecordName } from '../models/recordName';
import { Transfer } from '../models/transfer';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

    baseUrl = environment.apiUrl + "transfers/";

	constructor(private http: HttpClient) { }


    getRecordNames(): Observable<RecordName[]> {
        return this.http.get<RecordName[]>(this.baseUrl + "record-names");
	}

    getTransfersByFilter(playerName?: string, pageNumber: any = 0, pageSize: any = 10): Observable<PaginatedResult<Transfer[]>> {
		let params = new HttpParams();
		params = params.append('pageSize', pageSize);
		params = params.append('pageNumber', pageNumber);
		if (playerName != null)
			params = params.append('playerName', playerName);
        
        return this.http.get<PaginatedResult<Transfer[]>>(this.baseUrl, { params });
	}

    getTransfer(id) {
		return this.http.get<Transfer>(this.baseUrl + id);
	}

    createTransfer(objecToCreate) {
		return this.http.post(this.baseUrl, objecToCreate);
	}

    updateTransfer(id, objectToUpdate) {
		return this.http.put(this.baseUrl + id, objectToUpdate);
	}

	deleteTransfer(id: any) {
		return this.http.delete(this.baseUrl + id);
	}

}
