import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Chat } from '../models/chat';
import { RecordName } from '../models/recordName';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

    baseUrl = environment.apiUrl + "chats/";

	constructor(private http: HttpClient) { }

    getChat(teamId) {
		return this.http.get<Chat>(this.baseUrl + teamId);
	}

}
